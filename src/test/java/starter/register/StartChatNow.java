package starter.register;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;

public class StartChatNow implements Task {
    private String gender;

    public StartChatNow(String gender) {

        this.gender = gender.toLowerCase();
    }

    public static StartChatNow withGender(String gender) {
        return Instrumented.instanceOf(StartChatNow.class).withProperties(gender);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        String name = actor.getName();
        actor.attemptsTo(
                Enter.theValue(name).into(ChatWithoutRegistrationForm.USERNAME),
                Check.whether(gender == "male")
                .andIfSo(Click.on(ChatWithoutRegistrationForm.MALE))
                .otherwise(Click.on(ChatWithoutRegistrationForm.FEMALE)),
                Click.on(ChatWithoutRegistrationForm.START_CHAT_NOW)
        );
    }
}
