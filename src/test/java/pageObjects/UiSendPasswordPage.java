package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class UiSendPasswordPage extends BasePage{
	
	public  UiSendPasswordPage(WebDriver driver) throws IOException
	{
		super(driver);
	}
public WebDriverWait wait;


public void baseSendPasswordPage(String lang) throws InterruptedException  //----------------------------------------------------------------------------------------------------------------------------
{  
	
	 //  Verify Logo
    WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
    Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed!");
    String styleAttr = logo.getAttribute("style");
    String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
    Reporter.log("Extracted Logo SRC: " + src, true);
 // ✅ Get expected logo name from config.properties
    String expectedLogo = p.getProperty("expectedLogo");
    Assert.assertTrue(styleAttr.contains(expectedLogo),  "Correct logo is missing!");
    //  Verify Forgot Password Text
    WebElement forgotPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Forgot Password']")));
    Assert.assertTrue(forgotPassword.isDisplayed(), "forgot Password is NOT displayed!");
    String forgotPasswordText = forgotPassword.getText();		   

    //  Verify Message Icon and " Send Password via SMS" Text 		   
    WebElement messageIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='svg/images/msg.svg']")));
    Assert.assertTrue(messageIcon.isDisplayed(), "'Message Icon' is NOT displayed!");
    WebElement sendPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelSendPasswordSms']")));
    Assert.assertTrue(sendPassword.isDisplayed(), "'Send Password via SMS'  is NOT displayed!");
    String sendPasswordText = sendPassword.getText();	
    
    
    //  Verify Authentication Icon and "  Use a different verification option" Text 		   
    WebElement authenticationIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='svg/images/verificationOption.svg']")));
    Assert.assertTrue(authenticationIcon.isDisplayed(), " 'Authentication Icon' is NOT displayed!");
    WebElement differentVerification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelUseADiffrentVerificationOption']")));
    Assert.assertTrue(differentVerification.isDisplayed(), " 'Use a different verification option '  is NOT displayed!");
    String differentVerificationText = differentVerification.getText();	
    

    //  Forgot button
    WebElement cancelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='cancelChngPass']")));	
    Assert.assertTrue(cancelBtn.isDisplayed(), "Back Button is NOT displayed!");
    String cancelBtnText = cancelBtn.getAttribute("value");
    
    

   

    //  Expected Values (based on language)
    String  expForgotPass, expcancelBtn,expsendPassword,expdifferentVerification;
    switch (lang) {
        case "English":
        	expForgotPass = "Forgot Password";
        	expcancelBtn = "Cancel";
        	expsendPassword = "Send Password via SMS";
        	expdifferentVerification = "Use a different verification option";
           
            
            break;
        default:
            throw new IllegalArgumentException("Unsupported language: " + lang);
    }

    //  Assertions
    Assert.assertEquals(sendPasswordText, expsendPassword, "'Send Password via SMS' is mismatch");
    Assert.assertEquals(cancelBtnText, expcancelBtn, "'Cancel' text mismatch!");
    Assert.assertEquals(differentVerificationText, expdifferentVerification, " 'Use a different verification option' is mismatch!");		
    Assert.assertEquals(forgotPasswordText, expForgotPass, "'Forgot Password' heading mismatch!");
    Thread.sleep(2000);

    //  Footer Check
    String footer = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
    Assert.assertEquals(footer, "Privacy Policy | Terms and Conditions");

    String originalWindow = driver.getWindowHandle();

    //  Privacy Policy Page
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

    //  Terms and Conditions Page
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

    //  Back Arrow → Login Page
   cancelBtn.click();
   
	
}
}