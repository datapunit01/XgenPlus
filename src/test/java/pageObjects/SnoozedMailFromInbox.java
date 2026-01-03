package pageObjects;

import java.io.IOException;
import java.net.IDN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
		ruse.switchToMyAccountFramesIfViewMail1Re();

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

	

}
