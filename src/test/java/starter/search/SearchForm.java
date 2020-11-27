package starter.search;

import net.serenitybdd.screenplay.targets.Target;

public class SearchForm {
    public static final Target USERNAME = Target.the("Username field").locatedBy("#username");
    public static final Target SEARCH = Target.the("Search button").locatedBy("//input[@type='submit']");
}
