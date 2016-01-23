/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.DashboardController;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.DashboardModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.DashboardView;

/**
 *
 * @author Dell
 */
public class TestSoft {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Config.saveDefaultConfig();
        Config.loadConfig();
        
        DashboardView dashboardView = new DashboardView();
        DashboardModel dashboardModel = new DashboardModel();
        
        DashboardController dashboardController = new DashboardController(dashboardView, dashboardModel);
        dashboardView.setVisible(true);
    }
    
}
