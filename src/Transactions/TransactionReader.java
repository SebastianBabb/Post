package Transactions;
import java.io.*;

public class TransactionReader {
    
    BufferedReader transactionBuffer;
    String currentLine;
    Customer tempCustomer;
    ItemList newItemList;
    Payment payType;
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
        int itemsInCart = 0;
        try{
            //Reads the next customer from the file
            tempCustomer = new Customer(getNextLine());
            System.out.println(tempCustomer.getName());
            
            //Gets first Item line
            currentLine = getNextLine();
            while(nextLineIsItem()){   
                
                addItem(currentLine,itemsInCart);
                itemsInCart++;
                
                //prep next line
                currentLine = getNextLine();
            }
            
            newItemList = new ItemList(itemList,itemsInCart);
            
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            payType = addPayment(currentLine);
            
        }catch(Exception e){
            System.err.print("ERROR:" +e.getMessage());
        }
        
        nextTransaction = new Transaction(tempCustomer,newItemList,payType);

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
    
    void addItem(String itemString, int itemsInCart){
        String itemSplit[] = itemString.split(" +");
        
        itemList[itemsInCart][0] = Integer.parseInt(itemSplit[0]);
        
        if (itemSplit.length == 1) {
            itemList[itemsInCart][1] = 1;
        } else {
            itemList[itemsInCart][1] = Integer.parseInt(itemSplit[1]);
        }
    }
    
    Payment addPayment(String paymentString){
        Payment tempPayment;
        String paymentSplit[] = paymentString.split(" +");
        tempPayment = new Payment(paymentSplit[0],Double.parseDouble(paymentSplit[1]));
        return tempPayment;
    }
}
