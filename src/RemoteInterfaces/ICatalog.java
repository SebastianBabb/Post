package RemoteInterfaces;

import java.rmi.RemoteException;

/**
 *
 * @author asouza
 */
public interface ICatalog extends java.rmi.Remote {
    /**
     * 
     * @param upc
     * @return
     * @throws RemoteException 
     */
    public boolean UPCExists(String upc) throws RemoteException;
    /**
     * 
     * @param upc
     * @return
     * @throws RemoteException 
     */
    public IItem getItem(String upc) throws RemoteException;
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public String[] getUPCList() throws RemoteException;

}
