
package Transactions;

public class ItemList {
    int numberOfItems;
    int [][] items;
    
    public ItemList(int[][] list, int len){
        items=list;
        numberOfItems = len;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public int[][] getItems() {
        return items;
    }
}
