package main;

import StoreProducts.Catalog;
import StoreProducts.Item;
import StoreProducts.Store;
import Transactions.ItemLine;
import Transactions.payment.Credit;
import Transactions.payment.Payment;
import Transactions.Transaction;
import Transactions.TransactionReader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Point of Sales Terminal (POST)
 *
 * POST is responsible for processing transactions into invoices that will then
 * be sent to a specified output such as the screen or file.
 *
 * @author Gary Ng
 */
public class POST implements Runnable {

    private static final int COLUMN_WIDTH_L = -20;
    private static final int COLUMN_WIDTH_C = 10;
    private static final int COLUMN_WIDTH_R = 12;

    public static final int STATUS_OK = 0;
    public static final int STATUS_FAILED = 1;

    private final Queue<Task> queue = new LinkedList<>();
    private final Store store;
    private final Catalog catalog;
    private final Logger logger;

    public POST(Store store, Catalog catalog, Logger logger) {
        this.store = store;
        this.catalog = catalog;
        this.logger = logger;
    }

    @Override
    public void run() {
        execute();
    }

    /**
     * Retrieves a transaction from the queue, then generates the invoice to be
     * sent to the specified output.
     */
    private void execute() {
        if (!queue.isEmpty()) {
            Task task = queue.poll();
            String invoice = createInvoice(store.getStoreName(), task.getTransaction());
            logger.output(invoice);

            if (task.getCallback() != null) {
                task.getCallback().onFinish(STATUS_OK, invoice);
            }

            execute();
        }
    }

    /**
     * Receives a transaction from an external caller that gets pushed to the
     * queue to be processed later.
     * 
     * @param transaction
     * @param callback 
     */
    public void send(Transaction transaction, POSTCallback callback) {
        if (isValidTransaction(transaction)) {
            queue.add(new Task(transaction, callback));
            run();
        }
    }

    /**
     * Generates an invoice using a transaction.
     * 
     * @param storeName
     * @param transaction
     * @return invoice
     */
    private String createInvoice(String storeName, Transaction transaction) {
        StringBuilder builder = new StringBuilder();
        builder.append(storeName);
        builder.append("\n\n");

        String name = transaction.getCustomer().getName();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/y h:mm a");

        builder.append(format(COLUMN_WIDTH_L, name));
        builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, dateFormat.format(calendar.getTime())));
        builder.append('\n');

        float total = 0;

        for (ItemLine itemLine : transaction.getItemList()) {
            if (itemLine == null) {
                break;
            }

            String upc = itemLine.getUPC();
            int quantity = itemLine.getQuantity();
            
            String description;
            double price = 0;
            double subtotal = 0;

            if (catalog.UPCExists(upc)) {
                Item item = catalog.getItem(upc);

                description = item.getItemDescription();
                price = item.getItemPrice();
                subtotal = price * quantity;
            } else {
                description = String.format("??? [UPC %s]", upc);
            }

            builder.append(format(COLUMN_WIDTH_L, description));
            builder.append(format(COLUMN_WIDTH_C, String.format("%d @ %.2f", quantity, price)));
            builder.append(format(COLUMN_WIDTH_R, String.format("%.2f", subtotal)));
            builder.append('\n');

            total += subtotal;
        }

        builder.append("------");
        builder.append('\n');
        builder.append(format(COLUMN_WIDTH_L, "Total:"));
        builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, String.format("$%.2f", total)));
        builder.append('\n');

        Payment payment = transaction.getPayment();
        String tendered = null;

        switch (payment.getType()) {
            case Payment.TYPE_CHECK:
                tendered = "Paid by Check";
                break;
            case Payment.TYPE_CREDIT:
//                if (payment instanceof Credit) {
//                    Credit credit = (Credit) payment;
//                    tendered = String.format("Credit Card %s", credit.getNumber());
//                }
                
                //ABOVE CODE NOT WORKING FOR SOME REASON, payment instanceof Credit is always false
                
                //this is a dirty solution... :(
                tendered = String.format("Credit Card %s", (int) payment.getAmount());
                break;
        }

        if (tendered == null) {
            tendered = String.format("$%.2f", payment.getAmount());
        }

        String returned = String.format("$%.2f", payment.getAmount() - total);

        if(payment.getType() !=2){ //set ammount tendered/returned if NOT Credit
            builder.append(format(COLUMN_WIDTH_L, "Amount Tendered:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, tendered));
            builder.append('\n');
            builder.append(format(COLUMN_WIDTH_L, "Amount Returned:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, returned));
            builder.append('\n');
        }
        else{
            builder.append(format(COLUMN_WIDTH_L, "Paid with:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, tendered));
            builder.append('\n');
            
            //Checking if card approved
            if(!creditCardApproved()){
                builder.append("CREDIT CARD DECLINED!");
                builder.append('\n');
            }
        }

        return builder.toString();
    }

    /**
     * Wrapper function to simplify white space padding for any given strings.
     * 
     * @param width
     * @param value
     * @return padded string
     */
    public String format(int width, String value) {
        return String.format("%1$" + width + "s", value);
    }

    /**Simulating 10% credit card decline
     * 
     * @return false 10% of time
     */
    public boolean creditCardApproved(){
        Random rn = new Random();
        return rn.nextInt(10) > 0;  //if random number == 0, decline (10% to roll 0)
    }
    
    /**
     * Checks if transaction is valid before processing.
     * 
     * @param transaction
     * @return status of validity
     */
    public boolean isValidTransaction(Transaction transaction) {
        return true;
    }

    /**
     * Task contains both the transaction and callback, if any, to be pushed
     * to the queue.
     */
    private class Task {

        private final Transaction transaction;
        private final POSTCallback callback;

        public Task(Transaction transaction, POSTCallback callback) {
            this.transaction = transaction;
            this.callback = callback;
        }

        public Transaction getTransaction() {
            return transaction;
        }

        public POSTCallback getCallback() {
            return callback;
        }
    }

    /**
     * Callback function when a transaction is processed by the terminal.
     */
    public interface POSTCallback {

        void onFinish(int status, String invoice);
    }

    public static void main(String[] args) {
        Store store = new Store();
        Catalog catalog = store.createCatalogFromFile();

        POST post = new POST(store, catalog, new Logger() {
            @Override
            public void output(String output) {
                outputToConsole(output);
                outputToFile("src/invoices.txt", output + '\n');
            }
        });

        TransactionReader reader = new TransactionReader("src/Transactions/transactions.txt");

        while (reader.hasValidTransaction()) {
            Transaction transaction = reader.getNextTransaction();
            post.send(transaction, new POSTCallback() {
                @Override
                public void onFinish(int status, String invoice) {
                    if (status == POST.STATUS_OK) {

                    }
                }
            });
        }
    }
}
