
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    ProductReader() throws FileNotFoundException{
        this.fReader = new FileReader(new File("products.txt"));
        this.bReader = new BufferedReader(fReader);
    }
    
    ProductReader(String fName) throws FileNotFoundException{
        this.fReader = new FileReader(new File(fName));
        this.bReader = new BufferedReader(fReader);
    }
    
    //SAVES NEXT LINE, returns TRUE if next line exists
    //MUST CHECK hasNextProduct before getNextProduct!
    boolean hasNextProduct() throws IOException{
        curLine = bReader.readLine();
        
        return curLine != null;
    }
    
   //creates an item object from curLine and returns it
    /*
    itemLine[0] = UPC
    itemLine[1] = Descprition
    itemLine[2] = price
    
    */
   Item getNextProduct(){
       //Need to use \\s{2,} so string.split will capture multiple spaces.
       //But we do not want it ti split on single spaces because of product decriptions
       //--AJS
       String itemLine[] = curLine.split("\\s{2,}");
       
       //Item (UPC, Descr, Price, ID)
       Item nextItem = new Item(itemLine[0], itemLine[1], Float.parseFloat(itemLine[2]), 0);
       
       return nextItem;
   }
    /**
     * Testing Product reader
     * @param args 
     */
   public static void main(String[] args){
       ProductReader test = null;
        try {
            test = new ProductReader();
            
            while(test.hasNextProduct()){
                Item temp = test.getNextProduct();
                System.out.println(temp.toString());
            }
        } catch (FileNotFoundException ex) {
            if(test != null){
                try {
                    test.bReader.close();
                } catch (IOException ex1) {
                    System.err.println("IOException: could not close stream");
                }
            }
            System.err.println("FileNotFound -->" + ex.getMessage());
        } catch (IOException ex) {
            System.err.append("IOExcetion: Error reading from buffered reader");
        }
   }
}
