package Transactions;
import java.io.*;

public class TransactionReader {
    
    BufferedReader transactionBuffer;
    String currentLine;
    Customer tempCustomer;
    //ItemLine newItemLine;
    Payment payType;
    
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
        Transaction nextTransaction = new Transaction();
        int itemsInCart = 0;
        try{
            //Reads the next customer from the file
            tempCustomer = new Customer(getNextLine());
            nextTransaction.setCustomer(tempCustomer);
            
            //Gets first Item line
            currentLine = getNextLine();
            
            while(nextLineIsItem()){   
                
                nextTransaction.addItemLine(addItem(currentLine));              
                //prep next line
                currentLine = getNextLine();
            }
            
            //newItemLine = new ItemLine(); //RM LATER
            
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            payType = addPayment(currentLine);
            nextTransaction.setPayment(payType);
            
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        
        nextTransaction.printTransaction();

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
    
    boolean nextLineIsItem(){

        if(!currentLine.contains("CASH") &&
                !currentLine.contains("CREDIT") &&
                !currentLine.contains("CHECK")){
            return true;
        }
        
        //System.out.println(currentLine + "is false");
        return false;
    }
    
    ItemLine addItem(String itemString){
        
        String itemSplit[] = itemString.split("\\s{2,}");
        if(itemSplit.length==2){
            return new ItemLine(itemSplit[0], Integer.parseInt(itemSplit[1]));
        }else{
            return new ItemLine(itemSplit[0], 1);
        }
        
    }
    
    Payment addPayment(String paymentString){
        Payment tempPayment;
        String paymentSplit[] = paymentString.split(" +");
        tempPayment = new Payment(paymentSplit[0],Double.parseDouble(paymentSplit[1]));
        return tempPayment;
    }
}
