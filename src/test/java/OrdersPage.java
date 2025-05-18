import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertTrue;

public class OrdersPage {
    private final SelenideElement deliveredTab = $(byXpath("//span[text()='delivered']"));
    private final ElementsCollection checkboxList = $$(byXpath("//input[contains(@class, 'PrivateSwitchBase') and @type='checkbox']"));
    private final SelenideElement checkboxClickResult = $(byXpath("//h6[text()='3 items selected']"));

    public OrdersPage clickDeliveredTab() {
        deliveredTab.shouldBe(enabled).click();
        return this;
    }

    public OrdersPage clickFirstThreeCheckboxes() {
        checkboxList.shouldHave(size(26));
        for (int i = 1; i < 4 && i < checkboxList.size(); i++) {
            SelenideElement checkbox = checkboxList.get(i);
            checkbox.click();
        }
        return this;
    }

    public OrdersPage checkCheckboxClickResult() {
        checkboxClickResult.shouldHave(text("3 items selected"));
        boolean result = checkboxClickResult.isDisplayed();
        assertTrue("Выбрано 3 чекбокса, сообщение отображается", result);
        return this;
    }
}