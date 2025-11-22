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

public class UiAlternateEmail extends BasePage {
	
	public WebDriverWait wait;

	public UiAlternateEmail(WebDriver driver, String enteredValue) throws IOException {
	    super(driver);
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     
	        
	    }

	
	
	public void baseAlternateEmailPage(String lang) throws InterruptedException  //----------------------------------------------------------------------------------------------------------------------------
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
	    
	    //  Verify Add Security To Your Account Text
	    WebElement addSecurity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='add_security_to_your_account']")));
	    Assert.assertTrue(addSecurity.isDisplayed(), "'Add Security To Your Account' is NOT displayed!");
	    String addSecurityText = addSecurity.getText();		   

	    // Verify  Input Box
	    WebElement inputAlternateEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='comProAltId']")));
	    Assert.assertTrue(inputAlternateEmail.isDisplayed(), "'Alternate Email Id' is NOT displayed!");
	    String inputAlternateEmailText = inputAlternateEmail.getAttribute("placeholder");	
	    
	    
	    //  Verify " Provide your alternate email address" Text 		   
	    WebElement  provideAlternateEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divComProAltId']]")));
	    Assert.assertTrue(provideAlternateEmail.isDisplayed(), "'Provide your alternate email address' is NOT displayed!");
	    String provideAlternateEmailText = provideAlternateEmail.getText();	   
	    
	    
	    // Verify " We will use this information for sending notifications ------------------------------Account User Settings under security section." Text
	    WebElement  informationForSending = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='label_sendind_notification']")));
	    Assert.assertTrue(informationForSending.isDisplayed(), "'Provide your alternate email address' is NOT displayed!");
	    String informationForSendingText = informationForSending.getText();	  
	    
	    //  Next button	    
	    WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnNextStp1']")));	
	    Assert.assertTrue(nextBtn.isDisplayed(), " Next Button is NOT displayed!");
	    String nextBtnText =nextBtn.getAttribute("value");
	    
	    // Skip BUtton
	    WebElement skipBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btn_step_23']")));	
	    Assert.assertTrue(skipBtn.isDisplayed(), " Next Button is NOT displayed!");
	    String skipBtnText =skipBtn.getAttribute("value");
	    

	   

	    //  Expected Values (based on language)
	    String  expAddSecurity,expinputAlternateEmail,expprovideAlternateEmail,expinformationForSending,expnextBtn,expskipBtn;
	    switch (lang) {
	        case "English":
	        	expAddSecurity = "Add Security To Your Account";
	        	expinputAlternateEmail="Alternate Email Id";	        	
	        	expprovideAlternateEmail="Provide your alternate email address";
	        	expinformationForSending= "We will use this information for sending notifications. For example, we can send you a mail to help you access your account if you forget your password. You always have control over this functionality through Account User Settings under security section.";
	        	expnextBtn="Next";
	        	expskipBtn= "Skip";
	           
	            
	            break;
	        default:
	            throw new IllegalArgumentException("Unsupported language: " + lang);
	    }
	   
	    //  Assertions
	    Assert.assertEquals(addSecurityText, expAddSecurity, "'Add Security To Your Account' is mismatch");
	    Assert.assertEquals(inputAlternateEmailText, expinputAlternateEmail, "'Alternate Email Id' Placeholder text mismatch!");  	    
	    Assert.assertEquals(provideAlternateEmailText, expprovideAlternateEmail, "'Provide your alternate email address' is mismatch!");
	    Assert.assertEquals(informationForSendingText, expinformationForSending, "'We will use this information for sending notifications. For example, we can send you a mail to help you access your account if you forget your password. You always have control over this functionality through Account User Settings under security section.' is mismatch!");
	    Assert.assertEquals(nextBtnText, expnextBtn, "'Next' is mismatch!");
	    Assert.assertEquals(skipBtnText, expskipBtn, "'Skip' is mismatch!");
	    
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
	    Assert.assertEquals(provideAlternateEmailText, expprovideAlternateEmail);

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
	    Assert.assertEquals(provideAlternateEmailText, expprovideAlternateEmail);

	    //  Back Arrow → Login Page
	  // finishBtn.click();
	   
		
	}
}
