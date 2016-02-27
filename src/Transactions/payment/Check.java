package Transactions.payment;

import RemoteInterfaces.ICheck;
import java.rmi.RemoteException;

public class Check extends Payment implements ICheck {

    public Check(double amount) throws RemoteException {
        super(amount);
    }

    @Override
    public double getAmount() throws RemoteException {
        return super.getAmount();
    }

}
