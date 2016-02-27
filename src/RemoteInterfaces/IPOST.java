/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteInterfaces;

import Transactions.Invoice;
import java.rmi.RemoteException;

/**
 *
 * @author Tony
 */
public interface IPOST extends java.rmi.Remote {
    /**
     * 
     * @param storeName
     * @param invoice
     * @return
     * @throws RemoteException 
     */
    public String createInvoice(String storeName, IInvoice invoice) throws RemoteException;

}
