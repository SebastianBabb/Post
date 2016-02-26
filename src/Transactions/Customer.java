package Transactions;

import RemoteInterfaces.CustomerI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Customer holds relevant information about the customer.
 *
 * @author Jrubin
 */
public class Customer extends UnicastRemoteObject  implements CustomerI    {

    private String name;

    /**
     * Instantiates a blank customer.
     *
     */
    public Customer() throws RemoteException{
        this.name = null;
    }

    /**
     * Constructor that takes in a String and sets Customers name to that
     * string.
     *
     * @param newName
     */
    public Customer(String newName) throws RemoteException{
        this.name = newName;
    }

    /**
     * Returns the customer's name
     *
     * @return name
     */
    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name; 
    }

}
