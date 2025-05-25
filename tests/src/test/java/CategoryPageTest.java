import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CategoryPageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private CategoryPage categoryPage;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 60);
        categoryPage = new CategoryPage(driver, wait);
    }

    @Test
    public void properTitle() {
        // Open the category page
        categoryPage.open();
        // Check the page title
        String expectedTitle = "ROKSH élelmiszer házhozszállítás";
        String actualTitle = driver.getTitle();
        // Assert that the title is correct
        assertEquals("The page title did not match the expected value",
             expectedTitle, actualTitle);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
