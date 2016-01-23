/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant;

public class DeviceConfiguration implements Cloneable{
    public int CHANNEL_PERIOD;
    public int CHANNEL_FREQ;
    public boolean PAIRING_FLAG;
    public int TRANSMISSION_TYPE;
    public int DEVICE_TYPE;
    public String NAME;
    public int DEVICE_ID;
        
    public DeviceConfiguration(String NAME, int CHANNEL_FREQ, int DEVICE_TYPE) {
        this.CHANNEL_FREQ = CHANNEL_FREQ;
        this.DEVICE_TYPE = DEVICE_TYPE;
        this.NAME = NAME;
    }

    public DeviceConfiguration(String NAME, int CHANNEL_PERIOD, int CHANNEL_FREQ, boolean PAIRING_FLAG, 
            int TRANSMISSION_TYPE, int DEVICE_TYPE) {
        this.CHANNEL_PERIOD = CHANNEL_PERIOD;
        this.CHANNEL_FREQ = CHANNEL_FREQ;
        this.PAIRING_FLAG = PAIRING_FLAG;
        this.TRANSMISSION_TYPE = TRANSMISSION_TYPE;
        this.DEVICE_TYPE = DEVICE_TYPE;
        this.NAME = NAME;
    }
    
    public void setDeviceId(int id) {
        this.DEVICE_ID = id;
    }
    
    public void setName(String name) {
        this.NAME = name;
    }
}
