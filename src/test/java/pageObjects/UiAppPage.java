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

public class UiAppPage extends BasePage{
	
	public WebDriverWait wait;	
	 
	public UiAppPage(WebDriver driver) throws IOException {
	    super(driver);
	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	   
	        
	    }
	
	public void baseLoginViaAppPage(String lang) throws InterruptedException , IOException
	{
		// Logo Check
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
        Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed!");

        String styleAttr = logo.getAttribute("style");
        String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
        Reporter.log("Extracted Logo SRC: " + src, true);

     // ✅ Get expected logo name from config.properties
        String expectedLogo = p.getProperty("expectedLogo");
        Assert.assertTrue(styleAttr.contains(expectedLogo),  "Correct logo is missing!");
        
        // Web Login Options	        
        WebElement useWebLoginCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='switchOtpBtn']")));
        String useWebLoginCodeText=useWebLoginCode.getText();
        WebElement loginViaApp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginViaTotp']//h4[@id='smsLabel']")));
        String loginViaAppText=loginViaApp.getText();
       // WebElement openWebLabelTotpApp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loginViaTotp']//p[@id='openWebLabelApp']")));
        String openWebLabelTotpAppText="Open Your App And Use Web Login Option To Get TOTP Code";  
        
        // Input Boxes
        Thread.sleep(2000);
        WebElement inputBoxForEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totpEmail")));
        Assert.assertTrue(inputBoxForEmail.isDisplayed(), "Input Box for Email is NOT visible!");
        String inputBoxForEmailText=inputBoxForEmail.getAttribute("placeholder");
        String inputBoxForEmailValueText=inputBoxForEmail.getAttribute("value");
        
        WebElement inputBoxForCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='checkTotp']")));
        Assert.assertTrue(inputBoxForCode.isDisplayed(), "Input Box for Code is NOT visible!");
        String inputBoxForCodeText=inputBoxForCode.getAttribute("placeholder");
        
        // Buttons
        WebElement backbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='loginViaTotpBck']")));
        Assert.assertTrue(backbtn.isDisplayed(), "'Back' buttonis NOT visible!");
        String backbtnText=backbtn.getAttribute("value");
        WebElement logMeInbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='logmeDataAppViaTotp']")));
        Assert.assertTrue(logMeInbtn.isDisplayed(), "'Log Me In' button is NOT visible!");
        WebElement logMeIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='loginButtonTotp']")));
        String logMeIneText=logMeIn.getText();
        // Expected Values Based on Language
        
		 String expUseWebLoginCode,expLoginViaApp,expopenWebLabelApp,expinputBoxForEmailText,expinputBoxForCodeText,expBackbtn,expLogMeIn,expUserName;
		    switch (lang) {
		    
		        case "English":			        	
		        	expUseWebLoginCode = "Use Web Login Code";
		        	expLoginViaApp= "Login Via App";
		        	expopenWebLabelApp= "Open Your App And Use Web Login Option To Get TOTP Code";
		        	expinputBoxForEmailText="Enter your Email address";
		        	expinputBoxForCodeText="Enter your Verification code";
		        	expBackbtn= "Back";
		        	expLogMeIn="Log Me In";
		        	expUserName= UiLoginPage.userName;
		            break;
		        default:
		            throw new IllegalArgumentException("Unsupported language: " + lang);  
		    }
		    
		    // Assertions
		    Assert.assertTrue(useWebLoginCode.isDisplayed(), "'Use Web Login Code' button is NOT displayed!");
		    Assert.assertEquals(useWebLoginCodeText, expUseWebLoginCode, " 'Use Web Login Code' is not matching!");
		    Assert.assertEquals(loginViaAppText, expLoginViaApp, " 'Login Via App' is not matching!");
		    Assert.assertEquals(openWebLabelTotpAppText, expopenWebLabelApp, " 'Open Your App And Use Web Login Option To Get TOTP Code' is not matching!");
		    Assert.assertEquals(inputBoxForEmailText, expinputBoxForEmailText, " 'Enter your Email address' is not matching!");
		    Assert.assertEquals(inputBoxForCodeText, expinputBoxForCodeText, " 'Enter your Verification code' is not matching!");
		    Assert.assertEquals(backbtnText, expBackbtn, " 'Back' is not matching!");
		    Assert.assertEquals(logMeIneText, expLogMeIn, " 'Log Me In' is not matching!");
		    Assert.assertEquals(inputBoxForEmailValueText, expUserName, "'UserName' is NOT visible!");
		    
		    // Calling Use TOTP Code Page -------------------------------------------------------------------------------------
		    useWebLoginCode.click();
		    UiAppUsetotpCodePage autcp = new UiAppUsetotpCodePage(driver);	    
		    autcp.baseUseTotpCodeOfAppPage(lang);
		    
		    Assert.assertEquals(loginViaAppText, expLoginViaApp, " 'Login Via App' is not matching!");
		    Thread.sleep(2000);
		    //  Footer Check
		    String footer = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
		    Assert.assertEquals(footer, "Privacy Policy | Terms and Conditions");
		   // Store Current Window
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
		    Assert.assertEquals(loginViaAppText, expLoginViaApp);

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
		    Assert.assertEquals(loginViaAppText, expLoginViaApp);
		    
		//  Back Arrow → Login Page
		    backbtn.click();
		    Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).isDisplayed(),
		            "Login page did NOT load after clicking Back!");
    
		  
		    
		    
		    
	}

}
