import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final String username = ConfigLoader.getProperty("username");
    private final String password = ConfigLoader.getProperty("password");


    private final SelenideElement usernameField = $(byXpath("//input[@id=':r0:']"));
    private final SelenideElement passwordField = $(byXpath("//input[@id=':r2:']"));

    public LoginPage login() {
        usernameField.shouldBe(enabled);
        usernameField.setValue(username);
        passwordField.setValue(password);
        passwordField.pressEnter();
        return this;
    }

}
