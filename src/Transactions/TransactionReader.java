package Transactions;
import java.io.*;
import java.util.Scanner;

public class TransactionReader {
    
    BufferedReader transactionBuffer;
    String currentLine;
    String tempCustomer; //Placeholder until Customer class is made
    int itemList[][] = new int[100][2];
    
    //Initialize Transaction Reader with the Transaction File
    public TransactionReader(String fname){
        try{
        //FileReader fr = new FileReader("Transactions/transactions.txt");
        transactionBuffer = new BufferedReader(
                new FileReader(fname));
        
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    //Checks if The Transaction file has another entry (Blank line)
    boolean hasValidTransaction(){
        try{
            currentLine = transactionBuffer.readLine();
            if(currentLine == null){
                return false;
            }
            return true;
        }catch(Exception e){
            System.out.println("EOF");
        }
        return false;
    }
    
    //Gets the next transaction from file
    /**************************************************************
     *                                                            *
     * !!!  ASSUMES YOU HAVE CHECKED hasValidTransaction()  !!!   *
     *                                                            *
     **************************************************************/                                       
    Transaction getNextTransaction(){
        Transaction nextTransaction;
        Scanner lineScanner;
        int itemsInCart = 0;
        try{
            //Reads the next customer from the file
            tempCustomer = getNextLine();
            System.out.println(tempCustomer);
            
            //Gets first Item line
            currentLine = getNextLine();
            while(isPaymentLine()){   
                
                //System.out.println("Item: " + currentLine);
                lineScanner = new Scanner(currentLine);
                itemList[itemsInCart][0] = lineScanner.nextInt();
                
                if(lineScanner.hasNextInt()){
                    
                }
                
                itemsInCart++;
                //prep next line
                
                currentLine = getNextLine();
            }
            
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            System.out.println(currentLine);
            
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        
        nextTransaction = new Transaction(null,null,null);
        
        
        return nextTransaction;
    }
    
    private String getNextLine(){
        try{
            return transactionBuffer.readLine();
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        return null;
    }
    
    boolean isPaymentLine(){
        if(!currentLine.contains("CASH") &&
                !currentLine.contains("CREDIT") &&
                !currentLine.contains("CHECK")){
            return true;
        }
        return false;
    }
    
}
