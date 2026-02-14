package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        try {
            var field = testClass.getClass().getSuperclass().getDeclaredField("driver");
            field.setAccessible(true);
            WebDriver driver = (WebDriver) field.get(testClass);
            if (driver != null) {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(png));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
