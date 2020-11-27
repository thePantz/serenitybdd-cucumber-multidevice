package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.chat.ChatForm;
import starter.chat.SelectAvailableChat;
import starter.chat.SendMessage;
import starter.config.MultiDeviceCast;
import starter.navigation.NavigateTo;
import starter.register.StartChatNow;
import starter.search.SearchByUser;

public class ChatStepDefinitions {
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new MultiDeviceCast());
    }

    @Given("{actor} is a non-registered user")
    public void actorIsANonRegisteredUser(Actor actor) {
        String gender = actor.getName().toLowerCase() == "sergey" ? "male" : "female"; // this is bad, but good enough for a demo
        actor.attemptsTo(
                NavigateTo.theChatHomePage(),
                StartChatNow.withGender(gender)
        );
    }

    @When("{actor} messages {string} with the text {string}")
    public void sallyMessagesSergeyWithTheTextHello(Actor actor, String recipient, String message) {
        actor.attemptsTo(
                SearchByUser.withName(recipient),
                SelectAvailableChat.withName(recipient),
                SendMessage.withText(message)
        );
    }

    @Then("{actor} sees a message from {string} with the text {string}")
    public void actorSeesAMessageFromSomeone(Actor actor, String sender, String message) {
        actor.attemptsTo(
                SearchByUser.withName(sender),
                SelectAvailableChat.withName(sender)
        );
    }

}
