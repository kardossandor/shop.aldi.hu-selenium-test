import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import org.junit.*;

import java.net.URL;
import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class MainPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // Run in incognito mode
        options.addArguments("--incognito");

        // disable automation info 
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 60);
        this.mainPage = new MainPage(driver, wait);
    }

    @Test
    public void successfulLoginAndLogout() {
        driver.get("https://shop.aldi.hu");

        // Accept cookies popup
        mainPage.acceptCookies();

        // Log in
        String email = TestConfig.get("login.email");
        String password = TestConfig.get("login.password");
        mainPage.openLogin();
        mainPage.loginWith(email, password);
        assertTrue("Login was not successful", mainPage.isLoggedIn());

        // Add a product to the cart and verify
        mainPage.addProductToCart();
        assertTrue("Cart should not be empty after adding product", mainPage.getCartQuantity() > 0);
        mainPage.showCart();
        mainPage.removeFromCart();

        // Check house number
        mainPage.showAddressForm();
        String number = mainPage.getHouseNumber();
        assertTrue("House number should be 32", number.equals("32"));
        mainPage.closeAddressForm();

        // Log out and verify
        mainPage.logout();
        assertTrue("Login button should be visible after logout", mainPage.isLoginButtonVisible());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
