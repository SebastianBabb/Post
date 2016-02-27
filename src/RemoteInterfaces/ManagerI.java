package RemoteInterfaces;

import java.rmi.RemoteException;

/**
 *
 * @author asouza
 */
public interface ManagerI extends java.rmi.Remote {

    public InvoiceI newInvoice() throws RemoteException;

    public int getTransCount() throws RemoteException;

    public ItemI getItem(String upc) throws RemoteException;
    
    public CatalogI getCatalog() throws RemoteException;
    
     public POSTI getStorePOS() throws RemoteException;
     
     public String getStorename() throws RemoteException;
}
