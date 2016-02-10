
package Transactions;

public class Payment {
    String paymentType;
    double paymentNumber;
    
    public Payment(String paymentType, double paymentNumber){
        this.paymentType = paymentType;
        this.paymentNumber = paymentNumber;
    }
    
    void PrintPayment(){
        System.out.println("Type: "+this.paymentType+" Number: "+this.paymentNumber);
    }
    
}
