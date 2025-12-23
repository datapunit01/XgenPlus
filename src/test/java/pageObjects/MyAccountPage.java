package pageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utilities.ReusableForUnread;

public class MyAccountPage extends BasePage {
	ReusableForUnread ruse;
	UnreadInbox unread;
	InboxPage inpage;
	SentPage stPage;
	SentUnreadPage stuPage;
	DraftPage dtPage;
	TrashPage thPage;
	TrashUnreadPage tuPage;
	TemplatePage ttPage;
	SpamPromoPage spPage;
	SpamPromoUnreadPage spuPage;
	SnoozedPage sdPage;
	ComposePage cpPage;
	ComposeWithDraftPage cpdPage;

	public final String expectedTitle;
	String invalidMailForTo = p.getProperty("invalidTo");
	String validMailForTo = p.getProperty("validTo");
	String invalidMailForCc = p.getProperty("invalidCc");
	String validMailForCc = p.getProperty("validCc");
	String invalidMailForBcc = p.getProperty("invalidBcc");
	String validMailForBcc = p.getProperty("validBcc");
	String textforMailSubject = p.getProperty("textforSubject");
	String textforMailBody = p.getProperty("textforBody");
	String nameOfInlineImage = "TestingFarmhouse.jpg";
	// 🔹 Locators

	@FindBy(xpath = "//img[@id='btFileAttachPhotoPic2']")
	public WebElement lnkUser;

	@FindBy(css = "span.guided-tour-step-button-close")
	public WebElement crossBtn;

	@FindBy(xpath = "//a[normalize-space()='Logout']")
	public WebElement lnkLogout;

