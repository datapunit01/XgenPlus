package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) throws IOException {

        String timeStamp = new SimpleDateFormat("ddMMyyy_HHmmss").format(new Date());
        String screenshotPath =
                System.getProperty("user.dir")
                + "/target/screenshots/"
                + testName + "_" + timeStamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(screenshotPath);

        FileUtils.copyFile(src, dest);

        return screenshotPath;
    }
}
