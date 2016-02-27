/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions.payment;

import RemoteInterfaces.ICash;
import java.rmi.RemoteException;

/**
 *
 * @author Tony
 */
public class Cash extends Payment implements ICash{

    public Cash() throws RemoteException {
        super(0);
    }

    public Cash(double amount)  throws RemoteException{
        super(amount);
    }
    
    @Override
    public double getAmount() throws RemoteException {
        return super.getAmount();
    }
    
    
}
