package pageObjects;

import java.io.IOException;
import java.net.IDN;
import java.time.Duration;
//import java.util.concurrent.TimeoutException;
import org.openqa.selenium.TimeoutException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ExecutionEnvironment;
import utilities.ReusableForUnread;

public class SnoozedMailFromInbox extends BasePage {
	public ReusableForUnread ruse;

	String userName = p.getProperty("email");
	String userNameWithPunnycode = p.getProperty("email1");
	String userName1 = IDN.toUnicode(userNameWithPunnycode);
	String selectedDateOnCalendarText="28";

	@FindBy(xpath = "//input[@id='viewTo']")
	public WebElement quickViewOwner;

	@FindBy(xpath = "//input[@id='viewFrom']")
	public WebElement quickViewOwnerFrom;

	public SnoozedMailFromInbox(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
	}
	
	public boolean isInboxBtnDisplayed() {
		return ruse.isInboxDisplayedRe();
	}

	public void clickInboxBtn() {
		ruse.clickInboxRe();

	}
	
	public void switchToMyAccountFramesIcForInbox() {
		ruse.switchToMyAccountFramesIcRe();

	}
	
	public void clickFirstMailSubjectOnInbox() {
		ruse.clickOnMailDisplayedOnInboxRe();

	}
	
	public void switchToifViewMail1FrameFromIcFrame() {
		switchToMyAccountFrames();

	}
	
	public boolean isCrossBtnFromOpenedMailDisplayed() {
		return ruse.isCrossBtnFromOpenedMailDisplayedRe();
	}	
	

	public void clickCrossBtnFromOpenedMail() {
		ruse.clickCrossBtnFromOpenedMailRe();

	}
	

	public boolean isSnoozedBtnDisplayedInOpenedMail() {
		return ruse.isSnoozedBtnDisplayedInOpenedMailRe();
	}	
	

	public void clickSnoozedBtnInOpenedMail() {
		ruse.clickSnoozedBtnInOpenedMailRe();

	}
	

	public boolean isDateDisplayedOnCalendar() {
		return ruse.isDateDisplayedOnCalendarRe();
	}

	public void clickOnDateDisplayedOnCalendar() throws InterruptedException {
		ruse.clickOnDateDisplayedOnCalendarRe();		
	}

	public boolean isdateAboveSnoozeBtnOnCalendarDisplayed() {
		return ruse.isdateAboveSnoozeBtnOnCalendarDisplayedRe();
	}
	
	public void validateDateWithSelectedDate() throws InterruptedException {		
		String dateText = (ruse.dateAboveSnoozeBtnOnCalendar).getText();
		System.out.println("Selected Snooze Date is: " + dateText);
		Assert.assertTrue(
			    dateText.contains(selectedDateOnCalendarText),
			    "Expected text to contain" + selectedDateOnCalendarText + " but found: " + dateText
			);

	}
	
	public boolean isSnoozeBtnOnCalendarDisplayed() {
		return ruse.isSnoozeBtnOnCalendarDisplayedRe();
	}

	public void clickSnoozeBtnOnCalendar() throws InterruptedException {
		ruse.clickSnoozeBtnOnCalendarRe();
		
	}

	public void switchToMyAccountFrames() {
	
	    WebDriverWait wait;

	    // Jenkins needs more time
	    if (ExecutionEnvironment.isRunningOnJenkins()) {
	        wait = new WebDriverWait(driver, Duration.ofSeconds(45));
	    } else {
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	    driver.switchTo().defaultContent();

	    switchToFrameSafely("FB", wait);
	    switchToFrameSafely("FM", wait);
	    switchToFrameSafely("VC", wait);
	    switchToFrameSafely("ifViewMail1", wait);
	}

	private void switchToFrameSafely(String frameId, WebDriverWait wait) {

	    for (int attempt = 1; attempt <= 3; attempt++) {
	        try {
	            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
	            System.out.println(" Switched to frame: " + frameId);
	            return;
	        } catch (TimeoutException e) {
	            System.out.println(" Attempt " + attempt + " failed for frame: " + frameId);
	            if (attempt == 3) {
	                throw e;
	            }
	        }
	    }
	}


}
