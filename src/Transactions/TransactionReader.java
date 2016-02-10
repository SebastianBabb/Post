package Transactions;
import java.io.*;

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
        int itemsInCart = 0;
        try{
            //Reads the next customer from the file
            tempCustomer = getNextLine();
            System.out.println(tempCustomer);
            
            //Gets first Item line
            currentLine = getNextLine();
            
            while(nextLineIsItem()){   
                addItem(currentLine,itemsInCart);
                itemsInCart++;
                
                //prep next line
                currentLine = getNextLine();
            }
            
            for(int i=0;i<itemsInCart;i++){
                System.out.println("UPC: " + itemList[i][0]+ "\tQty: "+itemList[i][1]);
            }
            
            //CURRENTLINE CONTAINS THE PAYMENT INFO
            System.out.println("Payment Info: " + currentLine);
            
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
    
    void addPayment(String paymentString){
        String paymentSplit[] = paymentString.split(" +");
        
        
    }
}
