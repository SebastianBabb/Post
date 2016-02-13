package Transactions;

/**Customer holds relevant information about the customer.
 * 
 * @author Jrubin
 */
public class Customer {
    private String name;
    
    /**Instantiates a blank customer.
     * 
     */
    public Customer(){
        this.name= null;
    }
    /**
     * Constructor that takes in a String and sets Customers name to that string.
     * @param newName 
     */
    public Customer(String newName){
        this.name=newName;
    }

    /**
     * Returns the customer's name
     * @return name
     */
    public String getName(){
        return this.name;
    }
            
}
