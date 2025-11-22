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

public class UiSendPassword1Page extends BasePage {
	public WebDriverWait wait;
	
	
	public  UiSendPassword1Page(WebDriver driver) throws IOException
	{
		super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
    }
	public void baseSendPassword1Page(String lang) throws InterruptedException  //----------------------------------------------------------------------------------------------------------------------------
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

	    //  Verify Hi, punit.g To Reset Your Password , follow the instructions sent to your XXXXXXX893 Mobile No." Text 		   
	    WebElement hi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelHi']")));
	    Assert.assertTrue(hi.isDisplayed(), "'Hi' is NOT displayed!");
	    String hiTest = hi.getText();
	    
	    WebElement userNameHalf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='NameMsgM']")));
	    Assert.assertTrue(userNameHalf.isDisplayed(), "'User's Name before @'  is NOT displayed!");
	    String userNameHalfText = userNameHalf.getText();	
	    
	    WebElement ToResetYourPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelReset']")));
	    Assert.assertTrue(ToResetYourPassword.isDisplayed(), "'To Reset Your Password , follow the instructions sent to your'  is NOT displayed!");
	    String ToResetYourPasswordText = userNameHalf.getText();
	    
	    WebElement textError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='messSucess']//span[@class='text-error']")));
	    Assert.assertTrue(textError.isDisplayed(), "'XXXXXXX123'  is NOT displayed!");
	    String textErrorText = textError.getText();
	    
	    WebElement mobileNo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='labelMobile']")));
	    Assert.assertTrue(mobileNo.isDisplayed(), "'Mobile No'  is NOT displayed!");
	    String mobileNoText = mobileNo.getText();    
	    	  
	    //  Forgot button
	    WebElement finishBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='messSuccessFinishButton']")));	
	    Assert.assertTrue(finishBtn.isDisplayed(), "Finish Button is NOT displayed!");
	    String finishBtnText = finishBtn.getAttribute("value");
	    
	    

	   

	    //  Expected Values (based on language)
	    String  expForgotPass,exphi, expuserNameHalf,expToResetYourPassword,exptextError,expmobileNo,expfinishBtn;
	    switch (lang) {
	        case "English":
	        	expForgotPass = "Forgot Password";
	        	exphi="Hi";
	        	expuserNameHalf = UiLoginPage.userName;
	        	expToResetYourPassword = "To Reset Your Password , follow the instructions sent to your";
	        	exptextError = UiLoginPage.enteredValue;
	        	expmobileNo= "Mobile No";
	        	expfinishBtn="Finish";
	           
	            
	            break;
	        default:
	            throw new IllegalArgumentException("Unsupported language: " + lang);
	    }
	    String expecteduserNameHalf = expuserNameHalf.split("@")[0];  // "punit.g"
	    
	 // Extract only digits

	    String exptextError1 = exptextError.replaceAll("\\D+", "");
	    //  Assertions
	    Assert.assertEquals(hiTest, exphi, "'Hi' is mismatch");
	    Assert.assertEquals(finishBtnText, expfinishBtn, "'Finish' text mismatch!");
	    Assert.assertEquals(userNameHalfText, expecteduserNameHalf, " 'User's Name before @' is mismatch!");	
	    Assert.assertEquals(ToResetYourPasswordText, expToResetYourPassword, "'To Reset Your Password , follow the instructions sent to your' is mismatch!");
	    Assert.assertTrue(textErrorText.contains(exptextError1),"'" + exptextError1 + "' is mismatch! Actual text: " + textErrorText);
	    Assert.assertEquals(mobileNoText, expmobileNo, "'Mobile No' is mismatch!");
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
	   //cancelBtn.click();
	   
		
	}
	
	
}
