import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class MainPage extends BasePage {

    private final By emailInputLocator = By.id("mat-input-0");
    private final By passwordInputLocator = By.id("mat-input-1");
    private final By cookieAcceptButtonLocator = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");
    private final By houseNumberInputLocator = By.id("houseNumber");
    
    private final By loginButtonLocator = By.xpath("//app-log-in");
    private final By submitLoginButtonLocator = By.xpath("//*[@id='mat-tab-content-0-0']/div/form/button");
    private final By postLoginButtonLocator = By.xpath("/html/body/app-root/app-header/div/div[4]/app-log-in/button");
    private final By logoutButtonLocator = By.xpath("//*[@id=\"mat-menu-panel-0\"]/div/button[5]");
    private final By addToCartButtonLocator = By.xpath("(//lib-add-to-cart-button//button[contains(text(), 'Kosárba')])[1]");
    private final By cartQuantityLocator = By.xpath("/html/body/app-root/app-header/div/div[4]/div/div/div");
    private final By addressChangeButtonLocator = By.xpath("//*[@id=\"scroll-container\"]/div[1]/div[1]/a");
    private final By showCartLocator = By.xpath("/html/body/app-root/app-header/div/div[4]/div");
    private final By removeFromCartButtonLocator = By.xpath("//lib-basket-item/div/div/div[2]/div[1]/div");
    private final By overlayLocator = By.xpath("//*[@id=\"side-basket-container\"]/div/app-basket-new-wrapper/div[1]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void acceptCookies() {
        WebElement cookieAcceptButton = elementGet(cookieAcceptButtonLocator);
        cookieAcceptButton.click();
    }

    public void openLogin() {
        click(loginButtonLocator);
    }

    public void loginWith(String email, String password) {
        type(emailInputLocator, email);
        type(passwordInputLocator, password);
        click(submitLoginButtonLocator);
    }

    public boolean isLoggedIn() {
        return isElementVisible(postLoginButtonLocator);
    }

    public void logout() {
        elementGet(postLoginButtonLocator).click();
        elementGet(logoutButtonLocator).click();
    }

    public boolean isLoginButtonVisible() {
        return isElementVisible(loginButtonLocator);
    }

    public void addProductToCart() {
        WebElement element = elementGet(addToCartButtonLocator);
        element.click();
    }
    public int getCartQuantity() {
        String quantityText = elementGet(cartQuantityLocator).getText();
        return Integer.parseInt(quantityText);
    }
    public void showCart() {
        click(showCartLocator);
    }
    public void removeFromCart() {
        elementGet(removeFromCartButtonLocator).click();
        elementGet(overlayLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
    }
    public void showAddressForm() {
        elementGet(addressChangeButtonLocator).click();
    }
    public String getHouseNumber() {
        WebElement houseNumberInput = elementGet(houseNumberInputLocator);
        return houseNumberInput.getAttribute("value");
    }
    public void closeAddressForm() {
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        elementGone(houseNumberInputLocator);
    }
}
