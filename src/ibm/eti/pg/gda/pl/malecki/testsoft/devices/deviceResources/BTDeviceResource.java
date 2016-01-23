/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;

public class BTDeviceResource {

    private final Object inquiryCompletedEvent = new Object();
    private DiscoveryListener listener;
    private boolean inquiryStarted = false;
    protected List<RemoteDevice> devices = new ArrayList<>();
    private static BTDeviceResource instance;
    
    public static BTDeviceResource getInstance() {
        if (instance == null) {
             instance = new BTDeviceResource();
        }
        return instance;
    }
    
    public List<RemoteDevice> getDevices() {
        return devices;
    }

    public void findDevices() {
        devices = new ArrayList<RemoteDevice>();

        listener = new DiscoveryListener() {
            @Override
            public void deviceDiscovered(RemoteDevice btDevice,
                                         DeviceClass cod) {
                System.out.println("Device " + btDevice
                        .getBluetoothAddress() + " found");
                devices.add(btDevice);
                try {
                    System.out.println("     name " + btDevice
                            .getFriendlyName(false));
                } catch (IOException cantGetDeviceName) {
                    System.out.println("     device name don't available");
                }
            }

            @Override
            public void inquiryCompleted(int discType) {
                inquiryStarted = false;
                synchronized (inquiryCompletedEvent) {
                    inquiryCompletedEvent.notifyAll();
                }
                if (discType == DiscoveryListener.INQUIRY_COMPLETED) {
                    System.out.println(devices.size() +  " device(s) found");
                    System.out.println("Device Inquiry completed!");
                } else if (discType == DiscoveryListener.INQUIRY_ERROR) {
                    System.err.println("Error occured. Device Inquiry stopped!");
                    // TODO: Add window for error occure (Check if BT is active)
                }
            }

            @Override
            public void serviceSearchCompleted(int transID, int respCode) {
                //not used
            }

            @Override
            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                //not used
            }
        };

        synchronized (inquiryCompletedEvent) {
            try {
                boolean started = LocalDevice.getLocalDevice()
                        .getDiscoveryAgent()
                        .startInquiry(DiscoveryAgent.GIAC, listener);
                if (started) {
                    System.out.println("Wait for device inquiry to complete...");
                    inquiryStarted = true;
                    inquiryCompletedEvent.wait();
                }
            } catch (BluetoothStateException ex) {
                Logger.getLogger(BTDeviceResource.class.getName())
                        .log(Level.SEVERE, null, ex);
                // TODO: Add window for "javax.bluetooth.BluetoothStateException: BluetoothStack not detected"
            } catch (InterruptedException ex) {
                        Logger.getLogger(BTDeviceResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopFindDevices() {
        new Thread(){
             @Override
            public void run() {
                try {
                    LocalDevice.getLocalDevice().getDiscoveryAgent().cancelInquiry(listener);
                } catch (BluetoothStateException ex) {
                    System.out.println("Can't stop device inquiry.");
                }
            }
        }.start();
        inquiryStarted = false;
        synchronized (inquiryCompletedEvent) {
            inquiryCompletedEvent.notifyAll();
        }
        System.out.println("Device inquiry canceled.");
    }

    public boolean isInquryStarted() {
        return inquiryStarted;
    }

    public void connectByFriendlyName(String deviceName,
                                      String deviceType) {
        ConnectByFriendlyName cbfn =
                new ConnectByFriendlyName(deviceName, deviceType);
        cbfn.setName("Connection Thread");
        cbfn.start();
    }

    public void connect(String deviceAddress, String deviceType) {
        System.out.println("Trying connect with" + deviceAddress);
        Connect connect = new Connect(deviceAddress, deviceType);
        connect.setName("Connection Thread");
        connect.start();
    }

    private class ConnectByFriendlyName extends Thread {
        private String deviceName;
        private String deviceType;

        public ConnectByFriendlyName(String aDeviceName,
                                     String aDeviceType) {
            super();
            this.deviceName = aDeviceName;
            this.deviceType = aDeviceType;

        }

        @Override
        public void run() {
            StreamConnection connection = connectByName(deviceName);
            if (connection == null) {
                connectedFail();
            } else {
                connected(connection, deviceType);
            }
        }
    }

    private class Connect extends Thread {
        private String deviceAddress;
        private String deviceType;

        public Connect() {
            super();
        }

        public Connect(String aDeviceAddress, String aDeviceType) {
            super();
            this.deviceAddress = aDeviceAddress;
            this.deviceType = aDeviceType;
        }

        @Override
        public void run() {
            StreamConnection connection = connect(deviceAddress);
            if (connection == null) {
                connectedFail();
            } else {
                connected(connection, deviceType);
            }
        }
    }
    
    public StreamConnection connectByName(String deviceName) {
        StreamConnection results = null;
        if (devices == null) {
            findDevices();
        }
        for (RemoteDevice device : devices) {
            String name;
            try {
                name = device.getFriendlyName(false);
            } catch (Exception e) {
                name = device.getBluetoothAddress();
            }

            if (name.equals(deviceName)) {
                System.out.println("Trying connect witch "
                        + device.getBluetoothAddress() + "\n");
                results = connect(device.getBluetoothAddress());
            }
        }
        System.out.println("Device " + deviceName + " not found");
        return results;
    }

    public StreamConnection connect(String deviceAddress) {
        //Polar iWL add: 0022D000F0A7

        StreamConnection connection = null;
        try {
            System.out.println("Started");
            connection = (StreamConnection) Connector
                    .open("btspp://" + deviceAddress
                    + ":1;authenticate=false;encrypt=false;master=true");
        } catch (Exception e) {
            System.out.println("Błąd połączenia z urządzeniem");

        }

        return connection;
    }
    

    private void connected(StreamConnection connection, String deviceType) {
        //main.getMessageResource().startRead(connection, deviceType);
        //main.connectionEstabilished();
    }

    private void connectedFail() {
        //main.connectionFailure();
    }
}
