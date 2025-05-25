import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

public class CookiePage extends BasePage {

    private final String url = "https://shop.aldi.hu/cookiek";

    private final By headingLocator = By.xpath("//h2[contains(text(), 'Cookie tájékoztató')]");
    private final By cookieAcceptButtonLocator = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private final By homepageLinkLocator = By.xpath("//*[@id='scroll-container']/ul/li/a");

    public CookiePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.err.println("Failed to open the Cookie page: " + e.getMessage());
        }
    }

    public boolean isProperHeadingPresent() {
        WebElement heading = elementGet(headingLocator);
        return heading != null;
    }

    public void acceptCookies() {
        WebElement cookieAcceptButton = elementGet(cookieAcceptButtonLocator);
        cookieAcceptButton.click();
    }

    public boolean isCookiePopupGone() {
        return elementGone(cookieAcceptButtonLocator);
    }

    public boolean hoverChangesColor() {
        WebElement element = elementGet(homepageLinkLocator);
        String colorBefore = getColorOfElement(homepageLinkLocator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        String colorAfter = getColorOfElement(homepageLinkLocator);
        return !colorBefore.equals(colorAfter);
    }

}
