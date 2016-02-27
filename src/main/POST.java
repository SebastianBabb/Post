package main;

import RemoteInterfaces.ICash;
import RemoteInterfaces.ICheck;
import RemoteInterfaces.ICredit;
import RemoteInterfaces.IInvoice;
import StoreProducts.Catalog;
import RemoteInterfaces.IItem;
import RemoteInterfaces.IItemLine;
import StoreProducts.Store;
import Transactions.Invoice;
import Transactions.payment.Check;
import Transactions.payment.Credit;
import RemoteInterfaces.IPayment;
import RemoteInterfaces.IPOST;
import Transactions.payment.Cash;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Point of Sales Terminal (POST)
 *
 * POST is responsible for processing transactions into invoices that will then
 * be sent to a specified output such as the screen or file.
 *
 * @author Gary Ng
 */
public class POST extends UnicastRemoteObject implements IPOST {

    private static final int COLUMN_WIDTH_L = -20;
    private static final int COLUMN_WIDTH_C = 10;
    private static final int COLUMN_WIDTH_R = 12;

    public static final int STATUS_OK = 0;
    public static final int STATUS_FAILED = 1;

    private final Store store;
    private final Catalog catalog;
    private final Logger logger;

    public POST(Store store, Logger logger) throws RemoteException {
        this.store = store;
        this.catalog = store.getCatalog();
        this.logger = logger;
    }

    /**
     * Receives a transaction from an external caller
     *
     * @param transaction
     * @param callback
     * @throws java.rmi.RemoteException
     */
    public void send(Invoice transaction, POSTCallback callback) throws RemoteException {
        if (isValidTransaction(transaction)) {
            String invoice = createInvoice(store.getStoreName(), transaction);
            logger.output(invoice);

            if (callback != null) {
                callback.onFinish(STATUS_OK, invoice);
            }
        }
    }

    /**
     * Generates an invoice using a transaction.
     *
     * @param storeName
     * @param invoice
     * @return invoice
     * @throws java.rmi.RemoteException
     */
    public String createInvoice(String storeName, IInvoice invoice) throws RemoteException {
        StringBuilder builder = new StringBuilder();
        builder.append(storeName);
        builder.append("\n\n");
        String name = invoice.getCustomer().getName();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/y h:mm a");

        builder.append(format(COLUMN_WIDTH_L, name));
        builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, dateFormat.format(calendar.getTime())));
        builder.append('\n');

        float total = 0;
        IItemLine tmp_li;
        int size = invoice.getCartSize();
        for (int i = 0; i < size; i++) {
            tmp_li = invoice.getItemLineAtIndex(i);
            if (tmp_li == null) {
                break;
            }

            String upc = tmp_li.getUPC();
            int quantity = tmp_li.getQuantity();

            String description;
            double price = 0;
            double subtotal = 0;

            if (catalog.UPCExists(upc)) {
                IItem item = catalog.getItem(upc);

                description = item.getItemDescription();
                price = item.getItemPrice();
                subtotal = price * quantity;
            } else {
                description = String.format("??? [UPC %s]", upc);
            }
        
            builder.append(String.format("%-25s %4s @ %8.2f   %8.2f\n",description, quantity, price, subtotal));
            total += subtotal;
        }

        builder.append("------");
        builder.append('\n');
        builder.append(format(COLUMN_WIDTH_L, "Total:"));
        builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, String.format("$%.2f", total)));
        builder.append('\n');

        IPayment payment =  invoice.getPayment();
        String tendered = "";

        if (payment instanceof ICheck) {
            tendered = "Paid by Check";
        } else if (payment instanceof ICredit) {
            ICredit credit = (ICredit) payment;
            tendered = String.format("Credit Card %s", credit.getNumber());
        } else if( payment instanceof ICash) {
            tendered = String.format("$%.2f", payment.getAmount());
        }

        String returned = String.format("$%.2f", payment.getAmount() - total);

        if (payment instanceof ICredit) {
            builder.append(format(COLUMN_WIDTH_L, "Paid with:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, tendered));
            builder.append('\n');

            //Checking if card approved
            if (!creditCardApproved()) {
                builder.append("CREDIT CARD DECLINED!");
                builder.append('\n');
            }
        } else {
            builder.append(format(COLUMN_WIDTH_L, "Amount Tendered:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, tendered));
            builder.append('\n');
            builder.append(format(COLUMN_WIDTH_L, "Amount Returned:"));
            builder.append(format(COLUMN_WIDTH_C + COLUMN_WIDTH_R, returned));
            builder.append('\n');
        }
        System.out.println(builder.toString());
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
     * Simulating 10% credit card decline
     *
     * @return false 10% of time
     */
    public boolean creditCardApproved() {
        Random rn = new Random();
        return rn.nextInt(10) > 0;  //if random number == 0, decline (10% to roll 0)
    }

    /**
     * Checks if transaction is valid before processing.
     *
     * @param transaction
     * @return status of validity
     */
    public boolean isValidTransaction(Invoice transaction) {
        return true;
    }

    /**
     * Callback function when a transaction is processed by the terminal.
     */
    public interface POSTCallback {

        void onFinish(int status, String invoice);
    }
}
