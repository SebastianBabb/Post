package Transactions;

/**Transaction class Takes a Customer, Array of LineItems and Payment type 
 * to create a whole Transaction.
 * @author Jrubin
 */
public class Transaction {
    private static final int MAX_LINES = 100;
    
    private Customer customer;
    private ItemLine lineItems[];
    private Payment payment;
    private int numOfLines;
    
    /** Constructs a Transaction object with a null Customer, empty ItemLine
     * array and empty Payment info.
     */
    public Transaction(){
        this.customer = new Customer(null);
        this.lineItems = new ItemLine[MAX_LINES];
        this.numOfLines = 0;
        this.payment = new Payment();
        
    }
    /** Prints all the information of the Transaction
     * 
     */
    void printTransaction(){
        System.out.println("Customer: " + customer.getName());
        for(int i=0;i<numOfLines;i++){
            lineItems[i].printItem();  
        }
        
    }
    
    /** Returns the Customer object
     * 
     * @return Customer
     */
    Customer getCustomer(){
        return this.customer;
    }
    
    /**Returns the array of LineItems
     * 
     * @return ItemLine array 
     */
    ItemLine[] getItemList(){
        return this.lineItems;
    }
    /**Returns the payment for this transaction
     * 
     * @return payment type
     */
    Payment getPayment(){
        return this.payment;
    }
    
    /**Returns the number of different items purchased in this transaction
     * 
     * @return number of lines 
     */
    int getCartSize(){
        return this.numOfLines;
    }
    
    /**takes a string, makes a new customer, and makes that customer the 
     * new customer for this transaction
     * @param newName 
     */
    void setCustomer(String newName){
        this.customer = new Customer(newName);
    }
    /**takes a Customer object and makes it the customer for this transaction
     * 
     * @param newCustomer 
     */
    void setCustomer(Customer newCustomer){
        this.customer = newCustomer;
    }
    
    /**Takes an ItemLine object and adds it to the Item List
     * 
     * @param newLn 
     */
    void addItemLine(ItemLine newLn){
        this.lineItems[numOfLines] = newLn;
        this.numOfLines++;
    }
    
    /**Takes a Payment object to set Payment
     * 
     * @param newPayment 
     */
    void setPayment(Payment newPayment){
        this.payment = newPayment;
    }
     
}
