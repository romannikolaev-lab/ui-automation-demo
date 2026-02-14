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

@Epic("Checkout")
@Feature("Guest checkout")
public class CheckoutTest extends BaseTest {
    @Story("Shipping method becomes visible after valid delivery address")
    @Test
    public void checkoutHappyPathShippingMethodShouldBeVisible() {
        String item = "Black heels";

        DeliveryAddress address = new DeliveryAddress(
                "Linda Vista",
                "San Diego",
                "California",
                "92111"
        );

        CheckoutPage checkout = new HomePage(driver)
                .clickCatalog()
                .openProduct(item)
                .addToCart()
                .clickCheckoutOnProductDetailsPage()
                .clickCheckoutOnCartPage()
                .fillInfo("Roman", "Nikolaev", address);

        Assert.assertTrue(checkout.isShippingMethodSectionVisible());
    }

    @Story("Demo: Screenshot on test failure")
    @Test
    public void demoScreenshotAttachedOnFailure() {
        // Intentional failure for reporting demo
        driver.get(ConfigReader.get("baseUrl"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {};
        Assert.fail("Intentional failure to demonstrate Allure screenshot attachment");
    }
}
