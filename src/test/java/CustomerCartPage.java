import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step("Изменение адреса клиента на новый 'Groove street'")
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

    @Step("Изменения адреса клиента на исходный")
    public CustomerCartPage revertAddress(String oldAddress) {
        backupAddress.shouldBe(enabled);
        backupAddress.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        backupAddress.sendKeys(oldAddress);
        return this;
    }

}
