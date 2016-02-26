/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import StoreProducts.Item;
import Transactions.Transaction; 
import Transactions.payment.Payment; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import StoreProducts.Store;
import StoreProducts.Catalog;
import main.Logger;
import main.POST;

/**
 *
 * @author sebastian
 */
public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {
  	private final Store store;
  	private final Catalog catalog;
	private final POST post;
	
	public RemoteImplementation() throws RemoteException {
		store = new Store();
        this.store.createCatalogFromFile();
		this.catalog = store.getCatalog();
        post = new POST(this.store, new Logger() {
            @Override
            public void output(String output) {
                outputToConsole(output);
                outputToFile("src/invoices.txt", output + '\n');
            }
        });
	}
	
	@Override
	public String getStoreName() throws RemoteException {
		return this.store.getStoreName();
	}

	@Override
	public String getStoreAddress() throws RemoteException {
		return this.store.getStoreAddress();
	}

	@Override
	public String getDateTime() throws RemoteException {
        //get current date time with Date()
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
	}

	@Override
	public boolean UPCExists(String upc) throws RemoteException {
		return catalog.UPCExists(upc);
	}

	@Override
	public Item getItem(String upc) throws RemoteException {
		return catalog.getItem(upc);
	}

	@Override
	public String[] getUPCList() throws RemoteException {
		return catalog.getUPCList();
	}

	@Override
	public void sendTransaction(Transaction transaction) {
		post.send(transaction, new POST.POSTCallback() {
			@Override
			public void onFinish(int status, String invoice) {
				if (status == POST.STATUS_OK) {
					// Nothing to do.
				}
			}
		});
	}

	@Override
	public boolean sendPayment(Payment payment) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Catalog getCatalog() {
		return this.catalog;
	}
	
	@Override
	public boolean upcExist(String upc) throws RemoteException  {
		if(this.getItem(upc) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String getItemUPC(int id) {
		// Why do we need the item upc if we have the item?  Should this return
		// a upc from an item name, not an Item object?
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
