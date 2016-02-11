
package Transactions;

public class Payment {
    private String paymentType;
    private double paymentNumber;
    
    public Payment(){
        paymentType=null;
        paymentNumber=-1;
    }
    
    public Payment(String paymentType, double paymentNumber){
        this.paymentType = paymentType;
        this.paymentNumber = paymentNumber;
    }
    
    void PrintPayment(){
        System.out.println("Type: "+this.paymentType+" Number: "+this.paymentNumber);
    }
    
}
