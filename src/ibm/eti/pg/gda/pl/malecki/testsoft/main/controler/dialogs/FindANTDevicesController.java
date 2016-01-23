/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.BTDeviceResource;
import ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant.ANTDeviceResource;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.FindANTDevicesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.FindBTDevicesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.SelectDeviceModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.FindANTDevicesView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.FindBTDevicesView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.SelectDeviceView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dell
 */
public class FindANTDevicesController {
    private FindANTDevicesView findDevicesView;
    private FindANTDevicesModel findDevicesModel;
    private PropertiesController propertiesController;
    
    public FindANTDevicesController(FindANTDevicesView findDevicesView, FindANTDevicesModel findDevicesModel, 
            PropertiesController propertiesController) {
        this.findDevicesModel = findDevicesModel;
        this.findDevicesView= findDevicesView;
        this.propertiesController = propertiesController;
        
        findDevicesView.setCancelListener(new CancelButtonListener());
        findDevicesView.setSelectListener(new SelectButtonListener());
        
        findDevicesView.setVisible(true);
    }
    
    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            findFinshed();
        }
    };
    
    private void findFinshed() {
        findDevicesView.dispose();
    }
    
    private class SelectButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: save selected devices
            findDevicesView.dispose();
        }
    }
    
}
