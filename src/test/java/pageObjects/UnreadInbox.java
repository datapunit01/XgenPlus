package pageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utilities.ReusableForUnread;



public class UnreadInbox extends BasePage 
{
	
	public ReusableForUnread ruse; 
    String userName = p.getProperty("email");
      
	public UnreadInbox(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	 @FindBy(xpath = "//span[@class='F3']")
	 public WebElement newMailText;
	 
	 @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;
	 
	
	
	public void switchToFrameMcForUnReadInbox() 
	
	{
        //driver.switchTo().defaultContent();
        WebElement mcFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("MC")));
        driver.switchTo().frame(mcFrame);
        System.out.println("✅ Switched to MC frames");
    }	
		       
	 public boolean isNewMailTextDisplayedForUnReadInbox()
     {    	
       return ruse.isElementDisplayed(newMailText, " 'New Mail' text is visible. ");
     }
	 
	 public boolean isToggleBtnDisplayedForUnReadInbox() 
	 {
		return ruse.isToggleBtnDisplayedRe();
	 }
	 
	 public void clickToggleBtnForUnReadInbox() throws InterruptedException 
	 {
		ruse.clickToggleBtnRe();			
	 }
	 
	 public boolean isShowInteractionDisplayedForUnReadInbox() 
	 {
		return ruse.isShowInteractionDisplayedRe();
	 }
	 
	 public boolean isSortBtnDisplayedForUnReadInbox() 
	 {
		return ruse.isSortBtnDisplayedRe();
	 }
	 
	 public void clickSortBtnForUnReadInbox() throws InterruptedException 
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
	 
	 public void clickCancelBtnOfBlockPopupForUnReadInbox() throws InterruptedException 
	 {
		ruse.blockEmailCancelBtnRe();
		
	 }	
	 
	 public void clickBlockBtnOfBlockPopupForUnReadInbox() throws InterruptedException 
	 {
		ruse.blockEmailBlockBtnRe();
		
	 }
	 
	 public void clickDeleteBtnForUnReadInbox() throws InterruptedException 
	 {
		ruse.deleteBtnRe();
		
	 }	
	 
	 public void clickunreadReadEamilBtnForUnReadInbox() throws InterruptedException 
	 {
		ruse.unreadReadEamilRe();
		
	 }	
	 
	 public void clickforwordManyBtnForUnReadInbox() throws InterruptedException 
	 {
		ruse.forwordManyBtnRe();
		
	 }
	 
	 public void clickSortButtonByNameForUnReadInbox() throws InterruptedException 
	 {
		 String[] buttons = {"Date", "From", "Subject", "Size"};

		 for (String btn : buttons) {
		     ruse.clickSortButtonByNameRe(btn);
		 }
		//ruse.clickDateBtnRe();
		
	 }	 
	 	 
	 public boolean isQuickViewBoxDisplayedForUnReadInbox() 
	 {
		return ruse.isReQuickViewBoxDisplayed();
	 }
	 
	 public void clickQuickViewBoxForUnReadInbox() throws InterruptedException 
	 {
		ruse.clickReQuickViewBox();
		String quickViewToOwner = quickViewOwner.getAttribute("value");
		Assert.assertTrue(quickViewToOwner.contains(userName), " Quick View Page is Not verified Because here i am comparing the user name of mail id and the quick mail view by ");
		System.out.println(" Quick view page verified successfully");
		 Thread.sleep(2000);
	 }
	 
	 public void reclickQuickViewBoxForUnReadInbox() throws InterruptedException 
		{
			ruse.clickReuncheckQuickViewBox();
			
	    }
         
	public boolean isUnReadInboxDisplayedForUnReadInbox() 
	{
		return ruse.isReUnReadInboxDisplayed();
    }
	
	public boolean isChkMainBoxDisplayedForUnReadInbox()
    {    	
        return ruse.isReChkMainBoxDisplayed();
    }
	
	public void clickChkMainBoxForUnReadInbox() throws InterruptedException 
    {
    	ruse.clickReChkMainBox();
    }
	
	public boolean areAllEmailsSelectedViaAllForUnReadInbox() 
    {
       return ruse.areReAllEmailsSelectedViaAll();        
    }
	
	public void clickChkMainBoxforUncheckForUnReadInbox() throws InterruptedException 
	{
		ruse.clickReChkMainBoxforUncheck();
    }
	
	public boolean areAllEmailsUnselectedViaAllForUnReadInbox() 
    {
        return ruse.areReAllEmailsUnselectedViaAll();           
    }
	
	public boolean isDrpDownBtnDisplayedForUnReadInbox()
    {    	
        return ruse.isDrpDownBtnDisplayedRe();
    }
	
	public void clickDrpDownBtnForUnReadInbox() 
	{
	    ruse.clickReDrpDownBtn();
	}
	
	public boolean isDrpDownReadBtnDisplayedForUnReadInbox()
	{    	
	    return ruse.isReDrpDownReadBtnDisplayed();
	}
	
	public boolean isDrpDownUnreadBtnDisplayedForUnReadInbox()
    {    	
        return ruse.isReDrpDownUnreadBtnDisplayed();
    }
	
	public void clickDrpDownBtnOffForUnReadInbox() {
		ruse.clickDrpDownBtnRe();
	}
	
	public void clickEachCheckboxOneByOneForUnReadInbox() throws InterruptedException 
	{      
	   ruse.clickEachCheckboxOneByOneRe();
	}
	
    public void clickUnReadInboxForUnReadInbox()
    {
        ruse.clickReUnReadInbox();
    }
      
    public void moveToTrashForUnReadInbox() throws InterruptedException
    {
        ruse.moveToTrashRe();
    }
    

    
    
   
    
    
       
   
    
   

    
    
 
    

    

    
 
    
   


}
