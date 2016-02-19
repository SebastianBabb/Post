package Transactions;

import Transactions.payment.Check;
import Transactions.payment.Credit;
import Transactions.payment.Payment;

import java.io.*;

/**
 * TransactionReader class reads from file fname, parses customer name,
 * a list of items with quantity, and payment type, then Returns a Transaction
 * object with this information
 * @author Jrubin
 */
public class TransactionReader {    
    BufferedReader transactionBuffer;
    String currentLine;
    Customer tempCustomer;
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
    public boolean hasValidTransaction(){
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

    /**
     * Gets the next transaction from file. 
     * !!! ASSUMES YOU HAVE CHECKED hasValidTransaction() !!!
     * Reads Customer from the text file, then reads the list of item/quantity
     * pairs,then reads the paymentType/paymentNumber pair. Takes all of this
     * and returns a Transaction object.
     * @return 
     */
    public Transaction getNextTransaction(){
        Transaction nextTransaction = new Transaction();
        try{
            //Reads the next customer from the file
            tempCustomer = new Customer(getNextLine());
            nextTransaction.setCustomer(tempCustomer);
            
            //Gets first Item line.
            currentLine = getNextLine();
            
            //Process currentLine Item, get next line, check if next line is an Item
            while(nextLineIsItem()){       
                nextTransaction.addItemLine(addItem(currentLine));              
                //prep next line
                currentLine = getNextLine();
            }
            
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            payType = addPayment(currentLine);
            nextTransaction.setPayment(payType);

        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        
        return nextTransaction;
    }
    
    /**
     * Get the next line from buffer and return it as a String
     * @return 
     */
    private String getNextLine(){
        try{
            return transactionBuffer.readLine();
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        return null;
    }
    
    /**
     * Check if the current line is an item. accomplishes this by checking for
     * CASH, CREDIT, or CHECK in the first line after the items 
     * @return 
     */
    boolean nextLineIsItem(){

        if(!currentLine.contains("CASH") &&
                !currentLine.contains("CREDIT") &&
                !currentLine.contains("CHECK")){
            return true;
        }
        return false;
    }
    
    /**
     * Takes a String containing an item/Quantity pair and parses it into 
     * a ItemLine object, which it returns
     * @param itemString
     * @return ItemLine object with parsed information
     */
    ItemLine addItem(String itemString){
        String itemSplit[] = itemString.split("\\s{2,}");
        if(itemSplit.length==2){
            return new ItemLine(itemSplit[0], Integer.parseInt(itemSplit[1]));
        }else{
            return new ItemLine(itemSplit[0], 1);
        }  
    }
    /**
     * Takes a String with paymentInfo/PaymentNumber pair and parses it into
     * a Payment Object, which it returns
     * @param paymentString
     * @return 
     */
    Payment addPayment(String paymentString){
        Payment tempPayment;
        String paymentSplit[] = paymentString.split(" +");
        String type = paymentSplit[0];

        switch (type) {
            case "CHECK":
                tempPayment = new Check(Double.parseDouble(paymentSplit[1]));
                break;
            case "CREDIT":
                tempPayment = new Credit(0, paymentSplit[1]);
                break;
            default:
                tempPayment = new Payment();
                break;
        }

        return tempPayment;
    }
}
