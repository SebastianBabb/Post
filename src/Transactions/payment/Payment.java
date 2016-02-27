package Transactions.payment;

import RemoteInterfaces.IPayment;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Payment extends UnicastRemoteObject implements IPayment {

    private final double amount;

    public Payment() throws RemoteException {
        this.amount = 0;
    }

    public Payment(double amount) throws RemoteException {
        this.amount = amount;
    }

    @Override
    public double getAmount()throws RemoteException {
        return amount;
    }
}
