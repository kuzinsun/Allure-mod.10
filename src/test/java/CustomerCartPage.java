import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CustomerCartPage {
    private final SelenideElement address = $(byXpath("//textarea[@name='address']"));
    private final SelenideElement saveButton = $(byXpath("//button[@aria-label='Save']"));
    private final SelenideElement backupAddress = $(byXpath("//textarea[@name='address']"));

    /*public CustomerCartPage clickAddress() {
        driver.findElement(address).click();
        return this;
    }*/

    public String getOldAddress() {
        address.shouldBe(visible);
        return address.getText();
    }

    public CustomerCartPage enterAddress() {
        address.shouldBe(visible);
        address.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        address.sendKeys("Groove street");
        return this;
    }

    public CustomerCartPage clickSaveButton() {
        saveButton.shouldBe(enabled);
        saveButton.click();
        return this;
    }

    public CustomerCartPage revertAddress(String oldAddress) {
        backupAddress.shouldBe(enabled);
        backupAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        backupAddress.sendKeys(oldAddress);
        return this;
    }

}
