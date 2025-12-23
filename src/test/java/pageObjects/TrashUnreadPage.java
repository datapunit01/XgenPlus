package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class TrashUnreadPage extends BasePage {
	public ReusableForUnread ruse;

	String userName = p.getProperty("email");
	String userNameWithPunnycode = p.getProperty("email1");
	String userName1 = IDN.toUnicode(userNameWithPunnycode);

	@FindBy(xpath = "//input[@id='viewTo']")
	public WebElement quickViewOwner;

	@FindBy(xpath = "//input[@id='viewFrom']")
	public WebElement quickViewOwnerFrom;

	public TrashUnreadPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
	}

	public boolean isTrashUnreadBtnDisplayed() {
		return ruse.isTrashUnreadBtnDisplayedRe();
	}

	public void clickTrashUnreadBtn() {
		ruse.clickTrashUnreadBtnRe();

	}

	public void switchToMyAccountFramesMcForTrashUnread() {
		ruse.switchToUnReadInboxFrameMc();

	}

	public boolean isChkMainBoxDisplayedForTrashUnread() {
		return ruse.isReChkMainBoxDisplayed();
	}

	public void clickChkMainBoxForTrashUnread() throws InterruptedException {
		ruse.clickReChkMainBox();
	}

	public boolean areAllEmailsSelectedViaAllForTrashUnread() throws InterruptedException {
		return ruse.areAllEmailsSelectedViaAllRe();
	}

	public void clickChkMainBoxforUncheckForTrashUnread() throws InterruptedException {
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);
	}

	public boolean areAllEmailsUnselectedViaAllForTrashUnread() {
		return ruse.areAllEmailsUnselectedViaAllRe();
	}

	public boolean isDrpDownBtnDisplayedForTrashUnread() {
		return ruse.isDrpDownBtnDisplayedRe();
	}

	public void clickDrpDownBtnOnForTrashUnread() throws InterruptedException {
		ruse.clickReDrpDownBtn();
		isDrpDownReadBtnDisplayedForTrashUnread();
		isDrpDownUnreadBtnDisplayedForTrashUnread();
		Thread.sleep(1000);
	}

	public void clickDrpDownBtnOffForTrashUnread() {
		ruse.clickDrpDownBtnRe();
	}

	public boolean isDrpDownReadBtnDisplayedForTrashUnread() {
		return ruse.isReDrpDownReadBtnDisplayed();
	}

	public boolean isDrpDownUnreadBtnDisplayedForTrashUnread() {
		return ruse.isReDrpDownUnreadBtnDisplayed();
	}

	public void clickEachCheckboxOneByOneForTrashUnread() throws InterruptedException {
		ruse.clickEachCheckboxOneByOneRe();
	}

	public boolean isToggleBtnDisplayedForTrashUnread() {
		return ruse.isToggleBtnDisplayedRe();
	}

	public void clickToggleBtnForTrashUnread() throws InterruptedException {
		ruse.clickToggleBtnRe();
	}

	public boolean isSortBtnDisplayedForTrashUnread() {
		return ruse.isSortBtnDisplayedRe();
	}

	public void clickSortBtnForTrashUnread() throws InterruptedException {
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

	public void clickSortButtonByNameForTrashUnread() throws InterruptedException {
		String[] buttons = { "Date", "From", "Subject", "Size" };

		for (String btn : buttons) {
			ruse.clickSortButtonByNameRe(btn);
		}
		// ruse.clickDateBtnRe();

	}

	public void moveToInboxFromTrashUnread() throws InterruptedException {
		ruse.moveToInboxFromTrashUnreadRe();
	}

	public void clickCancelBtnOfBlockPopupForTrashUnread() throws InterruptedException {
		ruse.blockEmailCancelBtnTrashUnreadRe();

	}

	public void clickBlockBtnOfBlockPopupForTrashUnread() throws InterruptedException {
		ruse.blockEmailBlockBtnTrashUnreadRe();

	}

	public void clickDeleteBtnForTrashUnread() throws InterruptedException {
		ruse.deleteBtnTrashUnreadRe();

	}

	public void clickunreadReadEamilBtnForTrashUnread() throws InterruptedException {
		ruse.unreadReadEamilTrashUnreadRe();

	}

	public boolean isQuickViewBoxDisplayedForTrashUnread() {
		return ruse.isReQuickViewBoxDisplayed();
	}

	public void clickQuickViewBoxForTrashUnread() throws InterruptedException {
		ruse.clickReQuickViewBox();
		String quickViewToOwner = quickViewOwner.getAttribute("value");
		String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");
		Assert.assertTrue(
				quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName)
						|| quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1),
				" Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
		System.out.println(" Quick view page verified successfully");
	}

	public void reclickQuickViewBoxForTrashUnread() throws InterruptedException {
		ruse.clickReuncheckQuickViewBox();

	}

	public void clickforwordManyBtnForTrashUnread() throws InterruptedException {
		ruse.forwordManyBtnTrashUnreadRe();

	}

}
