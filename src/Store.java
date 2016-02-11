
import java.io.IOException;
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
    
    String storeName;
    String storeAddress;
    
    
    //DEFAULT CONSTRUCTOR
    Store(){
        this.storeName = "General Store";
        this.storeAddress = "100 Main Stree";
        
    }
    
    //SECONDARY CONSTRUCTOR
    Store(String newName, String newAddress){
        this.storeName = newName;
        this.storeAddress = newAddress;
        
    }
    
    //store name getter
    String getStoreName(){
        
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
    
    Catalog createCatalogFromFile(){
        Catalog storeCatalog = new Catalog();
        ProductReader storePR;
        
        
        //PPENING FILE
        storePR = new ProductReader();

        //FILILNG CATALOG
        while(storePR.hasNextProduct()){
            storeCatalog.addProduct(storePR.getNextProduct());
        }
        
        return storeCatalog;
    }
    
   
}
