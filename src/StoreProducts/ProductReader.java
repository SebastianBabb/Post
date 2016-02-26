package StoreProducts;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
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
 * 
 * 
 * USED BY: Store
 * 
 * Initializes the catalog
 */
public class ProductReader {
    
    private BufferedReader bReader;
    private FileReader fReader;
    private String curLine;  //holds line loaded in hasNextProduct to return in getNextProduct
    
    //DEFAULT CONSTRUCTOR
    ProductReader(){
        try {
            this.fReader = new FileReader(new File("products.txt"));
            this.bReader = new BufferedReader(fReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ProductReader(String fName){
        try {
            this.fReader = new FileReader(new File(fName));
            this.bReader = new BufferedReader(fReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //SAVES NEXT LINE, returns TRUE if next line exists
    //MUST CHECK hasNextProduct before getNextProduct!
    boolean hasNextProduct(){
        try {
            curLine = bReader.readLine();
            
            return curLine != null;
        } catch (IOException ex) {
            Logger.getLogger(ProductReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   //creates an item object from curLine and returns it
    /*
    itemLine[0] = UPC
    itemLine[1] = Descprition
    itemLine[2] = price
    
    */
   Item getNextProduct() throws RemoteException{
       String itemLine[] = curLine.split("\\s{2,}");
       
//       System.out.println("itemLine: SIZE " + itemLine.length );
//       for(int i = 0; i < itemLine.length; i++){
//           System.out.println("itemLine: " + i + " " + itemLine[i]);
//       }
       
       //Item (UPC, Descr, Price, ID)
       Item nextItem = new Item(itemLine[0], itemLine[1], Double.parseDouble(itemLine[2]), 0);
       
       return nextItem;
   }
}
