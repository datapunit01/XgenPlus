package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class UiForgotPasswordPage extends BasePage {

    public WebDriverWait wait;
  
    public UiForgotPasswordPage(WebDriver driver) throws IOException {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
    }

    /**
     * ✅ Option 1: Use username passed from LoginPage
     */
    public void baseForgotPasswordPage(String lang) throws InterruptedException {
        
        // Verify Logo
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
        Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed!");
        String styleAttr = logo.getAttribute("style");
        String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
        Reporter.log("Extracted Logo SRC: " + src, true);
     // ✅ Get expected logo name from config.properties
        String expectedLogo = p.getProperty("expectedLogo");
        Assert.assertTrue(styleAttr.contains(expectedLogo),  "Correct logo is missing!");

        // Verify Forgot Password Text
        WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Forgot Password']")));
        Assert.assertTrue(forgotPassword.isDisplayed(), "Forgot Password heading is NOT displayed!");
        String forgotPasswordText = forgotPassword.getText();

        // Verify Input box with readonly value
        WebElement inputEmailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailId']")));
        Assert.assertTrue(inputEmailBox.isDisplayed(), "Input Box is NOT displayed!");
        String inputBoxPlaceholderText = inputEmailBox.getAttribute("placeholder");
        String inputEmailBoxText = inputEmailBox.getAttribute("value");

        // Buttons
        WebElement backBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='backToPass']//input[@name='Back']")));
        Assert.assertTrue(backBtn.isDisplayed(), "Back Button is NOT displayed!");
        String backBtnText = backBtn.getAttribute("value");

        WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='btnNextStp1']//input[@name='Next']")));
        Assert.assertTrue(nextBtn.isDisplayed(), "Next Button is NOT displayed!");
        String nextBtnText = nextBtn.getAttribute("value");

        // ✅ Expected Values
        String expForgotPass, expBackBtn, expNextBtn, expInputBoxPlaceholder, expInputEmailBox;
        switch (lang) {
            case "English":
                expForgotPass = "Forgot Password";
                expBackBtn = "Back";
                expNextBtn = "Next";
                expInputBoxPlaceholder = "email address";
                expInputEmailBox = UiLoginPage.userName;  // ✅ passed from LoginPage
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + lang);
        }

        // ✅ Assertions
        Assert.assertEquals(inputEmailBoxText, expInputEmailBox, "'UserName' is NOT visible!");
        Assert.assertEquals(inputBoxPlaceholderText, expInputBoxPlaceholder, "'Placeholder' mismatch!");
        Assert.assertEquals(nextBtnText, expNextBtn, "'Next' button mismatch!");
        Assert.assertEquals(backBtnText, expBackBtn, "'Back' button mismatch!");
        Assert.assertEquals(forgotPasswordText, expForgotPass, "'Forgot Password' heading mismatch!");

        Thread.sleep(2000);

        // Footer Check
        String footer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
        Assert.assertEquals(footer, "Privacy Policy | Terms and Conditions");

        // Navigate Privacy Policy
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("aPrivacyPolicy"))).click();
        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                System.out.println("Privacy Tab URL: " + driver.getCurrentUrl());
                break;
            }
        }
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Welcome to the XGENPLUS Privacy Policy']"))).isDisplayed(),
                "Privacy page not loaded!");
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(2000);
        driver.switchTo().frame("topFrame");
        Assert.assertEquals(forgotPasswordText, expForgotPass);

        // Navigate Terms and Conditions
        wait.until(ExpectedConditions.elementToBeClickable(By.id("aTermsCond"))).click();
        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                System.out.println("Terms Tab URL: " + driver.getCurrentUrl());
                break;
            }
        }
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='XgenPlus Terms of Service']"))).isDisplayed(),
                "Terms page not loaded!");
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(2000);
        driver.switchTo().frame("topFrame");
        Assert.assertEquals(forgotPasswordText, expForgotPass);

        // Back Arrow → Login Page
        backBtn.click();
    }

}
