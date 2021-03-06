package Transactions;

/**ItemLine is a class that contains a single item in the cart
 * and specifies the quantity of that item to be checked out.
 * @author Jrubin
 */
public class ItemLine {
    private int quantity;
    private String UPC;
    
    /**
     * Construct a blank invalid item
     */
    public ItemLine(){
        this.UPC=null;
        this.quantity=-1;
    }
    /**
     * Takes a UPC and a quantity for a new item to be added to the transaction.
     * @param newUPC
     * @param newQuantity 
     */
    public ItemLine(String newUPC, int newQuantity ){
        this.UPC=newUPC;
        this.quantity=newQuantity;
    }
    /**
     * Prints out the UPC and the quantity of the item on this line
     */
    void printItem(){
        System.out.println("UPC is: "+ this.UPC+ "\tQuantity is: "+this.quantity);
        
    }
    
    /**
     * Returns the UPC of the item in this line 
     * @return UPC
     */
    public String getUPC(){
        return this.UPC;
    }
    
    /**
     * Returns the quantity of the items in this line.
     * @return quantity
     */
    public int getQuantity(){
        return this.quantity;
    }
    
    /**
     * Takes a String representing a new UPC and sets UPC to this
     * @param newUPC 
     */
    void setUPC(String newUPC){
        this.UPC= newUPC;
    }
    
    /**
     * Sets the quantity of the item in this line to the input vlaue
     * @param newQuantity 
     */
    void setQuantity(int newQuantity){
        this.quantity = newQuantity;
    }
        
}
