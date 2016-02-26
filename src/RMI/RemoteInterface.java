package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import StoreProducts.Item;
import Transactions.Transaction; 
import Transactions.payment.Payment; 
import StoreProducts.Catalog;

/**
 *
 * @author Jrubin
 */
public interface RemoteInterface extends Remote {
	// Store methods.
    public String getStoreName() throws RemoteException;
    String getStoreAddress() throws RemoteException;
    String getDateTime() throws RemoteException;

	// Catalog methods.	
    public boolean UPCExists(String upc) throws RemoteException;
    public Item getItem(String upc)  throws RemoteException;
    String[] getUPCList() throws RemoteException;

	// Trancsaction methods.
	public boolean sendTransaction(Transaction transaction) throws RemoteException;
	public boolean sendPayment(Payment payment) throws RemoteException;
	public StoreProducts.Catalog getCatalog() throws RemoteException;
	public boolean upcExist() throws RemoteException;
	public String getItemUPC(Item item) throws RemoteException;
	
}
