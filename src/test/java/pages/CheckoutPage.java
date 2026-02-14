package pages;

import dto.DeliveryAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;

    private final By firstName = By.cssSelector("input[name='firstName']:not([hidden]):not([aria-hidden='true'])");
    private final By lastName = By.cssSelector("input[name='lastName']:not([hidden]):not([aria-hidden='true'])");
    private final By zip = By.cssSelector("input[name='postalCode']:not([hidden]):not([aria-hidden='true'])");
    private final By payNowBtn = By.id("checkout-pay-button");
    private final By addressInput = By.cssSelector("input[name='address1']:not([hidden]):not([aria-hidden='true'])");
    private final By city = By.cssSelector("input[name='city']:not([hidden]):not([aria-hidden='true'])");
    private final By state = By.cssSelector("select[name='zone']:not([hidden]):not([aria-hidden='true'])");
    private final By shippingMethodSection = By.xpath(
            "//fieldset[@id='shipping_methods']//p[normalize-space()='International Shipping']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage fillInfo(String fn, String ln, DeliveryAddress address) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .urlContains("/checkouts/"));
        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(addressInput).sendKeys(address.address);
        driver.findElement(city).sendKeys(address.city);
        new Select(driver.findElement(state))
                .selectByVisibleText(address.state);
        driver.findElement(zip).sendKeys(address.zip);
        return this;
    }

    public boolean isShippingMethodSectionVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingMethodSection)).isDisplayed();
    }
}
