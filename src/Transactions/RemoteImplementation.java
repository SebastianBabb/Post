/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transactions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author sebastian
 */
public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {

	public RemoteImplementation() throws RemoteException {}
	
    public String testPrint() throws RemoteException {
		return "Hello, Client!";
    }
	
}
