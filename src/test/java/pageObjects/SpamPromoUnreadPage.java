package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class SpamPromoUnreadPage extends BasePage
{
	public ReusableForUnread ruse;    
    String userName = p.getProperty("email");
    String userNameWithPunnycode = p.getProperty("email1");
    String userName1 = IDN.toUnicode(userNameWithPunnycode);
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
    
    @FindBy(xpath = "//input[@id='viewFrom']")
	 public WebElement quickViewOwnerFrom;
      
	public SpamPromoUnreadPage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	public boolean isSpamPromoUnreadBtnDisplayed() 
	 {
		 return ruse.isSpamPromoUnreadBtnDisplayedRe();
	 }

	 public void clickSpamPromoUnreadBtn()
	 {
		 ruse.clickSpamPromoUnreadBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForSpamPromoUnread()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }

	 public boolean isChkMainBoxDisplayedForSpamPromoUnread()
	    {    	
	        return ruse.isReChkMainBoxDisplayed();
	    }
		
		public void clickChkMainBoxForSpamPromoUnread() throws InterruptedException 
	    {
	    	ruse.clickChkMainBoxSpamPromoRe();
	    }

		public boolean areAllEmailsSelectedViaAllForSpamPromoUnread() throws InterruptedException 
	    {	
	       return ruse.areAllEmailsSelectedViaAllRe();        
	    }
		
		public void clickChkMainBoxforUncheckForSpamPromoUnread() throws InterruptedException 
		{
			ruse.clickReChkMainBoxforUncheck();
			Thread.sleep(1500);   
	    }
		
		public boolean areAllEmailsUnselectedViaAllForSpamPromoUnread() 
	    {
	        return ruse.areAllEmailsUnselectedViaAllRe();           
	    }
		
		public boolean isDrpDownBtnDisplayedForSpamPromoUnread()
	    {    	
	        return ruse.isDrpDownBtnDisplayedRe();
	    }
		
		public void clickDrpDownBtnOnForSpamPromoUnread() throws InterruptedException 
		{
		    ruse.clickReDrpDownBtn();
		    isDrpDownReadBtnDisplayedForSpamPromoUnread();
		    isDrpDownUnreadBtnDisplayedForSpamPromoUnread();
		    Thread.sleep(1000);
		}
		
		public void clickDrpDownBtnOffForSpamPromoUnread() 
		{
		    ruse.clickDrpDownBtnRe();	    
		}
		
		public boolean isDrpDownReadBtnDisplayedForSpamPromoUnread()
		{    	
		    return ruse.isReDrpDownReadBtnDisplayed();
		}
		
		public boolean isDrpDownUnreadBtnDisplayedForSpamPromoUnread()
	    {    	
	        return ruse.isReDrpDownUnreadBtnDisplayed();
	    }
		
		public void clickEachCheckboxOneByOneForSpamPromoUnread() throws InterruptedException 
		{      
		   ruse.clickEachCheckboxOneByOneSpamPromoRe();
		}
		
		 public boolean isToggleBtnDisplayedForSpamPromoUnread() 
		 {
			return ruse.isToggleBtnDisplayedRe();
		 }
		 
		 public void clickToggleBtnForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.clickToggleBtnRe();			
		 }
		 
		 public boolean isShowInteractionDisplayedForSpamPromoUnread() 
		 {
			return ruse.isShowInteractionDisplayedRe();
		 }
		 
		 public boolean isSortBtnDisplayedForSpamPromoUnread() 
		 {
			return ruse.isSortBtnDisplayedRe();
		 }
		 
		 public void clickSortBtnForSpamPromoUnread() throws InterruptedException 
		 {
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
		 
		 public void clickSortButtonByNameForSpamPromoUnread() throws InterruptedException 
		 {
			 String[] buttons = {"Date", "From", "Subject", "Size"};

			 for (String btn : buttons) {
			     ruse.clickSortButtonByNameRe(btn);
			 }
			//ruse.clickDateBtnRe();
			
		 }	
		 
		 public void moveToTrashForSpamPromoUnread() throws InterruptedException
		    {
		        ruse.moveToTrashSpamPromoUnreadRe();
		    }
		 
		 public void clickCancelBtnOfBlockPopupForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.blockEmailCancelBtnSpamPromoUnreadRe();
			
		 }	
		 
		 public void clickBlockBtnOfBlockPopupForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.blockEmailBlockBtnSpamPromoUnreadRe();
			
		 }
		 
		 public void clickDeleteBtnForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.deleteBtnSpamPromoUnreadRe();
			
		 }
		 
		 public void clickunreadReadEamilBtnForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.unreadReadEamilSpamPromoUnreadRe();
			
		 }	
		 
		 public boolean isQuickViewBoxDisplayedForSpamPromoUnread() 
		 {
			return ruse.isReQuickViewBoxDisplayed();
		 }
		 
		 public void clickQuickViewBoxForSpamPromoUnread() throws InterruptedException 
		 {
			ruse.clickReQuickViewBox();
			String quickViewToOwner = quickViewOwner.getAttribute("value");			
			String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");			
			Assert.assertTrue(quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName) || quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
			System.out.println(" Quick view page verified successfully");
		 }
		 
		 public void reclickQuickViewBoxForSpamPromoUnread() throws InterruptedException 
			{
				ruse.clickReuncheckQuickViewBox();
				driver.navigate().refresh();       
				ruse.switchToMyAccountFrames();   
				
		    }
}
