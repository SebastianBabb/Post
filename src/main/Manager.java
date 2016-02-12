/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import StoreProducts.Catalog;
import StoreProducts.Store;
import Transactions.Transaction;
import Transactions.TransactionReader;

/**
 *
 * @author andre_000
 */
public class Manager {
    
    Store store;
    
    public void init(){
        this.store = new Store();
        this.store.createCatalogFromFile();

        System.out.println("Current Catalog: \n");
        this.store.getCatalog().printCatalog();
        System.out.println("\nProcessing Transactoins: \n");
        
        POST post = new POST(this.store, new Logger() {
            @Override
            public void output(String output) {
                outputToConsole(output);
                outputToFile("src/invoices.txt", output + '\n');
            }
        });

        TransactionReader reader = new TransactionReader("transactions.txt");

        while (reader.hasValidTransaction()) {
            Transaction transaction = reader.getNextTransaction();
            post.send(transaction, new POST.POSTCallback() {
                @Override
                public void onFinish(int status, String invoice) {
                    if (status == POST.STATUS_OK) {

                    }
                }
            });
        }
    }
    
    Manager(){
        
    }
    
    public static void main(String[] args){
        Manager manager = new Manager();
        manager.init();
    }
}
