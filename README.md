# SerenityBDD, Cucumber, and Multiple Devices

By Default, Serenity does not provide a way to assign a specific driver configuration to a specific actor when using Cucumber.
For example, when testing a chat application. One user is on Desktop, the other is on Mobile.
This project shows my approach to using multiple devices with SerenityBDD.

## Serenity.conf
I've added a new section in serenity.conf called devices.
This is a collection of driver configurations. Each entry can contain any driver configs you would normally place on the root of serenity.conf

```
devices {
    pixel3 {
        webdriver.remote.driver = appium
        browserstack {
            osVersion = "10.0"
            deviceName = "Google Pixel 3"
        }
        appium {
            platformName = Android
            browserName = chrome
        }
    }
    win10chrome {
        webdriver.remote.driver = chrome

        browserstack {
            os = "Windows"
            osVersion = "10"
            browserVersion = latest
            browserName = chrome
        }
    }
    all {
        #place any configs you want to apply to all devices here
    }
}
```

you may assign these devices to a particular role using the format:
 
roles.{roleName}.device = {deviceName}

e.g. `roles.sandra.device = pixel3`

## DeviceConfiguration

This is where the magic happens. This object reads the device config from serenity.conf and returns a WebDriver
```
WebDriver driverFromRole = DeviceConfiguration.from(environmentVariables).getDriverForRole("sandra");
WebDriver driverFromDeviceName = DeviceConfiguration.from(environmentVariables).getDriverForDevice("win10chrome");
```

## MultiDeviceCast
this cast will create a driver based on the actor/role name
```
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new MultiDeviceCast());
    }
```

I have a Parameter definition that allows me to easily convert my actor names to cast members

```
    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }
```

When this is called it will use the appropriately configured driver based on the name

## Putting it all together

My Feature file like like this:
```
  Scenario: Sandra messages Sergey
    Given Sergey is a non-registered user
    And Sandra is a non-registered user
    When she messages 'Sergey' with the text 'hello'
    Then Sergey sees a message from 'Sally' with the text 'hello'
```

The specified actor names are important as they determine the driver to be used for that step
these names can be whatever you've specified under serenity.conf under roles.{actorName}.device

## Notes

- If you're using remote devices be sure to place webdriver.remote.url on the root of serenity.conf
- If you're using browserstack be sure to place the user, key, and server configs on the root of serenity.conf
- I have not tested this with SauceLabs
- I hastily built this sample. The primary goal was to show how you could configure multiple devices but there are likely issues.



