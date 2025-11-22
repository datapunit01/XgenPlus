package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ReusableForUnread;

public class TemplatePage extends BasePage
{
public ReusableForUnread ruse; 	
	
    String userName = p.getProperty("email");
    
    
    @FindBy(xpath = "//input[@id='viewTo']")
	 public WebElement quickViewOwner;	
	
	public TemplatePage(ThreadLocal<WebDriver> driver) throws IOException 
	{
    	super(driver.get());
        ruse = new ReusableForUnread(driver);
	}
	
	public boolean isTemplateBtnDisplayed() 
	 {
		 return ruse.isTemplateBtnDisplayedRe();
	 }

	 public void clickTemplateBtn()
	 {
		 ruse.clickTemplateBtnRe();
	    
	 }
	 
	 public void switchToMyAccountFramesMcForTemplate()
	 {
		 ruse.switchToUnReadInboxFrameMc();
	    
	 }
	 
	 public boolean isChkMainBoxDisplayedForTemplate()
	    {    	
	        return ruse.isReChkMainBoxDisplayed();
	    }
		
   public void clickChkMainBoxForTemplate() throws InterruptedException 
	    {
	    	ruse.clickChkMainBoxOfDraftRe();
	    }
 
   public boolean areAllEmailsSelectedViaAllForTemplate() throws InterruptedException 
   {	
      return ruse.areAllEmailsSelectedViaAllRe();        
   }
   
   public void clickChkMainBoxforUncheckForTemplate() throws InterruptedException 
	{
		ruse.clickReChkMainBoxforUncheck();
		Thread.sleep(1500);   
   }
	
	public boolean areAllEmailsUnselectedViaAllForTemplate() 
   {
       return ruse.areAllEmailsUnselectedViaAllRe();           
   }
	
	public void clickEachCheckboxOneByOneForTemplate() throws InterruptedException 
	{      
	   ruse.clickEachCheckboxOneByOneForDraftRe();
	}
	
	 public boolean isToggleBtnDisplayedForTemplate() 
	 {
		return ruse.isToggleBtnDisplayedRe();
	 }
	 
	 public void clickToggleBtnForTemplate() throws InterruptedException 
	 {
		ruse.clickToggleBtnRe();			
	 } 
	 	 
	 public boolean isSortBtnDisplayedForTemplate() 
	 {
		return ruse.isSortBtnDisplayedRe();
	 }
	 
	 public void clickSortBtnForTemplate() throws InterruptedException 
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
	 
	 public void clickSortButtonByNameForTemplate() throws InterruptedException 
	 {
		 String[] buttons = {"Date", "From", "Subject", "Size"};

		 for (String btn : buttons) {
		     ruse.clickSortButtonByNameRe(btn);
		 }
		//ruse.clickDateBtnRe();
		
	 }	
	 
	 public void moveToTrashForTemplate() throws InterruptedException
	    {
	        ruse.moveToTrashTemplateRe();
	    }
	 
	 public void clickDeleteBtnForTemplate() throws InterruptedException 
	 {
		ruse.deleteBtnTemplateRe();
		driver.navigate().refresh();       
		ruse.switchToMyAccountFrames();   
		
	 }

}
