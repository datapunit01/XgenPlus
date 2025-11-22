package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class InboxPage extends BasePage {
	@FindBy(xpath = "//a[@title='Inbox']//span[contains(text(),'Inbox')]")
	public WebElement inboxBtn;

	@FindBy(xpath = "//input[@id='viewTo']")
	public WebElement quickViewOwner;

	public ReusableForUnread ruse;
	String userName = p.getProperty("email");

	public InboxPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
	}

	public boolean isInboxBtnDisplayed() {
		return ruse.isInboxDisplayedRe();
	}

	public void clickInboxBtn() {
		ruse.clickInboxRe();

	}

	public void inboxPageMenubeforCheckForInbox() throws InterruptedException {
		ruse.inboxPageMenubeforCheckRe();

	}

	public void switchToMyAccountFramesIcForInbox() {
		ruse.switchToMyAccountFramesIcRe();

	}

	public boolean isChkMainBoxDisplayedForInbox() {
		return ruse.isReChkMainBoxDisplayed();
	}

	public void clickChkMainBoxForInbox() throws InterruptedException {
		ruse.clickReChkMainBox();
	}

	public boolean areAllEmailsSelectedViaAllForInbox() throws InterruptedException {
		return ruse.areAllEmailsSelectedViaAllRe();
	}

	public void clickChkMainBoxforUncheckForInbox() throws InterruptedException {
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);
	}

	public boolean areAllEmailsUnselectedViaAllForInbox() {
		return ruse.areAllEmailsUnselectedViaAllRe();
	}

	public boolean isDrpDownBtnDisplayedForInbox() {
		return ruse.isDrpDownBtnDisplayedRe();
	}

	public void clickDrpDownBtnOnForInbox() throws InterruptedException {
		ruse.clickReDrpDownBtn();
		isDrpDownReadBtnDisplayedForInbox();
		isDrpDownUnreadBtnDisplayedForInbox();
		Thread.sleep(1000);
	}

	public void clickDrpDownBtnOffForInbox() {
		ruse.clickDrpDownBtnRe();
	}

	public boolean isDrpDownReadBtnDisplayedForInbox() {
		return ruse.isReDrpDownReadBtnDisplayed();
	}

	public boolean isDrpDownUnreadBtnDisplayedForInbox() {
		return ruse.isReDrpDownUnreadBtnDisplayed();
	}

	public void clickEachCheckboxOneByOneForInbox() throws InterruptedException {
		ruse.clickEachCheckboxOneByOneRe();
	}

	public boolean isToggleBtnDisplayedForInbox() {
		return ruse.isToggleBtnDisplayedRe();
	}

	public void clickToggleBtnForInbox() throws InterruptedException {
		ruse.clickToggleBtnRe();
	}

	public boolean isShowInteractionDisplayedForInbox() {
		return ruse.isShowInteractionDisplayedRe();
	}

	public boolean isSortBtnDisplayedForInbox() {
		return ruse.isSortBtnDisplayedRe();
	}

	public void clickSortBtnForInbox() throws InterruptedException {
		ruse.clickSortBtnRe();
		ruse.isSortByTextDisplayedRe();
		ruse.isDateBtnDisplayedRe();
		ruse.isFromBtnDisplayedRe();
		ruse.isSubjectBtnDisplayedRe();
		ruse.isSizeBtnDisplayedRe();
		ruse.isSortOrderTextDisplayedRe();
		ruse.isOldestOnTopBtnDisplayedRe();
		ruse.isNewestOnTopBtnDisplayedRe();

	}

	public void clickSortButtonByNameForInbox() throws InterruptedException {
		String[] buttons = { "Date", "From", "Subject", "Size" };

		for (String btn : buttons) {
			ruse.clickSortButtonByNameRe(btn);
		}
		// ruse.clickDateBtnRe();

	}

	public void moveToTrashForInbox() throws InterruptedException {
		ruse.moveToTrashInboxRe();
	}

	public void clickCancelBtnOfBlockPopupForInbox() throws InterruptedException {
		ruse.blockEmailCancelBtnInboxRe();

	}

	public void clickBlockBtnOfBlockPopupForInbox() throws InterruptedException {
		ruse.blockEmailBlockBtnInboxRe();

	}

	public void clickDeleteBtnForInbox() throws InterruptedException {
		ruse.deleteBtnInboxRe();

	}

	public void clickunreadReadEamilBtnForInbox() throws InterruptedException {
		ruse.unreadReadEamilInboxRe();

	}

	public boolean isQuickViewBoxDisplayedForInbox() {
		return ruse.isReQuickViewBoxDisplayed();
	}

	public void clickQuickViewBoxForInbox() throws InterruptedException {
		ruse.clickReQuickViewBox();
		String quickViewToOwner = quickViewOwner.getAttribute("value");
		Assert.assertTrue(quickViewToOwner.contains(userName),
				" Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
		System.out.println(" Quick view page verified successfully");
	}

	public void reclickQuickViewBoxForInbox() throws InterruptedException {
		ruse.clickReuncheckQuickViewBox();

	}

	public void clickforwordManyBtnForInbox() throws InterruptedException {
		ruse.forwordManyBtnInboxRe();

	}
}
