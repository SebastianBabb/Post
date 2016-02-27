/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgui.payment;

import Transactions.payment.Cash;
import Transactions.payment.Payment;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author asouza
 */
public class GPaymentCashPanel extends javax.swing.JPanel {

    /**
     * Creates new form GPayamentCash
     */
    public GPaymentCashPanel(JPanel parent) {
        initComponents();
        this.parent = (GPaymentPanel) parent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCash = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnPayCash = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(338, 34));
        setMinimumSize(new java.awt.Dimension(338, 34));

        lblCash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCash.setText("Amount");
        lblCash.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAmount.setToolTipText("");
        txtAmount.setMaximumSize(new java.awt.Dimension(58, 19));
        txtAmount.setMinimumSize(new java.awt.Dimension(58, 19));
        txtAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAmountKeyReleased(evt);
            }
        });

        btnPayCash.setText("Pay");
        btnPayCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPayCashMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCash, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPayCash, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPayCash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPayCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPayCashMouseClicked
        if (this.txtAmount.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Must Give money.", "Cash Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (Double.parseDouble(this.txtAmount.getText()) < 0) {
            JOptionPane.showMessageDialog(this, "Must Give positive amount for money.", "Cash Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            double amnt_given;
            amnt_given = Double.parseDouble(this.txtAmount.getText());
            Payment cp = new Cash(amnt_given);
            this.parent.sendPaymentToFrame(cp);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Cash Error.", "Invalid Error", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException ex) {

        }

    }//GEN-LAST:event_btnPayCashMouseClicked

    private void txtAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAmountKeyReleased

    }//GEN-LAST:event_txtAmountKeyReleased

    public double getAmount() {
        return Double.parseDouble(this.txtAmount.getText());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPayCash;
    private javax.swing.JLabel lblCash;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables
    private final String regex = "\\d+";
    private final GPaymentPanel parent;
}
