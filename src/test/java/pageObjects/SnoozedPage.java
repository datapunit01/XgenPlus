package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class SnoozedPage extends BasePage {
	public ReusableForUnread ruse;

	String userName = p.getProperty("email");
	String userNameWithPunnycode = p.getProperty("email1");
	String userName1 = IDN.toUnicode(userNameWithPunnycode);

	@FindBy(xpath = "//input[@id='viewTo']")
	public WebElement quickViewOwner;

	@FindBy(xpath = "//input[@id='viewFrom']")
	public WebElement quickViewOwnerFrom;

	public SnoozedPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
	}

	public boolean isSnoozedBtnDisplayedForSnoozedRe() {
		return ruse.isSnoozedBtnDisplayedRe();
	}

	public void clickSnoozedBtnForSnoozed() {
		ruse.clickSnoozedBtnRe();

	}

	public void switchToMyAccountFramesMcForSnoozed() {
		ruse.switchToUnReadInboxFrameMc();

	}

	public boolean isChkMainBoxDisplayedForSnoozed() {
		return ruse.isReChkMainBoxDisplayed();
	}

	public void clickChkMainBoxForSnoozed() throws InterruptedException {
		ruse.clickSnoozedRe();
	}

	public boolean areAllEmailsSelectedViaAllForSnoozed() throws InterruptedException {
		return ruse.areAllEmailsSelectedViaAllRe();
	}

	public void clickChkMainBoxforUncheckForSnoozed() throws InterruptedException {
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);
	}

	public boolean areAllEmailsUnselectedViaAllForSnoozed() {
		return ruse.areAllEmailsUnselectedViaAllRe();
	}

	public void clickEachCheckboxOneByOneForSnoozed() throws InterruptedException {
		ruse.clickEachCheckboxOneByOneSnoozedRe();
	}

	public boolean isToggleBtnDisplayedForSnoozed() {
		return ruse.isToggleBtnDisplayedRe();
	}

	public void clickToggleBtnForSnoozed() throws InterruptedException {
		ruse.clickToggleBtnRe();
	}

	public boolean isShowInteractionBtnDisplayedForSnoozed() {
		return ruse.isShowInteractionDisplayedRe();
	}

	public boolean isSortBtnDisplayedForSnoozed() {
		return ruse.isSortBtnDisplayedRe();
	}

	public void clickSortBtnForSnoozed() throws InterruptedException {
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

	public void clickSortButtonByNameForSnoozed() throws InterruptedException {
		String[] buttons = { "Date", "From", "Subject", "Size" };

		for (String btn : buttons) {
			ruse.clickSortButtonByNameRe(btn);
		}
		// ruse.clickDateBtnRe();

	}
	
	public void moveToTrashForSnoozed() throws InterruptedException {
		ruse.moveToInboxFromSnoozedRe();
	}

	public void clickCancelBtnOfBlockPopupForSnoozed() throws InterruptedException {
		ruse.blockEmailCancelBtnSnoozedRe();

	}

	public void clickBlockBtnOfBlockPopupForSnoozed() throws InterruptedException {
		ruse.blockEmailBlockBtnSnoozedRe();

	}

	public void clickDeleteBtnForSnoozed() throws InterruptedException {
		ruse.deleteBtnSnoozedRe();

	}
	
	public boolean isQuickViewBoxDisplayedForSnoozed() {
		return ruse.isReQuickViewBoxDisplayed();
	}

	public void clickQuickViewBoxForSnoozed() throws InterruptedException {
		ruse.clickReQuickViewBox();
		String quickViewToOwner = quickViewOwner.getAttribute("value");
		String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");
		Assert.assertTrue(
				quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName)
						|| quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1),
				" Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
		System.out.println(" Quick view page verified successfully");
	}

	public void reclickQuickViewBoxForSnoozed() throws InterruptedException {
		ruse.clickReuncheckQuickViewBox();

	}

	public void clickforwordManyBtnForSnoozed() throws InterruptedException {
		ruse.forwordManyBtnSnoozedRe();

	}

	

}
