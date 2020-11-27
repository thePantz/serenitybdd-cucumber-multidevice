package starter.config;

import net.serenitybdd.core.webdriver.driverproviders.AppiumDriverProvider;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import net.thucydides.core.webdriver.WebdriverManager;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Properties;

public class DeviceConfiguration {
    private final EnvironmentVariables environmentVariables;
    private final String GLOBAL_DEVICES = "devices.all.";

    private DeviceConfiguration(EnvironmentVariables environmentVariables) {

        this.environmentVariables = environmentVariables;
    }

    public static DeviceConfiguration from(EnvironmentVariables environmentVariables) {
        return new DeviceConfiguration(environmentVariables);
    }

    /**
     * Gets the configured webdriver for the specified role
     * For example, 'customer' will look up role.customer.device from serenity.conf
     *
     * @param role the desired role
     * @return a webdriver with capabilities as configured
     */
    public WebDriver getDriverForRole(String role) {
        String userDeviceKey = "roles." + role.toLowerCase() + ".device";
        String device = environmentVariables.getProperty(userDeviceKey);

        return getDriverForDevice(device);
    }

    /**
     * Gets the configured webdriver for the specified device
     * For example, 'pixel3' will look up devices.pixel3 from serenity.conf
     *
     * @param deviceName the name of the desired device
     * @return a webdriver with capabilities as configured
     */
    public WebDriver getDriverForDevice(String deviceName) {
        String deviceKey = "devices." + deviceName + ".";
        Map<String, String> deviceProperties = getDeviceProperties(deviceKey);

        WebdriverManager webDriverManager = ThucydidesWebDriverSupport.getWebdriverManager();
        for (String prop : deviceProperties.keySet()) {
            webDriverManager.withProperty(prop, deviceProperties.get(prop));
        }

        String driverName = deviceProperties.get("webdriver.remote.driver");
        return webDriverManager.getWebdriver(driverName);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Map<String, String> getDeviceProperties(String key) {
        Properties globalDeviceProps = environmentVariables.getPropertiesWithPrefix(GLOBAL_DEVICES);
        Properties deviceSpecificProps = environmentVariables.getPropertiesWithPrefix(key);

        deviceSpecificProps.putAll(globalDeviceProps);
        renameKeys(deviceSpecificProps, key);
        return (Map) deviceSpecificProps;
    }

    /**
     * Strips prefix from properties. e.g. 'devices.pixel3.browserstack' will become 'browserstack'
     *
     * @param props     device properties
     * @param deviceKey a key pointing to device specific props e.g. devices.pixel3
     */
    private void renameKeys(Properties props, String deviceKey) {

        for (String prop : props.stringPropertyNames()) {
            String value = props.getProperty(prop);
            props.remove(prop);
            prop = prop.replace(GLOBAL_DEVICES, "")
                    .replace(deviceKey, "");
            props.put(prop, value);
        }
    }
}
