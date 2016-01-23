/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibm.eti.pg.gda.pl.malecki.testsoft.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 * @author SebaTab
 */
public final class Config {

    public static String DEVICE_PROTOCOL;
    public static String DEVICE_ADDRESS;
    public static String DEVICE_NAME;
    public static Locale LOCALE;
    public static Integer LANGUAGE_ID;
    private static final String CONFIG_DIR = "config.properties";
    private static ResourceBundle bundle;
    
    private final static String DEVICE_PROTOCOL_LABEL = "deviceProtocol";
    private final static String DEVICE_ADDRESS_LABEL = "deviceAddress";
    private final static String DEVICE_NAME_LABEL = "deviceName";
    private final static String LANGUAGE_ID_LABEL = "languageID";
    private final static String LOCALE_LANGUAGE_LABEL = "localeLanguage";
    private final static String LOCALE_COUNTRY_LABEL = "localeCountry";

    private Config() { }

    public static void loadConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(CONFIG_DIR);

            // load a properties file
            prop.load(input);

            DEVICE_PROTOCOL = prop.getProperty(DEVICE_PROTOCOL_LABEL);
            DEVICE_ADDRESS = prop.getProperty(DEVICE_ADDRESS_LABEL);
            DEVICE_NAME = prop.getProperty(DEVICE_NAME_LABEL);
            String langID = prop.getProperty(LANGUAGE_ID_LABEL);
            if (langID != null) {
                LANGUAGE_ID = Integer.valueOf(prop.getProperty(LANGUAGE_ID_LABEL));
            }
            if (LOCALE == null)  {
                LOCALE = new Locale(prop.getProperty(LOCALE_LANGUAGE_LABEL),
                                prop.getProperty(LOCALE_COUNTRY_LABEL));
            }

	} catch (IOException ex) {
            System.out.println("No config file. Start create file");
            createConfig();
	} finally {
            bundle = ResourceBundle.getBundle("ibm.eti.pg.gda.pl.malecki.testsoft.config.config", LOCALE);

            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	}
    }

    public static void saveDefaultConfig() {
        Properties prop = new Properties();
	OutputStream output = null;

	try {
            output = new FileOutputStream(CONFIG_DIR);
            
            prop.setProperty(DEVICE_PROTOCOL_LABEL, "ANT+");
            prop.setProperty("deviceAddress", "0022D000F0A7");
            prop.setProperty("deviceName", "Polar iWL");
            prop.setProperty("languageID", "1");
            prop.setProperty("localeLanguage", "pl");
            prop.setProperty("localeCountry", "PL");

            // save properties to project root folder
            prop.store(output, null);
	} catch (IOException io) {
            io.printStackTrace();
	} finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	}
    }

    public static void createConfig() {
        Properties prop = new Properties();
	OutputStream output = null;

	try {
            output = new FileOutputStream(CONFIG_DIR);

            prop.setProperty("localeLanguage", "en");
            prop.setProperty("localeCountry", "EN");
            prop.setProperty("languageID", "0");

            // save properties to project root folder
            prop.store(output, null);

            LOCALE = new Locale(prop.getProperty("localeLanguage"),
                                prop.getProperty("localeCountry"));
	} catch (IOException io) {
		io.printStackTrace();
	} finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	}
    }

    public static void saveConfig(Properties prop) {
        OutputStream output = null;

	try {

            output = new FileOutputStream("config.properties");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            loadConfig();
	}
    }
    
    public static String getProperty(String property) {
        return Config.bundle.getString(property);
    }
    
    public static String[] getPropertiesList(String property) {
        return Config.bundle.getString(property).split(",");
    }
}
