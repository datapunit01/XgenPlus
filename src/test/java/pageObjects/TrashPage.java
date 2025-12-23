package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import utilities.ReusableForUnread;

public class TrashPage extends BasePage
{
	
public ReusableForUnread ruse; 	
	
    String userName = p.getProperty("email");
    String userNameWithPunnycode = p.getProperty("email1");
    String userName1 = IDN.toUnicode(userNameWithPunnycode);
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
    
    @FindBy(xpath = "//input[@id='viewFrom']")
	 public WebElement quickViewOwnerFrom;
	
	public TrashPage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	 public boolean isTrashBtnDisplayed() 
	 {
		 return ruse.isTrashBtnDisplayedRe();
	 }

	 public void clickTrashBtn()
	 {
		 ruse.clickTrashBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForTrash()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }
	 
	 public boolean isChkMainBoxDisplayedForTrash()
	    {    	
	        return ruse.isReChkMainBoxDisplayed();
	    }
		
		public void clickChkMainBoxForTrash() throws InterruptedException 
	    {
	    	ruse.clickReChkMainBox();
	    }
		
		public boolean areAllEmailsSelectedViaAllForTrash() throws InterruptedException 
	    {	
	       return ruse.areAllEmailsSelectedViaAllRe();        
	    }
		
		public void clickChkMainBoxforUncheckForTrash() throws InterruptedException 
		{
			ruse.clickReChkMainBoxforUncheck();
			Thread.sleep(1500);   
	    }
		
		public boolean areAllEmailsUnselectedViaAllForTrash() 
	    {
	        return ruse.areAllEmailsUnselectedViaAllRe();           
	    }
		
		public boolean isDrpDownBtnDisplayedForTrash()
	    {    	
	        return ruse.isDrpDownBtnDisplayedRe();
	    }
		
		public void clickDrpDownBtnOnForTrash() throws InterruptedException 
		{
		    ruse.clickReDrpDownBtn();
		    isDrpDownReadBtnDisplayedForTrash();
		    isDrpDownUnreadBtnDisplayedForTrash();
		    Thread.sleep(1000);
		}
		
		public void clickDrpDownBtnOffForTrash() 
		{
		    ruse.clickDrpDownBtnRe();	    
		}
		
		public boolean isDrpDownReadBtnDisplayedForTrash()
		{    	
		    return ruse.isReDrpDownReadBtnDisplayed();
		}
		
		public boolean isDrpDownUnreadBtnDisplayedForTrash()
	    {    	
	        return ruse.isReDrpDownUnreadBtnDisplayed();
	    }
		
		public void clickEachCheckboxOneByOneForTrash() throws InterruptedException 
		{      
		   ruse.clickEachCheckboxOneByOneRe();
		}
		
		 public boolean isToggleBtnDisplayedForTrash() 
		 {
			return ruse.isToggleBtnDisplayedRe();
		 }
		 
		 public void clickToggleBtnForTrash() throws InterruptedException 
		 {
			ruse.clickToggleBtnRe();			
		 }
		 
		 
		 public boolean isSortBtnDisplayedForTrash() 
		 {
			return ruse.isSortBtnDisplayedRe();
		 }
		 
		 public void clickSortBtnForTrash() throws InterruptedException 
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
		 
		 public void clickSortButtonByNameForTrash() throws InterruptedException 
		 {
			 String[] buttons = {"Date", "From", "Subject", "Size"};

			 for (String btn : buttons) {
			     ruse.clickSortButtonByNameRe(btn);
			 }
			//ruse.clickDateBtnRe();
			
		 }	
		 
		 public void moveToInboxFromTrash() throws InterruptedException
		    {
		        ruse.moveToInboxFromTrashRe();
		    }
		    
		 public void clickCancelBtnOfBlockPopupForTrash() throws InterruptedException 
		 {
			ruse.blockEmailCancelBtnTrashRe();
			
		 }	
		 
		 public void clickBlockBtnOfBlockPopupForTrash() throws InterruptedException 
		 {
			ruse.blockEmailBlockBtnTrashRe();
			
		 }
		 
		 public void clickDeleteBtnForTrash() throws InterruptedException 
		 {
			ruse.deleteBtnTrashRe();
			
		 }
		 
		 public void clickunreadReadEamilBtnForTrash() throws InterruptedException 
		 {
			ruse.unreadReadEamilTrashRe();
			
		 }	
		 
		 public boolean isQuickViewBoxDisplayedForTrash() 
		 {
			return ruse.isReQuickViewBoxDisplayed();
		 }
		 
		 public void clickQuickViewBoxForTrash() throws InterruptedException 
		 {
			ruse.clickReQuickViewBox();
			String quickViewToOwner = quickViewOwner.getAttribute("value");			
			String quickViewFromOwner = quickViewOwnerFrom.getAttribute("value");			
			Assert.assertTrue(quickViewToOwner.contains(userName) || quickViewFromOwner.contains(userName) || quickViewToOwner.contains(userName1) || quickViewFromOwner.contains(userName1), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
			System.out.println(" Quick view page verified successfully");
		 }
		 
		 public void reclickQuickViewBoxForTrash() throws InterruptedException 
			{
				ruse.clickReuncheckQuickViewBox();
				
		    }
		 
		 public void clickforwordManyBtnForTrash() throws InterruptedException 
		 {
			ruse.forwordManyBtnTrashRe();
			
		 }

}
