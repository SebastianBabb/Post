/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre_000
 */
public class Catalog {
    
    
    String description;
    String UPC;
    double price;
    int ID;
    
    //DEFAULT CONSTRUCTOR
    Catalog(){
        
        this.description = "no description";
        this.UPC = "0000";
        this.price = 0.00;
        this.ID = 0;
    }
    
    //SECONDARY CONSTRUCTOR
    Catalog(String newDescr, String newUPC, double newPrice, int newID){
        
        this.description = newDescr;
        this.UPC = newUPC;
        this.price = newPrice;
        this.ID = newID;
        
    }
    
    //GETTERS
    String getItemDescription(){
        
        return this.description;
    }
    
    String getItemUPC(){
        
        return this.UPC;
    }
    
    double getItemPrice(){
        
        return this.price;
    }
    
    int getItemID(){
        
        return this.ID;
    }
    
}
