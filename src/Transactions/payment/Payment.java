package Transactions.payment;

public class Payment {

    public static final int TYPE_CASH = 0;
    public static final int TYPE_CHECK = 1;
    public static final int TYPE_CREDIT = 2;

    private final int type;
    private final double amount;

    public Payment(int type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public Payment(String strType, double amount) {
        switch (strType) {
            case "CHECK":
                type = TYPE_CHECK;
                break;
            case "CREDIT":
                type = TYPE_CREDIT;
                break;
            default:
                type = TYPE_CASH;
                break;
        }
    
        this.amount = amount;
    }
    
    public int getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
}
