package RemoteInterfaces;

import java.rmi.RemoteException;
import main.POST;

/**
 *
 * @author asouza
 */
public interface IManager extends java.rmi.Remote {

    public IInvoice newInvoice() throws RemoteException;

    public int getTransCount() throws RemoteException;

    public IItem getItem(String upc) throws RemoteException;

    public ICatalog getCatalog() throws RemoteException;

    public IPOST getStorePOS() throws RemoteException;
    
    public String getStorename() throws RemoteException;
;
}
