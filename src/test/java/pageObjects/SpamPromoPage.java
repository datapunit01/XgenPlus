package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class SpamPromoPage extends BasePage
{	
	
		
	public ReusableForUnread ruse;    
    String userName = p.getProperty("email");
    String userNameWithPunnycode = p.getProperty("email1");
    String userName1 = IDN.toUnicode(userNameWithPunnycode);
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
    
    @FindBy(xpath = "//input[@id='viewFrom']")
	 public WebElement quickViewOwnerFrom;
      
	public SpamPromoPage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}


	 public boolean isSpamPromoBtnDisplayed() 
	 {
		 return ruse.isSpamPromoBtnDisplayedRe();
	 }

	 public void clickSpamPromoBtn()
	 {
		 ruse.clickSpamPromoBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForSpamPromo()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }
	 
	 public boolean isChkMainBoxDisplayedForSpamPromo()
	    {    	
	        return ruse.isReChkMainBoxDisplayed();
	    }
		
		public void clickChkMainBoxForSpamPromo() throws InterruptedException 
	    {
	    	ruse.clickChkMainBoxSpamPromoRe();
	    }
		
		public boolean areAllEmailsSelectedViaAllForSpamPromo() throws InterruptedException 
	    {	
	       return ruse.areAllEmailsSelectedViaAllRe();        
	    }
		
		public void clickChkMainBoxforUncheckForSpamPromo() throws InterruptedException 
		{
			ruse.clickReChkMainBoxforUncheck();
			Thread.sleep(1500);   
	    }
		
		public boolean areAllEmailsUnselectedViaAllForSpamPromo() 
	    {
	        return ruse.areAllEmailsUnselectedViaAllRe();           
	    }
		
		public boolean isDrpDownBtnDisplayedForSpamPromo()
	    {    	
	        return ruse.isDrpDownBtnDisplayedRe();
	    }
		
		public void clickDrpDownBtnOnForSpamPromo() throws InterruptedException 
		{
		    ruse.clickReDrpDownBtn();
		    isDrpDownReadBtnDisplayedForSpamPromo();
		    isDrpDownUnreadBtnDisplayedForSpamPromo();
		    Thread.sleep(1000);
		}
		
		public void clickDrpDownBtnOffForSpamPromo() 
		{
		    ruse.clickDrpDownBtnRe();	    
		}
		
		public boolean isDrpDownReadBtnDisplayedForSpamPromo()
		{    	
		    return ruse.isReDrpDownReadBtnDisplayed();
		}
		
		public boolean isDrpDownUnreadBtnDisplayedForSpamPromo()
	    {    	
	        return ruse.isReDrpDownUnreadBtnDisplayed();
	    }
		
		public void clickEachCheckboxOneByOneForSpamPromo() throws InterruptedException 
		{      
		   ruse.clickEachCheckboxOneByOneSpamPromoRe();
		}
		
		 public boolean isToggleBtnDisplayedForSpamPromo() 
		 {
			return ruse.isToggleBtnDisplayedRe();
		 }
		 
		 public void clickToggleBtnForSpamPromo() throws InterruptedException 
		 {
			ruse.clickToggleBtnRe();			
		 }
		 
		 public boolean isShowInteractionDisplayedForSpamPromo() 
		 {
			return ruse.isShowInteractionDisplayedRe();
		 }
		 
		 public boolean isSortBtnDisplayedForSpamPromo() 
		 {
			return ruse.isSortBtnDisplayedRe();
		 }
		 
		 public void clickSortBtnForSpamPromo() throws InterruptedException 
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
		 
		 public void clickSortButtonByNameForSpamPromo() throws InterruptedException 
		 {
			 String[] buttons = {"Date", "From", "Subject", "Size"};

			 for (String btn : buttons) {
			     ruse.clickSortButtonByNameRe(btn);
			 }
			//ruse.clickDateBtnRe();
			
		 }	
		 
		 public void moveToTrashForSpamPromo() throws InterruptedException
		    {
		        ruse.moveToTrashSpamPromoRe();
		    }
		 
		 public void clickCancelBtnOfBlockPopupForSpamPromo() throws InterruptedException 
		 {
			ruse.blockEmailCancelBtnSpamPromoRe();
			
		 }	
		 
		 public void clickBlockBtnOfBlockPopupForSpamPromo() throws InterruptedException 
		 {
			ruse.blockEmailBlockBtnSpamPromoRe();
			
		 }
		 
		 public void clickDeleteBtnForSpamPromo() throws InterruptedException 
		 {
			ruse.deleteBtnSpamPromoRe();
			
		 }
		 
		 public void clickunreadReadEamilBtnForSpamPromo() throws InterruptedException 
		 {
			ruse.unreadReadEamilSpamPromoRe();
			
		 }	
		 
		 public boolean isQuickViewBoxDisplayedForSpamPromo() 
		 {
			return ruse.isReQuickViewBoxDisplayed();
		 }
		 
		 public void clickQuickViewBoxForSpamPromo() throws InterruptedException 
		 {
			ruse.clickReQuickViewBox();
			String quickViewToOwner = quickViewOwner.getAttribute("value");			
			String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");			
			Assert.assertTrue(quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName) || quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
			System.out.println(" Quick view page verified successfully");
		 }
		 
		 public void reclickQuickViewBoxForSpamPromo() throws InterruptedException 
			{
				ruse.clickReuncheckQuickViewBox();
				driver.navigate().refresh();       
				ruse.switchToMyAccountFrames();   
				
		    }
		 
		 
}
