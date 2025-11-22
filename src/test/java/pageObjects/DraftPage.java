package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ReusableForUnread;

public class DraftPage extends BasePage
{

    public ReusableForUnread ruse; 	
	
    String userName = p.getProperty("email");
    
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
	
	public DraftPage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	public boolean isDraftBtnDisplayedForDraft() 
	 {
		 return ruse.isDraftBtnDisplayedRe();
	 }

	 public void clickDraftBtnForDraft()
	 {
		 ruse.clickDraftBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForDraft()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }
	 
	 public boolean isChkMainBoxDisplayedForDraft()
	    {    	
	        return ruse.isReChkMainBoxDisplayed();
	    }
		
   public void clickChkMainBoxForDraft() throws InterruptedException 
	    {
	    	ruse.clickChkMainBoxOfDraftRe();
	    }
 
   public boolean areAllEmailsSelectedViaAllForDraft() throws InterruptedException 
   {	
      return ruse.areAllEmailsSelectedViaAllRe();        
   }
   
   public void clickChkMainBoxforUncheckForDraft() throws InterruptedException 
	{
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);   
   }
	
	public boolean areAllEmailsUnselectedViaAllForDraft() 
   {
       return ruse.areAllEmailsUnselectedViaAllRe();           
   }
	
	public void clickEachCheckboxOneByOneForDraft() throws InterruptedException 
	{      
	   ruse.clickEachCheckboxOneByOneForDraftRe();
	}
	
	 public boolean isToggleBtnDisplayedForDraft() 
	 {
		return ruse.isToggleBtnDisplayedRe();
	 }
	 
	 public void clickToggleBtnForDraft() throws InterruptedException 
	 {
		ruse.clickToggleBtnRe();			
	 } 
	 	 
	 public boolean isSortBtnDisplayedForDraft() 
	 {
		return ruse.isSortBtnDisplayedRe();
	 }
	 
	 public void clickSortBtnForDraft() throws InterruptedException 
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
	 
	 public void clickSortButtonByNameForDraft() throws InterruptedException 
	 {
		 String[] buttons = {"Date", "From", "Subject", "Size"};

		 for (String btn : buttons) {
		     ruse.clickSortButtonByNameRe(btn);
		 }
		//ruse.clickDateBtnRe();
		
	 }	
	 
	 public void moveToTrashForDraft() throws InterruptedException
	    {
	        ruse.moveToTrashDraftRe();
	    }
	 
	 public void clickDeleteBtnForDraft() throws InterruptedException 
	 {
		ruse.deleteBtnDraftRe();
		driver.navigate().refresh();       
		ruse.switchToMyAccountFrames();   
		
	 }
}
