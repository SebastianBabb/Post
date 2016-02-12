package main;

import Transactions.ItemLine;
import Transactions.payment.Credit;
import Transactions.payment.Payment;
import Transactions.Transaction;
import Transactions.TransactionReader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

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
    private static final int COLUMN_WIDTH_R = 20;

    public static final int STATUS_OK = 0;
    public static final int STATUS_FAILED = 1;

    private final Queue<Task> queue = new LinkedList<>();
    private final String storeName;
    private final Logger logger;

    public POST(String storeName, Logger logger) {
        this.storeName = storeName;
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
            String invoice = createInvoice(storeName, task.getTransaction());
            logger.output(invoice);

            if (task.getCallback() != null) {
                task.getCallback().onFinish(0, invoice);
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
        builder.append(format(COLUMN_WIDTH_R, dateFormat.format(calendar.getTime())));
        builder.append('\n');

        float total = 0;

        for (ItemLine itemLine : transaction.getItemList()) {
            if (itemLine == null) {
                break;
            }

            String upc = itemLine.getUPC();
            int quantity = itemLine.getQuantity();

            Item item = new Item();

            String description = item.getItemDescription();
            double price = item.getItemPrice();
            double subtotal = item.getItemPrice() * quantity;

            builder.append(format(COLUMN_WIDTH_L, description + ":"));
            builder.append(format(COLUMN_WIDTH_R, String.format("%d @ %.2f %.2f", quantity, price, subtotal)));
            builder.append('\n');

            total += subtotal;
        }

        builder.append("------");
        builder.append('\n');
        builder.append(format(COLUMN_WIDTH_L, "Total:"));
        builder.append(format(COLUMN_WIDTH_R, String.format("$%.2f", total)));
        builder.append('\n');

        Payment payment = transaction.getPayment();
        String tendered = null;

        switch (payment.getType()) {
            case Payment.TYPE_CHECK:
                tendered = "Paid by Check";
                break;
            case Payment.TYPE_CREDIT:
                if (payment instanceof Credit) {
                    Credit credit = (Credit) payment;
                    tendered = String.format("Credit Card %s", credit.getNumber());
                }
                break;
        }

        if (tendered == null) {
            tendered = String.format("$%.2f", payment.getAmount());
        }

        String returned = String.format("$%.2f", payment.getAmount() - total);

        builder.append(format(COLUMN_WIDTH_L, "Amount Tendered:"));
        builder.append(format(COLUMN_WIDTH_R, tendered));
        builder.append('\n');
        builder.append(format(COLUMN_WIDTH_L, "Amount Returned:"));
        builder.append(format(COLUMN_WIDTH_R, returned));
        builder.append('\n');

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
    private static class Task {

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
        POST post = new POST("CSC 668/868", new Logger() {
            @Override
            public void output(String output) {
                outputToConsole(output);
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
