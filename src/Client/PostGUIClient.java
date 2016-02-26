package Client;

import RMI.RemoteInterface;
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
			
//        try{
//            Registry registry = LocateRegistry.getRegistry(null);
//            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");
//            Transaction response = stub.getTransaction();
//        }catch(Exception e){
//            System.err.println("Client Exception: "+ e.toString());
//            e.printStackTrace();
//        }
    }
    
}
