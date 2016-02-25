package postgui.product;

import javax.swing.JOptionPane;
import postgui.invoice.InvoicePanel;

/**
 *
 * @author Tony
 */
public class GProductPanel extends javax.swing.JPanel {

    /**
     * Creates new form GProductPanel
     */
    public GProductPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upcLabel = new javax.swing.JLabel();
        cboItemList = new javax.swing.JComboBox();
        qtyLabel = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Product", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        upcLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upcLabel.setText("UPC");

        cboItemList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Item", "1111 Wings 3.99", "1112 Shoes 54.99", "1113 Hats 34.99", "1114 Boxers 49.99" }));

        qtyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        qtyLabel.setText("Quantity");

        txtQty.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQtyKeyReleased(evt);
            }
        });

        btnAddItem.setText("Add Item");
        btnAddItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddItemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(qtyLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(upcLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtQty)
                    .addComponent(btnAddItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboItemList, 0, 197, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(upcLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboItemList, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qtyLabel)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtQtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyReleased
        String tmp = this.txtQty.getText();
        if (tmp.length() < 1) {
            return;
        }
        if (!tmp.matches(this.regex)) {
            this.txtQty.setText(tmp.substring(0, tmp.length() - 1));
        }
    }//GEN-LAST:event_txtQtyKeyReleased

    private void btnAddItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddItemMouseClicked
        if (this.cboItemList.getSelectedIndex() < 1) {
            JOptionPane.showMessageDialog(this, "Must Select an Item.", "Item Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (this.txtQty.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Must Select a Quantity.", "Quantity Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String itm = (String) this.cboItemList.getSelectedItem();
        String qty = (String) this.txtQty.getText();
        String items[] = itm.split("\\s{1,}");

        if (items.length < 1 || qty.length() < 1) {
            return;
        }
        float prc = Float.parseFloat(items[2]);
        int qqty = Integer.parseInt(qty);
        float n_prc = prc * qqty;
        this.inv_p.updateTotalLabel(n_prc);
        String row = String.format("%-25s %-10s %13s %17s\n", items[1], qty, prc, n_prc);
        this.inv_p.addItemToInvoice(row);
        this.resetProductPanel();

    }//GEN-LAST:event_btnAddItemMouseClicked

    private void resetProductPanel() {
        this.txtQty.setText("");
        this.cboItemList.setSelectedIndex(0);
    }

    public void attachInvoicePanel(javax.swing.JPanel ip) {;
        this.inv_p = (InvoicePanel) ip;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JComboBox cboItemList;
    private javax.swing.JLabel qtyLabel;
    private javax.swing.JTextField txtQty;
    private javax.swing.JLabel upcLabel;
    // End of variables declaration//GEN-END:variables
    private final String regex = "\\d+";
    javax.swing.JPanel inv_panel;
    private InvoicePanel inv_p;

}