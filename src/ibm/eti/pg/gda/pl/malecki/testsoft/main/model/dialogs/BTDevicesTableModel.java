/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs;

import java.io.IOException;
import java.util.List;
import javax.bluetooth.RemoteDevice;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BTDevicesTableModel extends AbstractTableModel implements TableModel {
    private List<RemoteDevice> devices;

    public BTDevicesTableModel(List<RemoteDevice> aDevices) {
        super();
        devices = aDevices;
    }

    @Override
    public int getRowCount() {
        return devices.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            RemoteDevice device = devices.get(rowIndex);
        Object value = null;
        switch(columnIndex) {
            case 0:
                value = rowIndex + 1;
                break;
            case 1:
                try {
                    value = device.getFriendlyName(false);
                } catch (IOException ex) {
                    value = "Not available";
                }
                break;
            case 2:
                value = device.getBluetoothAddress();
            default:
                break;
        }
        return value;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String[] columns = new String[]{"Lp", "Device name", "Device address"};
        return columns[columnIndex];
    }
}