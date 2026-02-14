package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {
    private final WebDriver driver;

    private final By title = By.cssSelector("title");

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductDetailsPage openProduct(String productName) {
        By productLink = By.xpath("//a[.//h3[normalize-space()='" + productName + "']]");
        WebElement el = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(productLink));
        el.click();
        return new ProductDetailsPage(driver);
    }
}
