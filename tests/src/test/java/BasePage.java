import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected WebElement elementGet(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected boolean elementGone(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    protected boolean isElementVisible(By locator) {
        try {
            return elementGet(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    protected void click(By locator) {
        WebElement element = elementGet(locator);
        element.click();
    }
    protected void type(By locator, String text) {
        WebElement element = elementGet(locator);
        element.clear();
        element.sendKeys(text);
    }
    protected void waitForTitleToContain(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }
    public String getColorOfElement(By locator) {
        WebElement element = elementGet(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(
            "return window.getComputedStyle(arguments[0]).getPropertyValue('color');",
            element
        );
    }

}
