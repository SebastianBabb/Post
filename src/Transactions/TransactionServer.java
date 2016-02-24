package Transactions;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Start the server before running the client.
 * 
 * @author Jrubin
 */
public class TransactionServer {
    
    public TransactionServer() {}
	
    public static void main(String args[]) {
		try {
			RemoteImplementation remoteImplementation = new RemoteImplementation();
			
			// Start the registry.
			LocateRegistry.createRegistry(1099);
			
			// Bind the remote object to the registry.
			Context namingContext = new InitialContext();
			namingContext.bind("rmi:remote_implementation", remoteImplementation);
			
	//	    TransactionServer transactionServer = new TransactionServer();
	//	    RemoteImplementation stub = (RemoteImplementation) UnicastRemoteObject.exportObject(transactionServer, 1099);

			// Bind the remote object's stub in the registry
	//	    Registry registry = LocateRegistry.getRegistry();
	//	    registry.bind("Remote", );

			System.err.println("Server ready...");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
	}
    }
    
}