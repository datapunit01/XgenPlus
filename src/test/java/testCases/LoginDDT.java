package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class LoginDDT extends BaseClass{

		
	
	@Test(priority = 1,dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")// getting data provider from different class
	
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException, IOException
	{
		//logger.info("***** stating TC_003_LoginDDT ******");
				
			LoginPage lp = new LoginPage(driver);

			// 1️ Verify email input box is visible and enter email
			lp.isEmailBoxDisplayed();
			lp.setEmail(email);

			// 2️ Verify Next button is visible and clickable
			lp.isNextButtonDisplayed();
			lp.clickNext();
			Thread.sleep(3000);
			// 3️ Verify password input box is visible and enter password
			lp.isPasswordBoxDisplayed();
			lp.setPassword(pwd);

			// 4️ Verify Login button is visible and clickable
			lp.isLoginButtonDisplayed();
			lp.clickLogin();
			
			// 5 Verify Skip button is visible and clickable
			lp.isSkipButtonDisplayed();
		    lp.clickSkip();			
	     	Thread.sleep(5000);   
				       	    	   	        
		   if(exp.equalsIgnoreCase("Valid"))
	     	{
			  // driver.navigate().refresh();
			// My Account Page
	            MyAccountPage macc = new MyAccountPage(driver);
	            macc.switchToMyAccountFrames();
	            macc.isCrossDisplayed();
		        macc.clickCross();
	            Assert.assertTrue(macc.verifyPageElements(), "MyAccount Page not displayed!");
	            Assert.assertTrue(macc.isUserDisplayed(), "User icon not visible!");
	            macc.clickUser();
	            Thread.sleep(2000);
	            Assert.assertTrue(macc.isLogoutDisplayed(), "Logout link not visible!");
	            macc.clickLogout();
		    }
		
		// 🔹 Case 2: Invalid Login (wrong password or wrong email)
	        else if (exp.equalsIgnoreCase("Invalid")) 
	        {	           
	                Assert.fail("Invalid login did not redirect to Email Page or show error!");	            
	        }
		
		   // Click User icon
		
		
	}
		
		
		
		//Thread.sleep(3000);
		//logger.info("***** Finished TC_003_LoginDDT ******");
		
	
	
}

