/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.cowboycoders.ant.ChannelId;

public class SearchANTDevices {
    private List<SearchANTDevice> searchDevices;
    private ANTDeviceResource resource;
    
    public SearchANTDevices() {
        init();
    };
    
    private void init() {
        resource = ANTDeviceResource.getInstance();
        searchDevices = new ArrayList<>();
    }
    
    public List<SearchANTDevice> getSearchDevices() {
        return searchDevices;
    }
    
    public void startSearchDevices(DeviceConfiguration device, CallbackListener listener) {
       SearchANTDevice searchDevice = new SearchANTDevice(device, listener);
       searchDevice.startSearch();
       searchDevices.add(searchDevice);
    }
    
    public void startSearchDevices(DeviceConfiguration device) {
       startSearchDevices(device, null);
    }
    
    public synchronized void stopSearchDevices(SearchANTDevice searchDevice) {
       searchDevice.stopSearch();
       searchDevices.remove(searchDevice);
    }
    
    public void stopSearchDevices(DeviceConfiguration device) {
        for (SearchANTDevice searchDevice : searchDevices) {
            if (searchDevice.getDevice().equals(device)) {
                stopSearchDevices(searchDevice);
            }
        }
    }
    
    public void stopSearch() {
        while (searchDevices.size() > 0) {
            SearchANTDevice searchDevice = searchDevices.get(0);
            stopSearchDevices(searchDevice);
        }
        resource.stopNode();
    }
    
    public static void main(String[] args) throws InterruptedException {
        new SearchANTDevices().test();       
    }
    
    private void test() throws InterruptedException {
        startSearchDevices(Utils.HRM);
        startSearchDevices(Utils.SPD_CAD);
        startSearchDevices(Utils.POWER);
        System.out.println("Scanning for 10 seconds");
        Thread.sleep(10000);    
        
        for (SearchANTDevice searchDevice : searchDevices) {
            System.out.println(searchDevice.getDevice().NAME);
            Set<ChannelId> devices = searchDevice.getDevicesFound();
            Iterator<ChannelId> iterator = devices.iterator();
            while(iterator.hasNext()) {
                ChannelId deviceId = iterator.next();
                System.out.println(" -> " + deviceId.getDeviceNumber());
            }
        }
        stopSearch(); 
    }

    public class CallbackListener {
        public CallbackListener() {
        }
        
        public void addFoundDevice(ChannelId deviceId) {
            System.out.println("FOUNDED -> " + deviceId.getDeviceNumber());
        }
    }
}
