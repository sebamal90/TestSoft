/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.BTDeviceResource;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.FindBTDevicesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.SelectDeviceModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.FindBTDevicesView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.SelectDeviceView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindBTDevicesController {
    private FindBTDevicesView findDevicesView;
    private FindBTDevicesModel findDevicesModel;
    private PropertiesController propertiesController;
    private final DeviceSearchingListener devList;
    
    public FindBTDevicesController(FindBTDevicesView findDevicesView, FindBTDevicesModel findDevicesModel, 
            PropertiesController propertiesController) {
        this.findDevicesModel = findDevicesModel;
        this.findDevicesView= findDevicesView;
        this.propertiesController = propertiesController;
        
        findDevicesView.setCancelListener(new CancelButtonListener());
        
        devList = new DeviceSearchingListener();
        devList.setName("DeviceSearchingLitener Thread");
        devList.start();
        
        findDevicesView.setVisible(true);
    }
    
    protected class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            findFinshed();
        }
    };
    
    protected void findFinshed() {
        devList.stopFindDevices();
        devList.stopListen();
    }
    
    private class DeviceSearchingListener extends Thread {
        private boolean work = true;
        private BTDeviceResource deviceResource = BTDeviceResource.getInstance();
                
        @Override
        public void run() {
            deviceResource.findDevices();
            findDevicesView.dispose();
            if (work) {
                new SelectDeviceController(
                        new SelectDeviceView(propertiesController.getPropertiesView(), deviceResource.getDevices()), 
                        new SelectDeviceModel(), 
                        propertiesController
                );
            }
        }

        public void stopListen() {
            work = false;
        }
        
        public void stopFindDevices() {
            deviceResource.stopFindDevices();
        }
        
        public BTDeviceResource getDeviceResource(){
            return deviceResource;
        }
    }
}
