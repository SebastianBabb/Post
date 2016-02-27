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

    public ManagerI getManager() throws RemoteException {
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
        if (PostClient.self_pc == null) {
            try {
                return self_pc = new PostClient();
            } catch (RemoteException ex) {
                Logger.getLogger(PostClient.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return self_pc;
        }

    }

}
