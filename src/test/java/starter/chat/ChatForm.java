package starter.chat;

import net.serenitybdd.screenplay.targets.Target;

public class ChatForm {
    public static final Target MESSAGE = Target.the("MessageField").locatedBy("#msg_content");
    public static final Target SEND = Target.the("MessageField").locatedBy("//a[contains(@class,'btn-chat-send')]");
}
