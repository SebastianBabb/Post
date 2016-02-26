/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteInterfaces;

import java.rmi.RemoteException;

/**
 *
 * @author asouza
 */
public interface ItemI extends java.rmi.Remote {

    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public String getItemDescription() throws RemoteException;
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public String getItemUPC() throws RemoteException;
    /**
     * 
     * @return
     * @throws RemoteException 
     */
    public double getItemPrice() throws RemoteException;
   
}
