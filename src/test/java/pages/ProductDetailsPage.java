package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {
    private final WebDriver driver;

    private final By addToCartBtn = By.id("add");
    private final By checkoutLink = By.cssSelector("a.checkout");
    private final By cartCount = By.id("cart-target-desktop");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductDetailsPage addToCart() {
        driver.findElement(addToCartBtn).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                        .executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public void waitCartCountNotZero() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> {
            String txt = d.findElement(cartCount).getText().trim();
            return !txt.contains("(0)");
        });
    }

    public CartPage clickCheckoutOnProductDetailsPage() {
        waitCartCountNotZero();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutLink));
        btn.click();
        return new CartPage(driver);
    }
}
