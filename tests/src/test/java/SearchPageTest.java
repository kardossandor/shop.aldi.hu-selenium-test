import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class SearchPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SearchPage searchPage;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 60);
        searchPage = new SearchPage(driver, wait);
    }

    @Test
    public void searchHeadingShouldBeVisibleAfterOpeningSearchPage() {
        // Navigate to the search page
        searchPage.open();
        // Accept cookie consent
        searchPage.acceptCookies();
        // Verify that the expected heading is present
        assertTrue("Expected heading 'Keresés' not found on search results page", searchPage.isProperHeadingPresent());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
