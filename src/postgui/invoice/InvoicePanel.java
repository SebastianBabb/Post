
package postgui.invoice;


public interface InvoicePanel {
    /**
     * Adds a row to the invoice text area. It is assumed
     * that the row is already formatted.
     * @param row Formatted row to add to invoice panel 
     */
    public void addItemToInvoice(String row);
    
    /**
     * Add the price of a newly added item to the current
     * running total.
     * @param addedPrice new price to add to current 
     * running total.
     */
    public void updateTotalLabel(double addedPrice);
    
    /**
     * 
     */
    public void resetPanel();
   
}
