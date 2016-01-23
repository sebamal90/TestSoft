/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs;

import javax.swing.JTable;

/**
 *
 * @author Dell
 */
public class SelectDeviceModel {
    private JTable devicesTable;
    
    public void setDevicesTable(JTable devicesTable) {
        this.devicesTable = devicesTable;
    }
    
    private int getSelectedRow() {
        return devicesTable.getSelectedRow();
    }
    
    public String getSelectedDeviceName() {
        return (String) devicesTable.getValueAt(getSelectedRow(), 1);
    }
    
    public String getSelectedDeviceAddress() {
        return (String) devicesTable.getValueAt(getSelectedRow(), 2);
    }    
}
