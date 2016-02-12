package StoreProducts;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    //DEFAULT CONSTRUCTOR
    public Store(){
        this.storeName = "General Store";
        this.storeAddress = "100 Main Stree";
        
    }
    
    //SECONDARY CONSTRUCTOR
    Store(String newName, String newAddress){
        this.storeName = newName;
        this.storeAddress = newAddress;
        
    }
    
    public Catalog getCatalog(){
        
        return this.catalog;
    }
    
    public void setCatalog(Catalog newCatalog){
        
        this.catalog = newCatalog;
    }
    
    //store name getter
    public String getStoreName(){
        
        return this.storeName;
    }
    
    //store addr getter
    String getStoreAddress(){
        
        return this.storeAddress;
    }
    
    //store name setter
    int setStoreName(String newName){
        
        this.storeName = newName;
        
        return 1;
    }
    
    //store addr setter
    int setStoreAddress(String newAddress){
        
        this.storeAddress = newAddress;
        
        return 1;
    }
    
    String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public Catalog createCatalogFromFile(){
        Catalog storeCatalog = new Catalog();
        ProductReader storePR;
        
        
        //PPENING FILE
        storePR = new ProductReader();

        //FILILNG CATALOG
        while(storePR.hasNextProduct()){
            storeCatalog.addProduct(storePR.getNextProduct());
        }
        
        //marks catalog that it has been init'd from file
        storeCatalog.productInitDone();
        
        this.catalog = storeCatalog;
        return storeCatalog;
    }
    
    
   
}
