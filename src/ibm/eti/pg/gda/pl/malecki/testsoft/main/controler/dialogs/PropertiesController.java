/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.main.controler.dialogs;

import ibm.eti.pg.gda.pl.malecki.testsoft.config.Config;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.FindANTDevicesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.FindBTDevicesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.model.dialogs.PropertiesModel;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.FindANTDevicesView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.FindBTDevicesView;
import ibm.eti.pg.gda.pl.malecki.testsoft.main.view.dialogs.PropertiesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ComboBoxModel;

public class PropertiesController {
    private final PropertiesView propertiesView;
    private final PropertiesModel propertiesModel;
    
    public PropertiesController(PropertiesView propertiesView, PropertiesModel propertiesModel) {
        this.propertiesModel = propertiesModel;
        this.propertiesView= propertiesView;
        
        propertiesView.addItemChangeListener(new ItemChangeListener());
        propertiesView.addCancelButtonActionListener(new CancelButtonListener());
        propertiesView.addSaveButtonActionListener(new SaveButtonListener());
                
        propertiesView.setLanguageList(getLanguageList());
        propertiesView.setLanguage(getLanguageIdx());
        
        propertiesView.setProtocolList(getProtocolList());
        propertiesView.setProtocol(getProtocolIdx());
        
        propertiesView.addSelectANTDeviceActionListener(new SelectANTDeviceActionListener());
        propertiesView.addSelectBTDeviceActionListener(new SelectBTDeviceActionListener());
        setDeviceData(Config.DEVICE_PROTOCOL);
        
        propertiesView.setVisible(true);
    }

    public void setBTDevice(String selectedDeviceName, String selectedDeviceAddress) {
        propertiesView.setBTData(selectedDeviceName, selectedDeviceAddress);}

    private class SelectBTDeviceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startFindBTDevice();
        }
    }
    
    private class SelectANTDeviceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startFindANTDevice();
        }
    }
    
    public void startFindBTDevice() {
        new FindBTDevicesController(new FindBTDevicesView(propertiesView), new FindBTDevicesModel(), this);
    }
    
    public void startFindANTDevice() {
        new FindANTDevicesController(new FindANTDevicesView(propertiesView), new FindANTDevicesModel(), this);
    }
    
    private class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                setDeviceData((String) event.getItem());
            }
        }       
    }
    
    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            propertiesView.dispose();
        }
    };
    
    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            saveProperties();
            propertiesView.dispose();
        }
    };
    
    private void saveProperties() {
        java.util.Properties prop = new java.util.Properties();
        
        if (propertiesView.getProtocol().equals("ANT+")) {
            if (propertiesView.getHrDevice() != null) {
                prop.setProperty("hrDeviceID", propertiesView.getHrDevice());
            }
            
            if (propertiesView.getPowDevice() != null) {
                prop.setProperty("powerDeviceID", propertiesView.getPowDevice());
            }
            
            if (propertiesView.getSpCadDevice() != null) {
                prop.setProperty("spCadDeviceID", propertiesView.getSpCadDevice());
            }
        } else if (propertiesView.getProtocol().equals("Bluetooth 2.0")) {
            if (propertiesView.getDeviceAddress() != null) {
                prop.setProperty("deviceAddress", propertiesView.getDeviceAddress());
            }
           
            if (propertiesView.getDeviceName() != null) {
                prop.setProperty("deviceName", propertiesView.getDeviceName());
            }
            prop.setProperty("deviceType", "Polar Wear-Link");
        }
        
        prop.setProperty("deviceProtocol", propertiesView.getProtocol());
        setLanguage(prop);

        Config.saveConfig(prop);
    }
    
    private void setLanguage(java.util.Properties prop) {
        if (propertiesView.getLanguageList().getSelectedIndex() == 0) {
            prop.setProperty("languageID", "0");
            prop.setProperty("localeLanguage", "en");
            prop.setProperty("localeCountry", "US");
        } else if (propertiesView.getLanguageList().getSelectedIndex() == 1) {
            prop.setProperty("languageID", "1");
            prop.setProperty("localeLanguage", "pl");
            prop.setProperty("localeCountry", "PL");
        }
    }
    
    private ComboBoxModel<String> getLanguageList() {
        return new javax.swing.DefaultComboBoxModel<>(Config.getPropertiesList("Properties.languageList"));
    }
    
    private ComboBoxModel<String> getProtocolList() {
        return new javax.swing.DefaultComboBoxModel<>(Config.getPropertiesList("Properties.deviceProtocolList"));
    }
    
    private int getLanguageIdx() {
        int result = 0;

        if (Config.LANGUAGE_ID != null) {
            return Config.LANGUAGE_ID;
        }
        return result;
    }
        
    private int getProtocolIdx() {
        int result = 0;
        String[] protocolList = Config.getPropertiesList("Properties.deviceProtocolList");
        
        for (int i = 0; i < protocolList.length; i++) {
            if (protocolList[i].equals(Config.DEVICE_PROTOCOL)) {
                return i;
            }
        }
        return result;
    }
    
    private void setDeviceData(String protocol) {
        if(protocol != null) {
            if (protocol.equals("ANT+")) {
                propertiesView.setANTData("1777", "1442", "");
            } else if (protocol.equals("Bluetooth 2.0")) {
                propertiesView.setBTData(Config.DEVICE_NAME, Config.DEVICE_ADDRESS);
            }
        }
    }
    
    public PropertiesView getPropertiesView() {
        return propertiesView;
    }
}
