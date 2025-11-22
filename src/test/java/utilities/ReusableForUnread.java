package utilities;

import java.io.IOException;
import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pageObjects.BasePage;

public class ReusableForUnread extends BasePage {

	int expectedCount;
	String userName = p.getProperty("email");
	String userNameWithPunnycode = p.getProperty("email1");
	String userName1 = IDN.toUnicode(userNameWithPunnycode);
	String alertMsgEmail = p.getProperty("alertMessage");

	List<WebElement> rows;

	public ReusableForUnread(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
	}

	@FindBy(xpath = "//span[starts-with(@id, 'SpnNew-1-')]")
	public WebElement UnReadInboxBtn;

	@FindBy(xpath = "//label[@for='chkMain']")
	public WebElement chkMainBox;

	@FindBy(xpath = "//label[normalize-space()='Quick View']")
	public WebElement quickViewBox;

	@FindBy(xpath = "//*[name()='path' and contains(@d,'M0.0396528')]")
	public WebElement drpDownBtn;

	@FindBy(xpath = "//span[@class='xgenCheckToggleMore']//*[name()='svg']")
	public WebElement toggleBtn;

	@FindBy(xpath = "//span[@id='InterShow']")
	public WebElement showInteraction;

	@FindBy(xpath = "//span[normalize-space()='Sort']")
	public WebElement sortBtn;

	@FindBy(xpath = "//li[normalize-space()='Sort By']")
	public WebElement sortByText;

	@FindBy(xpath = "//li[@id='xgenCheckListDate']")
	public WebElement dateBtn;

	@FindBy(xpath = "//li[@id='xgenCheckListFrom']")
	public WebElement fromBtn;

	@FindBy(xpath = "//li[@id='xgenCheckListSub']")
	public WebElement subjectBtn;

	@FindBy(xpath = "//li[@id='xgenCheckListSize']")
	public WebElement sizeBtn;

	@FindBy(xpath = "//li[normalize-space()='Sort Order']")
	public WebElement sortOrderText;

	@FindBy(xpath = "//li[@id='sortDtOld']")
	public WebElement oldestOnTopBtn;

	@FindBy(xpath = "//li[@id='sortDtNew']")
	public WebElement newestOnTopBtn;

	@FindBy(xpath = "//li[@id='sortFromAsc']")
	public WebElement sortFromAscBtn;

	@FindBy(xpath = "//li[@id='sortFromDesc']")
	public WebElement sortFromDescBtn;

	@FindBy(xpath = "//li[@id='sortSubAsc']")
	public WebElement sortSubAscBtn;

	@FindBy(xpath = "//li[@id='sortSubDesc']")
	public WebElement sortSubDescBtn;

	@FindBy(xpath = "//li[@id='sortSizeLess']")
	public WebElement sortSizeLessBtn;

	@FindBy(xpath = "//li[@id='sortSizeLarge']")
	public WebElement sortSizeLargeBtn;

	@FindBy(xpath = "//span[@onclick='chkReadMail()']")
	public WebElement readBtn;

	@FindBy(xpath = "//span[@onclick='chkUnReadMail()']")
	public WebElement unreadBtn;

	@FindBy(xpath = "//span[normalize-space()='Move To']")
	public WebElement moveToBtn;

	@FindBy(xpath = "//div[@id='mCSB_1_container']//li[contains(text(),'Trash')]")
	public WebElement moveToTrashBtn;

	@FindBy(xpath = "//div[@id='mCSB_1_container']//li[contains(text(),'Inbox')]")
	public WebElement moveToInboxBtn;

	@FindBy(xpath = "//div[@id='mCSB_1_container']//li[contains(text(),'Spam-Promo')]")
	public WebElement moveToSecureBtn;

	@FindBy(xpath = "//div[@id='mCSB_1_container']//li[contains(text(),'Secure')]")
	public WebElement moveToSpamPromoBtn;	
	
	String moveToDynamicFoldersBtn = "//div[@id='mCSB_1_container']/li";

	@FindBy(xpath = "//a[@class='xgenDropBlockIcon']//span[1]")
	public WebElement blockBtn;

	@FindBy(xpath = "//div[normalize-space()='Block Email Address']")
	public WebElement blockEmailAddressText;

	@FindBy(xpath = "//label[@for='blockmails']")
	public WebElement emailAddressSelectedText;

	@FindBy(xpath = "//tbody//tr//ol//span[1]")
	public WebElement blockEmailToggleBtn;

	@FindBy(xpath = "//tbody//tr//ol//li[1]")
	public WebElement blockSelectedEmail;

	@FindBy(xpath = "//label[normalize-space()='Move Selected Emails To Trash']")
	public WebElement blockMoveSelectedEmailsToTrashCheckBox;

	@FindBy(xpath = "//label[normalize-space()='Delete Selected Mail From Trash']")
	public WebElement blockDeleteSelectedMailFromTrashCheckBox;

	@FindBy(xpath = "//input[@class='xgenResetBtn']")
	public WebElement blockCancelBtn;

	String parentRowForBlockPage = "./ancestor::tr";

	String childRowForBlockPage = ".//font[@class='F3' and @title]";

	@FindBy(xpath = "//input[@id='confirmblockEmails']")
	public WebElement blockpageBlockBtn;

	@FindBy(xpath = "//font[contains(text(),'Selected Email addresses have been blocked, and th')]")
	public WebElement blockAlertText;

	@FindBy(xpath = "//input[@id='cok']")
	public WebElement blockAlertOkBtn;

	@FindBy(xpath = "//a[@class='xgenCheckToggleReload']//span[1]")
	public WebElement forwordManyBtn;

	@FindBy(xpath = "//button[text()='Forward Many As One Email']")
	public WebElement forwordManyAsOneEmailBtn;

	@FindBy(xpath = "//body[1]/form[1]/div[4]/table[1]/tbody[1]/tr[2]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[5]/a[1]")
	public WebElement undeletekBtn;

	@FindBy(xpath = "//span[@onclick='chkNoOfMails(\"del\")']")
	public WebElement deleteBtn;

	@FindBy(xpath = "//span[normalize-space()='Mark as Read | Unread']")
	public WebElement markAsReadUnreadBtn;

	@FindBy(xpath = "//span[normalize-space()='Not Spam']")
	public WebElement notSpamBtn;

	@FindBy(xpath = "//p[@class='FB'][@id='fromto0']")
	public WebElement selectedEmailUnread;

	@FindBy(xpath = "//p[@class='F3'][@id='fromto0']")
	public WebElement selectedEmailRead;

	// --- All individual email checkboxes ---
	@FindBy(xpath = "//tr[@class='MOVEABLETR' and contains(@id,'MailRowId') and (@style='background-color: rgba(196, 196, 196, 0.1); cursor: pointer;' or @style='background-color: rgba(196, 196, 196, 0.2); cursor: pointer;')]")
	List<WebElement> emailCheckboxesAll;

	@FindBy(xpath = "//tr[@class='MOVEABLETR' and contains(@id,'MailRowId')]")
	List<WebElement> emailCheckboxesAllInbox;

	@FindBy(xpath = "//tr[@class='MOVEABLETR' and contains(@id,'MailRowId') and (@style= 'background-color: rgb(255, 255, 255); cursor: pointer;'  or @style='background-color: rgba(255, 170, 170, 0.09); cursor: pointer;')]")
	List<WebElement> emailUnCheckboxesAll;

	@FindBy(xpath = "//tr[@class='MOVEABLETR' and contains(@id,'MailRowId') and (@style= 'background-color: rgb(255, 255, 255); cursor: pointer;' or @style='background-color: rgba(255, 170, 170, 0.09); cursor: pointer;')]")
	List<WebElement> emailUnCheckboxesAll1;

	@FindBy(xpath = "//tr[@class='MOVEABLETR' and contains(@id,'MailRowId') and @style= 'background-color:#FFFFFF;cursor:pointer;']")
	List<WebElement> emailUnCheckboxesAll2;

	@FindBy(xpath = "//input[@name='chkInbox']")
	List<WebElement> listCheckBox;

	// ------------------------------ Section For Inbox Page
	// -------------------------------------------------------------------------------------------------------------------------

