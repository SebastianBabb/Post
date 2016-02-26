package RemoteInterfaces;


import java.rmi.RemoteException;

/**
 *
 * @author asouza
 */
public interface InvoiceI extends java.rmi.Remote {

    public CustomerI getCustomer() throws RemoteException;

    /**
     * Returns the array of LineItems
     *
     * @return ItemLine array
     * @throws java.rmi.RemoteException
     */
    public ItemLineI[] getItemList() throws RemoteException;

    /**
     * Returns the payment for this transaction
     *
     * @return payment type
     * @throws java.rmi.RemoteException
     */
    public PaymentI getPayment() throws RemoteException;

    /**
     * Returns the number of different items purchased in this transaction
     *
     * @return number of lines
     * @throws java.rmi.RemoteException
     */
    public int getCartSize() throws RemoteException;

    /**
     * takes a string, makes a new customer, and makes that customer the new
     * customer for this transaction
     *
     * @param newName
     * @throws java.rmi.RemoteException
     */
    public void setCustomer(String newName) throws RemoteException;

    /**
     * takes a Customer object and makes it the customer for this transaction
     *
     * @param newCustomer
     * @throws java.rmi.RemoteException
     */
    public void setCustomer(CustomerI newCustomer) throws RemoteException;

    /**
     * Takes an ItemLine object and adds it to the Item List
     *
     * @param newLn
     * @throws java.rmi.RemoteException
     */
    public void addItemLine(ItemLineI newLn) throws RemoteException;

    public void addItemLine(String upc, int quantity) throws RemoteException;

    /**
     * Takes a Payment object to set Payment
     *
     * @param newPayment
     */
    public void setPayment(PaymentI newPayment) throws RemoteException;

    

    }
