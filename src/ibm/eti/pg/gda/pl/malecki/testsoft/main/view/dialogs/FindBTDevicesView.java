/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

/**
 *
 * @author Dell
 */
public class FindBTDevicesView extends JDialog {

    private final JButton cancel;
    public FindBTDevicesView(java.awt.Dialog parent) {
        super(parent, true);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(450, 150));
        this.setSize(new Dimension(450, 150));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setResizable(false);
        this.setTitle(Config.getProperty("Properties.searching"));

        final JProgressBar aJProgressBar = new JProgressBar();
        aJProgressBar.setIndeterminate(true);
        aJProgressBar.setPreferredSize(new Dimension(420, 60));
        this.add(aJProgressBar);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        cancel = new JButton(Config.getProperty("Properties.cancel"));
        cancel.setPreferredSize(new Dimension(100, 30));
        this.add(cancel);
        this.pack();
    }
    
    public void setCancelListener(ActionListener listener) {
        cancel.addActionListener(listener);
    }
}