	@FindBy(xpath = "//a[@title='Inbox']//span[contains(text(),'Inbox')]")
	public WebElement inboxBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //span[normalize-space()='Check Mail']")
	public WebElement checkMailBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='All']")
	public WebElement allBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Ecommerce']")
	public WebElement ecommerceBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Gov']")
	public WebElement govBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Bank']")
	public WebElement bankBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Social']")
	public WebElement socialBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Travel']")
	public WebElement travelBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Otp']")
	public WebElement otpBtn;

	@FindBy(xpath = "//div[@class='xgenDummyRight'] //a[normalize-space()='Subscriptions']")
	public WebElement subscriptionsBtn;

	@FindBy(xpath = "//span[text()='Sent']")
	public WebElement sentBtn;

	@FindBy(xpath = "//span[starts-with(@id, 'SpnNew-2-')]")
	public WebElement sentUnreadBtn;

	@FindBy(xpath = "//span[text()='Draft']")
	public WebElement draftBtn;

	@FindBy(xpath = "//span[text()='Trash']")
	public WebElement trashBtn;

	@FindBy(xpath = "//span[starts-with(@id, 'SpnNew-5-')]")
	public WebElement trashUnreadBtn;

	@FindBy(xpath = "//span[text()='Template']")
	public WebElement templateBtn;

	@FindBy(xpath = "//span[text()='Snoozed']")
	public WebElement snoozedBtn;

	@FindBy(xpath = "//span[text()='Spam-Promo']")
	public WebElement spamPromoBtn;

	@FindBy(xpath = "//span[starts-with(@id, 'SpnNew-23-')]")
	public WebElement spamPromoUnreadBtn;

	@FindBy(xpath = "//span[text()='More']")
	public WebElement moreBtn;

	@FindBy(xpath = "//b[normalize-space()='My Folder']")
	public WebElement myFolderBtn;
	
	@FindBy(xpath = "//img[contains(@id, 'plusFolder')]")
	public WebElement subFolderBtn;

	public boolean isReUnReadInboxDisplayed() {
		String verifyCountOfUnreadMail = UnReadInboxBtn.getText().replaceAll("[^0-9]", "").trim();
		expectedCount = Integer.parseInt(verifyCountOfUnreadMail);
		return isElementDisplayed(UnReadInboxBtn, "UnReadInbox icon is visible.");
	}

	public void switchToMyAccountFrames() {
		driver.switchTo().defaultContent();
		WebElement fbFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FB")));
		driver.switchTo().frame(fbFrame);

		WebElement fmFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FM")));
		driver.switchTo().frame(fmFrame);

		System.out.println(" Switched to FB → FM frames");
	}

	public void switchToMyAccountFramesIcRe() {
		driver.switchTo().defaultContent();
		WebElement fbFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FB")));
		driver.switchTo().frame(fbFrame);

		WebElement fmFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FM")));
		driver.switchTo().frame(fmFrame);

		System.out.println(" Switched to FB → FM frames");

		WebElement icFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("IC")));
		driver.switchTo().frame(icFrame);
		System.out.println(" Switched to FM → IC frames");
	}

	public void switchToUnReadInboxFrameMc()

	{
		// driver.switchTo().defaultContent();
		WebElement mcFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("MC")));
		driver.switchTo().frame(mcFrame);
		System.out.println(" Switched to MC frames");
	}

	public void switchToInboxFrameIc()

	{
		// driver.switchTo().defaultContent();
		WebElement icFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("IC")));
		driver.switchTo().frame(icFrame);
		System.out.println(" Switched to IC frames");
	}

	public void switchToMyAccountFramesFbRe() {
		driver.switchTo().defaultContent();
		WebElement fbFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FB")));
		driver.switchTo().frame(fbFrame);

		System.out.println(" Switched to Default → FB frames");
	}

	public boolean isInboxDisplayedRe() {
		return isElementDisplayed(inboxBtn, "Inbox icon is Visible");
	}

	public void clickInboxRe() {
		safeClick(inboxBtn, "Inbox button is clicked sucessfully.");
	}

	public void clickReUnReadInbox() {
		safeClick(UnReadInboxBtn, "UnReadInbox button is clicked sucessfully. ");
	}

	public boolean isToggleBtnDisplayedRe() {
		return isElementDisplayed(toggleBtn, " Toggle button 'three dots' is Visible. ");
	}

	public void clickToggleBtnRe() {
		safeClick(toggleBtn, " Toggle button 'three dots' clicked Successfully ");
	}

	public boolean isShowInteractionDisplayedRe() {
		return isElementDisplayed(showInteraction, " Show Interaction is Visible. ");
	}

	public boolean isSortBtnDisplayedRe() {
		return isElementDisplayed(sortBtn, " Sort button is Visible. ");
	}

	public void clickSortBtnRe() {
		safeClick(sortBtn, " Sort button clicked Successfully ");
	}

	public boolean isSortByTextDisplayedRe() {
		return isElementDisplayed(sortByText, " Sort By text is Visible. ");
	}

	public boolean isDateBtnDisplayedRe() {
		return isElementDisplayed(dateBtn, " Date button is Visible. ");
	}

	public boolean isFromBtnDisplayedRe() {
		return isElementDisplayed(fromBtn, " from button is Visible. ");
	}

	public boolean isSubjectBtnDisplayedRe() {
		return isElementDisplayed(subjectBtn, " Subject button is Visible. ");
	}

	public boolean isSizeBtnDisplayedRe() {
		return isElementDisplayed(sizeBtn, " Size button is Visible. ");
	}

	public boolean isSortOrderTextDisplayedRe() {
		return isElementDisplayed(sortOrderText, " Sort Order Text is Visible. ");
	}

	public boolean isOldestOnTopBtnDisplayedRe() {
		return isElementDisplayed(oldestOnTopBtn, " Oldest On Top button is Visible for Date. ");
	}

	public boolean isNewestOnTopBtnDisplayedRe() {
		return isElementDisplayed(newestOnTopBtn, " Newest On Top button is Visible for Date. ");
	}

	public boolean isSortSizeLessBtnDisplayedRe() {
		return isElementDisplayed(sortSizeLessBtn, " Smallest on top button is Visible for Size. ");
	}

	public boolean isSortSizeLargeBtnDisplayedRe() {
		return isElementDisplayed(sortSizeLargeBtn, " Largest on top button is Visible for Size. ");
	}

	public boolean isSortSubAscBtnDisplayedRe() {
		return isElementDisplayed(sortSubAscBtn, " Ascending button is Visible for subject. ");
	}

	public boolean isSortSubDescBtnDisplayedRe() {
		return isElementDisplayed(sortSubDescBtn, " Descending button is Visible for subject. ");
	}

	public boolean isSortFromAscBtnDisplayedRe() {
		return isElementDisplayed(sortFromAscBtn, " Ascending button is Visible for From. ");
	}

	public boolean isSortFromDescBtnDisplayedRe() {
		return isElementDisplayed(sortFromDescBtn, " Descending button is Visible for From. ");
	}

	public boolean isReQuickViewBoxDisplayed() {
		return isElementDisplayed(quickViewBox, " Quick View checkBox is Visible. ");
	}

	public void clickReQuickViewBox() {
		safeClick(quickViewBox, " Quick View checkbox clicked Successfully ");
	}

	public void clickReuncheckQuickViewBox() {
		safeClick(quickViewBox, " Quick View checkbox unchecked Successfully ");
	}

	public boolean isReChkMainBoxDisplayed() {
		return isElementDisplayed(chkMainBox, " Check Main Box icon is visible. ");
	}

	public boolean isDrpDownBtnDisplayedRe() {
		return isElementDisplayed(drpDownBtn, " Check Drop Down Button icon ");
	}

	public void clickReDrpDownBtn() {
		safeClick(drpDownBtn, " Check Drop Down Button Clicked Successfully. ");
	}

	public void clickDrpDownBtnRe() {
		safeClick(drpDownBtn, " Check Drop Down Button Clicked again Successfully. ");
	}

	public boolean isReDrpDownReadBtnDisplayed() {
		return isElementDisplayed(readBtn, " In Drop Down Button, Read button is visible.");
	}

	public boolean isReDrpDownUnreadBtnDisplayed() {
		return isElementDisplayed(unreadBtn, " In Drop Down Button, Unread button is visible.");
	}

	public boolean isMoveToBtnDisplayed() {
		return isElementDisplayed(moveToBtn, " Move to button is visible.");
	}

	public void clickMoveToBtn() {
		safeClick(moveToBtn, " Move to button is Clicked successfully.");
	}

	public boolean isMoveToTrashBtnDisplayed() {
		return isElementDisplayed(moveToTrashBtn, " Trash button is visible after click on Move to Button..");
	}

	public void clickMoveToTrashBtn() {
		safeScrollAndClick(moveToTrashBtn, " Trash button is Clicked and selected mail moved into trash successfully.");
	}

	public boolean isMoveToInboxBtnDisplayed() {
		return isElementDisplayed(moveToInboxBtn, " Inbox button is visible after click on Move to Button.");
	}

	public void clickMoveToInboxBtn() {
		safeScrollAndClick(moveToInboxBtn, " Inbox button is Clicked and selected mail moved into trash successfully.");
	}

	public boolean isMoveToSecureBtnDisplayed() {
		return isElementDisplayed(moveToSecureBtn, " Secure button is visible after click on Move to Button.");
	}

	public void clickMoveToSecureBtn() {
		safeClick(moveToSecureBtn, " Secure button is Clicked and selected mail moved into trash successfully.");
	}

	public boolean isMoveToSpamPromoBtnDisplayed() {
		return isElementDisplayed(moveToSpamPromoBtn, " Spam-Promo button is visible after click on Move to Button.");
	}

	public void clickMoveToSpamPromoBtn() {
		safeClick(moveToSpamPromoBtn, " Spam-Promo button is Clicked and selected mail moved into trash successfully.");
	}

	public boolean isBlockBtnDisplayed() {
		return isElementDisplayed(blockBtn, " Block button is visible on Menu bar.");
	}

	public void clickBlockBtn() {
		safeClick(blockBtn, " Block button is Clicked successfully from Menu bar..");
	}

	public boolean isBlockEmailAddressTextDisplayed() {
		return isElementDisplayed(blockEmailAddressText, " ' Block Email Address' Text is visible.");
	}

	public boolean isBlockEmailAddressSelectedTextDisplayed() {
		return isElementDisplayed(emailAddressSelectedText, " Selected Email's  Text is visible.");
	}

	public boolean isBlockEmailToggleBtnDisplayed() {
		return isElementDisplayed(blockEmailToggleBtn, " Toggle Button is visible.");
	}

	public boolean isBlockSelectedEmailDisplayed() {
		return isElementDisplayed(blockSelectedEmail, " Selected Email is visible.");
	}

	public boolean isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed() {
		return isElementDisplayed(blockMoveSelectedEmailsToTrashCheckBox,
				" Block Move Selected Emails To Trash CheckBox is visible.");
	}

	public boolean isBlockDeleteSelectedMailFromTrashCheckBoxDisplayed() {
		return isElementDisplayed(blockDeleteSelectedMailFromTrashCheckBox,
				" Delete Selected Mail From Trash CheckBox is visible.");
	}

	public boolean isblockCancelBtnDisplayed() {
		return isElementDisplayed(blockCancelBtn, " Cancel button is visible.");
	}

	public void clickBlockpageCancelBtn() {
		safeClick(blockCancelBtn, " Cancel button is Clicked successfully from Block pop up.");
	}

	public boolean isblockpageBlockBtnDisplayed() {
		return isElementDisplayed(blockpageBlockBtn, " Block button is visible on Block pop up.");
	}

	public void clickBlockpageBlockBtn() {
		safeClick(blockpageBlockBtn, " Block button is Clicked successfully from Block pop up.");
	}

	public boolean isBlockAlertTextDisplayed() {
		return isElementDisplayed(blockAlertText,
				" On alert page - 'Selected Email addresses have been blocked, and their emails have been moved to Trash' text is visible.");
	}

	public boolean isBlockAlertOkBtnDisplayed() {
		return isElementDisplayed(blockAlertOkBtn, " 'On alert page Ok Button' is visible.");
	}

	public void clickBlockAlertOkBtn() {
		safeClick(blockAlertOkBtn, " On Alert Page Ok button is Clicked successfully.");
	}

	public boolean isUnDeleteBtnDisplayed() {
		return isElementDisplayed(undeletekBtn, " Un / Delete button is visible.");
	}

	public boolean isDeleteBtnDisplayed() {
		return isElementDisplayed(deleteBtn, " Delete button is visible on menu bar.");
	}

	public void clickDeleteBtn() {
		safeClick(deleteBtn, " Delete button is Clicked successfully from menu bar .");
	}

	public boolean isUnDeleteBtnNotDisplayed() {
		return isElementNotDisplayed(undeletekBtn, " Un / Delete button is successfully Not visible.");
	}

	public boolean isDeleteBtnNotDisplayed() {
		return isElementNotDisplayed(deleteBtn, " Delete button is successfully Not visible.");
	}

	public boolean isFrwrdManyBtnDisplayed() {
		return isElementDisplayed(forwordManyBtn, " Forword Many button is visible on menu bar..");
	}

	public void clickFrwrdManyBtn() {
		safeClick(forwordManyBtn, " forword Many button is Clicked successfully from menu bar.");
	}

	public boolean isFrwrdManyAsOneEmailBtnDisplayed() {
		return isElementDisplayed(forwordManyAsOneEmailBtn,
				" Forword Many As One Email button is visible and user is on Forword Many page.");
	}

	public boolean isMarkAsReadUnreadBtnDisplayed() {
		return isElementDisplayed(markAsReadUnreadBtn, " Mark As Read | Unread button is visible on menu bar.");
	}

	public void clickMarkAsReadUnreadBtn() {
		safeClick(markAsReadUnreadBtn, " Mark As Read | Unread button is Clicked successfully from menu bar.");
	}

	public boolean isNotSpamBtnDisplayed() {
		return isElementDisplayed(notSpamBtn, " Not Spam button is visible on menu bar.");
	}

	public void clickNotSpamBtn() {
		safeClick(notSpamBtn, " Not Spam button is Clicked successfully from menu bar.");
	}

	public boolean isSelectedEmailUnreadDisplayed() {
		return isElementDisplayed(selectedEmailUnread, " Selected Email is Unreaded visible.");
	}

	public boolean isSelectedEmailUnreadVisible() {
		try {
			return selectedEmailUnread.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSelectedEmailReadDisplayed() {
		return isElementDisplayed(selectedEmailRead, " Selected Email is Readed visible.");
	}

	public boolean isSelectedEmailReadVisible() {
		try {
			return selectedEmailRead.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isMoveToBtnNotDisplayed() {
		return isElementNotDisplayed(moveToBtn, " Move to button is successfully Not visible.");
	}

	public boolean isBlockBtnNotDisplayed() {
		return isElementNotDisplayed(blockBtn, " Block button is Not successfully visible.");
	}

	public boolean isFrwrdManyBtnNotDisplayed() {
		return isElementNotDisplayed(forwordManyBtn, " Forword Many button is successfully Not visible.");
	}

	public boolean isMarkAsReadUnreadBtnNotDisplayed() {
		return isElementNotDisplayed(markAsReadUnreadBtn, " Mark As Read | Unread button is successfully Not visible.");
	}

	public boolean isNotSpamBtnNotDisplayed() {
		return isElementNotDisplayed(notSpamBtn, " Not Spam button is successfully Not visible.");
	}

	public boolean isCheckMailBtnDisplayed() {
		return isElementDisplayed(checkMailBtn, " Check Mail button is successfully visible On Inbox Page.");
	}

	public boolean isAllBtnDisplayed() {
		return isElementDisplayed(allBtn, " All button is successfully visible On Inbox Page.");
	}

	public boolean isEcommerceBtnDisplayed() {
		return isElementDisplayed(ecommerceBtn, " Ecommerce button is successfully visible On Inbox Page.");
	}

	public boolean isGovBtnDisplayed() {
		return isElementDisplayed(govBtn, " Gov button is successfully visible On Inbox Page.");
	}

	public boolean isBankBtnDisplayed() {
		return isElementDisplayed(bankBtn, " Bank button is successfully visible On Inbox Page.");
	}

	public boolean isSocialBtnDisplayed() {
		return isElementDisplayed(socialBtn, " Social button is successfully visible On Inbox Page.");
	}

	public boolean isTravelBtnDisplayed() {
		return isElementDisplayed(travelBtn, " Travel button is successfully visible On Inbox Page.");
	}

	public boolean isOtpBtnDisplayed() {
		return isElementDisplayed(otpBtn, " Otp button is successfully visible On Inbox Page.");
	}

	public boolean isSubscriptionsBtnDisplayed() {
		return isElementDisplayed(subscriptionsBtn, " Subscriptions button is successfully visible On Inbox Page.");
	}

	public boolean isSentBtnDisplayedRe() {
		return isElementDisplayed(sentBtn, " Sent button is successfully visible On Inbox Page.");
	}

	public void clickSentBtnRe() {
		safeClick(sentBtn, " Sent button is Clicked successfully from menu bar .");
	}

	public boolean isSentUnreadBtnDisplayedRe() {
		return isElementDisplayed(sentUnreadBtn, " Sent Unread button is successfully visible On Inbox Page.");
	}

	public void clickSentUnreadBtnRe() {
		safeClick(sentUnreadBtn, " Sent Unread button is Clicked successfully from menu bar .");
	}

	public boolean isDraftBtnDisplayedRe() {
		return isElementDisplayed(draftBtn, " Draft button is successfully visible On Inbox Page.");
	}

	public void clickDraftBtnRe() {
		safeClick(draftBtn, " Draft button is Clicked successfully from menu bar .");
	}

	public boolean isTrashBtnDisplayedRe() {
		return isElementDisplayed(trashBtn, " Trash button is successfully visible On Inbox Page.");
	}

	public void clickTrashBtnRe() {
		safeClick(trashBtn, " Trash button is Clicked successfully from menu bar .");
	}

	public boolean isTrashUnreadBtnDisplayedRe() {
		return isElementDisplayed(trashUnreadBtn, " Trash Unread button is successfully visible On Inbox Page.");
	}

	public void clickTrashUnreadBtnRe() {
		safeClick(trashUnreadBtn, " Trash Unread button is Clicked successfully from menu bar .");
	}

	public boolean isTemplateBtnDisplayedRe() {
		return isElementDisplayed(templateBtn, " Template button is successfully visible On Inbox Page.");
	}

	public void clickTemplateBtnRe() {
		safeClick(templateBtn, " Template  button is Clicked successfully from menu bar .");
	}

	public boolean isSnoozedBtnDisplayedRe() {
		return isElementDisplayed(snoozedBtn, " Snoozed button is successfully visible On Inbox Page.");
	}

	public void clickSnoozedBtnRe() {
		safeClick(snoozedBtn, " Snoozed  button is Clicked successfully from menu bar .");
	}

	public boolean isSpamPromoBtnDisplayedRe() {
		return isElementDisplayed(spamPromoBtn, " Spam-Promo button is successfully visible On Inbox Page.");
	}

	public void clickSpamPromoBtnRe() {
		safeClick(spamPromoBtn, " Spam-Promo  button is Clicked successfully from menu bar .");
	}

	public boolean isSpamPromoUnreadBtnDisplayedRe() {
		return isElementDisplayed(spamPromoUnreadBtn,
				" Spam Promo Unread Inbox button is successfully visible On Inbox Page.");
	}

	public void clickSpamPromoUnreadBtnRe() {
		safeClick(spamPromoUnreadBtn, "  Spam Promo Unread Inbox button is Clicked successfully from menu bar .");
	}

	public boolean isMoreBtnDisplayedRe() {
		return isElementDisplayed(moreBtn, " More button is successfully visible On Inbox Page.");
	}

	public void clickMoreBtnRe() {
		safeClick(moreBtn, "  More button is Clicked successfully from menu bar .");
	}

	public boolean isMyFolderBtnDisplayedRe() {
		return isElementDisplayed(myFolderBtn, " My Folder button is successfully visible On Inbox Page.");
	}

	public void clickMyFolderBtnRe() {
		safeClick(myFolderBtn, "  My Folder button is Clicked successfully from menu bar .");
	}
	
	public boolean isSubFolderBtnDisplayedRe() {
		return isElementDisplayed(subFolderBtn, " Sub Folder button is successfully visible On Inbox Page.");
	}

	public void clickSubFolderBtnRe() {
		safeClick(subFolderBtn, "  Sub Folder button is Clicked successfully from menu bar .");
	}
	                                                                                                                                                                                        
	public void clickReChkMainBox() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(1500);
		isMoveToBtnDisplayed();
		isBlockBtnDisplayed();
		isDeleteBtnDisplayed();
		isFrwrdManyBtnDisplayed();
		isMarkAsReadUnreadBtnDisplayed();
	}

	public void clickChkMainBoxOfSentRe() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(1500);
		isMoveToBtnDisplayed();
		isDeleteBtnDisplayed();
		isFrwrdManyBtnDisplayed();
		isMarkAsReadUnreadBtnDisplayed();
	}

	public void clickChkMainBoxOfDraftRe() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(1500);
		isMoveToBtnDisplayed();
		isDeleteBtnDisplayed();

	}

	public void clickReChkMainBoxforUncheck() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
		Thread.sleep(1500);
		isMoveToBtnNotDisplayed();
		isBlockBtnNotDisplayed();
		isDeleteBtnNotDisplayed();
		isFrwrdManyBtnNotDisplayed();
		isMarkAsReadUnreadBtnNotDisplayed();
	}

	public void clickSnoozedRe() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(1500);
		isMoveToBtnDisplayed();
		isBlockBtnDisplayed();
		isDeleteBtnDisplayed();
		isFrwrdManyBtnDisplayed();
	}

	public void clickChkMainBoxSpamPromoRe() throws InterruptedException {
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(1500);
		isMoveToBtnDisplayed();
		isBlockBtnDisplayed();
		isDeleteBtnDisplayed();
		isNotSpamBtnDisplayed();
		isMarkAsReadUnreadBtnDisplayed();
	}

	public void clickforRefreshInboxUnreadRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isReUnReadInboxDisplayed();
		clickReUnReadInbox();
		Thread.sleep(2000);
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshInboxRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isInboxDisplayedRe();
		clickInboxRe();
		switchToMyAccountFramesIcRe();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshSentRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isSentBtnDisplayedRe();
		clickSentBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshSentUnreadRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isSentUnreadBtnDisplayedRe();
		clickSentUnreadBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshDraftRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isDraftBtnDisplayedRe();
		clickDraftBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshTrashRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isTrashBtnDisplayedRe();
		clickTrashBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshTrashUnreadRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isTrashUnreadBtnDisplayedRe();
		clickTrashUnreadBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshTemplateRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isTemplateBtnDisplayedRe();
		clickTemplateBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshSnoozedRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isSnoozedBtnDisplayedRe();
		clickSnoozedBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshSpamPromoRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isSpamPromoBtnDisplayedRe();
		clickSpamPromoBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public void clickforRefreshSpamPromoUnreadRe() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		switchToMyAccountFrames();
		isSpamPromoUnreadBtnDisplayedRe();
		clickSpamPromoUnreadBtnRe();
		switchToUnReadInboxFrameMc();
		safeClick(chkMainBox, "Check Main Box button is Clicked and all Check boxes are checked");
		Thread.sleep(2000);
		safeClick(chkMainBox, "Check Main Box button is Clicked again and all Check boxes are unchecked");
	}

	public boolean isElementDisplayed(WebElement element, String elementName) {
		try {
			boolean status = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
			Assert.assertTrue(status, elementName + " is NOT visible or clickable!");
			System.out.println(elementName);
			return status;
		} catch (Exception e) {
			Assert.fail(elementName + " not visible/clickable. Exception: " + e.getMessage());
			return false;
		}
	}

	public boolean isElementNotDisplayed(WebElement element, String elementName) {
		try {

			// Wait for the element to become invisible (or not present in the DOM)
			boolean status = wait.until(ExpectedConditions.invisibilityOf(element));

			// Assert that the status is true (meaning it IS invisible)
			Assert.assertTrue(status, elementName + " is still VISIBLE!");
			System.out.println(elementName);
			return status;
		} catch (Exception e) {
			// If an exception occurs (e.g., TimeoutException), it means the element was
			// likely still visible, or the element was never attached to the DOM properly.
			Assert.fail(elementName + " failed the invisibility check. Exception: " + e.getMessage());
			return false;
		}
	}

	public void safeClick(WebElement element, String elementName) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			System.out.println(elementName);
		} catch (Exception e) {
			Assert.fail(" Failed to click " + elementName + ". Exception: " + e.getMessage());
		}
	}
	
	
	
	public void safeScrollAndClick(WebElement element, String elementName) {
	    try {	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();	     
	        System.out.println(elementName);      
	        } 
	       catch (Exception e) {	        
	        Assert.fail("❌ Failed to scroll to and click " + elementName + ". Exception: " + e.getMessage());
	    }
	}

	public boolean areReAllEmailsSelectedViaAll() {
		wait.until(ExpectedConditions.visibilityOfAllElements(emailCheckboxesAll));
		int selectedCount = 0;
		for (WebElement checkbox : emailCheckboxesAll) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgba(196, 196, 196, 0.1); cursor: pointer;")) {
				selectedCount++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		System.out.println("Total email checkboxes available in Unread Inbox " + selectedCount);
		System.out.println("All email checkboxes are selected.");
		Assert.assertEquals(expectedCount, selectedCount,
				"Unread Inbox's email count is not matching with expected email count value ");
		System.out.println("Value Of UnreadInbox is " + expectedCount
				+ " Which Equals to value of unread Email available " + selectedCount);
		return selectedCount == emailCheckboxesAll.size();

	}

	public List<WebElement> scrollAndGetInboxRows() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		long lastHeight = (long) js.executeScript("return document.body.scrollHeight;");

		while (true) {
			// Scroll to bottom
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(1200); // allow loading time

			long newHeight = (long) js.executeScript("return document.body.scrollHeight;");

			if (newHeight == lastHeight) {
				break; // reached bottom
			}
			lastHeight = newHeight;
		}

		// Re-locate elements AFTER scrolling (avoids stale elements)
		List<WebElement> rows = emailCheckboxesAll;

		System.out.println("Total inbox rows found: " + rows.size());
		return rows;
	}

	public List<WebElement> scrollInboxToBottomAndGetRows() throws InterruptedException {
		// Step 1: Switch to correct iframe
		// driver.switchTo().defaultContent();
		// driver.switchTo().frame("FM");

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Step 2: Get the scrollable container
		WebElement container = driver.findElement(By.id("mCSB_2_container"));

		long lastScrollTop = -1;

		while (true) {
			// Scroll down the container
			js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", container);

			Thread.sleep(700);

			long scrollTop = (long) js.executeScript("return arguments[0].scrollTop;", container);

			if (scrollTop == lastScrollTop) {
				break; // Fully scrolled
			}
			lastScrollTop = scrollTop;
		}

		// Step 3: Re-locate inbox rows AFTER scroll
		List<WebElement> rows = driver
				.findElements(By.xpath("//tr[@class='MOVEABLETR' and contains(@id,'MailRowId')]"));

		// System.out.println("Total rows found: " + rows.size());
		return rows;
	}

	public boolean areAllEmailsSelectedViaAllRe() throws InterruptedException {
		List<WebElement> rows = emailCheckboxesAll;
		// System.out.println("Total rows found: " + rows.size());
		// wait.until(ExpectedConditions.visibilityOfAllElements(rows));
		int selectedCount = 0;
		for (WebElement checkbox : rows) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgba(196, 196, 196, 0.1); cursor: pointer;")
					|| classes.contains("background-color: rgba(196, 196, 196, 0.2); cursor: pointer;")) {
				selectedCount++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		System.out.println("Total email checkboxes available and Checked : " + selectedCount);
		// System.out.println("All email checkboxes are selected.");
		return selectedCount == rows.size();

	}

	public boolean areReAllEmailsUnselectedViaAll() {
		List<WebElement> rows = emailUnCheckboxesAll;
		// System.out.println("Total rows found: " + rows.size());
		// wait.until(ExpectedConditions.visibilityOfAllElements(emailUnCheckboxesAll));
		int unselectedCount = 0;
		for (WebElement checkbox : rows) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgb(255, 255, 255); cursor: pointer;")) {
				unselectedCount++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		System.out.println("Total email checkboxes available  after uncheck the checkboxes:: " + unselectedCount);
		// System.out.println("All email checkboxes are unselected after uncheck the
		// checkboxes:.");
		Assert.assertEquals(expectedCount, unselectedCount,
				"Unread Inbox's email count is not matching with expected email count value ");
		System.out.println("Value Of UnreadInbox is " + expectedCount
				+ " Which Equals to value of unread Email available after uncheck the checkboxes: " + unselectedCount);
		return unselectedCount == rows.size();

	}

	public boolean areAllEmailsUnselectedViaAllRe() {
		List<WebElement> rows = emailUnCheckboxesAll;
		// System.out.println("Total rows found: " + rows.size());
		// wait.until(ExpectedConditions.visibilityOfAllElements(emailUnCheckboxesAll));
		int unselectedCount = 0;
		for (WebElement checkbox : rows) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgb(255, 255, 255); cursor: pointer;")
					|| classes.contains("background-color: rgba(255, 170, 170, 0.09); cursor: pointer;")) {
				unselectedCount++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		System.out.println("Total email checkboxes available  after uncheck the checkboxes: " + unselectedCount);
		// System.out.println("All email checkboxes are unselected after uncheck the
		// checkboxes.");
		return unselectedCount == rows.size();

	}

	public int areReAllEmailsUnselectedViaAll1() throws InterruptedException {

		rows = emailUnCheckboxesAll1;
		// System.out.println("Total rows found: " + rows.size());
		// wait.until(ExpectedConditions.visibilityOfAllElements(emailUnCheckboxesAll1));
		int unselectedCount = 0;
		for (WebElement checkbox : rows) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgb(255, 255, 255); cursor: pointer;")
					|| classes.contains("background-color: rgba(255, 170, 170, 0.09); cursor: pointer;")) {
				unselectedCount++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		return unselectedCount;

	}

	public int areCountOfTotalMailInPageRe() throws InterruptedException {

		rows = emailUnCheckboxesAll2;
		// System.out.println("Total rows found: " + rows.size());
		// wait.until(ExpectedConditions.visibilityOfAllElements(emailUnCheckboxesAll1));
		int countofTotalMailInPage = 0;
		for (WebElement checkbox : rows) {
			String classes = checkbox.getAttribute("style");
			if (classes.contains("background-color: rgb(255, 255, 255); cursor: pointer;")) {
				countofTotalMailInPage++;
			} else {
				System.out.println(" Checkbox not visually selected: " + classes);
			}
		}

		return countofTotalMailInPage;

	}

	public void clickSortButtonByNameRe(String buttonName) throws InterruptedException {
		WebElement targetButton;

		switch (buttonName.toLowerCase()) {
		case "date":
			targetButton = dateBtn;
			break;
		case "from":
			targetButton = fromBtn;
			break;
		case "subject":
			targetButton = subjectBtn;
			break;
		case "size":
			targetButton = sizeBtn;
			break;
		default:
			throw new IllegalArgumentException("❌ Invalid button name: " + buttonName);
		}
		safeClick(targetButton, buttonName + " button clicked Successfully ");

		Thread.sleep(500);
		clickToggleBtnRe();
		clickSortBtnRe();
		isDateBtnDisplayedRe();
		isFromBtnDisplayedRe();
		isSubjectBtnDisplayedRe();
		isSizeBtnDisplayedRe();
		isSortOrderTextDisplayedRe();
		String buttonClasses = targetButton.getAttribute("class");
		String buttonCheckList = targetButton.getAttribute("id");
		// String combinedKey = buttonClasses + "|" + buttonCheckList;
		if (buttonClasses.contains("checkedIcon")) {
			switch (buttonCheckList) {
			case "xgenCheckListDate":
				System.out.println(" Date button is marked as 'selected'.");
				isOldestOnTopBtnDisplayedRe();
				isNewestOnTopBtnDisplayedRe();
				break;

			case "xgenCheckListFrom":
				System.out.println(" From button is marked as 'selected'.");
				isSortFromAscBtnDisplayedRe();
				isSortFromDescBtnDisplayedRe();
				break;

			case "xgenCheckListSize":
				System.out.println(" Size button is marked as 'selected'.");
				isSortSizeLessBtnDisplayedRe();
				isSortSizeLargeBtnDisplayedRe();
				break;

			case "xgenCheckListSub":
				System.out.println(" Size button is marked as 'selected'.");
				isSortSubAscBtnDisplayedRe();
				isSortSubDescBtnDisplayedRe();
				break;

			}

		}
	}

	public void clickEachCheckboxOneByOneRe() throws InterruptedException {

		List<WebElement> checkboxes = listCheckBox;

		System.out.println("Total checkboxes found: " + checkboxes.size());

		for (int i = 0; i < checkboxes.size() && i < 2; i++) {
			WebElement checkbox = checkboxes.get(i);

			// Scroll to element to ensure clickability
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			// ✅ Click to check
			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				isBlockBtnDisplayed();
				isDeleteBtnDisplayed();
				isFrwrdManyBtnDisplayed();
				isMarkAsReadUnreadBtnDisplayed();
			}

			// ✅ Verify checked
			if (!checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get selected!");
			}

			// ✅ Click again to uncheck
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Unchecked checkbox: " + (i + 1));
			Thread.sleep(500);
			isMoveToBtnNotDisplayed();
			isBlockBtnNotDisplayed();
			isDeleteBtnNotDisplayed();
			isFrwrdManyBtnNotDisplayed();
			isMarkAsReadUnreadBtnNotDisplayed();

			// ✅ Verify unchecked
			if (checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get unselected!");
			}

			Thread.sleep(500);
		}

	}

	public void clickEachCheckboxOneByOneForSentRe() throws InterruptedException {

		List<WebElement> checkboxes = listCheckBox;

		System.out.println("Total checkboxes found: " + checkboxes.size());

		for (int i = 0; i < checkboxes.size() && i < 2; i++) {
			WebElement checkbox = checkboxes.get(i);

			// Scroll to element to ensure clickability
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			// ✅ Click to check
			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				isDeleteBtnDisplayed();
				isFrwrdManyBtnDisplayed();
				isMarkAsReadUnreadBtnDisplayed();
			}

			// ✅ Verify checked
			if (!checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get selected!");
			}

			// ✅ Click again to uncheck
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Unchecked checkbox: " + (i + 1));
			Thread.sleep(500);
			isMoveToBtnNotDisplayed();
			isDeleteBtnNotDisplayed();
			isFrwrdManyBtnNotDisplayed();
			isMarkAsReadUnreadBtnNotDisplayed();

			// ✅ Verify unchecked
			if (checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get unselected!");
			}

			Thread.sleep(500);
		}

	}

	public void clickEachCheckboxOneByOneForDraftRe() throws InterruptedException {

		List<WebElement> checkboxes = listCheckBox;

		System.out.println("Total checkboxes found: " + checkboxes.size());

		for (int i = 0; i < checkboxes.size() && i < 2; i++) {
			WebElement checkbox = checkboxes.get(i);

			// Scroll to element to ensure clickability
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			// ✅ Click to check
			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				isDeleteBtnDisplayed();

			}

			// ✅ Verify checked
			if (!checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get selected!");
			}

			// ✅ Click again to uncheck
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Unchecked checkbox: " + (i + 1));
			Thread.sleep(500);
			isMoveToBtnNotDisplayed();
			isDeleteBtnNotDisplayed();

			// ✅ Verify unchecked
			if (checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get unselected!");
			}

			Thread.sleep(500);
		}

	}

	public void clickEachCheckboxOneByOneSnoozedRe() throws InterruptedException {

		List<WebElement> checkboxes = listCheckBox;

		System.out.println("Total checkboxes found: " + checkboxes.size());

		for (int i = 0; i < checkboxes.size() && i < 2; i++) {
			WebElement checkbox = checkboxes.get(i);

			// Scroll to element to ensure clickability
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			// ✅ Click to check
			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				isBlockBtnDisplayed();
				isDeleteBtnDisplayed();
				isFrwrdManyBtnDisplayed();

			}

			// ✅ Verify checked
			if (!checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get selected!");
			}

			// ✅ Click again to uncheck
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Unchecked checkbox: " + (i + 1));
			Thread.sleep(500);
			isMoveToBtnNotDisplayed();
			isBlockBtnNotDisplayed();
			isDeleteBtnNotDisplayed();
			isFrwrdManyBtnNotDisplayed();

			// ✅ Verify unchecked
			if (checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get unselected!");
			}

			Thread.sleep(500);
		}

	}

	public void clickEachCheckboxOneByOneSpamPromoRe() throws InterruptedException {

		List<WebElement> checkboxes = listCheckBox;

		System.out.println("Total checkboxes found: " + checkboxes.size());

		for (int i = 0; i < checkboxes.size() && i < 2; i++) {
			WebElement checkbox = checkboxes.get(i);

			// Scroll to element to ensure clickability
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			// ✅ Click to check
			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				isBlockBtnDisplayed();
				isDeleteBtnDisplayed();
				isNotSpamBtnDisplayed();
				isMarkAsReadUnreadBtnDisplayed();
			}

			// ✅ Verify checked
			if (!checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get selected!");
			}

			// ✅ Click again to uncheck
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Unchecked checkbox: " + (i + 1));
			Thread.sleep(500);
			isMoveToBtnNotDisplayed();
			isBlockBtnNotDisplayed();
			isDeleteBtnNotDisplayed();
			isNotSpamBtnNotDisplayed();
			isMarkAsReadUnreadBtnNotDisplayed();

			// ✅ Verify unchecked
			if (checkbox.isSelected()) {
				throw new RuntimeException("❌ Checkbox " + (i + 1) + " did NOT get unselected!");
			}

			Thread.sleep(500);
		}

	}

	public void moveToTrashRe() throws InterruptedException {
		clickforRefreshInboxUnreadRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToTrashBtnDisplayed();
			//	isMoveToSecureBtnDisplayed();
			//	isMoveToSpamPromoBtnDisplayed();
			    extractFolderNamesFromMoveTo(driver);				
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashInboxRe() throws InterruptedException {
		clickforRefreshInboxRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToTrashBtnDisplayed();
			//	isMoveToSecureBtnDisplayed();
			//	isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashSentRe() throws InterruptedException {
		clickforRefreshSentRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
		//		isMoveToInboxBtnDisplayed();
		//		isMoveToTrashBtnDisplayed();
		//		isMoveToSecureBtnDisplayed();
		//		isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashSentUnreadRe() throws InterruptedException {
		clickforRefreshSentUnreadRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToInboxBtnDisplayed();
			//	isMoveToTrashBtnDisplayed();
			//	isMoveToSecureBtnDisplayed();
			//	isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashDraftRe() throws InterruptedException {
		clickforRefreshDraftRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
				isMoveToTrashBtnDisplayed();
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToInboxFromTrashRe() throws InterruptedException {
		clickforRefreshTrashRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToInboxBtnDisplayed();
			//	isMoveToTrashBtnDisplayed();
			//	isMoveToSecureBtnDisplayed();
			//	isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToInboxBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToInboxFromTrashUnreadRe() throws InterruptedException {
		clickforRefreshTrashUnreadRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToInboxBtnDisplayed();
			//	isMoveToTrashBtnDisplayed();
			//	isMoveToSecureBtnDisplayed();
			//	isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToInboxBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashTemplateRe() throws InterruptedException {
		clickforRefreshTemplateRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
				isMoveToTrashBtnDisplayed();
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToInboxFromSnoozedRe() throws InterruptedException {
		clickforRefreshSnoozedRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToInboxBtnDisplayed();
		//		isMoveToTrashBtnDisplayed();
		//		isMoveToSecureBtnDisplayed();
		//		isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashSpamPromoRe() throws InterruptedException {
		clickforRefreshSpamPromoRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
		//		isMoveToInboxBtnDisplayed();
		//		isMoveToTrashBtnDisplayed();
		//		isMoveToSecureBtnDisplayed();
		//		isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void moveToTrashSpamPromoUnreadRe() throws InterruptedException {
		clickforRefreshSpamPromoUnreadRe();

		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			String checkboxId = checkbox1.getAttribute("value");
			System.out.println("Selected checkbox's Value : " + checkboxId);
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMoveToBtnDisplayed();
				clickMoveToBtn();
				Thread.sleep(500);
			//	isMoveToInboxBtnDisplayed();
		//		isMoveToTrashBtnDisplayed();
		//		isMoveToSecureBtnDisplayed();
			// 	isMoveToSpamPromoBtnDisplayed();
				extractFolderNamesFromMoveTo(driver);
				clickMoveToTrashBtn();

				int afterCount = areReAllEmailsUnselectedViaAll1();

				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the 'after' count (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before move to trash : " + beforeCount
						+ " Total checkboxes found After move to trash : " + afterCount);
				break;

			}

		}

	}

	public void blockEmailCancelBtnRe() throws InterruptedException {

		clickforRefreshInboxUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath("parentRowForBlockPage"));
			WebElement titleElement = parentRow.findElement(By.xpath("childRowForBlockPage"));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				Thread.sleep(2000);
				switchToMyAccountFrames();

				System.out.println(" Block Pop up Has been opend Successfully. ");
				isBlockEmailAddressTextDisplayed();
				isBlockEmailAddressSelectedTextDisplayed();
				isBlockEmailToggleBtnDisplayed();
				// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
				// selected. ");
				// System.out.println(" Toggle button is selected. ");
				isBlockSelectedEmailDisplayed();
				String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
						.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
				System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
				Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
						"On the block Pop up  email id is not matching with selected checkbox's email id ");
				System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
				isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
				// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
				// Checkbox for move email to trash is not checked. ");
				// System.out.println(" Checkbox fro move email to trash is checked. ");
				isblockCancelBtnDisplayed();
				clickBlockpageCancelBtn();
				switchToUnReadInboxFrameMc();
				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
						+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
						+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
						+ " After clicked on Cancel button of block page.");
				break;

			}
		}

	}

	public void blockEmailCancelBtnInboxRe() throws InterruptedException {

		clickforRefreshInboxRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				Thread.sleep(2000);
				switchToMyAccountFrames();

				System.out.println(" Block Pop up Has been opend Successfully. ");
				isBlockEmailAddressTextDisplayed();
				isBlockEmailAddressSelectedTextDisplayed();
				isBlockEmailToggleBtnDisplayed();
				// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
				// selected. ");
				// System.out.println(" Toggle button is selected. ");
				isBlockSelectedEmailDisplayed();
				String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
						.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
				System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
				Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
						"On the block Pop up  email id is not matching with selected checkbox's email id ");
				System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
				isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
				// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
				// Checkbox for move email to trash is not checked. ");
				// System.out.println(" Checkbox fro move email to trash is checked. ");
				isblockCancelBtnDisplayed();
				clickBlockpageCancelBtn();
				switchToInboxFrameIc();
				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
						+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
						+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
						+ " After clicked on Cancel button of block page.");
				break;

			}
		}

	}

	public void blockEmailCancelBtnTrashRe() throws InterruptedException {

		clickforRefreshTrashRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);

				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockDeleteSelectedMailFromTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					clickBlockpageCancelBtn();
					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
							+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
							+ " After clicked on Cancel button of block page.");
					break;

				}

				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email.   ");
				break;

			}
		}

	}

	public void blockEmailCancelBtnTrashUnreadRe() throws InterruptedException {

		clickforRefreshTrashUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);

				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockDeleteSelectedMailFromTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					clickBlockpageCancelBtn();
					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
							+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
							+ " After clicked on Cancel button of block page.");
					break;

				}

				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email.   ");
				break;

			}
		}

	}

	public void blockEmailCancelBtnSnoozedRe() throws InterruptedException {

		clickforRefreshSnoozedRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);

				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					clickBlockpageCancelBtn();
					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
							+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
							+ " After clicked on Cancel button of block page.");
					break;

				}

				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email.   ");
				break;

			}
		}

	}

	public void blockEmailCancelBtnSpamPromoRe() throws InterruptedException {

		clickforRefreshSpamPromoRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);

				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					clickBlockpageCancelBtn();
					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
							+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
							+ " After clicked on Cancel button of block page.");
					break;

				}

				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email.   ");
				break;

			}
		}

	}

	public void blockEmailCancelBtnSpamPromoUnreadRe() throws InterruptedException {

		clickforRefreshSpamPromoUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);

				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					clickBlockpageCancelBtn();
					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount == beforeCount, " Expected afterCount (" + afterCount
							+ ") should be equals to the beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " Total checkboxes found After Block the mail and move to trash : " + afterCount
							+ " After clicked on Cancel button of block page.");
					break;

				}

				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email.   ");
				break;

			}
		}

	}

	public void blockEmailBlockBtnRe() throws InterruptedException {
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				Thread.sleep(2000);
				switchToMyAccountFrames();

				System.out.println(" Block Pop up Has been opend Successfully. ");
				isBlockEmailAddressTextDisplayed();
				isBlockEmailAddressSelectedTextDisplayed();
				isBlockEmailToggleBtnDisplayed();
				// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
				// selected. ");
				// System.out.println(" Toggle button is selected. ");
				isBlockSelectedEmailDisplayed();
				String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
						.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
				System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
				Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
						"On the block Pop up  email id is not matching with selected checkbox's email id ");
				System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
				isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
				// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
				// Checkbox for move email to trash is not checked. ");
				// System.out.println(" Checkbox fro move email to trash is checked. ");
				isblockCancelBtnDisplayed();
				isblockpageBlockBtnDisplayed();
				clickBlockpageBlockBtn();
				Thread.sleep(2000);

				System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
				isBlockAlertTextDisplayed();
				isBlockAlertOkBtnDisplayed();
				clickBlockAlertOkBtn();

				switchToUnReadInboxFrameMc();
				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
						+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
				break;

			}
		}

	}

	public void blockEmailBlockBtnInboxRe() throws InterruptedException {
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				Thread.sleep(2000);
				switchToMyAccountFrames();

				System.out.println(" Block Pop up Has been opend Successfully. ");
				isBlockEmailAddressTextDisplayed();
				isBlockEmailAddressSelectedTextDisplayed();
				isBlockEmailToggleBtnDisplayed();
				// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
				// selected. ");
				// System.out.println(" Toggle button is selected. ");
				isBlockSelectedEmailDisplayed();
				String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
						.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
				System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
				Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
						"On the block Pop up  email id is not matching with selected checkbox's email id ");
				System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
				isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
				// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
				// Checkbox for move email to trash is not checked. ");
				// System.out.println(" Checkbox fro move email to trash is checked. ");
				isblockCancelBtnDisplayed();
				isblockpageBlockBtnDisplayed();
				clickBlockpageBlockBtn();
				Thread.sleep(2000);

				System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
				isBlockAlertTextDisplayed();
				isBlockAlertOkBtnDisplayed();
				clickBlockAlertOkBtn();

				switchToInboxFrameIc();
				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
						+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
						+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
				break;

			}
		}

	}

	public void blockEmailBlockBtnTrashRe() throws InterruptedException {
		clickforRefreshTrashRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			// Removing extra content and getting text between < senderTitle > .
			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockDeleteSelectedMailFromTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					isblockpageBlockBtnDisplayed();
					clickBlockpageBlockBtn();
					Thread.sleep(2000);

					System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
					isBlockAlertTextDisplayed();
					isBlockAlertOkBtnDisplayed();
					clickBlockAlertOkBtn();

					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
							+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
					break;

				}
				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email. ");
				break;

			}
		}

	}

	public void blockEmailBlockBtnTrashUnreadRe() throws InterruptedException {
		clickforRefreshTrashUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockDeleteSelectedMailFromTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					isblockpageBlockBtnDisplayed();
					clickBlockpageBlockBtn();
					Thread.sleep(2000);

					System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
					isBlockAlertTextDisplayed();
					isBlockAlertOkBtnDisplayed();
					clickBlockAlertOkBtn();

					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
							+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
					break;

				}
				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email. ");
				break;

			}
		}

	}

	public void blockEmailBlockBtnSnoozedRe() throws InterruptedException {
		clickforRefreshSnoozedRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					isblockpageBlockBtnDisplayed();
					clickBlockpageBlockBtn();
					Thread.sleep(2000);

					System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
					isBlockAlertTextDisplayed();
					isBlockAlertOkBtnDisplayed();
					clickBlockAlertOkBtn();

					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
							+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
					break;

				}
				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email. ");
				break;

			}
		}

	}

	public void blockEmailBlockBtnSpamPromoRe() throws InterruptedException {
		clickforRefreshSpamPromoRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					isblockpageBlockBtnDisplayed();
					clickBlockpageBlockBtn();
					Thread.sleep(2000);

					System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
					isBlockAlertTextDisplayed();
					isBlockAlertOkBtnDisplayed();
					clickBlockAlertOkBtn();

					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
							+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
					break;

				}
				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email. ");
				break;

			}
		}

	}

	public void blockEmailBlockBtnSpamPromoUnreadRe() throws InterruptedException {
		clickforRefreshSpamPromoUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);
			// String checkboxText = checkbox1.getAttribute("value");
			// Step 2️⃣: Get the parent <tr> of the checkbox
			WebElement parentRow = checkbox1.findElement(By.xpath(parentRowForBlockPage));
			WebElement titleElement = parentRow.findElement(By.xpath(childRowForBlockPage));

			// Step 4️⃣: Extract the title value
			String senderTitle = titleElement.getAttribute("title");
			System.out.println("Selected checkbox's Value : " + senderTitle);

			String email = senderTitle.replaceAll(".*<(.*)>.*", "$1");
			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isBlockBtnDisplayed();
				clickBlockBtn();
				if (!email.equals(userName) && !email.equals(userName1)) {
					Thread.sleep(2000);
					switchToMyAccountFrames();

					System.out.println(" Block Pop up Has been opend Successfully. ");
					isBlockEmailAddressTextDisplayed();
					isBlockEmailAddressSelectedTextDisplayed();
					isBlockEmailToggleBtnDisplayed();
					// Assert.assertTrue(blockEmailToggleBtn.isSelected()," Toggle button is not
					// selected. ");
					// System.out.println(" Toggle button is selected. ");
					isBlockSelectedEmailDisplayed();
					String selectedcheckboxText = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].childNodes[0].nodeValue.trim();", blockSelectedEmail);
					System.out.println("Selected checkbox's Value on Block pop up : " + selectedcheckboxText);
					Assert.assertTrue(senderTitle.contains(selectedcheckboxText),
							"On the block Pop up  email id is not matching with selected checkbox's email id ");
					System.out.println(" On the block Pop up  email id is matching with selected checkbox's email id ");
					isBlockMoveSelectedEmailsToTrashCheckBoxDisplayed();
					// Assert.assertTrue(blockMoveSelectedEmailsToTrashCheckBox.isSelected(),"
					// Checkbox for move email to trash is not checked. ");
					// System.out.println(" Checkbox fro move email to trash is checked. ");
					isblockCancelBtnDisplayed();
					isblockpageBlockBtnDisplayed();
					clickBlockpageBlockBtn();
					Thread.sleep(2000);

					System.out.println(" Alert Page Has been opend Successfully after clicked on block button. ");
					isBlockAlertTextDisplayed();
					isBlockAlertOkBtnDisplayed();
					clickBlockAlertOkBtn();

					switchToUnReadInboxFrameMc();
					int afterCount = areReAllEmailsUnselectedViaAll1();
					Assert.assertTrue(afterCount < beforeCount, "Expected afterCount (" + afterCount
							+ ") to be less than the  beforecount (" + beforeCount + "). Assertion failed.");
					System.out.println("Total checkboxes found before Block the mail and move to trash : " + beforeCount
							+ " And Total checkboxes found After Block the mail and move to trash : " + afterCount);
					break;

				}
				String alertText = "";
				Alert alert = driver.switchTo().alert();
				alertText = alert.getText();
				String actualAlert = alertMsgEmail;
				Assert.assertEquals(actualAlert, alertText, "Alert Text is not Matching with Actual Alert Text In");
				alert.accept();
				System.out.println(
						" Selected Checkbox's owner is the owner of Opened mail id that's why we can not block this email. ");
				break;

			}
		}

	}

	public void deleteBtnRe() throws InterruptedException {

		clickforRefreshInboxUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnInboxRe() throws InterruptedException {

		clickforRefreshInboxRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnSentRe() throws InterruptedException {

		clickforRefreshSentRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnSentUnreadRe() throws InterruptedException {

		clickforRefreshSentUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnDraftRe() throws InterruptedException {

		clickforRefreshDraftRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnTrashRe() throws InterruptedException {

		clickforRefreshTrashRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnTrashUnreadRe() throws InterruptedException {

		clickforRefreshTrashUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnTemplateRe() throws InterruptedException {

		clickforRefreshTemplateRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnSnoozedRe() throws InterruptedException {

		clickforRefreshSnoozedRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnSpamPromoRe() throws InterruptedException {

		clickforRefreshSpamPromoRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void deleteBtnSpamPromoUnreadRe() throws InterruptedException {

		clickforRefreshSpamPromoUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {
			int beforeCount = checkboxes.size();
			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isDeleteBtnDisplayed();
				clickDeleteBtn();
				Thread.sleep(2000);

				int afterCount = areReAllEmailsUnselectedViaAll1();
				Assert.assertTrue(afterCount < beforeCount, " Expected afterCount (" + afterCount
						+ ") should be less than to the beforecount (" + beforeCount + "). Assertion failed.");
				System.out.println("Total checkboxes found before Delete the mail : " + beforeCount
						+ "  and Total checkboxes found After Delete the mail : " + afterCount);
				break;

			}
		}

	}

	public void unreadReadEamilRe() throws InterruptedException {

		clickforRefreshInboxUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadDisplayed()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilInboxRe() throws InterruptedException {

		clickforRefreshInboxRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilSentRe() throws InterruptedException {

		clickforRefreshSentRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEmailSentUnreadRe() throws InterruptedException {

		clickforRefreshSentUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilTrashRe() throws InterruptedException {

		clickforRefreshTrashRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilTrashUnreadRe() throws InterruptedException {

		clickforRefreshTrashUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilSpamPromoRe() throws InterruptedException {

		clickforRefreshSpamPromoRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void unreadReadEamilSpamPromoUnreadRe() throws InterruptedException {

		clickforRefreshSpamPromoUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isMarkAsReadUnreadBtnDisplayed();

				if (isSelectedEmailUnreadVisible()) {
					System.out.println(" Selected Email is Unreaded. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailReadDisplayed();
				} else {
					System.out.println(" Selected Email is Readed. ");
					clickMarkAsReadUnreadBtn();
					isSelectedEmailUnreadDisplayed();
				}

				break;

			}
		}

	}

	public void forwordManyBtnRe() throws InterruptedException {

		clickforRefreshInboxUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void forwordManyBtnInboxRe() throws InterruptedException {

		clickforRefreshInboxRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				switchToMyAccountFrames();
				switchToUnReadInboxFrameMc();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void forwordManyBtnSentRe() throws InterruptedException {

		clickforRefreshSentRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				switchToMyAccountFrames();
				switchToUnReadInboxFrameMc();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void forwordManyBtnTrashRe() throws InterruptedException {

		clickforRefreshTrashRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				switchToMyAccountFrames();
				switchToUnReadInboxFrameMc();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void forwordManyBtnTrashUnreadRe() throws InterruptedException {

		clickforRefreshTrashUnreadRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				switchToMyAccountFrames();
				switchToUnReadInboxFrameMc();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void forwordManyBtnSnoozedRe() throws InterruptedException {

		clickforRefreshSnoozedRe();
		List<WebElement> checkboxes = listCheckBox;

		for (int i = 0; i < checkboxes.size(); i++) {

			// System.out.println("Total checkboxes found before move to trash : " +
			// beforeCount);
			WebElement checkbox1 = checkboxes.get(i);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox1);

			// ✅ Click to check
			if (!checkbox1.isSelected() && i == 0) {

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox1);
				System.out.println("✅ Checked checkbox: " + (i + 1));
				Thread.sleep(500);
				isFrwrdManyBtnDisplayed();
				clickFrwrdManyBtn();
				switchToMyAccountFrames();
				switchToUnReadInboxFrameMc();
				isFrwrdManyAsOneEmailBtnDisplayed();
				driver.navigate().refresh();
				switchToMyAccountFrames();
				break;

			}
		}

	}

	public void inboxPageMenubeforCheckRe() throws InterruptedException {

		isCheckMailBtnDisplayed();
		System.out.println(" Inbox Page opened successfully. ");
		isAllBtnDisplayed();
		isEcommerceBtnDisplayed();
		isGovBtnDisplayed();
		isBankBtnDisplayed();
		isSocialBtnDisplayed();
		isTravelBtnDisplayed();
		isOtpBtnDisplayed();
		isSubscriptionsBtnDisplayed();
	}

	// Assume 'driver' is your initialized WebDriver instance
	public List<String> getDynamicFolderNames(WebDriver driver) throws InterruptedException {

	    isMoreBtnDisplayedRe();
	    clickMoreBtnRe();
	    switchToUnReadInboxFrameMc();
	    isMyFolderBtnDisplayedRe();
	    clickMyFolderBtnRe();

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    List<String> folderNames = new ArrayList<>();

	 
	    // STEP 1: Expand ALL nested folders
	    
	    boolean moreToExpand = true;

	    while (moreToExpand) {

	        moreToExpand = false; // assume no more "+" icons

	        List<WebElement> plusIcons =
	            driver.findElements(By.xpath("//img[starts-with(@id,'plusFolder')]"));

	        for (WebElement plus : plusIcons) {

	            try {
	                String src = plus.getAttribute("src");

	                // Only click "+" icons (not "-" icons)
	                if (src.contains("plus")) {
	                    js.executeScript("arguments[0].scrollIntoView(true);", plus);
	                    Thread.sleep(150);

	                    js.executeScript("arguments[0].click();", plus);
	                    Thread.sleep(400);  // wait for subfolders to load

	                    moreToExpand = true; // new folders loaded → check again
	                }

	            } catch (Exception ignored) {}
	        }
	    }

	
	    // STEP 2: Extract folder names
	
	    List<WebElement> folderElements =
	        driver.findElements(By.xpath("//td[contains(@id,'FolderCol')]//b"));

	    for (WebElement el : folderElements) {
	        try {
	            String name = el.getText().trim();
	            if (!name.isEmpty() && !folderNames.contains(name)) {
	                folderNames.add(name);
	                System.out.println("Extracted Folder Name: " + name);
	            }
	        } catch (Exception ignore) {}
	    }

	    return folderNames;
	}
	
	public void printFolderNames() throws InterruptedException {

        List<String> folderList = getDynamicFolderNames(driver);

        System.out.println("Folder names inside More's My Folder : " + folderList);
    }

	public List<String> extractFolderNamesFromMoveTo(WebDriver driver) throws InterruptedException {
	    List<String> folderNames = new ArrayList<>();
	    WebElement container = driver.findElement(By.id("mCSB_1_container"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    int previousSize = -1;
	    int stableCount = 0;
	    int maxStable = 3;

	    while (true) {
	        List<WebElement> items = container.findElements(By.xpath(".//li"));

	        for (WebElement li : items) {
	            String name = li.getText().trim();
	            if (!name.isEmpty() && !folderNames.contains(name)) {
	                folderNames.add(name);
	                System.out.println( name + " button is visible after click on Move to Button.");
	            }
	        }

	        if (folderNames.size() == previousSize) {
	            stableCount++;
	        } else {
	            stableCount = 0;
	        }
	        previousSize = folderNames.size();

	        if (stableCount >= maxStable) break;

	        // if there is at least one li, scroll the last one into view
	        if (!items.isEmpty()) {
	            WebElement last = items.get(items.size() - 1);
	            js.executeScript("arguments[0].scrollIntoView(true);", last);
	        } else {
	            // nothing to scroll, break to avoid infinite loop
	            break;
	        }

	        Thread.sleep(800);
	    }

	    return folderNames;
	}

	public void printFolderNamesFromMoveTo() throws InterruptedException {

        List<String> folderList = extractFolderNamesFromMoveTo(driver);

        System.out.println("Folder names inside Move To : " + folderList);
    }

}
