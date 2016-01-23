/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

/**
 *
 * @author Dell
 */
public class FindANTDevicesView extends javax.swing.JDialog {

    /**
     * Creates new form FindANTDevicesView
     * @param parent
     */
    public FindANTDevicesView(java.awt.Dialog parent) {
        super(parent);
        initComponents();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setResizable(false);
        this.setTitle(Config.getProperty("Properties.searching"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        device1 = new javax.swing.JLabel();
        device2 = new javax.swing.JLabel();
        device3 = new javax.swing.JLabel();
        devices1 = new javax.swing.JComboBox<>();
        devices2 = new javax.swing.JComboBox<>();
        devices3 = new javax.swing.JComboBox<>();
        devices4 = new javax.swing.JComboBox<>();
        devices5 = new javax.swing.JComboBox<>();
        device4 = new javax.swing.JLabel();
        device5 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        setDev = new javax.swing.JButton();
        status1 = new javax.swing.JLabel();
        status2 = new javax.swing.JLabel();
        status3 = new javax.swing.JLabel();
        status4 = new javax.swing.JLabel();
        status5 = new javax.swing.JLabel();
        value1 = new javax.swing.JLabel();
        value2 = new javax.swing.JLabel();
        value3 = new javax.swing.JLabel();
        value4 = new javax.swing.JLabel();
        value5 = new javax.swing.JLabel();
        state1 = new javax.swing.JButton();
        state2 = new javax.swing.JButton();
        state3 = new javax.swing.JButton();
        state4 = new javax.swing.JButton();
        state5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        device1.setText("HRM");

        device2.setText("SPD + CAD");

        device3.setText("POWER");

        device4.setText("SPEED");

        device5.setText("CADENCE");

        cancel.setText("Cancel");

        setDev.setText("Select");

        status1.setText("status");

        status2.setText("status");

        status3.setText("status");

        status4.setText("status");

        status5.setText("status");

        value1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value1.setText("0");

        value2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value2.setText("0");

        value3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value3.setText("0");

        value4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value4.setText("0");

        value5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        value5.setText("0");

        state1.setText("jButton3");

        state2.setText("jButton4");

        state3.setText("jButton5");

        state4.setText("jButton6");

        state5.setText("jButton7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(device1)
                    .addComponent(device2)
                    .addComponent(device3)
                    .addComponent(device4)
                    .addComponent(device5))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(devices4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devices1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devices2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devices3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devices5, javax.swing.GroupLayout.Alignment.TRAILING, 0, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(state3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(state4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(state5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(state1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(state2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(status2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(status1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(value1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(value2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(value3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(value4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(value5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(setDev, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(121, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(device1)
                    .addComponent(devices1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status1)
                    .addComponent(value1)
                    .addComponent(state1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(device2)
                    .addComponent(devices2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status2)
                    .addComponent(value2)
                    .addComponent(state2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(device3)
                    .addComponent(devices3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status3)
                    .addComponent(value3)
                    .addComponent(state3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devices4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(device4)
                    .addComponent(status4)
                    .addComponent(value4)
                    .addComponent(state4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devices5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(device5)
                    .addComponent(status5)
                    .addComponent(value5)
                    .addComponent(state5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(setDev))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setCancelListener(ActionListener listener) {
        cancel.addActionListener(listener);
    }
    
    public void setSelectListener(ActionListener selectButtonListener) {
        setDev.addActionListener(selectButtonListener);
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FindANTDevicesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FindANTDevicesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FindANTDevicesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FindANTDevicesView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FindANTDevicesView dialog = new FindANTDevicesView(new javax.swing.JDialog());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JLabel device1;
    private javax.swing.JLabel device2;
    private javax.swing.JLabel device3;
    private javax.swing.JLabel device4;
    private javax.swing.JLabel device5;
    private javax.swing.JComboBox<String> devices1;
    private javax.swing.JComboBox<String> devices2;
    private javax.swing.JComboBox<String> devices3;
    private javax.swing.JComboBox<String> devices4;
    private javax.swing.JComboBox<String> devices5;
    private javax.swing.JButton setDev;
    private javax.swing.JButton state1;
    private javax.swing.JButton state2;
    private javax.swing.JButton state3;
    private javax.swing.JButton state4;
    private javax.swing.JButton state5;
    private javax.swing.JLabel status1;
    private javax.swing.JLabel status2;
    private javax.swing.JLabel status3;
    private javax.swing.JLabel status4;
    private javax.swing.JLabel status5;
    private javax.swing.JLabel value1;
    private javax.swing.JLabel value2;
    private javax.swing.JLabel value3;
    private javax.swing.JLabel value4;
    private javax.swing.JLabel value5;
    // End of variables declaration//GEN-END:variables

}
