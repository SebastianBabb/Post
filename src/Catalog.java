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
    private static final int MAX_PRODUCTS = 100;
    
    private Item products[];   //the store's products, MAX 100
    private int numProducts;  //stores num products currently in catalog, also indicates new open space for addition of item
    private boolean productsInitialized = false; 
    
    //DEFAULT CONSTRUCTOR
    Catalog(){
        this.products = new Item[MAX_PRODUCTS];
        this.numProducts = 0;  //first available stop = 0
    }
    
    //SECONDARY CONSTRUCTOR
    Catalog(Item newProducts[], int newNumProducts){
        
        if(newNumProducts > 100){ //exceeding max products
            System.out.println("ERROR: products list cannot exceed 100, will only take first 100.");
        }
        
        //inseart new products list
        for(int i = 0; i < MAX_PRODUCTS; i++){
            this.products[i] = newProducts[i];
            this.numProducts +=1;
        }    
        
    }
    
    int addProduct(){
        
        return 1;
    }
    
}
