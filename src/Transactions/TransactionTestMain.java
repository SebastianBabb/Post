
package Transactions;

public class TransactionTestMain {

    public static void main(String[] args) {
        TransactionReader r = new TransactionReader("Transactions/transactions.txt");
        while(r.hasValidTransaction()){
            r.getNextTransaction();
        }
    }
}
