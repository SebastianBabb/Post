/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgui;

import Client.PostClient;
import RemoteInterfaces.IManager;
import Transactions.Customer;
import Transactions.Invoice;
import Transactions.ItemLine;
import Transactions.payment.Payment;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Tony
 */
public final class GPost extends javax.swing.JFrame {

    /**
     * Creates new form GPost
     */
    public GPost() {
        initComponents();
        this.gPanelTime.startTimer();
        this.attachComponents();
    }

    public void attachComponents() {
        this.gProductPanel.attachInvoicePanel(this.gInvoiceListPanel.getPanel());
        this.gPaymentPanel.addFrameReference(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gPaymentPanel = new postgui.payment.GPaymentPanel();
        gPanelTime = new postgui.time.GTimePanel();
        gInvoiceListPanel = new postgui.invoice.GInvoicePanel();
        gProductPanel = new postgui.product.GProductPanel();
        lblCustName = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POST");
        setAlwaysOnTop(true);
        setResizable(false);

        lblCustName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCustName.setText("Customer Name");
        lblCustName.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gPanelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gPaymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCustName, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txtCustomerName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gInvoiceListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(gProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblCustName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gInvoiceListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gPaymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(gPanelTime, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>
        //</editor-fold>
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new GPost().setVisible(true);
//            }
//        });
//        
//     
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private postgui.invoice.GInvoicePanel gInvoiceListPanel;
    private postgui.time.GTimePanel gPanelTime;
    private postgui.payment.GPaymentPanel gPaymentPanel;
    private postgui.product.GProductPanel gProductPanel;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JTextField txtCustomerName;
    // End of variables declaration//GEN-END:variables
    private PostClient pc;

    public PostClient getPc() {
        return pc;
    }

    public void setPc(PostClient pc) {
        this.pc = pc;
    }

    public void retrieveUPCList() {
        try {
            IManager mi = (IManager) this.pc.getManager();
            String[] list = mi.getCatalog().getUPCList();
            this.gProductPanel.loadUPClist(list);
        } catch (RemoteException ex) {
            Logger.getLogger(GPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param p
     */
    public void recievePaymentFromPanel(Payment p) {
        this.sendInvoiceToRemote(p);
    }
    
    private void sendInvoiceToRemote(Payment p){
        String name = this.txtCustomerName.getText();
        ItemLine[] items = this.gProductPanel.arrListToArray();
        
        if(name.equals("")){
            JOptionPane.showMessageDialog(this, "Must Give Customer Name.", "Customer Name", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(items.length < 1){
            JOptionPane.showMessageDialog(this, "Must Add items before paying.", "Empty Invoice", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Invoice curInv = new Invoice(new Customer(name),items, p,items.length);
            IManager mgr = (IManager) this.pc.getManager();
            String sname = mgr.getStorename();
            
            String inv_back = mgr.getStorePOS().createInvoice(sname, curInv);
            GPrintedInvoiceFrame f_inv = new GPrintedInvoiceFrame();
            f_inv.passInvString(inv_back);
            f_inv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f_inv.setVisible(true);
            f_inv.setAlwaysOnTop(true);
            this.clearAllInfoAfterSale();
        } catch (RemoteException ex) {
            Logger.getLogger(GPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void clearAllInfoAfterSale(){
        this.txtCustomerName.setText("");
        this.gPaymentPanel.removePaymentPanels();
        this.gInvoiceListPanel.resetPanel();
        
    }
}