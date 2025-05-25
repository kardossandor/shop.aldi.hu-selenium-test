import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class SearchPage extends BasePage {

    private final String url = "https://shop.aldi.hu/aldi/kinalat/kereses/keny%C3%A9r?s=keny%C3%A9r";
    private final By cookieAcceptButtonLocator = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private final By headingLocator = By.xpath("//h1[contains(text(), 'Keresett szöveg: kenyér')]");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.err.println("Error opening the page: " + e.getMessage());
        }
        
    }
    public void acceptCookies() {
        WebElement cookieAcceptButton = elementGet(cookieAcceptButtonLocator);
        cookieAcceptButton.click();
    }
    public boolean isProperHeadingPresent() {
        WebElement heading = elementGet(headingLocator);
        return heading != null;
    }
}
