/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import RemoteInterfaces.IManager;
import StoreProducts.Catalog;
import RemoteInterfaces.ICatalog;
import RemoteInterfaces.IItem;
import StoreProducts.Store;
import Transactions.Invoice;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import RemoteInterfaces.IInvoice;
import RemoteInterfaces.IPOST;

/**
 *
 * @author andre_000
 */
public class Manager extends UnicastRemoteObject implements IManager {

    private Store store;


    private Catalog catalog;
    private int transCount = 0;
    final private Map<Integer, IInvoice> trans_hist;

    @Override
    public IInvoice newInvoice() throws RemoteException {
        trans_hist.put(transCount, new Invoice());
        transCount++;
        return trans_hist.get(transCount - 1);
    }
    
    @Override
    public int getTransCount() throws RemoteException {
        return transCount;
    }

    @Override
    public IItem getItem(String upc) throws RemoteException {
        return catalog.getItem(upc);
    }
    
    @Override
    public IPOST getStorePOS() {
        return this.store.getPos();
    }
    
    public String getStorename(){
       return this.store.getStoreName();
    }
    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void init() throws RemoteException {
        this.store = new Store();
        this.store.createCatalogFromFile();
        POST post = new POST(this.store, new Logger() {
            @Override
            public void output(String output) {
                outputToConsole(output);
                outputToFile("src/invoices.txt", output + '\n');
            }
        });
        this.store.setPos(post);

//        TransactionReader reader = new TransactionReader("transactions.txt");
//
//        while (reader.hasValidTransaction()) {
//            Invoice transaction = reader.getNextTransaction();
//            post.send(transaction, new POST.POSTCallback() {
//                @Override
//                public void onFinish(int status, String invoice) {
//                    if (status == POST.STATUS_OK) {
//
//                    }
//                }
//            });
//        }
    }

    public Manager() throws RemoteException {
        this.trans_hist = new HashMap();
    }

//    public static void main(String[] args) throws RemoteException {
//        Manager manager = new Manager();
//        manager.init();
//    }

    @Override
    public ICatalog getCatalog() throws RemoteException {
       return this.catalog;
    }
}
