package Transactions;

import RemoteInterfaces.CustomerI;
import RemoteInterfaces.InvoiceI;
import RemoteInterfaces.ItemLineI;
import Transactions.payment.Payment;
import RemoteInterfaces.PaymentI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Invoice class Takes a Customer, Array of LineItems and Payment type to create
 * a whole Invoice.
 *
 * @author Jrubin
 */
public class Invoice extends UnicastRemoteObject implements InvoiceI {

    private static final int MAX_LINES = 100;

    private Customer customer;
    private ItemLine lineItems[];
    private Payment payment;
    private int numOfLines;

    /**
     * Constructs a Transaction object with a null Customer, empty ItemLine
     * array and empty Payment info.
     *
     * @throws java.rmi.RemoteException
     */
    public Invoice() throws RemoteException {
        this.customer = new Customer(null);
        this.lineItems = new ItemLine[MAX_LINES];
        this.numOfLines = 0;
        this.payment = new Payment();

    }

    public Invoice(Customer customer, ItemLine[] lineItems, Payment payment, int numOfLines) throws RemoteException {
        this.customer = customer;
        this.lineItems = lineItems;
        this.payment = payment;
        this.numOfLines = numOfLines;
    }

    /**
     * Prints all the information of the Invoice
     *
     */
    void printTransaction() throws RemoteException {
        System.out.println("Customer: " + customer.getName());
        for (int i = 0; i < numOfLines; i++) {
            lineItems[i].printItem();
        }

    }

    /**
     * Returns the Customer object
     *
     * @return Customer
     */
    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Returns the array of LineItems
     *
     * @return ItemLine array
     */
    @Override
    public ItemLine[] getItemList() {
        return this.lineItems;
    }

    /**
     * Returns the payment for this transaction
     *
     * @return payment type
     */
    @Override
    public PaymentI getPayment() {
        return this.payment;
    }

    /**
     * Returns the number of different items purchased in this transaction
     *
     * @return number of lines
     */
    @Override
    public int getCartSize() {
        return this.numOfLines;
    }

    /**
     * takes a string, makes a new customer, and makes that customer the new
     * customer for this transaction
     *
     * @param newName
     */
    @Override
    public void setCustomer(String newName) throws RemoteException {
        this.customer = new Customer(newName);
    }

    /**
     * takes a Customer object and makes it the customer for this transaction
     *
     * @param newCustomer
     */
    @Override
    public void setCustomer(CustomerI newCustomer) throws RemoteException {
        this.customer = (Customer) newCustomer;
    }

    /**
     * Takes an ItemLine object and adds it to the Item List
     *
     * @param newLn
     */
    @Override
    public void addItemLine(ItemLineI newLn) throws RemoteException {
        this.lineItems[numOfLines] = (ItemLine) newLn;
        this.numOfLines++;
    }

    /**
     * Takes a Payment object to set Payment
     *
     * @param newPayment
     */
    @Override
    public void setPayment(PaymentI newPayment) throws RemoteException {
        this.payment = (Payment) newPayment;
    }

    /**
     * takes a string and integer, makes a ItemLine, and adds it to the Item
     * List
     *
     * @param upc
     * @param quantity
     * @throws java.rmi.RemoteException
     */
    @Override
    public void addItemLine(String upc, int quantity) throws RemoteException {
        this.lineItems[numOfLines] = new ItemLine(upc, quantity);
    }

    @Override
    public ItemLine getItemLineAtIndex(int i) throws RemoteException {
        if (i >= this.numOfLines) {
            return null;
        }
        return this.lineItems[i];
    }

}
