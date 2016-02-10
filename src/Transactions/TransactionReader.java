package Transactions;
import java.io.*;
import java.util.Scanner;

public class TransactionReader {
    
    BufferedReader transactionBuffer;
    String currentLine;
    String tempCustomer; //Placeholder until Customer class is made
    String itemList[] = new String[100];
    
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
        int itemsInCart = 0;
        try{
            //Reads the next customer from the file
            tempCustomer = transactionBuffer.readLine();
            System.out.println(tempCustomer);
            
            //Gets first Item line
            currentLine = transactionBuffer.readLine();
            while(!currentLine.contains("CASH") &&
                    !currentLine.contains("CREDIT") &&
                    !currentLine.contains("CHECK")){
                System.out.println("Item: " + currentLine);
                itemsInCart++;
                //prep next line
                currentLine = transactionBuffer.readLine();

            }
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            Scanner paymentType = new Scanner(currentLine);
            System.out.println(currentLine);
            
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        
        nextTransaction = new Transaction(null,null,null);
        
        
        return nextTransaction;
    }
    
}
