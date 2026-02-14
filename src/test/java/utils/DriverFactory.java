package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser");
        if (browser == null || browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        throw new IllegalArgumentException("Unsupported browser " + browser);
    }
}
