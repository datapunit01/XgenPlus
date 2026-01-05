package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.MyAccountPage;
import utilities.EmailUtil;
import pageObjects.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utilities.ScreenshotUtil;
import utilities.ExecutionEnvironment;


public class BaseClass {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger;
	public Properties p;
	public WebDriverWait wait;

	public WebDriver getDriver() {
		return driver.get();
	}

	
/*	public void setup(String br) throws IOException {
		// Load config.properties
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		/*		
		// Browser setup
		switch (br.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "C:\\driver\\edgedriver_win64\\msedgedriver.exe");
			driver.set(new EdgeDriver());
			break;
		default:
			throw new IllegalArgumentException("❌ Invalid browser: " + br);
		}
	
		// Headless Browser setup
		switch (br.toLowerCase()) {

		case "chrome":
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions chromeOptions = new ChromeOptions();
		  //  chromeOptions.addArguments("--headless=new");
		    chromeOptions.addArguments("--no-sandbox");
		    chromeOptions.addArguments("--disable-dev-shm-usage");
		    chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-extensions");
		    driver.set(new ChromeDriver(chromeOptions));
		    break;

		case "firefox":
		    WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.addArguments("--headless");
		    driver.set(new FirefoxDriver(firefoxOptions));
		    break;

		case "edge":
		    WebDriverManager.edgedriver().setup();
		    EdgeOptions edgeOptions = new EdgeOptions();
		    edgeOptions.addArguments("--headless=new");
		    driver.set(new EdgeDriver(edgeOptions));
		    break;

		default:
		    throw new IllegalArgumentException("Invalid browser: " + br);
		}
	

		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();
		getDriver().get(p.getProperty("appURL"));

		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		logger = LogManager.getLogger(this.getClass());
		logger.info(" Browser launched: " + br);
		logger.info(" Application URL opened: " + p.getProperty("appURL"));
	}*/
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {

	    // Load config.properties
	    FileReader file = new FileReader("./src/test/resources/config.properties");
	    p = new Properties();
	    p.load(file);

	    boolean isJenkins = ExecutionEnvironment.isRunningOnJenkins();

	    logger = LogManager.getLogger(this.getClass());

	    if (isJenkins) {
	        logger.info("🚀 Running on JENKINS (Windows)");
	    } else {
	        logger.info("🖥️ Running on LOCAL machine");
	    }

	    switch (br.toLowerCase()) {

	        case "chrome":
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions chromeOptions = new ChromeOptions();

	            // ❗ DO NOT FORCE HEADLESS
	            if (isJenkins) {
	            	chromeOptions.addArguments("--no-sandbox");
	            	chromeOptions.addArguments("--disable-dev-shm-usage");
	            	chromeOptions.addArguments("--window-size=1920,1080");
	            	chromeOptions.addArguments("--disable-gpu");
	            	chromeOptions.addArguments("--disable-notifications");
	            	chromeOptions.addArguments("--disable-extensions");
	            	chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
	            }

	            chromeOptions.addArguments("--window-size=1920,1080");
	            chromeOptions.addArguments("--no-sandbox");
	            chromeOptions.addArguments("--disable-dev-shm-usage");

	            driver.set(new ChromeDriver(chromeOptions));
	            break;

	        case "firefox":
	            WebDriverManager.firefoxdriver().setup();
	            FirefoxOptions firefoxOptions = new FirefoxOptions();

	            if (isJenkins) {
	                // ❗ Do NOT add headless unless required
	                firefoxOptions.addArguments("--width=1920");
	                firefoxOptions.addArguments("--height=1080");
	            }

	            driver.set(new FirefoxDriver(firefoxOptions));
	            break;

	        case "edge":
	            WebDriverManager.edgedriver().setup();
	            EdgeOptions edgeOptions = new EdgeOptions();

	            if (isJenkins) {
	                edgeOptions.addArguments("--start-maximized");
	            }

	            driver.set(new EdgeDriver(edgeOptions));
	            break;

	        default:
	            throw new IllegalArgumentException("❌ Invalid browser: " + br);
	    }

	    getDriver().manage().deleteAllCookies();
	    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    getDriver().manage().window().maximize();
	    getDriver().get(p.getProperty("appURL"));

	    // ✅ Jenkins-aware explicit wait
	    if (isJenkins) {
	        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(40));
	    } else {
	        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
	    }

	    logger.info("Browser launched: " + br);
	    logger.info("Application URL opened: " + p.getProperty("appURL"));
	}


	@BeforeMethod
	public void switchToLoginFrame() {
		getDriver().switchTo().defaultContent();
		getDriver().switchTo().frame("topFrame");
	}

	// ✅ Reusable Login Method
	public MyAccountPage doLogin() throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);

		// 1️ Verify email input box is visible and enter email
		lp.isEmailBoxDisplayed();
		lp.setEmail(p.getProperty("email"));

		// 2️ Verify Next button is visible and clickable
		lp.isNextButtonDisplayed();
		lp.clickNext();
		Thread.sleep(2000);

		// 3️ Verify password input box is visible and enter password
		lp.isPasswordBoxDisplayed();
		lp.setPassword(p.getProperty("password"));

		// 4️ Verify Login button is visible and clickable
		lp.isLoginButtonDisplayed();
		lp.clickLogin();

		// 5 Verify Skip button is visible and clickable
		if (lp.isSkipButtonForSkipPageDisplayed()) {
			lp.isSkipButtonDisplayed();
			lp.clickSkip();
		}
		// lp.isSkipButtonDisplayed();
		// lp.clickSkip();
		Thread.sleep(3000);

		// -------------------- Step 2: MyAccount Page --------------------
		MyAccountPage macc = new MyAccountPage(driver);

		// Switch to nested frames
		macc.switchToMyAccountFrames();

		macc.isCrossDisplayed();
		macc.clickCross();

		// Verify page elements and title
		macc.verifyPageElements();
		return macc;
	}
	
	@AfterMethod
	public void captureFailureScreenshot(ITestResult result) {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        try {
	            String path = ScreenshotUtil.captureScreenshot(
	                    getDriver(),
	                    result.getName()
	            );
	            logger.error("Test Failed. Screenshot saved at: " + path);
	        } catch (IOException e) {
	            logger.error("Failed to capture screenshot", e);
	        }
	    }
	}

	@AfterClass
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
			logger.info(" Browser closed successfully.");
		}
	}
		@AfterSuite
	public void sendLogsAfterSuite() throws IOException {
        String logPath = System.getProperty("user.dir") + "/logs/automation.log";
        EmailUtil.sendLogsByEmail(logPath);
    }
}