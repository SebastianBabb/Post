package Transactions;

/**
 * Transaction Temporarily uses Strings for Customer, int arrays for
 * lineItem and a string for payment type 
 * until these classes are implemented 
 */
public class Transaction {
    Customer customer;
    ItemList lineItem;
    Payment payment;
    

    public Transaction(Customer customer, ItemList lineItem, Payment payment){
        
        this.customer = customer;
        this.lineItem = lineItem;
        this.payment = payment;
    }
        
}
