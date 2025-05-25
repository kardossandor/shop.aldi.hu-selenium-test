import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends BasePage {

    private final String url = "https://shop.aldi.hu/kinalat/pekaru";

    public CategoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get(url);
    }
}
