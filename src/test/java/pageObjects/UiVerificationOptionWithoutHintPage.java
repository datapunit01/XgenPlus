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

public class UiVerificationOptionWithoutHintPage extends BasePage{
	public WebDriverWait wait;
	
	public  UiVerificationOptionWithoutHintPage(WebDriver driver) throws IOException
	{
		super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
    }    

	
	public void baseVerificationOptionWithoutHintPage(String lang) throws InterruptedException  //----------------------------------------------------------------------------------------------------------------------------
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

	    //  Verify " You have not filled your Hint Question in Security Setting.In case of any problem please contact our customer care." Text 		   
	    WebElement hint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelCare']")));
	    Assert.assertTrue(hint.isDisplayed(), "'You have not filled your Hint Question in Security Setting.In case of any problem please contact our customer care.' is NOT displayed!");
	    String hintText = hint.getText();	    
	    	    	  
	    //  Finish button
	    WebElement finishBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='']//a[@href='javascript:;']//input[@name='Finish']")));	
	    Assert.assertTrue(finishBtn.isDisplayed(), "Finish Button is NOT displayed!");
	    String finishBtnText = finishBtn.getAttribute("value");
	    
	    

	   

	    //  Expected Values (based on language)
	    String  expForgotPass,exphint,expfinishBtn;
	    switch (lang) {
	        case "English":
	        	expForgotPass = "Forgot Password";
	        	exphint="You have not filled your Hint Question in Security Setting.In case of any problem please contact our customer care.";	        	
	        	expfinishBtn="Finish";
	           
	            
	            break;
	        default:
	            throw new IllegalArgumentException("Unsupported language: " + lang);
	    }
	   
	    //  Assertions
	    Assert.assertEquals(hintText, exphint, "'You have not filled your Hint Question in Security Setting.In case of any problem please contact our customer care.' is mismatch");
	    Assert.assertEquals(finishBtnText, expfinishBtn, "'Finish' text mismatch!");	    
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
	   finishBtn.click();
	   
		
	}
}
