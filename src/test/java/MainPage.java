import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement ordersLink = $(byXpath("//a[@href='#/orders']"));
    private final SelenideElement invoicesLink = $(byXpath("//a[@href='#/invoices']"));
    private final SelenideElement customersLink = $(byXpath("//a[@href='#/customers' and text()='Customers']"));

    public MainPage ordersLinkClick() {
        ordersLink.shouldBe(enabled);
        ordersLink.click();
        return this;
    }

    public MainPage invoicesLinkClick() {
        invoicesLink.shouldBe(enabled);
        invoicesLink.click();
        return this;
    }

    public MainPage customersLinkClick() {
        customersLink.shouldBe(enabled);
        customersLink.click();
        return this;
    }

}
