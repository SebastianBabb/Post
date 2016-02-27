package postgui.payment;

import RemoteInterfaces.IPayment;
import Transactions.payment.Payment;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import postgui.GPost;

/**
 *
 * @author Tony
 */
public class GPaymentPanel extends javax.swing.JPanel {

    /**
     * Creates new form GPaymentPanel
     */
    public GPaymentPanel() {
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

        PaymentPanel = new javax.swing.JPanel();
        cboPaymentType = new javax.swing.JComboBox();
        lblPaymentType = new javax.swing.JLabel();
        jPaymentTypeSubPanel = new javax.swing.JPanel();

        PaymentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Payment"));
        PaymentPanel.setName("Payment"); // NOI18N

        cboPaymentType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Payment", "Cash", "Credit Card", "Check" }));
        cboPaymentType.setToolTipText("Payment Type");
        cboPaymentType.setName("cboPaymentType"); // NOI18N
        cboPaymentType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPaymentTypeItemStateChanged(evt);
            }
        });

        lblPaymentType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaymentType.setText("Payment Type");
        lblPaymentType.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPaymentTypeSubPanelLayout = new javax.swing.GroupLayout(jPaymentTypeSubPanel);
        jPaymentTypeSubPanel.setLayout(jPaymentTypeSubPanelLayout);
        jPaymentTypeSubPanelLayout.setHorizontalGroup(
            jPaymentTypeSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPaymentTypeSubPanelLayout.setVerticalGroup(
            jPaymentTypeSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PaymentPanelLayout = new javax.swing.GroupLayout(PaymentPanel);
        PaymentPanel.setLayout(PaymentPanelLayout);
        PaymentPanelLayout.setHorizontalGroup(
            PaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(PaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPaymentTypeSubPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PaymentPanelLayout.createSequentialGroup()
                        .addComponent(lblPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(138, Short.MAX_VALUE))))
        );
        PaymentPanelLayout.setVerticalGroup(
            PaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPaymentType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPaymentType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPaymentTypeSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PaymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboPaymentTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPaymentTypeItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int indexSelected = this.cboPaymentType.getSelectedIndex();
            System.out.println(indexSelected);
            this.removePaymentPanels();
            switch (indexSelected) {
                case 0:
                    this.removePaymentPanels();
                    return;
                case 1:
                    this.removePaymentPanels();
                    this.currentSubPanel = new GPaymentCashPanel(this);
                    break;
                case 2:
                    this.currentSubPanel = new GPaymentCreditPanel(this);   
                    break;
                case 3:
                    this.removePaymentPanels();
                    this.currentSubPanel = new GPaymentCheckPanel(this);
                    break;
                default:
                    System.err.println("Error invalid idex returned");
            }
            this.currentSubPanel.setSize(338, 54);
            this.currentSubPanel.setAlignmentX(LEFT_ALIGNMENT);
            this.jPaymentTypeSubPanel.add(this.currentSubPanel);
            this.revalidate();
            this.repaint();

        }
    }//GEN-LAST:event_cboPaymentTypeItemStateChanged
    public void addFrameReference(JFrame jf) {
        this.PostFrame = (GPost) jf;
    }
    
    public void sendPaymentToFrame(Payment p){
        this.PostFrame.recievePaymentFromPanel(p);
        
    }
    public void removePaymentPanels() {
        this.jPaymentTypeSubPanel.removeAll();
        this.revalidate();
        this.repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PaymentPanel;
    private javax.swing.JComboBox cboPaymentType;
    private javax.swing.JPanel jPaymentTypeSubPanel;
    private javax.swing.JLabel lblPaymentType;
    // End of variables declaration//GEN-END:variables
    private GPost PostFrame;
    private JPanel currentSubPanel;

}
