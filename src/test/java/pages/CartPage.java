package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;

    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage clickCheckoutOnCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        btn.click();
        return new CheckoutPage(driver);
    }
}
