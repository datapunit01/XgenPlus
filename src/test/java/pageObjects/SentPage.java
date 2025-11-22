package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class SentPage extends BasePage {
	public ReusableForUnread ruse;

	String userName = p.getProperty("email");
    String userNameWithPunnycode = p.getProperty("email1");
    String userName1 = IDN.toUnicode(userNameWithPunnycode);
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
    
    @FindBy(xpath = "//input[@id='viewFrom']")
	 public WebElement quickViewOwnerFrom;
	public SentPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
	}

	public boolean isSentBtnDisplayed() {
		return ruse.isSentBtnDisplayedRe();
	}

	public void clickSentBtn() {
		ruse.clickSentBtnRe();

	}

	public void switchToMyAccountFramesMcForSent() {
		ruse.switchToUnReadInboxFrameMc();

	}

	public boolean isChkMainBoxDisplayedForSent() {
		return ruse.isReChkMainBoxDisplayed();
	}

	public void clickChkMainBoxForSent() throws InterruptedException {
		ruse.clickChkMainBoxOfSentRe();
	}

	public boolean areAllEmailsSelectedViaAllForSent() throws InterruptedException {
		return ruse.areAllEmailsSelectedViaAllRe();
	}

	public void clickChkMainBoxforUncheckForSent() throws InterruptedException {
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);
	}

	public boolean areAllEmailsUnselectedViaAllForSent() {
		return ruse.areAllEmailsUnselectedViaAllRe();
	}

	public boolean isDrpDownBtnDisplayedForSent() {
		return ruse.isDrpDownBtnDisplayedRe();
	}

	public boolean isDrpDownReadBtnDisplayedForSent() {
		return ruse.isReDrpDownReadBtnDisplayed();
	}

	public boolean isDrpDownUnreadBtnDisplayedForSent() {
		return ruse.isReDrpDownUnreadBtnDisplayed();
	}

	public void clickDrpDownBtnOnForSent() throws InterruptedException {
		ruse.clickReDrpDownBtn();
		isDrpDownReadBtnDisplayedForSent();
		isDrpDownUnreadBtnDisplayedForSent();
		Thread.sleep(1000);
	}

	public void clickDrpDownBtnOffForSent() {
		ruse.clickDrpDownBtnRe();
	}

	public void clickEachCheckboxOneByOneForSent() throws InterruptedException {
		ruse.clickEachCheckboxOneByOneForSentRe();
	}

	public boolean isToggleBtnDisplayedForSent() {
		return ruse.isToggleBtnDisplayedRe();
	}

	public void clickToggleBtnForSent() throws InterruptedException {
		ruse.clickToggleBtnRe();
	}

	public boolean isShowInteractionDisplayedForSent() {
		return ruse.isShowInteractionDisplayedRe();
	}

	public boolean isSortBtnDisplayedForSent() {
		return ruse.isSortBtnDisplayedRe();
	}

	public void clickSortBtnForSent() throws InterruptedException {
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

	public void clickSortButtonByNameForSent() throws InterruptedException {
		String[] buttons = { "Date", "From", "Subject", "Size" };

		for (String btn : buttons) {
			ruse.clickSortButtonByNameRe(btn);
		}
		// ruse.clickDateBtnRe();

	}

	public void moveToTrashForSent() throws InterruptedException {
		ruse.moveToTrashSentRe();
	}

	public void clickDeleteBtnForSent() throws InterruptedException {
		ruse.deleteBtnSentRe();

	}

	public void clickunreadReadEamilBtnForSent() throws InterruptedException {
		ruse.unreadReadEamilSentRe();

	}

	public boolean isQuickViewBoxDisplayedForSent() {
		return ruse.isReQuickViewBoxDisplayed();
	}

	public void clickQuickViewBoxForSent() throws InterruptedException {
		ruse.clickReQuickViewBox();
		String quickViewToOwner = quickViewOwner.getAttribute("value");			
		String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");			
		Assert.assertTrue(quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName) || quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
		System.out.println(" Quick view page verified successfully");
	}

	public void reclickQuickViewBoxForSent() throws InterruptedException {
		ruse.clickReuncheckQuickViewBox();

	}

	public void clickforwordManyBtnForSent() throws InterruptedException {
		ruse.forwordManyBtnSentRe();

	}

}
