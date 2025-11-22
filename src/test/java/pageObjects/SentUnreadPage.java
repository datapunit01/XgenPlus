package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class SentUnreadPage extends BasePage
{
	public ReusableForUnread ruse; 	
	
	String userName = p.getProperty("email");
    String userNameWithPunnycode = p.getProperty("email1");
    String userName1 = IDN.toUnicode(userNameWithPunnycode);
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
    
    @FindBy(xpath = "//input[@id='viewFrom']")
	 public WebElement quickViewOwnerFrom;
	
	public SentUnreadPage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	public boolean isSentUnreadBtnDisplayed() 
	 {
		 return ruse.isSentUnreadBtnDisplayedRe();
	 }

	 public void clickSentUnreadBtn()
	 {
		 ruse.clickSentUnreadBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForSentUnread()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }
	 
	 public boolean isChkMainBoxDisplayedForSentUnread() {
			return ruse.isReChkMainBoxDisplayed();
		}

		public void clickChkMainBoxForSentUnread() throws InterruptedException {
			ruse.clickChkMainBoxOfSentRe();
		}

		public boolean areAllEmailsSelectedViaAllForSentUnread() throws InterruptedException {
			return ruse.areAllEmailsSelectedViaAllRe();
		}

		public void clickChkMainBoxforUncheckForSentUnread() throws InterruptedException {
			ruse.clickReChkMainBoxforUncheck();
			Thread.sleep(1500);
		}

		public boolean areAllEmailsUnselectedViaAllForSentUnread() {
			return ruse.areAllEmailsUnselectedViaAllRe();
		}

		public boolean isDrpDownBtnDisplayedForSentUnread() {
			return ruse.isDrpDownBtnDisplayedRe();
		}

		public boolean isDrpDownReadBtnDisplayedForSentUnread() {
			return ruse.isReDrpDownReadBtnDisplayed();
		}

		public boolean isDrpDownUnreadBtnDisplayedForSentUnread() {
			return ruse.isReDrpDownUnreadBtnDisplayed();
		}

		public void clickDrpDownBtnOnForSentUnread() throws InterruptedException {
			ruse.clickReDrpDownBtn();
			isDrpDownReadBtnDisplayedForSentUnread();
			isDrpDownUnreadBtnDisplayedForSentUnread();
			Thread.sleep(1000);
		}

		public void clickDrpDownBtnOffForSentUnread() {
			ruse.clickDrpDownBtnRe();
		}

		public void clickEachCheckboxOneByOneForSentUnread() throws InterruptedException {
			ruse.clickEachCheckboxOneByOneForSentRe();
		}

		public boolean isToggleBtnDisplayedForSentUnread() {
			return ruse.isToggleBtnDisplayedRe();
		}

		public void clickToggleBtnForSentUnread() throws InterruptedException {
			ruse.clickToggleBtnRe();
		}

		public boolean isShowInteractionDisplayedForSentUnread() {
			return ruse.isShowInteractionDisplayedRe();
		}

		public boolean isSortBtnDisplayedForSentUnread() {
			return ruse.isSortBtnDisplayedRe();
		}

		public void clickSortBtnForSentUnread() throws InterruptedException {
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

		public void clickSortButtonByNameForSentUnread() throws InterruptedException {
			String[] buttons = { "Date", "From", "Subject", "Size" };

			for (String btn : buttons) {
				ruse.clickSortButtonByNameRe(btn);
			}
			// ruse.clickDateBtnRe();

		}
		
		public void moveToTrashForSentUnread() throws InterruptedException {
			ruse.moveToTrashSentUnreadRe();
		}
		
		public void clickDeleteBtnForSentUnread() throws InterruptedException {
			ruse.deleteBtnSentUnreadRe();

		}
		
		public void clickunreadReadEamilBtnForSentUnread() throws InterruptedException {
			ruse.unreadReadEmailSentUnreadRe();

		}
		
		public boolean isQuickViewBoxDisplayedForSentUnread() {
			return ruse.isReQuickViewBoxDisplayed();
		}
		
		public void clickQuickViewBoxForSentUnread() throws InterruptedException {
			ruse.clickReQuickViewBox();
			String quickViewToOwner = quickViewOwner.getAttribute("value");			
			String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");			
			Assert.assertTrue(quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName) || quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
			System.out.println(" Quick view page verified successfully");
		}

		public void reclickQuickViewBoxForSentUnread() throws InterruptedException {
			ruse.clickReuncheckQuickViewBox();

		}

		public void clickforwordManyBtnForSentUnread() throws InterruptedException {
			ruse.forwordManyBtnSentRe();

		}


}
