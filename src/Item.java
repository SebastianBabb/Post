/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andre_000
 */
public class Item {
    
    String description;
    String UPC;
    double price;
    int ID;
    
    
    Item(){
        
        this.description = "no description";
        this.UPC = "0000";
        this.price = 0.0;
        this.ID = 0;
    }
    
    Item(String newUPC, String newDescr, double newPrice, int newID){
        
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
    
    //SETTERS - return 1 if successful
    int setItemDescription(String newDescr){
        
        this.description = newDescr;
        
        return 1; //success
    }
    
    int setItemUPC(String newUPC){
        
        this.UPC = newUPC;
        
        return 1; //success
    }
    
    int setItemPrice(double newPrice){
        
        this.price = newPrice;
        
        return 1; //success
    }
    
    int setItemID(int newID){
        
        this.ID = newID;
        
        return 1; //success
    }
    
}
