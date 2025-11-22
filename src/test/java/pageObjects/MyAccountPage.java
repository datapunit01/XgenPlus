package pageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

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

	public final String expectedTitle;

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

	}

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
		System.out.println(
				" From Here Inbox Unread Page Will Start -----------------------------------------------------------------------------------------------------------------------");

		if (unread.isUnReadInboxDisplayedForUnReadInbox()) {
			unread.clickUnReadInboxForUnReadInbox();
			System.out.println("Unread Inbox opened successfully.");
		} else {
			System.out.println("Unread Inbox not visible!");
			Thread.sleep(3000);
		}

		unread.switchToFrameMcForUnReadInbox();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 5) {
			unread.isNewMailTextDisplayedForUnReadInbox();
			unread.isChkMainBoxDisplayedForUnReadInbox(); // Go to unread inbox
			unread.clickChkMainBoxForUnReadInbox(); // Click select all
	/*		Assert.assertTrue(unread.areAllEmailsSelectedViaAllForUnReadInbox(), " Not all emails were selected.");
			unread.clickChkMainBoxforUncheckForUnReadInbox();
			Assert.assertTrue(unread.areAllEmailsUnselectedViaAllForUnReadInbox(), " all emails were selected.");
			unread.isDrpDownBtnDisplayedForUnReadInbox();
			unread.clickDrpDownBtnForUnReadInbox();
			unread.clickDrpDownBtnOffForUnReadInbox();
			unread.clickEachCheckboxOneByOneForUnReadInbox();
			unread.isToggleBtnDisplayedForUnReadInbox();
			unread.clickToggleBtnForUnReadInbox();
			unread.isShowInteractionDisplayedForUnReadInbox();
			unread.isSortBtnDisplayedForUnReadInbox();
			unread.clickSortBtnForUnReadInbox();
			unread.clickSortButtonByNameForUnReadInbox();*/
			unread.moveToTrashForUnReadInbox();
			unread.clickCancelBtnOfBlockPopupForUnReadInbox();
			unread.clickBlockBtnOfBlockPopupForUnReadInbox();
			unread.clickDeleteBtnForUnReadInbox();
			unread.clickunreadReadEamilBtnForUnReadInbox();
			unread.isQuickViewBoxDisplayedForUnReadInbox();
			unread.clickQuickViewBoxForUnReadInbox();
			unread.reclickQuickViewBoxForUnReadInbox();
			unread.clickforwordManyBtnForUnReadInbox();
		} else {
			System.out.println(
					" Total Number of Emails In Inbox Unread Page are Less than 5 so we can not proceed with this page.");
			Assert.assertTrue(count >= 5,
					" Total Number of Emails In Inbox Unread Page are Less than 5 so we can not proceed with this page.");
		}
	}

