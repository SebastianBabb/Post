package Transactions;


public class Customer {
    
    private String name;
    
    public Customer(){
        this.name= null;
    }
    
    public Customer(String newName){
        this.name=newName;
    }
    
    
    String getName(){
        return this.name;
    }
            
}
