import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class InvoicesPage {
    private final SelenideElement dateGte = $(byXpath("//input[@name='date_gte']"));
    private final SelenideElement dateLte = $(byXpath("//input[@name='date_lte']"));
    private final SelenideElement expandButton = $(byXpath("//div[@aria-label=\"Expand\"]"));
    private final SelenideElement customer = $(byXpath("//div[contains(@class, 'MuiTypography-root MuiTypography-body')]"));//MuiTypography-root MuiTypography-body2 css-4prify
    private final SelenideElement addFilter = $(byXpath("//button[@aria-label='Add filter']"));
    private final SelenideElement chooseFilterType = $(byXpath("//li[@data-key='customer_id']"));
    private final SelenideElement sendCustomer = $(byXpath("//input[@role='combobox']"));
    private final SelenideElement changeAddressCheck = $(byXpath("//p[text()='Groove street']"));
    private final SelenideElement listBox = $(byXpath("//ul[@role=\"listbox\"]"));

    public InvoicesPage inputDateGte() {
        String formattedDate = "01012024".replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$3-$2-$1");
        dateGte.shouldBe(visible, enabled);
        dateGte.setValue("");
        executeJavaScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('change'));",
                dateGte,
                formattedDate
        );
        return this;
    }

    public InvoicesPage inputDateLte() {
        String formattedDate = "01082025".replaceAll("(\\d{2})(\\d{2})(\\d{4})", "$3-$2-$1");
        dateLte.shouldBe(visible, enabled);
        dateLte.setValue("");
        executeJavaScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('change'));",
                dateLte,
                formattedDate
        );
        return this;
    }

    public InvoicesPage clickExpandButton() {
        $(byXpath("//span[@role='progressbar']")).should(disappear);
        expandButton.shouldBe(enabled, visible);
        expandButton.click();
        return this;
    }

    public String[] customer() {
        String actualText = customer.getText();
        boolean containsText = actualText.contains("Korey Mohr");
        assertFalse("Клиент в первых заказах не Korey Mohr", containsText);
        String[] parts = actualText.split("\\s+", 3);
        return parts;
    }

    public InvoicesPage clickAddFilter() {
        addFilter.shouldBe(enabled);
        addFilter.click();
        return this;
    }

    public InvoicesPage clickChooseFilterType() {
        chooseFilterType.shouldBe(enabled);
        chooseFilterType.click();
        return this;
    }

    public InvoicesPage sendCustomer(String customerName) {
        sendCustomer.shouldBe(enabled);
        sendCustomer.setValue(customerName);
        sendCustomer.pressEnter();
        //sendCustomer.sendKeys(Keys.ENTER);
        return this;
    }

    public InvoicesPage chooseCustomerInFilterList(String customerName) {
        listBox.shouldBe(visible, enabled);
        SelenideElement customerInList = $(byXpath("//li[text()=\"" + customerName + "\"]"));
        customerInList.shouldBe(visible, enabled).click();
        return this;
    }

    public InvoicesPage changeAddressCheck() {
        changeAddressCheck.shouldHave(text("Groove street"));
        assertTrue(changeAddressCheck.isDisplayed());
        return this;
    }

    public InvoicesPage checkOldAddressRevert(String oldAddress) {
        SelenideElement check = $(byXpath("//p[text()='" + oldAddress + "']"));
        check.shouldHave(text(oldAddress));
        assertTrue(check.isDisplayed());
        return this;
    }

}
