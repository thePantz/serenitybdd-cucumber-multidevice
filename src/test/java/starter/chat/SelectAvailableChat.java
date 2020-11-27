package starter.chat;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class SelectAvailableChat implements Task {
    private String name;

    public SelectAvailableChat(String name) {
        this.name = name;
    }

    public static SelectAvailableChat withName(String name) {
        return Instrumented.instanceOf(SelectAvailableChat.class).withProperties(name);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(ChatList.ITEM.of(name)));
    }
}
