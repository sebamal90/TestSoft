/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.controler;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.dialogs.PropertiesController;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.DashboardModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.PropertiesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.DashboardView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.PropertiesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class DashboardController {

    private DashboardView dashboardView;
    private DashboardModel dashboardModel;
    
    public DashboardController(DashboardView dashboardView, DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
        this.dashboardView= dashboardView;
        
        this.dashboardView.addMenuBarListener(new MenuBarListener());
        this.dashboardView.addWindowListener(new WindowListener());
    }
    
    class MenuBarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String action = event.getActionCommand();
            if (action.equals(Config.getProperty("Main.exit"))) {
                closeApp();
            } else if (action.equals(Config.getProperty("Main.properties"))) {
                new PropertiesController(new PropertiesView(dashboardView, true), new PropertiesModel());
            } else if (action.equals(Config.getProperty("Main.new"))) {
                //new NewTest(main, true);
            }
        }
    };
    
    
    class WindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent evt) {
            closeApp();
        }
    }
    
    private void closeApp() {
        int confirm = JOptionPane.showOptionDialog(null,
                Config.getProperty("Main.exitConfirm"), null,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                Config.getPropertiesList("Main.exitConfirmOpt"), null);
        if (confirm == 0) {
            /*
            monitor.monitorUpdaterStop();
            if (menu.getDataTable() != null) {
                menu.getDataTable().stop();
            }
            if (menu.getGraph()  != null) {
                menu.getGraph().stop();
            }
            testResource.endTest();
            messageResource.stopRead();
            monitor.monitorUpdaterStop();
            */
            dashboardView.dispose();
            //System.exit(0);
        }
    }
    
}
