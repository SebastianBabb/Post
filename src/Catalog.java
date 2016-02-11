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
    
    int addProduct(Item newItem){
        if(numProducts < 100){
            products[numProducts] = newItem;
            products[numProducts].setItemID(numProducts);
            this.numProducts += 1;      //inc catalog size
            return 1;  //added succesfully
        }
        else{
            System.out.println("ERROR, catalog is full, did not add item");
            return -1;
        }
        
    }
    
    void printCatalog(){
        System.out.printf("%10s%25s%6s\n", "UPC", "Description", "Price");
        Item itemToPrint = new Item();
        for(int i = 0; i < this.numProducts; i++){
            itemToPrint = this.products[i];
            System.out.printf("%10s%25s%10s\n", itemToPrint.getItemUPC(), itemToPrint.getItemDescription(), itemToPrint.getItemPrice());
//            System.out.println()  
        }
        
    }
    
}
