package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Transactions.Transaction;
import Transactions.RemoteInterface;


public class PostGUIClient {
    private PostGUIClient() {}
    
    public static void main(String[] args){
        
        try{
            Registry registry = LocateRegistry.getRegistry(null);
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
            Transaction response = stub.getTransaction();
        }catch(Exception e){
            System.err.println("Client Exception: "+ e.toString());
            e.printStackTrace();
        }
    }
    
}
