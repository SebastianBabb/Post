package StoreProducts;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.POST;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andre_000
 */
public class Store {

    private String storeName;
    private String storeAddress;
    private Catalog catalog;
    private POST pos;

    //DEFAULT CONSTRUCTOR
    public Store() {
        this.storeName = "General Store";
        this.storeAddress = "100 Main Street";
    }

    //SECONDARY CONSTRUCTOR
    Store(String newName, String newAddress) {
        this.storeName = newName;
        this.storeAddress = newAddress;

    }

    public Catalog getCatalog() {

        return this.catalog;
    }

    public POST getPos() {
        return pos;
    }

    public void setPos(POST pos) {
        this.pos = pos;
    }

    public void setCatalog(Catalog newCatalog) {

        this.catalog = newCatalog;
    }

    //store name getter
    public String getStoreName() {
        return this.storeName;
    }

    //store addr getter
    public String getStoreAddress() {
        return this.storeAddress;
    }

    //store name setter
    public void setStoreName(String newName) {
        this.storeName = newName;

    }

    //store addr setter
    public void setStoreAddress(String newAddress) {
        this.storeAddress = newAddress;
    }

    String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Catalog createCatalogFromFile() throws RemoteException {
        Catalog storeCatalog = new Catalog();
        ProductReader storePR;

        //PPENING FILE
        storePR = new ProductReader();

        //FILILNG CATALOG
        while (storePR.hasNextProduct()) {
            storeCatalog.addProduct(storePR.getNextProduct());
        }

        //marks catalog that it has been init'd from file
        storeCatalog.productInitDone();

        this.catalog = storeCatalog;
        return storeCatalog;
    }

}
