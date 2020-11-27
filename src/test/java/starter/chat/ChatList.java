package starter.chat;

import net.serenitybdd.screenplay.targets.Target;

public class ChatList {
    public static final Target ITEM = Target.the("Chat Item").locatedBy("//h4[contains(text(),'{0}}')]");
}
