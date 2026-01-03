package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class T004_SnoozedMailFromInbox extends BaseClass {
	@Test(priority = 1)
	public void verify_SnoozedMailFromInbox() throws InterruptedException, IOException {		

		// Calling Instance of MyAccountPage from public MyAccountPage doLogin()
		MyAccountPage macc = doLogin();
		
		// Snoozed Mail From Inbox
		macc.verifySnoozedMailFromInbox();
		
		// Click User icon
		macc.isUserDisplayed();
		macc.clickUser();

		Thread.sleep(2000);

		// Logout
		macc.clickLogout();
	}

}
