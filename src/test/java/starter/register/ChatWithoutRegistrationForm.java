package starter.register;

import net.serenitybdd.screenplay.targets.Target;

public class ChatWithoutRegistrationForm {
    public static final Target USERNAME = Target.the("Username field").locatedBy("#username");
    public static final Target MALE = Target.the("Male radio button").locatedBy(".checkmark-male");
    public static final Target FEMALE = Target.the("Female radio button").locatedBy(".checkmark-female");
    public static final Target START_CHAT_NOW = Target.the("Start Chat button").locatedBy(".start-chat-btn");
}
