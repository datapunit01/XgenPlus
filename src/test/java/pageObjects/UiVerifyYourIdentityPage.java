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

public class UiVerifyYourIdentityPage extends BasePage{
	
	public WebDriverWait wait;

public UiVerifyYourIdentityPage(WebDriver driver) throws IOException {
    super(driver);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     
        
    }
	
	
	
	public void baseVerifyYourIdentifyPage(String lang) throws InterruptedException
	{
		
		// ✅ Verify Logo
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
		Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed!");

		String styleAttr = logo.getAttribute("style");
		String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
		Reporter.log("Extracted Logo SRC: " + src, true);
		// ✅ Get expected logo name from config.properties
        String expectedLogo = p.getProperty("expectedLogo");
        Assert.assertTrue(styleAttr.contains(expectedLogo),  "Correct logo is missing!");

		// ✅ Verify "Verify your identity"
		WebElement youridentity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("verifyUrIdentity")));
		String youridentityText = youridentity.getText();
		Assert.assertTrue(youridentity.isDisplayed(), "'Verify your identity' is NOT visible!");

		// ✅ Verify heading
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("step2label")));
		String headingText = heading.getText();
		Assert.assertTrue(heading.isDisplayed(), "Heading is NOT visible!");

		// ✅ Verify Mobile Number
		WebElement mobNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='mobNumber']")));
		String mobNumberText = mobNumber.getText();
		Assert.assertTrue(mobNumber.isDisplayed(), "Mobile Number is NOT visible!");

		// ✅ Verify Input Box
		WebElement inputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("otpValue")));
		String inputBoxText = inputBox.getAttribute("placeholder");
		Assert.assertTrue(inputBox.isDisplayed(), "Input Box is NOT visible!");

		// ✅ Buttons
		WebElement cancelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='otpCancel']")));
		String cancelBtnText = cancelBtn.getAttribute("value");
		Assert.assertTrue(cancelBtn.isDisplayed(), "'Cancel' button is NOT visible!");

		WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='getNxtEmail']")));
		String nextBtnText = nextBtn.getAttribute("value");
		Assert.assertTrue(nextBtn.isDisplayed(), "'Next' button is NOT visible!");
			    

		// ✅ Expected Values
		String expYouridentity, expheading, expPlaceHolder, expCancelbtn, expNextBtn, expMobNumber;
		switch (lang) {
		    case "English":
		        expYouridentity = "Verify your identity";
		        expheading = "A text message with a 6-digit verification code was just sent to";
		        expPlaceHolder = "Enter your Verification code";
		        expCancelbtn = "Cancel";
		       // baseForForgotEmail(enteredValue); // pulled from phone step
		         expMobNumber = UiLoginPage.enteredValue;
		        expNextBtn = "Next";
		        
		        break;
		    default:
		        throw new IllegalArgumentException("Unsupported language: " + lang);
		}

		// ✅ Assertions
		Assert.assertEquals(youridentityText, expYouridentity, "'Verify your identity' text mismatch!");
		Assert.assertEquals(headingText, expheading, "Heading text mismatch!");
		System.out.println("Using value from enterPhoneNumber(): " + expMobNumber);
		Assert.assertEquals(mobNumberText, expMobNumber, "Mobile Number mismatch!");
		Assert.assertEquals(inputBoxText, expPlaceHolder, "Placeholder text mismatch!");
		Assert.assertEquals(cancelBtnText, expCancelbtn, "Cancel button text mismatch!");
		Assert.assertEquals(nextBtnText, expNextBtn, "Next button text mismatch!");

		Thread.sleep(2000);

		// ✅ Footer Check
		String footer = wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
		Assert.assertEquals(footer, "Privacy Policy | Terms and Conditions");

		// ✅ Store current window
		String originalWindow = driver.getWindowHandle();

		// ✅ Privacy Policy Page
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
		Assert.assertEquals(youridentityText, expYouridentity);

		// ✅ Terms and Conditions Page
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
		Assert.assertEquals(youridentityText, expYouridentity);

		// ✅ Back to Find your email page
		cancelBtn.click();

		
	}

}
