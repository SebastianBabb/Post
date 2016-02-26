package StoreProducts;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andre_000
 */
public class Item implements Serializable {
    
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
    public String getItemDescription(){
        
        return this.description;
    }
    
    String getItemUPC(){
        
        return this.UPC;
       
    }
    
    public double getItemPrice(){
        
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

    /**
     * Add toString method to print formatted item objects
     * @return 
     * @Author asouza
     */
    @Override
    public String toString(){
        String itm = "";
         itm = String.format("%-6s %-30s $%-4.2f\n", this.UPC,this.description,this.price);        
        return itm;
    }
}
