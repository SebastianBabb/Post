package Transactions;

import Transactions.payment.Payment;

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

    public Customer getCustomer() {
        return customer;
    }

    public ItemList getItemList() {
        return lineItem;
    }

    public Payment getPayment() {
        return payment;
    }
}
