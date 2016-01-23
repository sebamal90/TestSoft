/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.SelectDeviceModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.SelectDeviceView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectDeviceController {

    private final SelectDeviceModel selectDeviceModel;
    private final SelectDeviceView selectDeviceView;
    private final PropertiesController propertiesController;
    
    public SelectDeviceController(SelectDeviceView selectDeviceView, SelectDeviceModel selectDeviceModel, PropertiesController propertiesController) {
        this.selectDeviceModel = selectDeviceModel;
        this.selectDeviceView= selectDeviceView;
        this.propertiesController = propertiesController;
                
        selectDeviceView.setSelectDeviceListener(new SelectDeviceListener());
        selectDeviceView.setFindDeviceListener(new FindDeviceListener());
        
        selectDeviceView.setVisible(true);
    }
    
    private class FindDeviceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                selectDeviceView.dispose();
                propertiesController.startFindBTDevice();
        }
    }

    private class SelectDeviceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                selectDeviceModel.setDevicesTable(selectDeviceView.getDevicesTable());
                propertiesController.setBTDevice(selectDeviceModel.getSelectedDeviceName(), selectDeviceModel.getSelectedDeviceAddress());
                selectDeviceView.dispose();
        }
    }
}
