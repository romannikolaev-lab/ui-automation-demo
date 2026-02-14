package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.DriverFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void createAllureEnvironment() {
        Path resultsDir = Paths.get("target/allure-results");
        try {
            Files.createDirectories(resultsDir);
            Properties props = new Properties();
            props.setProperty("Browser", "Chrome");
            props.setProperty("Environment", "QA");
            props.setProperty("OS", System.getProperty("os.name"));
            props.setProperty("Java", System.getProperty("java.version"));
            try (OutputStream os = Files.newOutputStream(resultsDir.resolve("environment.properties"))) {
                props.store(os, null);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Allure environment file", e);
        }
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }
}
