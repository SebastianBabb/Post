package Client;

import RemoteInterfaces.ManagerI;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostClient {

    private Registry registry;
    private static PostClient self_pc;
    
    private PostClient() throws RemoteException {
        this.registry = LocateRegistry.getRegistry();
    }

    public Registry getRegistry() {
        return registry;
    }
    
    public ManagerI getManager() throws RemoteException{
        try {
            return (ManagerI) registry.lookup("Manager");
        } catch (NotBoundException ex) {
           System.err.println("Registry item Not Found" + ex.getMessage());
        } catch (AccessException ex) {
            System.err.println("AccessException" + ex.getMessage());
        }
        return null;
    }
    
    public static PostClient getInstance() {
        if(PostClient.self_pc == null){
            try {
                return self_pc = new PostClient();
            } catch (RemoteException ex) {
                Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }else{
            return self_pc;
        }
        
    }

//    public static void main(String[] args) throws NamingException, RemoteException {
//        try {
//            Registry registry = LocateRegistry.getRegistry();
//            ManagerI sManager = (ManagerI) registry.lookup("Manager");
//
//            InvoiceI trans = sManager.newInvoice();
//
//            String[] list = sManager.getCatalog().getUPCList();
//            for (String x : list) {
//                System.out.println(x);
//            }
//            System.out.println();
//
//            System.out.println(sManager.getTransCount());
//        } catch (RemoteException e) {
//            System.out.println("Remote Exception: " + e);
//        } catch (NotBoundException e) {
//            System.out.println("NotBoundException: " + e);
//        }

//        Context namingContext = new InitialContext();
//
//        // Initialize a stub to make remote calls on.
//        RemoteInterface stub = (RemoteInterface) namingContext.lookup("rmi://localhost/remote_implementation");
//        // Make a call to remote object's testPrint() method.
//        System.out.println(stub.getStoreName());
//        System.out.println(stub.getStoreAddress());
//        Catalog c = stub.getCatalog();
//        c.printCatalog();
//        if (stub.UPCExists("1116")) {
//            System.out.println(stub.getItem("1116"));
//        } else {
//            System.out.println("UPC 1116J DOES NOT EXIST!");
//        }
    
}
