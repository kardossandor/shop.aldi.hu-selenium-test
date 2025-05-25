import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class CookiePageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private CookiePage cookiePage;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 60);
        cookiePage = new CookiePage(driver, wait);
    }

    @Test
    public void cookieHeadingShouldBeVisible() {
        // Open the cookie page
        cookiePage.open();
        // Accept the cookie banner
        cookiePage.acceptCookies();
        // Assert that the cookie popup is gone
        assertTrue("Cookie popup should disappear after accepting", cookiePage.isCookiePopupGone());
        // Assert that the heading is visible
        assertTrue("Expected heading 'Cookie tájékoztató' not found", cookiePage.isProperHeadingPresent());
        // Hover over the homepage link
        assertTrue("Hovering over the homepage link should change its color", cookiePage.hoverChangesColor());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
