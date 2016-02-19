package Transactions.payment;

public class Credit extends Payment {

    private final String number;

    public Credit(double amount, String number) {
        super(amount);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
