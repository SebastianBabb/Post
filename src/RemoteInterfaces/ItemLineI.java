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
public interface ItemLineI extends java.rmi.Remote {

    /**
     * Returns the UPC of the item in this line
     *
     * @return UPC
     * @throws java.rmi.RemoteException
     */
    public String getUPC() throws RemoteException;

    /**
     * Returns the quantity of the items in this line.
     *
     * @return quantity
     * @throws java.rmi.RemoteException
     */
    public int getQuantity() throws RemoteException;

    /**
     * Takes a String representing a new UPC and sets UPC to this
     *
     * @param newUPC
     * @throws java.rmi.RemoteException
     */
    void setUPC(String newUPC) throws RemoteException;

    /**
     * Sets the quantity of the item in this line to the input value
     *
     * @param newQuantity
     * @throws java.rmi.RemoteException
     */
    void setQuantity(int newQuantity) throws RemoteException;
    
}

