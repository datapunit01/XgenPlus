package utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import com.aventstack.extentreports.*;

import testBase.BaseClass;

public class ExtentTestListener extends BaseClass implements ITestListener {

    private ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentReportManager.getExtentReport();
        System.out.println("Extent initialized");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        test.info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("Extent flushed successfully");
    }
    private String captureScreenshot(String testName) {
        String path = System.getProperty("user.dir")
                + "/reports/screenshots/" + testName + ".png";
        try {
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
