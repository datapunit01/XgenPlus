/*package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class title extends BaseClass {

  

    @Test
    public void verifyTitle() {
        // safely switch into iframe if present
        driver.switchTo().defaultContent();
        List<WebElement> frames = driver.findElements(By.id("topFrame"));
        if (!frames.isEmpty()) {
            driver.switchTo().frame("topFrame");
        }

        // Try fetching <title> OR fallback to page title
        WebElement titleElement = driver.findElement(By.tagName("title"));
        String act_title = titleElement.getAttribute("textContent").trim();

        // Assertion
        Assert.assertEquals(act_title, "XgenPlus", "Title is not matching!");
    }

  
}*/
