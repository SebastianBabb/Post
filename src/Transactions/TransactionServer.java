package Transactions;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Jrubin
 */
public class TransactionServer implements RemoteInterface{
    
    public TransactionServer() {}

    public Transaction getTransaction() {
	return new Transaction();
    }
	
    public static void main(String args[]) {
	
	try {
	    TransactionServer obj = new TransactionServer();
	    RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(obj, 1099);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry();
	    registry.bind("RemoteInterface", stub);

	    System.err.println("Server ready");
	} catch (Exception e) {
	    System.err.println("Server exception: " + e.toString());
	    e.printStackTrace();
	}
    }
    
}