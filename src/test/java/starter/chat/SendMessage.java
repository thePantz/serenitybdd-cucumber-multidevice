package starter.chat;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static starter.chat.ChatForm.*;

public class SendMessage implements Task {
    private String message;

    public SendMessage(String message) {
        this.message = message;
    }

    public static SendMessage withText(String message) {
        return Instrumented.instanceOf(SendMessage.class).withProperties(message);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(message).into(MESSAGE),
                Click.on(SEND)
        );
    }
}
