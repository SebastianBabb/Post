package Transactions.payment;

import RemoteInterfaces.CheckI;
import java.rmi.RemoteException;

public class Check extends Payment implements CheckI {

    public Check(double amount) throws RemoteException {
        super(amount);
    }

    @Override
    public double getAmount() throws RemoteException {
        return super.getAmount();
    }

}
