package RMI;

import StoreProducts.Catalog;
import StoreProducts.Store;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import main.Manager;

/*
 * Start the server before running the client.
 * 
 * @author Jrubin
 */
public class TransactionServer {

     private Store store;
     
    public TransactionServer() {
    }

    void init() throws RemoteException {
        store = new Store();
        Catalog catalog = store.createCatalogFromFile();

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            Manager sm = new Manager();
            sm.init();
            sm.setCatalog(catalog);

            registry.rebind("Manager", (Manager) sm);

            for (String itemUPC : catalog.getUPCList()) {
                registry.rebind(itemUPC, catalog.getItem(itemUPC));
            }

            
            System.out.println("Server running...");

        } catch (Exception e) {
            System.out.println("Server not connected: " + e);
        }

    }

    public static void main(String args[]) {
         try {
             TransactionServer pserver = new TransactionServer();
             pserver.init();
         } catch (RemoteException ex) {
             System.err.println("Server Error: " + ex.getMessage());
         }
    }

}
