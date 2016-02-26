package Client;

import RMI.RemoteInterface;
import StoreProducts.Catalog;
import java.rmi.RemoteException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PostGUIClient {
    private PostGUIClient() {}
    
    public static void main(String[] args) throws NamingException, RemoteException {
			Context namingContext = new InitialContext();

			// Initialize a stub to make remote calls on.
			RemoteInterface stub = (RemoteInterface)namingContext.lookup("rmi://localhost/remote_implementation");
			// Make a call to remote object's testPrint() method.
			System.out.println(stub.getStoreName());
			System.out.println(stub.getStoreAddress());
			Catalog c = stub.getCatalog();
			c.printCatalog();
			if(stub.UPCExists("1116")) {
				System.out.println(stub.getItem("1116"));
			} else {
				System.out.println("UPC 1116J DOES NOT EXIST!");
			}
    }
}
