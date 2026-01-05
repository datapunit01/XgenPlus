package pageObjects;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BasePage {
	public WebDriverWait wait;
	public WebDriver driver;
	public Properties p;
	public BasePage(WebDriver driver) throws IOException
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
	}
	
	

}