/*	public void verifyInboxPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Inbox Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		inpage.isInboxBtnDisplayed();
		inpage.clickInboxBtn();
		inpage.switchToMyAccountFramesIcForInbox();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			inpage.inboxPageMenubeforCheckForInbox();
			inpage.isChkMainBoxDisplayedForInbox();
			inpage.clickChkMainBoxForInbox();
			Assert.assertTrue(inpage.areAllEmailsSelectedViaAllForInbox(), " Not all emails were selected.");
			inpage.clickChkMainBoxforUncheckForInbox();
			Assert.assertTrue(inpage.areAllEmailsUnselectedViaAllForInbox(), " all emails were selected.");
			inpage.isDrpDownBtnDisplayedForInbox();
			inpage.clickDrpDownBtnOnForInbox();
			inpage.clickDrpDownBtnOffForInbox();
			inpage.clickEachCheckboxOneByOneForInbox();
			inpage.isToggleBtnDisplayedForInbox();
			inpage.clickToggleBtnForInbox();
			inpage.isShowInteractionDisplayedForInbox();
			inpage.isSortBtnDisplayedForInbox();
			inpage.clickSortBtnForInbox();
			inpage.clickSortButtonByNameForInbox();
			inpage.moveToTrashForInbox();
			inpage.clickCancelBtnOfBlockPopupForInbox();
			inpage.clickBlockBtnOfBlockPopupForInbox();
			inpage.clickDeleteBtnForInbox();
			inpage.clickunreadReadEamilBtnForInbox();
			inpage.isQuickViewBoxDisplayedForInbox();
			inpage.clickQuickViewBoxForInbox();
			inpage.reclickQuickViewBoxForInbox();
			inpage.clickforwordManyBtnForInbox();
		} else {
			System.out.println(
					" Total Number of Emails In Inbox Page are Less than 4 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Inbox Page are Less than 4 so we can not proceed with this page.");
		}

	}

	public void verifySentPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Sent Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		stPage.isSentBtnDisplayed();
		stPage.clickSentBtn();
		stPage.switchToMyAccountFramesMcForSent();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			stPage.isChkMainBoxDisplayedForSent();
			stPage.clickChkMainBoxForSent();
			Assert.assertTrue(stPage.areAllEmailsSelectedViaAllForSent(), " Not all emails were selected.");
			stPage.clickChkMainBoxforUncheckForSent();
			Assert.assertTrue(stPage.areAllEmailsUnselectedViaAllForSent(), " all emails were selected.");
			stPage.isDrpDownBtnDisplayedForSent();
			stPage.clickDrpDownBtnOnForSent();
			stPage.clickDrpDownBtnOffForSent();
			stPage.clickEachCheckboxOneByOneForSent();
			stPage.isToggleBtnDisplayedForSent();
			stPage.clickToggleBtnForSent();
			stPage.isShowInteractionDisplayedForSent();
			stPage.isSortBtnDisplayedForSent();
			stPage.clickSortBtnForSent();
			stPage.clickSortButtonByNameForSent();
			stPage.moveToTrashForSent();
			stPage.clickDeleteBtnForSent();
			stPage.clickunreadReadEamilBtnForSent();
			stPage.isQuickViewBoxDisplayedForSent();
			stPage.clickQuickViewBoxForSent();
			stPage.reclickQuickViewBoxForSent();
			stPage.clickforwordManyBtnForSent();
		} else {
			System.out.println(
					" Total Number of Emails In Sent Page are Less than 4 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Sent Page are Less than 4 so we can not proceed with this page.");
		}

	}
	
	public void verifySentUnreadPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Sent Unread Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		stuPage.isSentUnreadBtnDisplayed();
		stuPage.clickSentUnreadBtn();
		stuPage.switchToMyAccountFramesMcForSentUnread();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			stuPage.isChkMainBoxDisplayedForSentUnread();
			stuPage.clickChkMainBoxForSentUnread();
			Assert.assertTrue(stuPage.areAllEmailsSelectedViaAllForSentUnread(), " Not all emails were selected.");
			stuPage.clickChkMainBoxforUncheckForSentUnread();
			Assert.assertTrue(stuPage.areAllEmailsUnselectedViaAllForSentUnread(), " all emails were selected.");
			stuPage.isDrpDownBtnDisplayedForSentUnread();
			stuPage.clickDrpDownBtnOnForSentUnread();
			stuPage.clickDrpDownBtnOffForSentUnread();
			stuPage.clickEachCheckboxOneByOneForSentUnread();
			stuPage.isToggleBtnDisplayedForSentUnread();
			stuPage.clickToggleBtnForSentUnread();
			stuPage.isShowInteractionDisplayedForSentUnread();
			stuPage.isSortBtnDisplayedForSentUnread();
			stuPage.clickSortBtnForSentUnread();
			stuPage.clickSortButtonByNameForSentUnread();
			stuPage.moveToTrashForSentUnread();
			stuPage.clickDeleteBtnForSentUnread();
			stuPage.clickunreadReadEamilBtnForSentUnread();
			stuPage.isQuickViewBoxDisplayedForSentUnread();
			stuPage.clickQuickViewBoxForSentUnread();
			stuPage.reclickQuickViewBoxForSentUnread();
			stuPage.clickforwordManyBtnForSentUnread();
		} else {
			System.out.println(
					" Total Number of Emails In Sent Unread Page are Less than 4 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Sent Unread Page are Less than 4 so we can not proceed with this page.");
		}

	}

	public void verifyDraftPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Draft Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		dtPage.isDraftBtnDisplayedForDraft();
		dtPage.clickDraftBtnForDraft();
		dtPage.switchToMyAccountFramesMcForDraft();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 2) {
			dtPage.isChkMainBoxDisplayedForDraft();
			dtPage.clickChkMainBoxForDraft();
			Assert.assertTrue(dtPage.areAllEmailsSelectedViaAllForDraft(), " Not all emails were selected.");
			dtPage.clickChkMainBoxforUncheckForDraft();
			Assert.assertTrue(dtPage.areAllEmailsUnselectedViaAllForDraft(), " all emails were selected.");
			dtPage.clickEachCheckboxOneByOneForDraft();
			dtPage.isToggleBtnDisplayedForDraft();
			dtPage.clickToggleBtnForDraft();
			dtPage.isSortBtnDisplayedForDraft();
			dtPage.clickSortBtnForDraft();
			dtPage.clickSortButtonByNameForDraft();
			dtPage.moveToTrashForDraft();
			dtPage.clickDeleteBtnForDraft();
		} else {
			System.out.println(
					" Total Number of Emails In Draft Page are Less than 2 so we can not proceed with this page.");
			Assert.assertTrue(count >= 2,
					" Total Number of Emails In Draft Page are Less than 2 so we can not proceed with this page.");
		}

	}

	public void verifyTrashPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Trash Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		thPage.isTrashBtnDisplayed();
		thPage.clickTrashBtn();
		thPage.switchToMyAccountFramesMcForTrash();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			thPage.isChkMainBoxDisplayedForTrash();
			thPage.clickChkMainBoxForTrash();
			Assert.assertTrue(thPage.areAllEmailsSelectedViaAllForTrash(), " Not all emails were selected.");
			thPage.clickChkMainBoxforUncheckForTrash();
			Assert.assertTrue(thPage.areAllEmailsUnselectedViaAllForTrash(), " all emails were selected.");
			thPage.isDrpDownBtnDisplayedForTrash();
			thPage.clickDrpDownBtnOnForTrash();
			thPage.clickDrpDownBtnOffForTrash();
			thPage.clickEachCheckboxOneByOneForTrash();
			thPage.isToggleBtnDisplayedForTrash();
			thPage.clickToggleBtnForTrash();
			thPage.isSortBtnDisplayedForTrash();
			thPage.clickSortBtnForTrash();
			thPage.clickSortButtonByNameForTrash();
			thPage.moveToTrashForTrash();
			thPage.clickCancelBtnOfBlockPopupForTrash();
			thPage.clickBlockBtnOfBlockPopupForTrash();
			thPage.clickDeleteBtnForTrash();
			thPage.clickunreadReadEamilBtnForTrash();
			thPage.isQuickViewBoxDisplayedForTrash();
			thPage.clickQuickViewBoxForTrash();
			thPage.reclickQuickViewBoxForTrash();
			thPage.clickforwordManyBtnForTrash();
		} else {
			System.out.println(
					" Total Number of Emails In Trash Page are Less than 2 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Trash Page are Less than 2 so we can not proceed with this page.");
		}

	}

	public void verifyTrashUnreadPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Trash Unread Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		tuPage.isTrashUnreadBtnDisplayed();
		tuPage.clickTrashUnreadBtn();
		tuPage.switchToMyAccountFramesMcForTrashUnread();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 5) {
			tuPage.isChkMainBoxDisplayedForTrashUnread();
			tuPage.clickChkMainBoxForTrashUnread();
			Assert.assertTrue(tuPage.areAllEmailsSelectedViaAllForTrashUnread(), " Not all emails were selected.");
			tuPage.clickChkMainBoxforUncheckForTrashUnread();
			Assert.assertTrue(tuPage.areAllEmailsUnselectedViaAllForTrashUnread(), " all emails were selected.");
			tuPage.isDrpDownBtnDisplayedForTrashUnread();
			tuPage.clickDrpDownBtnOnForTrashUnread();
			tuPage.clickDrpDownBtnOffForTrashUnread();
			tuPage.clickEachCheckboxOneByOneForTrashUnread();
			tuPage.isToggleBtnDisplayedForTrashUnread();
			tuPage.clickToggleBtnForTrashUnread();
			tuPage.isSortBtnDisplayedForTrashUnread();
			tuPage.clickSortBtnForTrashUnread();
			tuPage.clickSortButtonByNameForTrashUnread();
			tuPage.moveToTrashForTrashUnread();
			tuPage.clickCancelBtnOfBlockPopupForTrashUnread();
			tuPage.clickBlockBtnOfBlockPopupForTrashUnread();
			tuPage.clickDeleteBtnForTrashUnread();
			tuPage.clickunreadReadEamilBtnForTrashUnread();
			tuPage.isQuickViewBoxDisplayedForTrashUnread();
			tuPage.clickQuickViewBoxForTrashUnread();
			tuPage.reclickQuickViewBoxForTrashUnread();
			tuPage.clickforwordManyBtnForTrashUnread();
		} else {
			System.out.println(
					" Total Number of Emails In Trash Unread Page are Less than 5 so we can not proceed with this page.");
			Assert.assertTrue(count >= 5,
					" Total Number of Emails In Trash Unread Page are Less than 5 so we can not proceed with this page.");
		}

	}

	public void verifyTemplatePage() throws IOException, InterruptedException {

		System.out.println(
				" From Here Template Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		ttPage.isTemplateBtnDisplayed();
		ttPage.clickTemplateBtn();
		ttPage.switchToMyAccountFramesMcForTemplate();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 2) {
			ttPage.isChkMainBoxDisplayedForTemplate();
			ttPage.clickChkMainBoxForTemplate();
			Assert.assertTrue(ttPage.areAllEmailsSelectedViaAllForTemplate(), " Not all emails were selected.");
			ttPage.clickChkMainBoxforUncheckForTemplate();
			Assert.assertTrue(ttPage.areAllEmailsUnselectedViaAllForTemplate(), " all emails were selected.");
			ttPage.clickEachCheckboxOneByOneForTemplate();
			ttPage.isToggleBtnDisplayedForTemplate();
			ttPage.clickToggleBtnForTemplate();
			ttPage.isSortBtnDisplayedForTemplate();
			ttPage.clickSortBtnForTemplate();
			ttPage.clickSortButtonByNameForTemplate();
			ttPage.moveToTrashForTemplate();
			ttPage.clickDeleteBtnForTemplate();

		} else {
			System.out.println(
					" Total Number of Emails In Template Page are Less than 2 so we can not proceed with this page.");
			Assert.assertTrue(count >= 2,
					" Total Number of Emails In Template Page are Less than 2 so we can not proceed with this page.");
		}

	}

	public void verifySnoozedPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Snoozed Will Start -----------------------------------------------------------------------------------------------------------------------");
		sdPage.isSnoozedBtnDisplayedForSnoozedRe();
		sdPage.clickSnoozedBtnForSnoozed();
		sdPage.switchToMyAccountFramesMcForSnoozed();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			sdPage.isChkMainBoxDisplayedForSnoozed();
			sdPage.clickChkMainBoxForSnoozed();
			Assert.assertTrue(sdPage.areAllEmailsSelectedViaAllForSnoozed(), " Not all emails were selected.");
			sdPage.clickChkMainBoxforUncheckForSnoozed();
			Assert.assertTrue(sdPage.areAllEmailsUnselectedViaAllForSnoozed(), " all emails were selected.");
			sdPage.clickEachCheckboxOneByOneForSnoozed();
			sdPage.isToggleBtnDisplayedForSnoozed();
			sdPage.clickToggleBtnForSnoozed();
			sdPage.isShowInteractionBtnDisplayedForSnoozed();
			sdPage.isSortBtnDisplayedForSnoozed();
			sdPage.clickSortBtnForSnoozed();
			sdPage.clickSortButtonByNameForSnoozed();
			sdPage.moveToTrashForSnoozed();
			sdPage.clickCancelBtnOfBlockPopupForSnoozed();
			sdPage.clickBlockBtnOfBlockPopupForSnoozed();
			sdPage.clickDeleteBtnForSnoozed();
			sdPage.isQuickViewBoxDisplayedForSnoozed();
			sdPage.clickQuickViewBoxForSnoozed();
			sdPage.reclickQuickViewBoxForSnoozed();
			sdPage.clickforwordManyBtnForSnoozed();
		} else {
			System.out.println(
					" Total Number of Emails In Snoozed Page are Less than 4 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Snoozed Page are Less than 4 so we can not proceed with this page.");
		}

	}

	public void verifySpamPromoPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Spam-Promo Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		spPage.isSpamPromoBtnDisplayed();
		spPage.clickSpamPromoBtn();
		spPage.switchToMyAccountFramesMcForSpamPromo();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 4) {
			spPage.isChkMainBoxDisplayedForSpamPromo();
			spPage.clickChkMainBoxForSpamPromo();
			Assert.assertTrue(spPage.areAllEmailsSelectedViaAllForSpamPromo(), " Not all emails were selected.");
			spPage.clickChkMainBoxforUncheckForSpamPromo();
			Assert.assertTrue(spPage.areAllEmailsUnselectedViaAllForSpamPromo(), " all emails were selected.");
			spPage.isDrpDownBtnDisplayedForSpamPromo();
			spPage.clickDrpDownBtnOnForSpamPromo();
			spPage.clickDrpDownBtnOffForSpamPromo();
			spPage.clickEachCheckboxOneByOneForSpamPromo();
			spPage.isToggleBtnDisplayedForSpamPromo();
			spPage.clickToggleBtnForSpamPromo();
			spPage.isShowInteractionDisplayedForSpamPromo();
			spPage.isSortBtnDisplayedForSpamPromo();
			spPage.clickSortBtnForSpamPromo();
			spPage.clickSortButtonByNameForSpamPromo();
			spPage.moveToTrashForSpamPromo();
			spPage.clickCancelBtnOfBlockPopupForSpamPromo();
			spPage.clickBlockBtnOfBlockPopupForSpamPromo();
			spPage.clickDeleteBtnForSpamPromo();
			spPage.clickunreadReadEamilBtnForSpamPromo();
			spPage.isQuickViewBoxDisplayedForSpamPromo();
			spPage.clickQuickViewBoxForSpamPromo();
			spPage.reclickQuickViewBoxForSpamPromo();

		} else {
			System.out.println(
					" Total Number of Emails In Spam Promo Page are Less than 4 so we can not proceed with this page.");
			Assert.assertTrue(count >= 4,
					" Total Number of Emails In Spam Promo Page are Less than 4 so we can not proceed with this page.");

		}

	}

	public void verifySpamPromoUnreadPage() throws IOException, InterruptedException {
		System.out.println(
				" From Here Spam-Promo Unread Inbox Page Will Start -----------------------------------------------------------------------------------------------------------------------");
		spuPage.isSpamPromoUnreadBtnDisplayed();
		spuPage.clickSpamPromoUnreadBtn();
		spuPage.switchToMyAccountFramesMcForSpamPromoUnread();
		int count = ruse.areCountOfTotalMailInPageRe();
		if (count >= 5) {
			spuPage.isChkMainBoxDisplayedForSpamPromoUnread();
			spuPage.clickChkMainBoxForSpamPromoUnread();
			Assert.assertTrue(spuPage.areAllEmailsSelectedViaAllForSpamPromoUnread(), " Not all emails were selected.");
			spuPage.clickChkMainBoxforUncheckForSpamPromoUnread();
			Assert.assertTrue(spuPage.areAllEmailsUnselectedViaAllForSpamPromoUnread(), " all emails were selected.");
			spuPage.isDrpDownBtnDisplayedForSpamPromoUnread();
			spuPage.clickDrpDownBtnOnForSpamPromoUnread();
			spuPage.clickDrpDownBtnOffForSpamPromoUnread();
			spuPage.clickEachCheckboxOneByOneForSpamPromoUnread();
			spuPage.isToggleBtnDisplayedForSpamPromoUnread();
			spuPage.clickToggleBtnForSpamPromoUnread();
			spuPage.isShowInteractionDisplayedForSpamPromoUnread();
			spuPage.isSortBtnDisplayedForSpamPromoUnread();
			spuPage.clickSortBtnForSpamPromoUnread();
			spuPage.clickSortButtonByNameForSpamPromoUnread();
			spuPage.moveToTrashForSpamPromoUnread();
			spuPage.clickCancelBtnOfBlockPopupForSpamPromoUnread();
			spuPage.clickBlockBtnOfBlockPopupForSpamPromoUnread();
			spuPage.clickDeleteBtnForSpamPromoUnread();
			spuPage.clickunreadReadEamilBtnForSpamPromoUnread();
			spuPage.isQuickViewBoxDisplayedForSpamPromoUnread();
			spuPage.clickQuickViewBoxForSpamPromoUnread();
			spuPage.reclickQuickViewBoxForSpamPromoUnread();

		} else {
			System.out.println(
					" Total Number of Emails In Spam Promo Page are Less than 5 so we can not proceed with this page.");
			Assert.assertTrue(count >= 5,
					" Total Number of Emails In Spam Promo Page are Less than 5 so we can not proceed with this page.");

		}

	}*/
	
	

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
