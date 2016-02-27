package Transactions.payment;

import RemoteInterfaces.CreditI;
import java.rmi.RemoteException;

public class Credit extends Payment implements CreditI {

    private final String number;

    public Credit(double amount, String number) throws RemoteException {
        super(amount);
        this.number = number;
    }

    @Override
    public String getNumber() throws RemoteException {
        return number;
    }

    @Override
    public double getAmount() throws RemoteException {
        return super.getAmount();
    }

}
