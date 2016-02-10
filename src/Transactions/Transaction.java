package Transactions;

/**
 * Transaction Temporarily uses Strings for Customer, int arrays for
 * lineItem and a string for payment type 
 * until these classes are implemented 
 */
public class Transaction {
    String customer;
    int[] lineItem;
    String payment;
    

    public Transaction(String customer, int[] lineItem, String payment){
        
        this.customer = customer;
        this.lineItem = lineItem;
        this.payment = payment;
    }
        
}
