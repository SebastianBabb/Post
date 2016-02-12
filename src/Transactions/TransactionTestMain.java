
package Transactions;

/**
 * Tests TransactionReader by parsing through transactions.txt and creating
 * all of the objects
 * @author Jrubin
 */
public class TransactionTestMain {

    public static void main(String[] args) {
        TransactionReader r = new TransactionReader("Transactions/transactions.txt");
        while(r.hasValidTransaction()){
            r.getNextTransaction().printTransaction();
        }
    }
}
