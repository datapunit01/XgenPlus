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

public class UiPasswordPage extends BasePage {
	
	 public WebDriverWait wait;
	

	public UiPasswordPage(WebDriver driver) throws IOException 
	{
	        super(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	}
	
	
	public void basePasswordPage(String lang) throws InterruptedException, IOException
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

			    //  Verify Back Arrow + Username
			    WebElement backArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='svg/images/arrow-mail.svg']")));
			    Assert.assertTrue(backArrow.isDisplayed(), "Back Arrow is NOT displayed!");

			    String userNameText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail"))).getText();

			    //  Verify Password Page
			    String labelText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enterPasswordLabel"))).getText();
			    String plcHolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).getAttribute("placeholder");
			    WebElement showPassIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Show Password']")));
			    Assert.assertTrue(showPassIcon.isDisplayed(), "Show Password Icon is NOT displayed!");

			    //  Forgot Password Link
			    WebElement forgotPassLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frgtpass")));
			    String forgotYourPassText = forgotPassLink.getText();
			    forgotPassLink.click();	  
			   	
			    
			    //  Expected Values (based on language)
			    String expUserName, expEnterPass, expPlaceHolder, expForgotYourPass;
			    switch (lang) {
			        case "English":
			            expUserName = UiLoginPage.userName;
			            expEnterPass = "Enter Password";
			            expPlaceHolder = "Password";
			            expForgotYourPass = "Forgot your password?";
			           
			            break;
			        default:
			            throw new IllegalArgumentException("Unsupported language: " + lang);
			    }

			    //  Assertions
			    Assert.assertEquals(userNameText, expUserName, "'UserName' is NOT visible!");
			    Assert.assertEquals(labelText, expEnterPass, "'Enter Password' is NOT visible!");
			    Assert.assertEquals(plcHolder, expPlaceHolder, "Placeholder text mismatch!");
			    Assert.assertEquals(forgotYourPassText, expForgotYourPass, "'Forgot your password?' text mismatch!");
			   
			    Thread.sleep(2000);
			    
			    //  Forgot Password Page		   

			   

			    UiForgotPasswordPage fpg = new UiForgotPasswordPage(driver);
			    fpg.baseForgotPasswordPage(lang);
			    
			    
			    Thread.sleep(2000);
			    Assert.assertEquals(forgotYourPassText, expForgotYourPass, "'Forgot your password?' is mismatch!");
			    //  Login Button
			    WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginLabel")));
			    Assert.assertTrue(loginBtn.isDisplayed(), "Login Button is NOT displayed!");
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
			    Assert.assertEquals(forgotYourPassText, expForgotYourPass);

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
			    Assert.assertEquals(forgotYourPassText, expForgotYourPass);

			    //  Back Arrow → Login Page
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='svg/images/arrow-mail.svg']"))).click();
			    Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).isDisplayed(),
			            "Login page did NOT load after clicking Back!");
				
			}

}
