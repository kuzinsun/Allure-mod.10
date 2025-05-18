import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit.TextReport;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

@RunWith(Parameterized.class)
public class TestMarmelab {
    @Rule
    public TextReport textReport = new TextReport();

    @Parameterized.Parameter
    public String browserName;

    private LoginPage loginPage;
    private MainPage mainPage;
    private OrdersPage ordersPage;
    private InvoicesPage invoicesPage;
    private CustomersPage customersPage;
    private CustomerCartPage customerCartPage;

    public String getCustomer;
    public String getCustomerName;
    public String getCustomerSurename;
    public String oldAddress;

    @Parameterized.Parameters(name = "{0}")
    public static Object[] browsers() {
        return new Object[]{"chrome", "firefox"};
    }

    @Before
    public void setUp() {
        String chromeDriverPath = ConfigLoader.getProperty("chrome_driver_path");
        String firefoxDriverPath = ConfigLoader.getProperty("firefox_driver_path");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--password-store=basic");
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);
            Configuration.browserCapabilities = options;
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            Configuration.browserCapabilities = options;
        }

        Configuration.browser = browserName;
        Configuration.startMaximized = true;
        Configuration.headless = false;
        Configuration.timeout = 10000;

        open(ConfigLoader.getProperty("base_url"));

        loginPage = new LoginPage();
        mainPage = new MainPage();
        ordersPage = new OrdersPage();
        invoicesPage = new InvoicesPage();
        customersPage = new CustomersPage();
        customerCartPage = new CustomerCartPage();
    }

    /*public static void pause(int seconds) { //only for debug
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }*/

    @Test
    public void testMarmelab() {
        loginPage.login();

        mainPage.ordersLinkClick();

        ordersPage.clickDeliveredTab();

        ordersPage.clickFirstThreeCheckboxes();

        ordersPage.checkCheckboxClickResult();

        mainPage.invoicesLinkClick();

        invoicesPage.inputDateGte();

        invoicesPage.inputDateLte();

        String[] parts = invoicesPage.customer();

        getCustomer = parts[0] + " " + parts[1];
        getCustomerName = parts[0];
        getCustomerSurename = parts[1];

        mainPage.customersLinkClick();

        customersPage.search(getCustomer);

        customersPage.clickCustomerCart(getCustomerName, getCustomerSurename);

        oldAddress = customerCartPage.getOldAddress();

        customerCartPage.enterAddress();

        customerCartPage.clickSaveButton();

        mainPage.invoicesLinkClick();

        invoicesPage.clickAddFilter();

        invoicesPage.clickChooseFilterType();

        invoicesPage.sendCustomer(getCustomer);

        invoicesPage.chooseCustomerInFilterList(getCustomer);

        invoicesPage.clickExpandButton();

        invoicesPage.changeAddressCheck();

        mainPage.customersLinkClick();

        customersPage.clickSecondCustomerCart(getCustomerName, getCustomerSurename);

        customerCartPage.revertAddress(oldAddress);

        customerCartPage.clickSaveButton();

        mainPage.invoicesLinkClick();

        invoicesPage.checkOldAddressRevert(oldAddress);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
