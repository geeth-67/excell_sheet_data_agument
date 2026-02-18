package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.createInstance();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // If test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            test.fail("❌ Test Failed: " + result.getThrowable());
            try {
                test.addScreenCaptureFromPath(path, "Failure Screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // If test passes
        else if (result.getStatus() == ITestResult.SUCCESS) {
            String path = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            try {
                test.pass("✅ Test Passed Successfully")
                        .addScreenCaptureFromPath(path, "Success Screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        driver.quit();
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
