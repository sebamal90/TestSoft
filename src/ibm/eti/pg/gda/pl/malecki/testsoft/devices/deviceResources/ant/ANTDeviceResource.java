/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant;

import java.util.logging.ConsoleHandler;
import org.cowboycoders.ant.Node;
import org.cowboycoders.ant.interfaces.AntTransceiver;
import static org.cowboycoders.ant.interfaces.AntTransceiver.LOG_LEVEL;

/**
 *
 * @author Dell
 */
public class ANTDeviceResource {
       
    private static ANTDeviceResource instance;
    private Node node;
    
     public synchronized static ANTDeviceResource getInstance() {
        if (instance == null) {
             instance = new ANTDeviceResource();
        }
        return instance;
    }
    private SearchANTDevices s;
     
    private ANTDeviceResource() {
        setupLogging();

        AntTransceiver antchip = new AntTransceiver(0);
        node = new Node(antchip);
        node.start();
        node.reset();
    }
    
    public void stopNode() {
        node.stop();
        node = null;
    }
    
    public Node getNode() {
        if (node == null) {
            AntTransceiver antchip = new AntTransceiver(0);
            node = new Node(antchip);
            node.start();
            node.reset();
        }
        return node;
    }

    public void findDevices() {
        s = new SearchANTDevices();
        //default devices
        s.startSearchDevices(Utils.HRM);
        s.startSearchDevices(Utils.SPD_CAD);
        s.startSearchDevices(Utils.POWER);
        s.startSearchDevices(Utils.SPEED);
        s.startSearchDevices(Utils.CADENCE);
    }

    public void stopFindDevices() {
        s.stopSearch();
    }
    
    private void setupLogging() {
        // set logging level
        AntTransceiver.LOGGER.setLevel(LOG_LEVEL);
        ConsoleHandler handler = new ConsoleHandler();
        // PUBLISH this level
        handler.setLevel(LOG_LEVEL);
        AntTransceiver.LOGGER.addHandler(handler);
        // Don't duplicate messages by sending to parent handler as well
        AntTransceiver.LOGGER.setUseParentHandlers(false);
    }
}
