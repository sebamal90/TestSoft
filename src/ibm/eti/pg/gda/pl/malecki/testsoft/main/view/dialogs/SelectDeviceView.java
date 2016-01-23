/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.BTDevicesTableModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.bluetooth.RemoteDevice;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Dell
 */
public class SelectDeviceView extends JDialog {

    private final JButton setDev;
    private final JTable jtable;
    private final JButton findtDev;
    
    public  SelectDeviceView(java.awt.Dialog parent, List<RemoteDevice> devices) {
        super(parent, true);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(450, 200));
        this.setSize(new Dimension(450, 200));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setResizable(false);
        this.setTitle(Config.getProperty("Properties.selection"));

        BTDevicesTableModel model = new BTDevicesTableModel(devices);
        jtable = new JTable(model);
        jtable.getColumnModel().setColumnMargin(10);
        jtable.getColumnModel().getColumn(0).setMaxWidth(30);
        JScrollPane jscroll = new JScrollPane(jtable);
        jscroll.setPreferredSize(new Dimension(440, 100));
        this.add(jscroll);

        setDev = new JButton(Config.getProperty("Properties.deviceSelect"));
        setDev.setPreferredSize(new Dimension(150, 30));
        this.add(setDev);

        findtDev = new JButton(Config.getProperty("Properties.deviceFindAgain"));
        findtDev.setPreferredSize(new Dimension(150, 30));
        this.add(findtDev);

        JButton cancel = new JButton(Config.getProperty("Properties.cancel"));
        cancel.setPreferredSize(new Dimension(100, 30));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.add(cancel);

        this.pack();
    }

    public void setSelectDeviceListener(ActionListener selectDeviceListener) {
        setDev.addActionListener(selectDeviceListener);
    }
    
    public void setFindDeviceListener(ActionListener findDeviceListener) {
        findtDev.addActionListener(findDeviceListener);
    }

    public JTable getDevicesTable() {
        return jtable;
    }
}
