package ibm.eti.pg.gda.pl.malecki.testsoft.devices.deviceResources.ant;

import java.util.concurrent.TimeUnit;

import org.cowboycoders.ant.Channel;
import org.cowboycoders.ant.ChannelId;
import org.cowboycoders.ant.events.MessageCondition;
import org.cowboycoders.ant.events.MessageConditionFactory;
import org.cowboycoders.ant.messages.commands.ChannelRequestMessage;
import org.cowboycoders.ant.messages.commands.ChannelRequestMessage.Request;
import org.cowboycoders.ant.messages.responses.ChannelIdResponse;

/**
 * Contains some example utility functions
 * @author will
 *
 */
public class Utils {
    
    private static final int ANT_SPORT_FREQ = 57; // 0x39
    // ANT+sport period
    private static final int ANT_SPORT_HR_PERIOD = 8070;
    private static final int ANT_SPORT_POWER_PERIOD = 8182;
    private static final int ANT_SPORT_SPEED_PERIOD = 8118;
    private static final int ANT_SPORT_CADENCE_PERIOD = 8102;
    private static final int ANT_SPORT_SandC_PERIOD = 8086;
    private static final int ANT_SPORT_CONTROL_PERIOD = 8192;
    private static final int ANT_SPORT_KICKR_PERIOD = 2048;
    private static final int ANT_SPORT_MOXY_PERIOD = 8192;
    private static final int ANT_SPORT_TACX_VORTEX_PERIOD = 8192;
    private static final int ANT_SPORT_FITNESS_EQUIPMENT_PERIOD = 8192;
    private static final int ANT_FAST_QUARQ_PERIOD = (8182/16);
    private static final int ANT_QUARQ_PERIOD = (8182*4);
    // ANT+sport type
    private static final int ANT_SPORT_HR_TYPE = 120; //0x78
    private static final int ANT_SPORT_POWER_TYPE = 11;
    private static final int ANT_SPORT_SPEED_TYPE = 123; //0x7B
    private static final int ANT_SPORT_CADENCE_TYPE = 122; //0x7A
    private static final int ANT_SPORT_SandC_TYPE = 121; //0x79
    private static final int ANT_SPORT_MOXY_TYPE = 31; //0x1F
    private static final int ANT_SPORT_CONTROL_TYPE = 16; //0x10
    private static final int ANT_SPORT_TACX_VORTEX_TYPE = 61;
    private static final int ANT_SPORT_FITNESS_EQUIPMENT_TYPE = 17; //0x11
    private static final int ANT_FAST_QUARQ_TYPE = 96; //0x60
    private static final int ANT_QUARQ_TYPE = 96; //0x60 
   
    public static DeviceConfiguration HRM = new DeviceConfiguration("HRM", ANT_SPORT_HR_PERIOD, ANT_SPORT_FREQ, false, 1, ANT_SPORT_HR_TYPE);
    public static DeviceConfiguration SPD_CAD = new DeviceConfiguration("SPD_CAD", ANT_SPORT_SPEED_PERIOD, ANT_SPORT_FREQ, false, 1, ANT_SPORT_SandC_TYPE);
    public static DeviceConfiguration POWER = new DeviceConfiguration("POWER", ANT_SPORT_POWER_PERIOD,ANT_SPORT_FREQ, false, 5, ANT_SPORT_POWER_TYPE);
    public static DeviceConfiguration SPEED = new DeviceConfiguration("SPEED", ANT_SPORT_SPEED_PERIOD,ANT_SPORT_FREQ, false, 1, ANT_SPORT_SPEED_TYPE);
    public static DeviceConfiguration CADENCE = new DeviceConfiguration("CADENCE", ANT_SPORT_CADENCE_PERIOD,ANT_SPORT_FREQ, false, 1, ANT_SPORT_CADENCE_TYPE);
    public static DeviceConfiguration ALL = new DeviceConfiguration("ALL_ANT_SPORT", ANT_SPORT_FREQ, 0);
    
    public static void printChannelConfig(Channel channel) {
        // build request
        ChannelRequestMessage msg = new  ChannelRequestMessage(channel.getNumber(),Request.CHANNEL_ID);

        // response should be an instance of ChannelIdResponse
        MessageCondition condition = MessageConditionFactory.newInstanceOfCondition(ChannelIdResponse.class);

        try {
            // send request (blocks until reply received or timeout expired)
            ChannelIdResponse response = (ChannelIdResponse) channel.sendAndWaitForMessage(
                            msg, condition, 5L, TimeUnit.SECONDS, null);
            System.out.println();
            System.out.println("Device configuration: ");
            System.out.println("deviceID: " + response.getDeviceNumber());
            System.out.println("deviceType: " + response.getDeviceType());
            System.out.println("transmissionType: " + response.getTransmissionType());
            System.out.println("pairing flag set: " + response.isPairingFlagSet());
            System.out.println();
        } catch (Exception e) {
            // not critical, so just print error
            e.printStackTrace();
        }
    }

    /**
     * Requests a channels current channelId
     * @param channel
     * @return
     */
    public static ChannelId requestChannelId(Channel channel) {
        // build request
        ChannelRequestMessage msg = new  ChannelRequestMessage(channel.getNumber(),Request.CHANNEL_ID);

        // response should be an instance of ChannelIdResponse
        MessageCondition condition = MessageConditionFactory.newInstanceOfCondition(ChannelIdResponse.class);

        try {
            // send request (blocks until reply received or timeout expired)
            ChannelIdResponse response = (ChannelIdResponse) channel.sendAndWaitForMessage(
                            msg, condition, 5L, TimeUnit.SECONDS, null);
            return response.getChannelId();
        } catch (Exception e) {
            // not critical, so just print error
            e.printStackTrace();
        }
        return null;
    }
}
