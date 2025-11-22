package testCases;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class UiOfLoginPageForKannada extends BaseClass
{ 
      
    @Test(priority = 1)
    public void verifyLogo() 
    {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
        Assert.assertTrue(logo.isDisplayed(), "❌ Logo is NOT displayed!");

        String styleAttr = logo.getAttribute("style");
        String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
        Reporter.log("Extracted Logo SRC: " + src, true);

        Assert.assertTrue(styleAttr.contains("loginLogo1653914065139.svg"),
                "Correct logo is missing!");
    }

    @Test(priority = 2)
    public void verifyLanguageDropdown() 
    {
        WebElement langDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSetlang")));
        Select select = new Select(langDropdown);
        List<WebElement> options = select.getOptions();

        List<String> expectedValues = Arrays.asList(
                "English", "हिंदी", "ਪੰਜਾਬੀ", "বাঙালি", "తెలుగు", "தமிழ்",
                "ગુજરાતી", "मराठी", "русский", "中文", "العربية",
                "한국인", "ҚАЗАҚ", "qazaq latin", "казахский русский",
                "日本人", "ಕನ್ನಡ", "ଓଡିଆ", "संस्कृत", "ᱥᱟᱱᱛᱷᱟᱲᱤ"
        );

        for (String expected : expectedValues) {
            boolean found = options.stream().anyMatch(opt -> opt.getText().trim().equals(expected));
            Assert.assertTrue(found, "Option '" + expected + "' is missing!");
        }

        Assert.assertEquals(options.size(), expectedValues.size(),
                "Dropdown option count does not match expected!");
    }
    
    @Test(priority = 3)
    public void selectHindi() {
    	WebElement langDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSetlang")));
    	Assert.assertTrue(langDropdown.isDisplayed(), "❌ Dropdown is NOT visible!");

    	// Wrap dropdown in Select
    	Select select = new Select(langDropdown);

    	// ✅ Select ಕನ್ನಡ by visible text
    	select.selectByVisibleText("ಕನ್ನಡ");

    	// Verify ಕನ್ನಡ is selected
    	String selectedText = select.getFirstSelectedOption().getText();
    	Assert.assertEquals(selectedText, "ಕನ್ನಡ", "❌ Failed to select 'ಕನ್ನಡ' from dropdown.");
    	System.out.println("✅ Successfully selected '" + selectedText + "' from dropdown.");

    }

    @Test(priority = 4)
    public void signIn() 
    {
        WebElement sgn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='signInLabel']")));
        String labelText= sgn.getText();
        Assert.assertEquals(labelText, "ಸೈನ್ ಇನ್ ಮಾಡಿ", "Label text is not matching!");
    }

    @Test(priority = 5)
    public void inputBox() 
    {
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");

        String plcHolder = textBox.getAttribute("placeholder");
        Assert.assertEquals(plcHolder, "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್", "Placeholder text does not match expected.");

        textBox.sendKeys((p.getProperty("email")));
    }

    @Test(priority = 6)
    public void forgotEmailCancelButton() throws InterruptedException 
    {
    	   

    	baseForForgotEmail("Kannada"); 


    	    
    }

    @Test(priority = 7)
    public void toggleButton() throws InterruptedException 
    {
        WebElement icnbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='off']"))); 
        Assert.assertTrue(icnbtn.isDisplayed(), "Toggle Icon is NOT visible!");
        
        //icnbtn.click();
        Thread.sleep(3000);
     // Assert that the enabled toggle button is actually enabled
        Assert.assertTrue(icnbtn.isEnabled(), "The toggle button should be enabled.");
        Thread.sleep(3000);
       // icnbtn.click();
     // Assert that the disabled toggle button is actually enabled
        Assert.assertTrue(icnbtn.isEnabled(), "The toggle button should be disabled.");
      //  Thread.sleep(2000);
        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='RemeberLabel']")));
        String labelText = toggle.getText();
        Assert.assertEquals(labelText, "ನನ್ನನ್ನು ನೆನಪಿನಲ್ಲಿಡು", "Label text is not matching!");
    }

    @Test(priority = 8)
    public void nextBtnBackBtn() throws InterruptedException 
    {
        WebElement nxtbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signinNxt")));
        String labelText = nxtbtn.getAttribute("value");
        
        Assert.assertEquals(labelText, "ಮುಂದೆ", "Label text is not matching!");
        
        nxtbtn.click();
        Thread.sleep(2000);
     // Wait for Password Page heading
	    WebElement passwordPage = wait.until(ExpectedConditions
	            .visibilityOfElementLocated(By.id("enterPasswordLabel")));
	    String labelText2= passwordPage.getText();
	    Assert.assertEquals(labelText2, "ಪಾಸ್‌ವರ್ಡ್ ನಮೂದಿಸಿ", "Label text 2 is not matching!");
	    
	    

	    // 🔙 Click Back Arrow to go back to Login Page
	    WebElement backBtn = wait.until(ExpectedConditions
	            .elementToBeClickable(By.xpath("//img[@src='svg/images/arrow-mail.svg']")));
	    backBtn.click();

	    // After navigating back, re-check Login Page elements
	    WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");

        String plcHolder = textBox.getAttribute("placeholder");
        Assert.assertEquals(plcHolder, "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್", "Placeholder text does not match expected.");

    }

    @Test(priority = 9)
    public void secureLoginBackBtn() throws InterruptedException 
    {
        WebElement key = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='svg/images/secure-key.svg']")));
        Assert.assertTrue(key.isDisplayed(), "Key Icon is NOT visible!");

        WebElement slv = driver.findElement(By.id("secure_login_Via"));
        String labelText = slv.getText();
        Assert.assertEquals(labelText, "ಸುರಕ್ಷಿತ ಲಾಗಿನ್ ಮೂಲಕ", "Label text is not matching!");

        WebElement app = driver.findElement(By.xpath("//a[@id='loginVia_App']"));
        String labelText2 = app.getText();
	    Assert.assertEquals(labelText2, "ಅಪ್ಲಿಕೇಶನ್", "Label text is not matching!");
       
        
        app.click();
        
     // Wait for App Page heading
	    WebElement appPage = wait.until(ExpectedConditions
	            .visibilityOfElementLocated(By.xpath("//div[@id='loginViaTotp']//h4[@id='smsLabel']")));
	    Assert.assertTrue(appPage.isDisplayed(), "'Loign Via App' is NOT visible!");
	    Thread.sleep(2000);

	    // 🔙 Click Back Arrow to go back to Login Page
	    WebElement backBtn = wait.until(ExpectedConditions
	            .elementToBeClickable(By.id("loginViaTotpBck")));
	    backBtn.click();

	    // After navigating back, re-check Login Page elements
	    WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");

        String plcHolder = textBox.getAttribute("placeholder");
        Assert.assertEquals(plcHolder, "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್", "Placeholder text does not match expected.");

    }

    @Test(priority = 10)
    public void privacyAndTerm() throws InterruptedException 
    {
        String pipe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
        Assert.assertEquals(pipe, "Privacy Policy | Terms and Conditions", "Footer text does not match expected.");
        
        String originalWindowHandle = driver.getWindowHandle();
        
       // System.out.println("Orignal Window : " + originalWindowHandle);
       // This is for Privacy Policy Page................................................................................................... 
        WebElement privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='aPrivacyPolicy']")));
        privacy.click();
        Thread.sleep(2000);
     // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();
        
        // Iterate through all handles and switch to the new window
        for (String handle : allWindowHandles) 
        {
            if (!handle.equals(originalWindowHandle))
            {
                driver.switchTo().window(handle);
                System.out.println("Switched to the new tab with URL: " + driver.getCurrentUrl());
                // You can perform actions on the new tab here, e.g.,
                // Assert.assertTrue(driver.getCurrentUrl().contains("privacy.com"), "The URL should contain 'privacy.com'");
                break;
            }
        }
     
       
     // Wait for Privacy Page heading
	   WebElement privacyPage = wait.until(ExpectedConditions
	           .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Welcome to the XGENPLUS Privacy Policy']")));
	    Assert.assertTrue(privacyPage.isDisplayed(), "Welcome to the XGENPLUS Privacy Policy");
	 // Close the new tab
	    driver.close();
	    driver.switchTo().window(originalWindowHandle);
	    Thread.sleep(2000);
	    System.out.println("Switched back to the original tab with URL: " + driver.getCurrentUrl());
	 // After navigating back, re-check Login Page elements
	    driver.switchTo().frame("topFrame");
	    WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");

        String plcHolder = textBox.getAttribute("placeholder");
        Assert.assertEquals(plcHolder, "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್", "Placeholder text does not match expected.");

       
	 // This is for Terms and Conditions Page....................................................................................................   
	    
	    WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='aTermsCond']")));
	    terms.click();
	    Thread.sleep(2000);
       Set<String> allWindowHandlesTerm = driver.getWindowHandles();
        
        // Iterate through all handles and switch to the new window
        for (String handle : allWindowHandlesTerm) 
        {
            if (!handle.equals(originalWindowHandle))
            {
                driver.switchTo().window(handle);
                System.out.println("Switched to the new tab with URL: " + driver.getCurrentUrl());                
                break;
            }
        }
     
       
     // Wait for Terms and Conditions Page heading
	   WebElement termsPage = wait.until(ExpectedConditions
	           .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='XgenPlus Terms of Service']")));
	    Assert.assertTrue(termsPage.isDisplayed(), "XgenPlus Terms of Service");
	 // Close the new tab
	    driver.close();
	    driver.switchTo().window(originalWindowHandle);
	    Thread.sleep(2000);
	    System.out.println("Switched back to the original tab with URL: " + driver.getCurrentUrl());
	 // After navigating back, re-check Login Page elements
	    driver.switchTo().frame("topFrame");
	   // WebElement loginBox1 = wait.until(ExpectedConditions
	         //  .visibilityOfElementLocated(By.id("username")));
	    Assert.assertEquals(plcHolder, "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್", "Placeholder text does not match expected.");
    }

   /* @AfterClass
    public void tearDown() {
        driver.quit();
    }*/
}

