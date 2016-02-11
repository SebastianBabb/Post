package Transactions;

/**
 * Transaction Temporarily uses Strings for Customer, int arrays for
 * lineItem and a string for payment type 
 * until these classes are implemented 
 */
public class Transaction {
    private Customer customer;
    private ItemLine lineItems[];
    private Payment payment;
    private int numOfLines;
   
/*
    public Transaction(Customer customer, ItemLine[] lineItem, Payment payment){
        
        this.customer = customer;
        this.lineItems = lineItem;
        this.payment = payment;
    }
*/
    
    public Transaction(){
        this.customer = new Customer(null);
        this.lineItems = new ItemLine[100];
        this.numOfLines = 0;
        this.payment = new Payment();
        
    }
    
    void printTransaction(){
        System.out.println("Customer: " + customer.getName());
        for(int i=0;i<numOfLines;i++){
            lineItems[i].printItem();  
        }
        
    }
        
    Customer getCustomer(){
        return this.customer;
    }
    
    ItemLine[] getItemList(){
        return this.lineItems;
    }
    
    Payment getPayment(){
        return this.payment;
    }
    
    void setCustomer(String newName){
        this.customer = new Customer(newName);
    }
    
    void setCustomer(Customer newCustomer){
        this.customer = newCustomer;
    }
    
    void addItemLine(ItemLine newLn){
        this.lineItems[numOfLines] = newLn;
        this.numOfLines++;
    }
    
    void setPayment(Payment newPayment){
        this.payment = newPayment;
    }
    
    
}
