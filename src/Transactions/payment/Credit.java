package Transactions.payment;

public class Credit extends Payment {

    private final String number;

    public Credit(int type, double amount, String number) {
        super(type, amount);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
