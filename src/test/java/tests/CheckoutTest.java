package tests;

import base.BaseTest;
import dto.DeliveryAddress;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.HomePage;
import utils.ConfigReader;

import java.time.Duration;

@Epic("Checkout")
@Feature("Guest checkout")
public class CheckoutTest extends BaseTest {
    private static final String FIRST_NAME = "Roman";
    private static final String LAST_NAME = "Nikolaev";
    private static final String ITEM = "Black heels";
    private static final DeliveryAddress VALID_ADDRESS = new DeliveryAddress(
            "Linda Vista",
            "San Diego",
            "California",
            "92111"
    );

    private CheckoutPage openCheckoutWithItemAndValidAddress() {
        return new HomePage(driver)
                .clickCatalog()
                .openProduct(ITEM)
                .addToCart()
                .clickCheckoutOnProductDetailsPage()
                .clickCheckoutOnCartPage()
                .fillInfo(FIRST_NAME, LAST_NAME, VALID_ADDRESS);
    }

    @Story("Shipping method becomes visible after valid delivery address")
    @Test
    public void checkoutHappyPathShouldShowShippingMethod() {
        CheckoutPage checkout = openCheckoutWithItemAndValidAddress();
        Assert.assertTrue(checkout.waitForShippingMethodSectionVisible(Duration.ofSeconds(10)));
    }

    @Story("Validation: city is required (error appears after blur)")
    @Test
    public void checkoutCityRequiredShouldShowError() {
        CheckoutPage checkout = openCheckoutWithItemAndValidAddress();
        checkout.clearCity().triggerBlur();
        Assert.assertTrue(checkout.isCityErrorVisible(),
                "Expected 'Enter a city' error after clearing City");
    }

    @Story("Validation: ZIP code is required (error appears after blur)")
    @Test
    public void checkoutZipRequiredShouldShowError() {
        CheckoutPage checkout = openCheckoutWithItemAndValidAddress();
        checkout.clearZip().triggerBlur();
        Assert.assertTrue(checkout.isZipErrorVisible() && !checkout.isShippingMethodSectionVisible(),
                "Expected ZIP validation error and shipping method to be unavailable when ZIP is empty");

    }

    @Story("Demo: Screenshot on test failure")
    @Test(groups="demo")
    public void demoScreenshotAttachedOnFailure() {
        // Intentional failure for reporting demo
        driver.get(ConfigReader.get("baseUrl"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {};
        Assert.fail("Intentional failure to demonstrate Allure screenshot attachment");
    }
}
