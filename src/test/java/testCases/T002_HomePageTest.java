package testCases;

import java.io.IOException;
import org.testng.annotations.Test;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class T002_HomePageTest extends BaseClass {

	// @Test(dataProvider="LoginData",
	// dataProviderClass=DataProviders.class,groups="Datadriven")// getting data
	// provider from different class
	@Test(priority = 1)
	public void verify_HomePage() throws InterruptedException, IOException {
		

		// Calling Instance of MyAccountPage from public MyAccountPage doLogin()
		MyAccountPage macc = doLogin();

		// Check on UnreadInbox button
		// macc.verifyUnreadInboxPage();

		// Check Inbox icon
		// macc.verifyInboxPage();

		// Check Sent icon
		// macc.verifySentPage();
		
		// Check Sent Unread icon
		//   macc.verifySentUnreadPage();

		// Check Draft icon
		// macc.verifyDraftPage();

		// Check Trash icon
		// macc.verifyTrashPage();

		// Check Trash Unread icon
		// macc.verifyTrashUnreadPage();

		// Check Template icon
		// macc.verifyTemplatePage();

		// Check Snoozed icon
		// macc.verifySnoozedPage();

		// Check Spam-Promo icon
		// macc.verifySpamPromoPage();

		// Check Spam-Promo Unread icon
		 macc.verifySpamPromoUnreadPage();		   
		   

		// Click User icon
		macc.isUserDisplayed();
		macc.clickUser();

		Thread.sleep(2000);

		// Logout
		macc.isLogoutDisplayed();
		macc.clickLogout();

	}

}