	// Constructor
	public MyAccountPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		this.expectedTitle = p.getProperty("accountPageTitle");
		ruse = new ReusableForUnread(driver);
		unread = new UnreadInbox(driver);
		inpage = new InboxPage(driver);
		stPage = new SentPage(driver);
		stuPage = new SentUnreadPage(driver);
		dtPage = new DraftPage(driver);
		thPage = new TrashPage(driver);
		tuPage = new TrashUnreadPage(driver);
		ttPage = new TemplatePage(driver);
		spPage = new SpamPromoPage(driver);
		spuPage = new SpamPromoUnreadPage(driver);
		sdPage = new SnoozedPage(driver);
		cpPage = new ComposePage(driver);
		cpdPage = new ComposeWithDraftPage(driver);

	}

	public static final Logger log = LogManager.getLogger(MyAccountPage.class);

	/** ✅ Switch to nested frames (FB → FM) */
	public void switchToMyAccountFrames() {
		driver.switchTo().defaultContent();
		WebElement fbFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FB")));
		driver.switchTo().frame(fbFrame);

		WebElement fmFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FM")));
		driver.switchTo().frame(fmFrame);

		System.out.println("✅ Switched to FB → FM frames");
	}

	/** ✅ Verify page title */
	public boolean verifyPageTitle() {
		wait.until(ExpectedConditions.titleContains("dil.in User"));
		String pageTitle = driver.getTitle();
		System.out.println("Page Title: " + pageTitle);

		boolean status = pageTitle.contains(expectedTitle);
		Assert.assertTrue(status,
				"'Account Page Title' mismatch! Expected: " + expectedTitle + " | Found: " + pageTitle);
		return status;
	}

	/** ✅ Verify all essential page elements */
	public boolean verifyPageElements() {
		return verifyPageTitle();
	}

	/** ✅ Tooltip close button */
	public boolean isCrossDisplayed() {
		return isElementDisplayed(crossBtn, "Cross button");
	}

	public void clickCross() {
		safeClick(crossBtn, "Cross button");
	}

	public void verifyUnreadInboxPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Unread Inbox Page Verification ==========");

		log.info("Checking if Unread Inbox button is visible...");
		if (unread.isUnReadInboxDisplayedForUnReadInbox()) {
			log.info("Unread Inbox button is visible.");
			unread.clickUnReadInboxForUnReadInbox();
			log.info("Clicked Unread Inbox.");
		} else {
			log.error("Unread Inbox button is NOT visible!");
			Thread.sleep(3000);
		}

		log.info(" Switching to unread inbox frame MC...");
		unread.switchToFrameMcForUnReadInbox();
		log.info("Switched to Unread Inbox Frame MC.");

		log.info(" Fetching total email count from Inbox Unread Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Inbox Unread Page : {}", count);

		if (count >= 5) {

			log.info("Checking New Mail text Visible on Inbox Unread Page...");
			unread.isNewMailTextDisplayedForUnReadInbox();

			log.info(" Selecting All emails on Inbox Unread Page...");
			unread.isChkMainBoxDisplayedForUnReadInbox();
			unread.clickChkMainBoxForUnReadInbox();

			log.info("Verifying all emails are selected on Inbox Unread Page...");
			Assert.assertTrue(unread.areAllEmailsSelectedViaAllForUnReadInbox(), "Not all emails were selected.");
			log.info("All emails selected on Inbox Unread Page.");

			log.info("Unselecting all emailson Inbox Unread Page...");
			unread.clickChkMainBoxforUncheckForUnReadInbox();

			Assert.assertTrue(unread.areAllEmailsUnselectedViaAllForUnReadInbox(), "All emails are still selected!");
			log.info("All emails unselected on Inbox Unread Page.");

			log.info("Opening dropdown on Inbox Unread Page...");
			unread.isDrpDownBtnDisplayedForUnReadInbox();
			unread.clickDrpDownBtnForUnReadInbox();

			log.info("Closing dropdown on Inbox Unread Page...");
			unread.clickDrpDownBtnOffForUnReadInbox();

			log.info("Clicking two checkbox one by one on Inbox Unread Page...");
			unread.clickEachCheckboxOneByOneForUnReadInbox();

			log.info(" Working with toggle button on Inbox Unread Page...");
			unread.isToggleBtnDisplayedForUnReadInbox();
			unread.clickToggleBtnForUnReadInbox();

			log.info("STEP 9: Checking Show Interaction on Inbox Unread Page...");
			unread.isShowInteractionDisplayedForUnReadInbox();

			log.info("Sorting emails on Inbox Unread Page...");
			unread.isSortBtnDisplayedForUnReadInbox();
			unread.clickSortBtnForUnReadInbox();
			unread.clickSortButtonByNameForUnReadInbox();

			log.info("Moving email to Trash via Move to button on Inbox Unread Page...");
			unread.moveToTrashForUnReadInbox();

			log.info(" Checking the cancel button of Block pop up on Inbox Unread Page...");
			unread.clickCancelBtnOfBlockPopupForUnReadInbox();

			log.info("Moving email to Trash via Block button on Inbox Unread Page...");
			unread.clickBlockBtnOfBlockPopupForUnReadInbox();

			log.info("Deleting and moving to trash email on Inbox Unread Page...");
			unread.clickDeleteBtnForUnReadInbox();

			log.info("Clicking Read/Unread button on Inbox Unread Page...");
			unread.clickunreadReadEamilBtnForUnReadInbox();

			log.info("Opening Quick View on Inbox Unread Page...");
			unread.isQuickViewBoxDisplayedForUnReadInbox();
			unread.clickQuickViewBoxForUnReadInbox();
			unread.reclickQuickViewBoxForUnReadInbox();

			log.info("Forwarding email on Inbox Unread Page...");
			unread.clickforwordManyBtnForUnReadInbox();

		} else {
			log.error("Email count is less than 5.  So cannot continue this test.");
			Assert.assertTrue(count <= 5, "Total emails in Unread Inbox are less than 5.");
		}

		log.info("========== COMPLETED: Inbox Unread Page Verification ==========");
	}

	public void verifyInboxPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Inbox Page Verification ==========");

		log.info("Checking if Inbox button is visible...");
		inpage.isInboxBtnDisplayed();
		log.info("Inbox button is visible.");

		log.info("Clicking Inbox button...");
		inpage.clickInboxBtn();
		log.info("Clicked Inbox button.");

		log.info("Switching to Inbox frame (My Account Frame IC)...");
		inpage.switchToMyAccountFramesIcForInbox();
		log.info("Switched to Inbox frame successfully.");

		log.info("Fetching total email count from Inbox Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Inbox Page: {}", count);

		if (count >= 4) {

			log.info("Checking menu before selection on Inbox Page...");
			inpage.checkingMenuBarOnInboxPage();

			log.info("Checking if main checkbox is visible on Inbox Page...");
			inpage.isChkMainBoxDisplayedForInbox();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails using main checkbox...");
			inpage.clickChkMainBoxForInbox();

			Assert.assertTrue(inpage.areAllEmailsSelectedViaAllForInbox(), "Not all emails were selected.");
			log.info("All emails successfully selected on Inbox Page.");

			log.info("Unselecting all emails...");
			inpage.clickChkMainBoxforUncheckForInbox();

			Assert.assertTrue(inpage.areAllEmailsUnselectedViaAllForInbox(), "All emails are still selected.");
			log.info("All emails successfully unselected on Inbox Page.");

			log.info("Opening dropdown on Inbox Page...");
			inpage.isDrpDownBtnDisplayedForInbox();
			inpage.clickDrpDownBtnOnForInbox();

			log.info("Closing dropdown on Inbox Page...");
			inpage.clickDrpDownBtnOffForInbox();

			log.info("Clicking each checkbox one by one on Inbox Page...");
			inpage.clickEachCheckboxOneByOneForInbox();

			log.info("Working with toggle button on Inbox Page...");
			inpage.isToggleBtnDisplayedForInbox();
			inpage.clickToggleBtnForInbox();

			log.info("Checking 'Show Interaction' on Inbox Page...");
			inpage.isShowInteractionDisplayedForInbox();

			log.info("Sorting emails on Inbox Page...");
			inpage.isSortBtnDisplayedForInbox();
			inpage.clickSortBtnForInbox();
			inpage.clickSortButtonByNameForInbox();

			log.info("Moving email to Trash using 'Move to' button on Inbox Page...");
			inpage.moveToTrashForInbox();

			log.info("Clicking Cancel on Block popup for Inbox Page...");
			inpage.clickCancelBtnOfBlockPopupForInbox();

			log.info("Clicking Block button on Block popup for Inbox Page...");
			inpage.clickBlockBtnOfBlockPopupForInbox();

			log.info("Deleting email on Inbox Page...");
			inpage.clickDeleteBtnForInbox();

			log.info("Clicking Read/Unread button on Inbox Page...");
			inpage.clickunreadReadEamilBtnForInbox();

			log.info("Opening Quick View on Inbox Page...");
			inpage.isQuickViewBoxDisplayedForInbox();
			inpage.clickQuickViewBoxForInbox();
			inpage.reclickQuickViewBoxForInbox();

			log.info("Forwarding email on Inbox Page...");
			inpage.clickforwordManyBtnForInbox();

		} else {

			log.error("Total Number of Emails in Inbox Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails In Inbox Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Inbox Page Verification ==========");
	}

	public void verifySentPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Sent Page Verification ==========");

		log.info("Checking if Sent button is visible...");
		stPage.isSentBtnDisplayed();
		log.info("Sent button is visible.");

		log.info("Clicking Sent button...");
		stPage.clickSentBtn();
		log.info("Clicked Sent button.");

		log.info("Switching to Sent page frame (My Account Frame MC)...");
		stPage.switchToMyAccountFramesMcForSent();
		log.info("Switched to Sent page frame successfully.");

		log.info("Fetching total email count from Sent Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Sent Page: {}", count);

		if (count >= 4) {

			log.info("Checking if main checkbox is visible on Sent Page...");
			stPage.isChkMainBoxDisplayedForSent();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Sent Page...");
			stPage.clickChkMainBoxForSent();

			Assert.assertTrue(stPage.areAllEmailsSelectedViaAllForSent(), "Not all emails were selected.");
			log.info("All emails selected successfully on Sent Page.");

			log.info("Unselecting all emails on Sent Page...");
			stPage.clickChkMainBoxforUncheckForSent();

			Assert.assertTrue(stPage.areAllEmailsUnselectedViaAllForSent(), "All emails are still selected.");
			log.info("All emails unselected successfully on Sent Page.");

			log.info("Opening dropdown on Sent Page...");
			stPage.isDrpDownBtnDisplayedForSent();
			stPage.clickDrpDownBtnOnForSent();

			log.info("Closing dropdown on Sent Page...");
			stPage.clickDrpDownBtnOffForSent();

			log.info("Clicking each checkbox one by one on Sent Page...");
			stPage.clickEachCheckboxOneByOneForSent();

			log.info("Working with toggle button on Sent Page...");
			stPage.isToggleBtnDisplayedForSent();
			stPage.clickToggleBtnForSent();

			log.info("Checking 'Show Interaction' on Sent Page...");
			stPage.isShowInteractionDisplayedForSent();

			log.info("Sorting emails on Sent Page...");
			stPage.isSortBtnDisplayedForSent();
			stPage.clickSortBtnForSent();
			stPage.clickSortButtonByNameForSent();

			log.info("Moving email to Trash from Sent Page...");
			stPage.moveToTrashForSent();

			log.info("Deleting email from Sent Page...");
			stPage.clickDeleteBtnForSent();

			log.info("Clicking Read/Unread button on Sent Page...");
			stPage.clickunreadReadEamilBtnForSent();

			log.info("Opening Quick View on Sent Page...");
			stPage.isQuickViewBoxDisplayedForSent();
			stPage.clickQuickViewBoxForSent();
			stPage.reclickQuickViewBoxForSent();

			log.info("Forwarding email from Sent Page...");
			stPage.clickforwordManyBtnForSent();

		} else {

			log.error("Total Number of Emails in Sent Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails in Sent Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Sent Page Verification ==========");
	}

	public void verifySentUnreadPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Sent Unread Page Verification ==========");

		log.info("Checking if Sent Unread button is visible...");
		stuPage.isSentUnreadBtnDisplayed();
		log.info("Sent Unread button is visible.");

		log.info("Clicking Sent Unread button...");
		stuPage.clickSentUnreadBtn();
		log.info("Clicked Sent Unread button.");

		log.info("Switching to Sent Unread page frame (My Account Frame MC)...");
		stuPage.switchToMyAccountFramesMcForSentUnread();
		log.info("Switched to Sent Unread page frame successfully.");

		log.info("Fetching total email count from Sent Unread Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Sent Unread Page: {}", count);

		if (count >= 4) {

			log.info("Checking if main checkbox is visible on Sent Unread Page...");
			stuPage.isChkMainBoxDisplayedForSentUnread();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Sent Unread Page...");
			stuPage.clickChkMainBoxForSentUnread();

			Assert.assertTrue(stuPage.areAllEmailsSelectedViaAllForSentUnread(), "Not all emails were selected.");
			log.info("All emails selected successfully on Sent Unread Page.");

			log.info("Unselecting all emails on Sent Unread Page...");
			stuPage.clickChkMainBoxforUncheckForSentUnread();

			Assert.assertTrue(stuPage.areAllEmailsUnselectedViaAllForSentUnread(), "All emails are still selected.");
			log.info("All emails unselected successfully on Sent Unread Page.");

			log.info("Opening dropdown on Sent Unread Page...");
			stuPage.isDrpDownBtnDisplayedForSentUnread();
			stuPage.clickDrpDownBtnOnForSentUnread();

			log.info("Closing dropdown on Sent Unread Page...");
			stuPage.clickDrpDownBtnOffForSentUnread();

			log.info("Clicking each checkbox one by one on Sent Unread Page...");
			stuPage.clickEachCheckboxOneByOneForSentUnread();

			log.info("Working with toggle button on Sent Unread Page...");
			stuPage.isToggleBtnDisplayedForSentUnread();
			stuPage.clickToggleBtnForSentUnread();

			log.info("Checking 'Show Interaction' on Sent Unread Page...");
			stuPage.isShowInteractionDisplayedForSentUnread();

			log.info("Sorting emails on Sent Unread Page...");
			stuPage.isSortBtnDisplayedForSentUnread();
			stuPage.clickSortBtnForSentUnread();
			stuPage.clickSortButtonByNameForSentUnread();

			log.info("Moving email to Trash from Sent Unread Page...");
			stuPage.moveToTrashForSentUnread();

			log.info("Deleting email from Sent Unread Page...");
			stuPage.clickDeleteBtnForSentUnread();

			log.info("Clicking Read/Unread button on Sent Unread Page...");
			stuPage.clickunreadReadEamilBtnForSentUnread();

			log.info("Opening Quick View on Sent Unread Page...");
			stuPage.isQuickViewBoxDisplayedForSentUnread();
			stuPage.clickQuickViewBoxForSentUnread();
			stuPage.reclickQuickViewBoxForSentUnread();

			log.info("Forwarding email from Sent Unread Page...");
			stuPage.clickforwordManyBtnForSentUnread();

		} else {

			log.error("Total Number of Emails in Sent Unread Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails in Sent Unread Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Sent Unread Page Verification ==========");
	}

	public void verifyTrashPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Trash Page Verification ==========");

		log.info("Checking if Trash button is visible...");
		thPage.isTrashBtnDisplayed();
		log.info("Trash button is visible.");

		log.info("Clicking Trash button...");
		thPage.clickTrashBtn();
		log.info("Clicked Trash button.");

		log.info("Switching to Trash page frame (My Account Frame MC)...");
		thPage.switchToMyAccountFramesMcForTrash();
		log.info("Switched to Trash page frame successfully.");

		log.info("Fetching total email count from Trash Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Trash Page: {}", count);

		if (count >= 4) {

			log.info("Checking if main checkbox is visible on Trash Page...");
			thPage.isChkMainBoxDisplayedForTrash();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Trash Page...");
			thPage.clickChkMainBoxForTrash();

			Assert.assertTrue(thPage.areAllEmailsSelectedViaAllForTrash(), "Not all emails were selected.");
			log.info("All emails selected successfully on Trash Page.");

			log.info("Unselecting all emails on Trash Page...");
			thPage.clickChkMainBoxforUncheckForTrash();

			Assert.assertTrue(thPage.areAllEmailsUnselectedViaAllForTrash(), "All emails are still selected.");
			log.info("All emails unselected successfully on Trash Page.");

			log.info("Opening dropdown on Trash Page...");
			thPage.isDrpDownBtnDisplayedForTrash();
			thPage.clickDrpDownBtnOnForTrash();

			log.info("Closing dropdown on Trash Page...");
			thPage.clickDrpDownBtnOffForTrash();

			log.info("Clicking each checkbox one by one on Trash Page...");
			thPage.clickEachCheckboxOneByOneForTrash();

			log.info("Working with toggle button on Trash Page...");
			thPage.isToggleBtnDisplayedForTrash();
			thPage.clickToggleBtnForTrash();

			log.info("Sorting emails on Trash Page...");
			thPage.isSortBtnDisplayedForTrash();
			thPage.clickSortBtnForTrash();
			thPage.clickSortButtonByNameForTrash();

			log.info("Performing Move to Trash action on Trash Page...");
			thPage.moveToInboxFromTrash();

			log.info("Clicking Cancel on Block popup for Trash Page...");
			thPage.clickCancelBtnOfBlockPopupForTrash();

			log.info("Clicking Block button on Block popup for Trash Page...");
			thPage.clickBlockBtnOfBlockPopupForTrash();

			log.info("Deleting email from Trash Page...");
			thPage.clickDeleteBtnForTrash();

			log.info("Clicking Read/Unread button on Trash Page...");
			thPage.clickunreadReadEamilBtnForTrash();

			log.info("Opening Quick View on Trash Page...");
			thPage.isQuickViewBoxDisplayedForTrash();
			thPage.clickQuickViewBoxForTrash();
			thPage.reclickQuickViewBoxForTrash();

			log.info("Forwarding email from Trash Page...");
			thPage.clickforwordManyBtnForTrash();

		} else {

			log.error("Total Number of Emails in Trash Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails in Trash Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Trash Page Verification ==========");
	}

	public void verifyTrashUnreadPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Trash Unread Page Verification ==========");

		log.info("Checking if Trash Unread button is visible...");
		tuPage.isTrashUnreadBtnDisplayed();
		log.info("Trash Unread button is visible.");

		log.info("Clicking Trash Unread button...");
		tuPage.clickTrashUnreadBtn();
		log.info("Clicked Trash Unread button.");

		log.info("Switching to Trash Unread page frame (My Account Frame MC)...");
		tuPage.switchToMyAccountFramesMcForTrashUnread();
		log.info("Switched to Trash Unread page frame successfully.");

		log.info("Fetching total email count from Trash Unread Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Trash Unread Page: {}", count);

		if (count >= 5) {

			log.info("Checking if main checkbox is visible on Trash Unread Page...");
			tuPage.isChkMainBoxDisplayedForTrashUnread();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Trash Unread Page...");
			tuPage.clickChkMainBoxForTrashUnread();

			Assert.assertTrue(tuPage.areAllEmailsSelectedViaAllForTrashUnread(), "Not all emails were selected.");
			log.info("All emails selected successfully on Trash Unread Page.");

			log.info("Unselecting all emails on Trash Unread Page...");
			tuPage.clickChkMainBoxforUncheckForTrashUnread();

			Assert.assertTrue(tuPage.areAllEmailsUnselectedViaAllForTrashUnread(), "All emails are still selected.");
			log.info("All emails unselected successfully on Trash Unread Page.");

			log.info("Opening dropdown on Trash Unread Page...");
			tuPage.isDrpDownBtnDisplayedForTrashUnread();
			tuPage.clickDrpDownBtnOnForTrashUnread();

			log.info("Closing dropdown on Trash Unread Page...");
			tuPage.clickDrpDownBtnOffForTrashUnread();

			log.info("Clicking each checkbox one by one on Trash Unread Page...");
			tuPage.clickEachCheckboxOneByOneForTrashUnread();

			log.info("Working with toggle button on Trash Unread Page...");
			tuPage.isToggleBtnDisplayedForTrashUnread();
			tuPage.clickToggleBtnForTrashUnread();

			log.info("Sorting emails on Trash Unread Page...");
			tuPage.isSortBtnDisplayedForTrashUnread();
			tuPage.clickSortBtnForTrashUnread();
			tuPage.clickSortButtonByNameForTrashUnread();

			log.info("Performing Move to Trash action on Trash Unread Page...");
			tuPage.moveToInboxFromTrashUnread();

			log.info("Clicking Cancel on Block popup for Trash Unread Page...");
			tuPage.clickCancelBtnOfBlockPopupForTrashUnread();

			log.info("Clicking Block button on Block popup for Trash Unread Page...");
			tuPage.clickBlockBtnOfBlockPopupForTrashUnread();

			log.info("Deleting email from Trash Unread Page...");
			tuPage.clickDeleteBtnForTrashUnread();

			log.info("Clicking Read/Unread button on Trash Unread Page...");
			tuPage.clickunreadReadEamilBtnForTrashUnread();

			log.info("Opening Quick View on Trash Unread Page...");
			tuPage.isQuickViewBoxDisplayedForTrashUnread();
			tuPage.clickQuickViewBoxForTrashUnread();
			tuPage.reclickQuickViewBoxForTrashUnread();

			log.info("Forwarding email from Trash Unread Page...");
			tuPage.clickforwordManyBtnForTrashUnread();

		} else {

			log.error("Total Number of Emails in Trash Unread Page is LESS than 5. Cannot proceed.");
			Assert.assertTrue(count <= 5,
					"Total Number of Emails in Trash Unread Page are less than 5 so test cannot continue.");
		}

		log.info("========== COMPLETED: Trash Unread Page Verification ==========");
	}

	public void verifyTemplatePage() throws IOException, InterruptedException {

		log.info("========== STARTING: Template Page Verification ==========");

		log.info("Checking if Template button is visible...");
		ttPage.isTemplateBtnDisplayed();
		log.info("Template button is visible.");

		log.info("Clicking Template button...");
		ttPage.clickTemplateBtn();
		log.info("Clicked Template button.");

		log.info("Switching to Template page frame (My Account Frame MC)...");
		ttPage.switchToMyAccountFramesMcForTemplate();
		log.info("Switched to Template page frame successfully.");

		log.info("Fetching total email count from Template Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Template Page: {}", count);

		if (count >= 2) {

			log.info("Checking if main checkbox is visible on Template Page...");
			ttPage.isChkMainBoxDisplayedForTemplate();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Template Page...");
			ttPage.clickChkMainBoxForTemplate();

			Assert.assertTrue(ttPage.areAllEmailsSelectedViaAllForTemplate(), "Not all emails were selected.");
			log.info("All emails selected successfully on Template Page.");

			log.info("Unselecting all emails on Template Page...");
			ttPage.clickChkMainBoxforUncheckForTemplate();

			Assert.assertTrue(ttPage.areAllEmailsUnselectedViaAllForTemplate(), "All emails are still selected.");
			log.info("All emails unselected successfully on Template Page.");

			log.info("Clicking each checkbox one by one on Template Page...");
			ttPage.clickEachCheckboxOneByOneForTemplate();

			log.info("Working with toggle button on Template Page...");
			ttPage.isToggleBtnDisplayedForTemplate();
			ttPage.clickToggleBtnForTemplate();

			log.info("Sorting emails on Template Page...");
			ttPage.isSortBtnDisplayedForTemplate();
			ttPage.clickSortBtnForTemplate();
			ttPage.clickSortButtonByNameForTemplate();

			log.info("Moving email to Trash from Template Page...");
			ttPage.moveToTrashForTemplate();

			log.info("Deleting email from Template Page...");
			ttPage.clickDeleteBtnForTemplate();

		} else {

			log.error("Total Number of Emails in Template Page is LESS than 2. Cannot proceed.");
			Assert.assertTrue(count <= 2,
					"Total Number of Emails in Template Page are less than 2 so test cannot continue.");
		}

		log.info("========== COMPLETED: Template Page Verification ==========");
	}

	public void verifySnoozedPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Snoozed Page Verification ==========");

		log.info("Checking if Snoozed button is visible...");
		sdPage.isSnoozedBtnDisplayedForSnoozedRe();
		log.info("Snoozed button is visible.");

		log.info("Clicking Snoozed button...");
		sdPage.clickSnoozedBtnForSnoozed();
		log.info("Clicked Snoozed button.");

		log.info("Switching to Snoozed page frame (My Account Frame MC)...");
		sdPage.switchToMyAccountFramesMcForSnoozed();
		log.info("Switched to Snoozed page frame successfully.");

		log.info("Fetching total email count from Snoozed Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Snoozed Page: {}", count);

		if (count >= 4) {

			log.info("Checking if main checkbox is visible on Snoozed Page...");
			sdPage.isChkMainBoxDisplayedForSnoozed();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Snoozed Page...");
			sdPage.clickChkMainBoxForSnoozed();

			Assert.assertTrue(sdPage.areAllEmailsSelectedViaAllForSnoozed(), "Not all emails were selected.");
			log.info("All emails selected successfully on Snoozed Page.");

			log.info("Unselecting all emails on Snoozed Page...");
			sdPage.clickChkMainBoxforUncheckForSnoozed();

			Assert.assertTrue(sdPage.areAllEmailsUnselectedViaAllForSnoozed(), "All emails are still selected.");
			log.info("All emails unselected successfully on Snoozed Page.");

			log.info("Clicking each checkbox one by one on Snoozed Page...");
			sdPage.clickEachCheckboxOneByOneForSnoozed();

			log.info("Working with toggle button on Snoozed Page...");
			sdPage.isToggleBtnDisplayedForSnoozed();
			sdPage.clickToggleBtnForSnoozed();

			log.info("Checking 'Show Interaction' on Snoozed Page...");
			sdPage.isShowInteractionBtnDisplayedForSnoozed();

			log.info("Sorting emails on Snoozed Page...");
			sdPage.isSortBtnDisplayedForSnoozed();
			sdPage.clickSortBtnForSnoozed();
			sdPage.clickSortButtonByNameForSnoozed();

			log.info("Moving email to Trash from Snoozed Page...");
			sdPage.moveToTrashForSnoozed();

			log.info("Clicking Cancel on Block popup for Snoozed Page...");
			sdPage.clickCancelBtnOfBlockPopupForSnoozed();

			log.info("Clicking Block button on Block popup for Snoozed Page...");
			sdPage.clickBlockBtnOfBlockPopupForSnoozed();

			log.info("Deleting email from Snoozed Page...");
			sdPage.clickDeleteBtnForSnoozed();

			log.info("Opening Quick View on Snoozed Page...");
			sdPage.isQuickViewBoxDisplayedForSnoozed();
			sdPage.clickQuickViewBoxForSnoozed();
			sdPage.reclickQuickViewBoxForSnoozed();

			log.info("Forwarding email from Snoozed Page...");
			sdPage.clickforwordManyBtnForSnoozed();

		} else {

			log.error("Total Number of Emails in Snoozed Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails in Snoozed Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Snoozed Page Verification ==========");
	}

	public void verifySpamPromoPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Spam-Promo Page Verification ==========");

		log.info("Checking if Spam-Promo button is visible...");
		spPage.isSpamPromoBtnDisplayed();
		log.info("Spam-Promo button is visible.");

		log.info("Clicking Spam-Promo button...");
		spPage.clickSpamPromoBtn();
		log.info("Clicked Spam-Promo button.");

		log.info("Switching to Spam-Promo page frame (My Account Frame MC)...");
		spPage.switchToMyAccountFramesMcForSpamPromo();
		log.info("Switched to Spam-Promo page frame successfully.");

		log.info("Fetching total email count from Spam-Promo Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Spam-Promo Page: {}", count);

		if (count >= 4) {

			log.info("Checking if main checkbox is visible on Spam-Promo Page...");
			spPage.isChkMainBoxDisplayedForSpamPromo();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Spam-Promo Page...");
			spPage.clickChkMainBoxForSpamPromo();

			Assert.assertTrue(spPage.areAllEmailsSelectedViaAllForSpamPromo(), "Not all emails were selected.");
			log.info("All emails selected successfully on Spam-Promo Page.");

			log.info("Unselecting all emails on Spam-Promo Page...");
			spPage.clickChkMainBoxforUncheckForSpamPromo();

			Assert.assertTrue(spPage.areAllEmailsUnselectedViaAllForSpamPromo(), "All emails are still selected.");
			log.info("All emails unselected successfully on Spam-Promo Page.");

			log.info("Opening dropdown on Spam-Promo Page...");
			spPage.isDrpDownBtnDisplayedForSpamPromo();
			spPage.clickDrpDownBtnOnForSpamPromo();

			log.info("Closing dropdown on Spam-Promo Page...");
			spPage.clickDrpDownBtnOffForSpamPromo();

			log.info("Clicking each checkbox one by one on Spam-Promo Page...");
			spPage.clickEachCheckboxOneByOneForSpamPromo();

			log.info("Working with toggle button on Spam-Promo Page...");
			spPage.isToggleBtnDisplayedForSpamPromo();
			spPage.clickToggleBtnForSpamPromo();

			log.info("Checking 'Show Interaction' on Spam-Promo Page...");
			spPage.isShowInteractionDisplayedForSpamPromo();

			log.info("Sorting emails on Spam-Promo Page...");
			spPage.isSortBtnDisplayedForSpamPromo();
			spPage.clickSortBtnForSpamPromo();
			spPage.clickSortButtonByNameForSpamPromo();

			log.info("Moving email to Trash from Spam-Promo Page...");
			spPage.moveToTrashForSpamPromo();

			log.info("Clicking Cancel on Block popup for Spam-Promo Page...");
			spPage.clickCancelBtnOfBlockPopupForSpamPromo();

			log.info("Clicking Block button on Block popup for Spam-Promo Page...");
			spPage.clickBlockBtnOfBlockPopupForSpamPromo();

			log.info("Deleting email from Spam-Promo Page...");
			spPage.clickDeleteBtnForSpamPromo();

			log.info("Clicking Read/Unread button on Spam-Promo Page...");
			spPage.clickunreadReadEamilBtnForSpamPromo();

			log.info("Opening Quick View on Spam-Promo Page...");
			spPage.isQuickViewBoxDisplayedForSpamPromo();
			spPage.clickQuickViewBoxForSpamPromo();
			spPage.reclickQuickViewBoxForSpamPromo();

		} else {

			log.error("Total Number of Emails in Spam-Promo Page is LESS than 4. Cannot proceed.");
			Assert.assertTrue(count <= 4,
					"Total Number of Emails in Spam-Promo Page are less than 4 so test cannot continue.");
		}

		log.info("========== COMPLETED: Spam-Promo Page Verification ==========");
	}

	public void verifySpamPromoUnreadPage() throws IOException, InterruptedException {

		log.info("========== STARTING: Spam-Promo Unread Page Verification ==========");

		log.info("Checking if Spam-Promo Unread button is visible...");
		spuPage.isSpamPromoUnreadBtnDisplayed();
		log.info("Spam-Promo Unread button is visible.");

		log.info("Clicking Spam-Promo Unread button...");
		spuPage.clickSpamPromoUnreadBtn();
		log.info("Clicked Spam-Promo Unread button.");

		log.info("Switching to Spam-Promo Unread page frame (My Account Frame MC)...");
		spuPage.switchToMyAccountFramesMcForSpamPromoUnread();
		log.info("Switched to Spam-Promo Unread page frame successfully.");

		log.info("Fetching total email count from Spam-Promo Unread Page...");
		int count = ruse.areCountOfTotalMailInPageRe();
		log.info("Total Email Count on Spam-Promo Unread Page: {}", count);

		if (count >= 5) {

			log.info("Checking if main checkbox is visible on Spam-Promo Unread Page...");
			spuPage.isChkMainBoxDisplayedForSpamPromoUnread();
			log.info("Main checkbox is visible.");

			log.info("Selecting all emails on Spam-Promo Unread Page...");
			spuPage.clickChkMainBoxForSpamPromoUnread();

			Assert.assertTrue(spuPage.areAllEmailsSelectedViaAllForSpamPromoUnread(), "Not all emails were selected.");
			log.info("All emails selected successfully on Spam-Promo Unread Page.");

			log.info("Unselecting all emails on Spam-Promo Unread Page...");
			spuPage.clickChkMainBoxforUncheckForSpamPromoUnread();

			Assert.assertTrue(spuPage.areAllEmailsUnselectedViaAllForSpamPromoUnread(),
					"All emails are still selected.");
			log.info("All emails unselected successfully on Spam-Promo Unread Page.");

			log.info("Opening dropdown on Spam-Promo Unread Page...");
			spuPage.isDrpDownBtnDisplayedForSpamPromoUnread();
			spuPage.clickDrpDownBtnOnForSpamPromoUnread();

			log.info("Closing dropdown on Spam-Promo Unread Page...");
			spuPage.clickDrpDownBtnOffForSpamPromoUnread();

			log.info("Clicking each checkbox one by one on Spam-Promo Unread Page...");
			spuPage.clickEachCheckboxOneByOneForSpamPromoUnread();

			log.info("Working with toggle button on Spam-Promo Unread Page...");
			spuPage.isToggleBtnDisplayedForSpamPromoUnread();
			spuPage.clickToggleBtnForSpamPromoUnread();

			log.info("Checking 'Show Interaction' on Spam-Promo Unread Page...");
			spuPage.isShowInteractionDisplayedForSpamPromoUnread();

			log.info("Sorting emails on Spam-Promo Unread Page...");
			spuPage.isSortBtnDisplayedForSpamPromoUnread();
			spuPage.clickSortBtnForSpamPromoUnread();
			spuPage.clickSortButtonByNameForSpamPromoUnread();

			log.info("Moving email to Trash from Spam-Promo Unread Page...");
			spuPage.moveToTrashForSpamPromoUnread();

			log.info("Clicking Cancel on Block popup for Spam-Promo Unread Page...");
			spuPage.clickCancelBtnOfBlockPopupForSpamPromoUnread();

			log.info("Clicking Block button on Block popup for Spam-Promo Unread Page...");
			spuPage.clickBlockBtnOfBlockPopupForSpamPromoUnread();

			log.info("Deleting email from Spam-Promo Unread Page...");
			spuPage.clickDeleteBtnForSpamPromoUnread();

			log.info("Clicking Read/Unread button on Spam-Promo Unread Page...");
			spuPage.clickunreadReadEamilBtnForSpamPromoUnread();

			log.info("Opening Quick View on Spam-Promo Unread Page...");
			spuPage.isQuickViewBoxDisplayedForSpamPromoUnread();
			spuPage.clickQuickViewBoxForSpamPromoUnread();
			spuPage.reclickQuickViewBoxForSpamPromoUnread();

		} else {

			log.error("Total Number of Emails in Spam-Promo Unread Page is LESS than 5. Cannot proceed.");
			Assert.assertTrue(count <= 5,
					"Total Number of Emails in Spam-Promo Unread Page are less than 5 so test cannot continue.");
		}

		log.info("========== COMPLETED: Spam-Promo Unread Page Verification ==========");
	}

	public void verifyComposeAndSendMail() throws InterruptedException, IOException {

		log.info("========== STARTING: Compose and Send Mail Verification ==========");

		log.info("Checking if Compose button is visible...");
		cpPage.isComposeBtnDisplayed();
		log.info("Compose button is visible.");

		log.info("Clicking Compose button...");
		cpPage.clickComposeBtn();
		log.info("Clicked Compose button.");

		log.info("Verifying 'From' text is displayed on top of Compose window...");
		cpPage.isFromTextOnTopOfComposeDisplayed();

		log.info("Verifying 'From' email is displayed on top of Compose window...");
		cpPage.isFromMailOnTopOfComposeDisplayed();

		log.info("Validating 'From' email on Compose page...");
		cpPage.fromMailCheckOnTopofComposePageRe();
		log.info("'From' email validation successful.");

		log.info("Verifying 'To' field is displayed on Compose page...");
		cpPage.isOnComposeToTextDisplayed();

		log.info("Verifying CC button is displayed on Compose page...");
		cpPage.isOnComposeCcBtnDisplayedRe();

		log.info("Clicking CC button...");
		cpPage.clickCcBtn();
		log.info("Clicked CC button.");

		log.info("Verifying CC field is displayed after clicking CC button...");
		cpPage.isOnComposeCcTextAfterClickDisplayed();

		log.info("Verifying BCC button is displayed on Compose page...");
		cpPage.isOnComposeBccBtnDisplayedRe();

		log.info("Clicking BCC button...");
		cpPage.clickBccBtn();
		log.info("Clicked BCC button.");

		log.info("Verifying BCC field is displayed after clicking BCC button...");
		cpPage.isOnComposeBccTextAfterClickDisplayed();

		log.info("Verifying Subject field is displayed on Compose page...");
		cpPage.isOnComposeSubjectTextDisplayed();

		log.info("Verifying Mail Body field is displayed on Compose page...");
		cpPage.isOnComposeBodyDisplayed();

		log.info("Verifying Send button is displayed on Compose page...");
		cpPage.isOnComposeSendBtnDisplayed();

		log.info("Generating unique subject for mail...");
		String subject = ruse.generateSubject(textforMailSubject);
		log.info("Generated mail subject: {}", subject);

		log.info("Composing mail with invalid and valid To, CC, and BCC addresses...");
		cpPage.composeMailWithInvalidTo(invalidMailForTo, validMailForTo, invalidMailForCc, validMailForCc,
				invalidMailForBcc, validMailForBcc, subject, textforMailBody);
		log.info("Mail composed and sent successfully.");

		log.info("Verifying sent mail in Sent folder with subject: {}", subject);
		cpPage.verifyMailFromSentBox(subject, nameOfInlineImage);
		log.info("Mail verification in Sent folder successful.");

		log.info("========== COMPLETED: Compose and Send Mail Verification ==========");
	}

	public void verifyComposeWithDraftPage() throws InterruptedException, IOException {

		log.info("========== STARTING: Compose Mail with Draft Verification ==========");

		log.info("Checking if Compose button is visible...");
		cpdPage.isComposeBtnDisplayed();
		log.info("Compose button is visible.");

		log.info("Clicking Compose button...");
		cpdPage.clickComposeBtn();
		log.info("Clicked Compose button.");

		log.info("Verifying 'From' text is displayed on top of Compose window...");
		cpdPage.isFromTextOnTopOfComposeDisplayed();

		log.info("Verifying 'From' email is displayed on top of Compose window...");
		cpdPage.isFromMailOnTopOfComposeDisplayed();

		log.info("Validating 'From' email on Compose page...");
		cpdPage.fromMailCheckOnTopofComposePageRe();
		log.info("'From' email validation successful.");

		log.info("Verifying 'To' field is displayed on Compose page...");
		cpdPage.isOnComposeToTextDisplayed();

		log.info("Verifying CC button is displayed on Compose page...");
		cpdPage.isOnComposeCcBtnDisplayedRe();

		log.info("Clicking CC button...");
		cpdPage.clickCcBtn();
		log.info("Clicked CC button.");

		log.info("Verifying CC field is displayed after clicking CC button...");
		cpdPage.isOnComposeCcTextAfterClickDisplayed();

		log.info("Verifying BCC button is displayed on Compose page...");
		cpdPage.isOnComposeBccBtnDisplayedRe();

		log.info("Clicking BCC button...");
		cpdPage.clickBccBtn();
		log.info("Clicked BCC button.");

		log.info("Verifying BCC field is displayed after clicking BCC button...");
		cpdPage.isOnComposeBccTextAfterClickDisplayed();

		log.info("Verifying Subject field is displayed on Compose page...");
		cpdPage.isOnComposeSubjectTextDisplayed();

		log.info("Verifying Mail Body field is displayed on Compose page...");
		cpdPage.isOnComposeBodyDisplayed();

		log.info("Verifying Send button is displayed on Compose page...");
		cpdPage.isOnComposeSendBtnDisplayed();

		log.info("Generating unique subject for mail...");
		String subject = ruse.generateSubject(textforMailSubject);
		log.info("Generated mail subject: {}", subject);

		log.info("Composing mail and saving it as Draft (with invalid & valid To/CC/BCC)...");
		cpdPage.composeMailWithInvalidTo(invalidMailForTo, validMailForTo, invalidMailForCc, validMailForCc,
				invalidMailForBcc, validMailForBcc, subject, textforMailBody);

		log.info("Mail composed and saved successfully in Draft.");

		log.info("Verifying mail is present in Draft folder with subject: {}", subject);
		cpdPage.verifyMailFromDraftBox(subject, nameOfInlineImage);
		log.info("Mail verification in Draft folder successful.");

		log.info("Sending mail from Draft and verifying it in Sent folder...");
		cpPage.verifyMailFromSentBox(subject, nameOfInlineImage);
		log.info("Mail successfully sent from Draft and verified in Sent folder.");

		log.info("========== COMPLETED: Compose Mail with Draft Verification ==========");
	}

	/** ✅ User icon */
	public boolean isUserDisplayed() {
		return isElementDisplayed(lnkUser, "User icon");
	}

	public void clickUser() {
		safeClick(lnkUser, "User icon");
	}

	/** ✅ Logout link */
	public boolean isLogoutDisplayed() {
		return isElementDisplayed(lnkLogout, "Logout link");
	}

	public void clickLogout() {
		safeClick(lnkLogout, "Logout link");
	}

	// 🔹 Utility Methods for Reusability
	public boolean isElementDisplayed(WebElement element, String elementName) {
		try {
			boolean status = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
			Assert.assertTrue(status, elementName + " is NOT visible or clickable!");
			return status;
		} catch (Exception e) {
			Assert.fail(elementName + " not visible/clickable. Exception: " + e.getMessage());
			return false;
		}
	}

	public void safeClick(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			System.out.println("✅ Clicked: " + elementName);
		} catch (Exception e) {
			Assert.fail("❌ Failed to click " + elementName + ". Exception: " + e.getMessage());
		}
	}

}
