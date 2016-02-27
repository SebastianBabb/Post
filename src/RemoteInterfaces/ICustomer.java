/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoteInterfaces;

/**
 *
 * @author asouza
 */
public interface ICustomer extends java.rmi.Remote {

    public String getName() throws java.rmi.RemoteException;

    public void setName(String name) throws java.rmi.RemoteException;

}
