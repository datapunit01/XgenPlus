package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class T003_ComposeMailTest extends BaseClass {
	@Test(priority = 1)
	public void verify_ComposeMail() throws InterruptedException, IOException {		

		// Calling Instance of MyAccountPage from public MyAccountPage doLogin()
		MyAccountPage macc = doLogin();

		// Compose Mail
		//	macc.verifyComposeAndSendMail();
		
		// Compose Mail And save into Draft 
		macc.verifyComposeWithDraftPage();
		
		// Click User icon
		macc.isUserDisplayed();
		macc.clickUser();

		Thread.sleep(2000);

		// Logout
		macc.clickLogout();
	}

}
