package starter.config;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;

public class MultiDeviceCast extends Cast {

    public MultiDeviceCast() { this(new Ability[0]); }
    public MultiDeviceCast(Ability[] abilities) { super(abilities);}

    public static Cast whereEveryoneCan(Ability... abilities) { return new MultiDeviceCast(abilities);}

    @Override
    public Actor actorNamed(String actorName, Ability... abilities) {
        Actor newActor = super.actorNamed(actorName, abilities);
        if (newActor.abilityTo(BrowseTheWeb.class) == null) {
            newActor.can(BrowseTheWeb.with(this. theDriverFor(actorName)));
        }

        return newActor;
    }

    private WebDriver theDriverFor(String actorName) {
        EnvironmentVariables environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
        return DeviceConfiguration.from(environmentVariables).getDriverForRole(actorName);
    }
}
