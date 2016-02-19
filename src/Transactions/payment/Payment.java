package Transactions.payment;

public class Payment {

    private final double amount;
    
    public Payment() {
        this.amount = 0;
    }

    public Payment(double amount) {
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}
