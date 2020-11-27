package starter.search;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import starter.navigation.NavigationBar;

public class SearchByUser implements Task {
    private String userName;

    public SearchByUser(String userName) {

        this.userName = userName;
    }

    public static SearchByUser withName(String userName) {
        return Instrumented.instanceOf(SearchByUser.class).withProperties(userName);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(NavigationBar.SEARCH),
                Enter.theValue(userName).into(SearchForm.USERNAME),
                Click.on(SearchForm.SEARCH));
    }
}
