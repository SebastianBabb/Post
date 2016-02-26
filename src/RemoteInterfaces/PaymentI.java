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
public interface PaymentI {
    /**
     * 
     * @return 
     * @throws java.rmi.RemoteException 
     */
    public double getAmount() throws RemoteException;
    
}
