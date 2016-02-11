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
        
    Customer getCustomer(){
        return this.customer;
    }
    
    ItemList getItemList(){
        return this.lineItem;
    }
    
    Payment getPayment(){
        return this.payment;
    }
    
    void setCustomer(String newName){
        this.customer = new Customer(newName);
    }
    
    void setItemList(int[][] newList,int itemsInCart){
        this.lineItem = new ItemList(newList,itemsInCart);
    }
    
    void setPayment(String payType, int paymentData){
        this.payment = new Payment(payType, paymentData);
    }
    
    
}
