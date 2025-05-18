import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CustomersPage {
    private final SelenideElement searchField = $(byXpath("//input[@placeholder='Search']"));
    //private final By customerCart = By.xpath("//div[contains(@class, 'MuiTypography-root MuiTypography-body2')]");

    public CustomersPage search(String customer) {
        searchField.shouldBe(enabled);
        searchField.sendKeys(customer);
        searchField.pressEnter();
        return this;
    }

    public CustomersPage clickCustomerCart(String getCustomerName, String getCustomerSurename) {
        $(byXpath("//span[@role='progressbar']")).should(disappear);
        SelenideElement customerCart = $(byXpath("//div[text()=\"" + getCustomerName + "\" and text()=\"" + getCustomerSurename + "\"]"));
        customerCart.shouldBe(enabled, visible);
        customerCart.click();
        return this;
    }

    public CustomersPage clickSecondCustomerCart(String getCustomerName, String getCustomerSurename) {
        $(byXpath("//span[@role='progressbar']")).should(disappear);
        SelenideElement secondCustomerCart = $(byXpath("//div[text()=\"" + getCustomerName + "\" and text()=\"" + getCustomerSurename + "\"]"));
        secondCustomerCart.shouldBe(enabled, visible);
        secondCustomerCart.click();
        return this;
    }

}
