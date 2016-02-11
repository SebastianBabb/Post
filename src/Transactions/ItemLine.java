
package Transactions;

public class ItemLine {
    private int quantity;
    private String UPC;
    
    public ItemLine(){
        this.UPC=null;
        this.quantity=-1;
    }
    
    public ItemLine(String newUPC, int newQuantity ){
        this.UPC=newUPC;
        this.quantity=newQuantity;
    }
    
    void printItem(){
        System.out.println("UPC is: "+ this.UPC+ "\tQuantity is: "+this.quantity);
        
    }
    
    //ADD SETTERS AND GETTERS
}
