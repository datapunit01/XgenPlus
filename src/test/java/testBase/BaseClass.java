package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.MyAccountPage;
import pageObjects.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;
    public WebDriverWait wait;

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass
    @Parameters("browser")
    public void setup(String br) throws IOException {
        // Load config.properties
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

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

        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(p.getProperty("appURL"));

        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        logger = LogManager.getLogger(this.getClass());
        logger.info("✅ Browser launched: " + br);
        logger.info("✅ Application URL opened: " + p.getProperty("appURL"));
    }

    @BeforeMethod
    public void switchToLoginFrame() {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame("topFrame");
    }
    // ✅ Reusable Login Method
    public MyAccountPage doLogin() throws IOException, InterruptedException
    {
     
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
      //  lp.isSkipButtonDisplayed();
      //  lp.clickSkip();
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
    
    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            logger.info("✅ Browser closed successfully.");
        }
    }
}