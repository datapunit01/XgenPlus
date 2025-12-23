package pageObjects;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.ReusableForUnread;

// Import the SLF4J Logger
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ComposePage extends BasePage {
	
	// --- 1. Define the Logger for this class ---
	private static final Logger log = LoggerFactory.getLogger(ComposePage.class);
	
	
	public ReusableForUnread ruse;
	
    // Initializing properties/test data
	String invalidErrorMessageTextForTo = p.getProperty("invalidErrorMessageForTo");
	String invalidErrorMessageTextForCc = p.getProperty("invalidErrorMessageForCc");
	String invalidErrorMessageTextForBcc = p.getProperty("invalidErrorMessageForBcc");
	String forBoldText = p.getProperty("boldText");
	String forItalicText = p.getProperty("italicText");
	String forUnderlineText = p.getProperty("underlineText");
	String forStrikethroughText = p.getProperty("strikethroughText");
	String forNormalText = p.getProperty("normalText");
	String forFontSizeText = p.getProperty("fontSizeText");
	String forFontSize = p.getProperty("fontSize");
	String pathForFileUpload = "C:\\Users\\DATA\\OneDrive\\Desktop\\sample_invoice.pdf";
	String pathForInlineImageUpload = "C:\\Users\\DATA\\OneDrive\\Desktop\\TestingFarmhouse.jpg";		
	String validMsgForMailSuccess = p.getProperty("validSuccessMessage");
		

	public ComposePage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());
		ruse = new ReusableForUnread(driver);
		log.info("ComposePage initialized with WebDriver and ReusableForUnread utilities.");
	}

	public boolean isComposeBtnDisplayed() {
		log.debug("Checking if Compose Button is displayed.");
		return ruse.isComposeBtnDisplayedRe();
	}

	public void clickComposeBtn() {
		log.info("Clicking the Compose Button.");
		ruse.clickComposeBtnRe();

	}

	public boolean isFromTextOnTopOfComposeDisplayed() {
		log.debug("Checking if 'From' text is displayed on top of Compose window.");
		return ruse.isFromTextOnTopOfComposeDisplayedRe();
	}

	public boolean isFromMailOnTopOfComposeDisplayed() {
		log.debug("Checking if 'From' email address is displayed on top of Compose window.");
		return ruse.isFromMailOnTopOfComposeDisplayedRe();

	}

	public void fromMailCheckOnTopofComposePageRe() throws InterruptedException {
		log.info("Verifying the 'From' email address value on Compose Page.");
		ruse.fromMailCheckOnTopofComposePageRe();

	}

	public boolean isOnComposeToTextDisplayed() {
		log.debug("Checking if the 'To' field element is displayed.");
		return ruse.isOnComposeToTextDisplayedRe();

	}
	
	public void clickToField() {
		log.info("Clicking the 'To' input field.");
		ruse.clickToTextFieldRe();

	}

	public boolean isOnComposeCcBtnDisplayedRe() {
		log.debug("Checking if the 'Cc' button is displayed.");
		return ruse.isOnComposeCcBtnDisplayedRe();

	}

	public void clickCcBtn() {
		log.info("Clicking the 'Cc' button to reveal the field.");
		ruse.clickCcBtnRe();

	}

	public boolean isOnComposeCcTextAfterClickDisplayed() {
		log.debug("Checking if the 'Cc' text input field is displayed after clicking the button.");
		return ruse.isOnComposeCcTextAfterClickDisplayedRe();

	}

	public boolean isOnComposeBccBtnDisplayedRe() {
		log.debug("Checking if the 'Bcc' button is displayed.");
		return ruse.isOnComposeBccBtnDisplayedRe();

	}

	public void clickBccBtn() {
		log.info("Clicking the 'Bcc' button to reveal the field.");
		ruse.clickBccBtnRe();

	}

	public boolean isOnComposeBccTextAfterClickDisplayed() {
		log.debug("Checking if the 'Bcc' text input field is displayed after clicking the button.");
		return ruse.isOnComposeBccTextAfterClickDisplayedRe();

	}

	public boolean isOnComposeSubjectTextDisplayed() {
		log.debug("Checking if the 'Subject' text input field is displayed.");
		return ruse.isOnComposeSubjectTextDisplayedRe();

	}
	
	public void clickOnComposeSubjectTextField() {
		log.info("Clicking the 'Subject' text input field.");
		ruse.clickOnComposeSubjectTextFieldRe();

	}

	public boolean isOnComposeBodyDisplayed() {
		log.debug("Checking if the mail body/editor is displayed.");
		return ruse.isOnComposeBodyDisplayedRe();

	}
	
	public void clickOnComposeBody() {
		log.info("Clicking on the mail body/editor to gain focus.");
		ruse.clickOnComposeBodyRe();

	}

	public boolean isOnComposeSendBtnDisplayed() {
		log.debug("Checking if the 'Send' button is displayed.");
		return ruse.isOnComposeSendBtnDisplayedRe();

	}
	
	public void clickSendBtn() {
		log.info("Clicking the 'Send' button.");
		ruse.clickSendOnComposeBtnRe();

	}
	
	public boolean istoastMsgForSuccessDisplayed() {
		String toastMsgForMailSuccess = ruse.toastMsgForSuccess.getText();
		
        // Log the actual and expected values before assertion
        log.debug("Verifying success toast message. Actual: '{}', Expected: '{}'", toastMsgForMailSuccess, validMsgForMailSuccess);
        
		Assert.assertEquals(toastMsgForMailSuccess, validMsgForMailSuccess,
				"Invalid error message for mail success is displayed as expected.");
        
        log.info("Mail success toast message confirmed: {}", validMsgForMailSuccess);
		return ruse.istoastMsgForSuccessDisplayedRe();

	}
	
	public void composeMailWithInvalidTo(String invalidMailForTo, String validMailForTo, String invalidMailForCc, String validMailForCc, String invalidMailForBcc, String validMailForBcc, String subject, String textforMailBody) throws InterruptedException {
		
        log.info("--- Starting Mail Compose Test with Address Validation and Formatting ---");
	
		// --- TO Field Validation ---
		ruse.clickToTextFieldRe();
        log.debug("Inputting invalid 'To' address: {}", invalidMailForTo);
		ruse.onComposeToTextField.sendKeys(invalidMailForTo);
		
		clickSendBtn();
		Thread.sleep(1000); 
		
		String toastMsgForInvalidToText = ruse.toastMsgForInvalidTo.getText();
        log.debug("Toast message received for invalid 'To': {}", toastMsgForInvalidToText);
        
		Assert.assertEquals(toastMsgForInvalidToText, invalidErrorMessageTextForTo,
				"Invalid error message for To field was NOT displayed as expected.");
		
		log.info("Invalid 'To' address validation successful. Clearing field and setting valid address: {}", validMailForTo);
		Thread.sleep(1000);
		ruse.onComposeToTextField.clear();
		ruse.onComposeToTextField.sendKeys(validMailForTo);
		
	
		// --- CC Field Validation ---
		ruse.clickCcTextFieldRe();
        log.debug("Inputting invalid 'Cc' address: {}", invalidMailForCc);
		ruse.onComposeCcTextField.sendKeys(invalidMailForCc);
		
		clickSendBtn();
		String toastMsgForInvalidCcText = ruse.toastMsgForInvalidTo.getText();
        log.debug("Toast message received for invalid 'Cc': {}", toastMsgForInvalidCcText);
        
		Assert.assertEquals(toastMsgForInvalidCcText, invalidErrorMessageTextForCc,
				"Invalid error message for Cc field was NOT displayed as expected.");
		
		log.info("Invalid 'Cc' address validation successful. Clearing field and setting valid address: {}", validMailForCc);
		Thread.sleep(1000);
		ruse.onComposeCcTextField.clear();
		ruse.onComposeCcTextField.sendKeys(validMailForCc);
		
	
		// --- BCC Field Validation ---
		ruse.clickBccTextFieldRe();
        log.debug("Inputting invalid 'Bcc' address: {}", invalidMailForBcc);
		ruse.onComposeBccTextField.sendKeys(invalidMailForBcc);
		
		clickSendBtn();
		String toastMsgForInvalidBccText = ruse.toastMsgForInvalidTo.getText();
        log.debug("Toast message received for invalid 'Bcc': {}", toastMsgForInvalidBccText);
        
		Assert.assertEquals(toastMsgForInvalidBccText, invalidErrorMessageTextForBcc,
				"Invalid error message for Bcc field was NOT displayed as expected.");
		
		log.info("Invalid 'Bcc' address validation successful. Clearing field and setting valid address: {}", validMailForBcc);
		Thread.sleep(1000);
		ruse.onComposeBccTextField.clear();
		ruse.onComposeBccTextField.sendKeys(validMailForBcc);
		
		Thread.sleep(1000);
	
		// --- Subject and Body ---
		clickOnComposeSubjectTextField();        
	//	ruse.onComposeSubjectText.sendKeys(textforMailSubject);		
		log.debug("Setting mail subject: {}", subject);
		ruse.onComposeSubjectText.sendKeys(subject);
		
		Thread.sleep(1000);
		isOnComposeBodyDisplayed();		
		ruse.isOnComposeFormattingBtnDisplayedRe();
		
		
		// --- Text Formatting ---
		log.info("Applying text formatting styles.");
		ruse.typeBold(forBoldText);
		ruse.typeItalic(forItalicText);
		ruse.typeUnderline(forUnderlineText);
		ruse.typeStrikethrough(forStrikethroughText);
		ruse.typeNormalText(forNormalText);
		ruse.typeFontSizedText(forFontSize,forFontSizeText);
	
		// ruse.typeBackgroundColoredText( "Yellow"," This text has yellow background color. ");
		
		// ruse.isOnComposeAttachmentBtnDisplayedRe();
		// ruse.clickAttachmentBtnOnComposeRe();
		
        // --- Attachments ---
        log.info("Attaching standard file from path: {}", pathForFileUpload);
		ruse.attachFile(pathForFileUpload);
		
		Thread.sleep(1000);
        log.info("Attaching inline image from path: {}", pathForInlineImageUpload);
		ruse.attachInlineImage(pathForInlineImageUpload);
		
		// --- Final Send ---
		clickSendBtn();
		log.info("Clicked Send button. Verifying success toast.");
		istoastMsgForSuccessDisplayed();
		Thread.sleep(2000);
		log.info(" Mail Compose Test Completed Successfully ");
	}
	
	public void verifyMailFromSentBox(String mailSubjectText,String nameOfInlineImage) throws InterruptedException, IOException {
		log.info("Verifying sent mail in Sent Box with subject: {}", mailSubjectText);
		ruse.verifyMailInSentBoxRe(mailSubjectText,nameOfInlineImage);		
		log.info(" Compose's subjet Successfully verified from sent folder. ");		
		ruse.switchToMyAccountFrames();
	}

}