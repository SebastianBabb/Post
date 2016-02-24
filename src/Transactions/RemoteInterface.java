package Transactions;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Jrubin
 */
public interface RemoteInterface extends Remote{
    String testPrint() throws RemoteException;
}
