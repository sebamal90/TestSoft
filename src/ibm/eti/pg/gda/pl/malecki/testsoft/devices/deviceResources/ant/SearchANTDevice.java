/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant;

import ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant.SearchANTDevices.CallbackListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.cowboycoders.ant.Channel;
import org.cowboycoders.ant.ChannelId;
import org.cowboycoders.ant.Node;
import org.cowboycoders.ant.events.BroadcastListener;
import org.cowboycoders.ant.messages.ChannelType;
import org.cowboycoders.ant.messages.SlaveChannelType;
import org.cowboycoders.ant.messages.data.BroadcastDataMessage;

public class SearchANTDevice {
    private final DeviceConfiguration device;
    private ANTDeviceResource resource;
    private Channel channel;
    private Node node;
    private Lock channelLock;
    private Set<ChannelId> devicesFound;
    private final CallbackListener listener;
    
    public SearchANTDevice(DeviceConfiguration device, CallbackListener listener) {
        this.device = device;
        this.listener = listener;
        init();
    };
    
    public DeviceConfiguration getDevice() {
        return device;
    }
    
    private void init() {
        resource = ANTDeviceResource.getInstance();
        node = resource.getNode();        
        channelLock = new ReentrantLock();
        devicesFound = new HashSet<>();
    }
    
    public void startSearch() {
        Channel searchChannel = node.getFreeChannel();
        SearchListener searchListener = new SearchListener(searchChannel,channelLock, devicesFound);
        setupSearchChannel(searchChannel,searchListener, device);      
        searchChannel.open();
        channel = searchChannel;
    }
    
    public void stopSearch() {
        // search listener may still be processing so we guard this
        if (channel != null) {
            System.out.println("Shutting down " + channel.getName() + "...");
            try {
                channelLock.lock();
                channel.close();
                channel.removeAllRxListeners();
                node.freeChannel(channel);
            } finally {
                channelLock.unlock();
            }
        }
    }
    
    public Set<ChannelId> getDevicesFound() {
        return devicesFound;
    }
    
    private class SearchListener implements BroadcastListener<BroadcastDataMessage> {
        private final Channel channel;
        private final Set<ChannelId> found;
        private final Lock lock;
        private ChannelId WILDCARD_CHANNEL_ID;

        /**
         * 
         * @param channel the search channel
         * @param list of channels to add to
         */
        public SearchListener(Channel channel, Lock channelLock, Set<ChannelId> found) {
                this.channel = channel;
                this.found = found;
                this.lock = channelLock;
        }
        
        public void setWildCardChannelId(ChannelId wildCardChannelId) {
            this.WILDCARD_CHANNEL_ID = wildCardChannelId;
        }

        @Override
        public void receiveMessage(BroadcastDataMessage message) {
            // don't block the messenger thread
            new Thread() {
                public void run() {
                    doWork();
                }
            }.start();
        }

        private void doWork() {
            try {
                lock.lock();

                ChannelId channelId = Utils.requestChannelId(channel);

                // don't add wildcards to found devices list
                if (channelId.equals(WILDCARD_CHANNEL_ID)) {
                    return;
                }

                // if already in set
                if(!found.add(channelId)) {
                    return;
                }
                
                if (listener != null) {
                    listener.addFoundDevice(channelId);
                }
                
                if (found.size() > 4) {
                    System.out.println("reached maximum of 4 devices (" + channel.getName() + ")");
                    return;
                }

                // close so we can reset it back to a wildcard
                channel.close();

                System.out.println("found a " + channel.getName() + " device: ");
                Utils.printChannelConfig(channel);

                channel.blacklist(found.toArray(new ChannelId[] {}));
                // set back to wildcard
                channel.setId(WILDCARD_CHANNEL_ID);
                //reopen
                channel.open();
            } finally {
                lock.unlock();
            }
        }
    }
    
    public void setupSearchChannel(Channel channel, SearchListener listener, DeviceConfiguration device) {
        // Arbitrary name : useful for identifying channel
        channel.setName("C:" + device.NAME + "_SEARCH");

        // choose slave or master type. Constructors exist to set two-way/one-way and shared/non-shared variants.
        ChannelType channelType = new SlaveChannelType();

        // use ant network key "N:ANT+" 
        channel.assign(NetworkKeys.ANT_SPORT, channelType);


        // registers an instance of our callback with the channel
        channel.registerRxListener(listener, BroadcastDataMessage.class);

        /******* start device specific configuration ******/
        ChannelId wildCardChannelId = ChannelId.Builder.newInstance()
			.setDeviceNumber(ChannelId.WILDCARD)
			.setDeviceType(device.DEVICE_TYPE)
			.build();
        listener.setWildCardChannelId(wildCardChannelId);
        channel.setId(wildCardChannelId);

        channel.setFrequency(device.CHANNEL_FREQ);
        /******* end device specific configuration ******/
        // timeout before we give up looking for device
        channel.setSearchTimeout(Channel.SEARCH_TIMEOUT_NEVER);
    }
    
    public static void main(String[] args) throws InterruptedException {
        new SearchANTDevice(Utils.ALL, null).test();;
    }
    
    public void test() throws InterruptedException {
        startSearch();
        System.out.println("Scanning for 10 seconds");
        // search for 10 seconds
        Thread.sleep(10000);
        stopSearch();
        // cleans up : gives up control of usb device etc.
        resource.stopNode();
    }
}
