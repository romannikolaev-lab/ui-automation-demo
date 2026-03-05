package pages;

import dto.DeliveryAddress;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private final WebDriver driver;

    private final By firstName = By.cssSelector("input[name='firstName']:not([hidden]):not([aria-hidden='true'])");
    private final By lastName = By.cssSelector("input[name='lastName']:not([hidden]):not([aria-hidden='true'])");
    private final By zip = By.cssSelector("input[name='postalCode']:not([hidden]):not([aria-hidden='true'])");
    private final By addressInput = By.cssSelector("input[name='address1']:not([hidden]):not([aria-hidden='true'])");
    private final By city = By.cssSelector("input[name='city']:not([hidden]):not([aria-hidden='true'])");
    private final By state = By.cssSelector("select[name='zone']:not([hidden]):not([aria-hidden='true'])");
    private final By shippingMethodSection = By.xpath(
            "//fieldset[@id='shipping_methods']//p[normalize-space()='International Shipping']");
    private final By cityError = By.id("error-for-TextField4");
    private final By zipError = By.id("error-for-TextField5");

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
        return !driver.findElements(shippingMethodSection).isEmpty() && driver.findElement(shippingMethodSection)
                .isDisplayed();
    }

    public boolean waitForShippingMethodSectionVisible(Duration timeout) {
        try {
            return new WebDriverWait(driver, timeout).until(d -> isShippingMethodSectionVisible());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public CheckoutPage clearCity() {
        WebElement cityEl = driver.findElement(city);
        cityEl.click();
        cityEl.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        cityEl.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public CheckoutPage clearZip() {
        WebElement zipEl = driver.findElement(zip);
        zipEl.click();
        zipEl.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        zipEl.sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public CheckoutPage triggerBlur() {
        new Actions(driver).sendKeys(Keys.TAB).perform();
        return this;
    }

    public boolean isCityErrorVisible() {
        return !driver.findElements(cityError).isEmpty() && driver.findElement(cityError).isDisplayed();
    }

    public boolean isZipErrorVisible() {
        return !driver.findElements(zipError).isEmpty() && driver.findElement(zipError).isDisplayed();
    }
}
