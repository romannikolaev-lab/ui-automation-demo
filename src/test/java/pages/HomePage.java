package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    private final By catalogLink = By.cssSelector("nav a[href='/collections/all']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CatalogPage clickCatalog() {
        driver.findElement(catalogLink).click();
        return new CatalogPage(driver);
    }
}
