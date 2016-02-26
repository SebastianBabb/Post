/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postgui.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Tony
 */
public class GTimePanel extends javax.swing.JPanel {

    /**
     * Creates new form GPanelTime
     */
    public GTimePanel() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCurrentTime = new javax.swing.JTextArea();

        setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N

        txtCurrentTime.setEditable(false);
        txtCurrentTime.setColumns(25);
        txtCurrentTime.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        txtCurrentTime.setLineWrap(true);
        txtCurrentTime.setRows(2);
        txtCurrentTime.setWrapStyleWord(true);
        txtCurrentTime.setAutoscrolls(false);
        txtCurrentTime.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCurrentTime.setFocusable(false);
        txtCurrentTime.setOpaque(false);
        jScrollPane1.setViewportView(txtCurrentTime);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtCurrentTime;
    // End of variables declaration//GEN-END:variables

    private final Thread updateTime = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                curDT = tf.format(new Date());
                txtCurrentTime.setText(curDT);
                try {
                    Thread.sleep(999);
                } catch (InterruptedException ex) {
                    System.err.printf("Error In Time Update Thread\n", ex.getMessage());
                }

            }
        }
    });

    private final DateFormat tf = new SimpleDateFormat("EEE, MMM-dd-yyyy HH:mm:ss z");
    private String curDT;

    /**
     *
     */
    public void startTimer() {
        this.updateTime.start();
    }

    /**
     *
     */
    public void stopTimer() {
        this.updateTime.interrupt();
    }

}
