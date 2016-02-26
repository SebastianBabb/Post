package StoreProducts;

import RemoteInterfaces.CatalogI;
import RemoteInterfaces.ItemI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andre_000
 */
public class Catalog extends UnicastRemoteObject implements CatalogI {

    private static final int MAX_PRODUCTS = 100;

    private Item products[];   //the store's products, MAX 100
    private int numProducts;  //stores num products currently in catalog, also indicates new open space for addition of item
    private boolean productsInitialized = false;
    private String[] UPCList;

    //DEFAULT CONSTRUCTOR
    Catalog() throws RemoteException {
        this.products = new Item[MAX_PRODUCTS];
        this.numProducts = 0;  //first available stop = 0
        this.UPCList = new String[MAX_PRODUCTS];
    }

    //SECONDARY CONSTRUCTOR
    Catalog(Item newProducts[], int newNumProducts) throws RemoteException {

        if (newNumProducts > 100) { //exceeding max products
            System.out.println("ERROR: products list cannot exceed 100, will only take first 100.");
        }

        //inseart new products list
        for (int i = 0; i < MAX_PRODUCTS; i++) {
            this.products[i] = newProducts[i];
            this.UPCList[this.numProducts] = newProducts[i].getItemUPC();

            this.numProducts += 1;
        }
    }

    public int addProduct(Item newItem) throws RemoteException {
        if (this.numProducts < 100) {
            this.products[this.numProducts] = newItem;
            this.products[this.numProducts].setItemID(this.numProducts);
            this.UPCList[this.numProducts] = newItem.getItemUPC();

            this.numProducts += 1;      //inc catalog size
            return 1;  //added succesfully
        } else {
            System.out.println("ERROR, catalog is full, did not add item");
            return -1;
        }
    }

    public void printCatalog() throws RemoteException {
        System.out.printf("%10s%25s%10s\n", "UPC", "Description", "Price");
        System.out.println("--------------------------------------------------");
        Item itemToPrint = new Item();
        for (int i = 0; i < this.numProducts; i++) {
            itemToPrint = this.products[i];
            System.out.printf("%10s%25s%10s\n", itemToPrint.getItemUPC(), itemToPrint.getItemDescription(), itemToPrint.getItemPrice());
//            System.out.println()  
        }
    }

    public boolean UPCExists(String upc) throws RemoteException {
        for (int i = 0; i < this.numProducts; i++) {
            if (upc.equalsIgnoreCase(this.products[i].getItemUPC())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemI getItem(String upc) throws RemoteException {
        for (int i = 0; i < this.numProducts; i++) {
            if (upc.equalsIgnoreCase(this.products[i].getItemUPC())) {
                return this.products[i];
            }
        }
        return null;
    }

    //UPC list for GUI
    public String[] getUPCList() throws RemoteException {
        String rUPCList[] = new String[this.numProducts]; //upc list to return

        //create UPC list with proper size (SHRINKING)
        for (int i = 0; i < this.numProducts; i++) {
            rUPCList[i] = this.UPCList[i];
        }

        return rUPCList;
    }

    /**
     * Makes sure catalog does not get init multiple times from same file
     *
     */
    void productInitDone() {
        this.productsInitialized = true;
    }

}
