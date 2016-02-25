
package postgui.invoice;

/**
 *
 * @author Tony
 */
public interface InvoicePanel {
    public void addItemToInvoice(String row);
    
    public void updateTotalLabel(float addedPrice);
    
   
}
