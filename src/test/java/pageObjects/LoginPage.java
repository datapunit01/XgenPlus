package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LoginPage extends BasePage {

	public LoginPage(ThreadLocal<WebDriver> driver) throws IOException {
		super(driver.get());

	}

	@FindBy(id = "username")
	public WebElement txtEmailAddress;

	@FindBy(id = "signinNxt")
	WebElement nextBtn;

	@FindBy(id = "password")
	WebElement txtPassword;

	@FindBy(id = "loginBtn")
	WebElement loginBtn;

	@FindBy(xpath = "//input[@id='btn_step_23']")
	WebElement skipBtn;

	@FindBy(xpath = "//span[@id='spLoginErrmsg']")
	WebElement passPageError;

	@FindBy(xpath = "//span[@id='spLoginErrmsg']")
	WebElement passPageBlank;

	@FindBy(xpath = "//img[@src='svg/images/arrow-mail.svg']")
	WebElement passBackBtn;

	// -------------------- Reusable Helper --------------------
	private boolean isElementDisplayedAndClickable(WebElement element, String elementName) {
		try {
			boolean status = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
			Assert.assertTrue(status, elementName + " is NOT visible or clickable!");
			return status;
		} catch (Exception e) {
			Assert.fail(elementName + " not visible/clickable. Exception: " + e.getMessage());
			return false;
		}
	}

	// -------------------- Actions --------------------

	// Email
	public boolean isEmailBoxDisplayed() {
		return isElementDisplayedAndClickable(txtEmailAddress, "Email box");
	}

	public void setEmail(String email) {
		txtEmailAddress.clear();
		txtEmailAddress.sendKeys(email);
	}

	// Next
	public boolean isNextButtonDisplayed() {
		return isElementDisplayedAndClickable(nextBtn, "Next button");
	}

	public void clickNext() {
		nextBtn.click();
	}

	// Password
	public boolean isPasswordBoxDisplayed() {
		return isElementDisplayedAndClickable(txtPassword, "Password box");
	}

	public void setPassword(String password) {
		txtPassword.clear();
		txtPassword.sendKeys(password);
	}

	// Login
	public boolean isLoginButtonDisplayed() {
		return isElementDisplayedAndClickable(loginBtn, "Login button");
	}

	public void clickLogin() throws InterruptedException {
		loginBtn.click();
		Thread.sleep(2000);
		try {
			// Wait for potential error message
			String actualError = passPageError.getText().trim();
			String expectedError = p.getProperty("passwordPageError").trim();
			String actualErrorBlank = passPageError.getText().trim();
			String expectedErrorBlank = p.getProperty("passwordPageBlank").trim();
			if (actualError.equalsIgnoreCase(expectedError)) {
				System.out.println("Password Page Error :- " + actualError);
				txtPassword.clear();
				passBackBtn.click();
				txtEmailAddress.clear();
			} else if (actualErrorBlank.equalsIgnoreCase(expectedErrorBlank)) {
				System.out.println("Password Page Blank Error :- " + actualErrorBlank);
				passBackBtn.click();
				txtEmailAddress.clear();
			}
		} catch (Exception e) {
			// No error found → valid login case
			System.out.println("No password error displayed, login might be successful.");
		}
	}

	// Skip
	public boolean isSkipButtonDisplayed() {
		return isElementDisplayedAndClickable(skipBtn, "Skip button is successfully visible.");
	}

	public void clickSkip() {
		skipBtn.click();
	}

	public boolean isSkipButtonForSkipPageDisplayed() {
		try {
			return skipBtn.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}