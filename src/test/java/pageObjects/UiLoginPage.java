package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import testBase.BaseClass;

public class UiLoginPage extends BasePage{
	


	public Logger logger;  //Log4j
	
	public WebDriverWait wait;	
	public BaseClass base;
	public static String enteredValue;
	public static String userName;
	public String textBox;
    public UiLoginPage(WebDriver driver) throws IOException {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
    }
    
    public void baseVerifyLogo() throws InterruptedException 
	{
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='brand']")));
        Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed!");

        String styleAttr = logo.getAttribute("style");
        String src = styleAttr.substring(styleAttr.indexOf("url(") + 4, styleAttr.indexOf(")"));
        Reporter.log("Extracted Logo SRC: " + src, true);

     // ✅ Get expected logo name from config.properties
        String expectedLogo = p.getProperty("expectedLogo");
        Assert.assertTrue(styleAttr.contains(expectedLogo),  "Correct logo is missing!");
	}
    public void baseDefaultDropdown() throws InterruptedException 
	{
		WebElement langDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSetlang")));
        Assert.assertTrue(langDropdown.isDisplayed(), "Dropdown is NOT visible!");

        Select select = new Select(langDropdown);
        String selectedText = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedText, "English", "Default selected option should be 'English'.");
	}
	
	public void baseVerifyLanguageDropdown() throws InterruptedException 
	{
		WebElement langDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSetlang")));
        Select select = new Select(langDropdown);
        List<WebElement> options = select.getOptions();

        List<String> expectedValues = Arrays.asList(
                "English", "हिंदी", "ਪੰਜਾਬੀ", "বাঙালি", "తెలుగు", "தமிழ்",
                "ગુજરાતી", "मराठी", "русский", "中文", "العربية",
                "한국인", "ҚАЗАҚ", "qazaq latin", "казахский русский",
                "日本人", "ಕನ್ನಡ", "ଓଡିଆ", "संस्कृत", "ᱥᱟᱱᱛᱷᱟᱲᱤ"
        );

        for (String expected : expectedValues) {
            boolean found = options.stream().anyMatch(opt -> opt.getText().trim().equals(expected));
            Assert.assertTrue(found, "Option '" + expected + "' is missing!");
        }

        Assert.assertEquals(options.size(), expectedValues.size(),
                "Dropdown option count does not match expected!");
	}
	public void baseSighIn(String lang) throws InterruptedException 
	{
		WebElement sgn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='signInLabel']")));
        String labelText= sgn.getText();
		
		 String expSignIn;
		    switch (lang) {
		    
		        case "English":
		        	expSignIn= "Sign in";
		            break;
		        default:
		            throw new IllegalArgumentException("Unsupported language: " + lang);  
		    }
		    Assert.assertEquals(labelText, expSignIn, "Label text is not matching!");   
	}
	
	public void baseInputBox(String lang) throws InterruptedException {
        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        String plcHolder = textBox.getAttribute("placeholder");

        String expPlaceHolder;
        switch (lang) {
            case "English":
                expPlaceHolder = "Username/Email/Mobile";
                break;
            default:
                throw new IllegalArgumentException("Unsupported language: " + lang);
        }

        Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");
        Assert.assertEquals(plcHolder, expPlaceHolder, "Placeholder text does not match expected.");

        // Send keys from properties file
        textBox.sendKeys(p.getProperty("email"));

         userName = textBox.getAttribute("value");
	}
	
	public void baseForForgotEmail(String lang) throws InterruptedException, IOException 
		{
			 // Click Forgot Email link
		    WebElement forgot = wait.until(ExpectedConditions
		            .visibilityOfElementLocated(By.id("foorgetEmail")));
		    String labelText = forgot.getText();

		    String expForgotLabel, expFindEmail, expEnterMobile, expMobilePH, expCancel, expNext, expPlaceholder;
		    switch (lang) {
		        case "Hindi":
		            expForgotLabel = "ईमेल भूल गए?";
		            expFindEmail = "अपना ईमेल पता ढूंढें";
		            expEnterMobile = "अपना मोबाइल नंबर दर्ज करें";
		            expMobilePH = "मोबाइल नंबर";
		            expCancel = "रद्द करें";
		            expNext = "आगे जाये";
		            expPlaceholder = "उपयोगकर्ता नाम/ईमेल/ मोबाइल";
		            break;

		        case "English":
		            expForgotLabel = "Forgot Email?";
		            expFindEmail = "Find your email address";
		            expEnterMobile = "Enter your mobile number";
		            expMobilePH = "Mobile Number";
		            expCancel = "Cancel";
		            expNext = "Next";
		            expPlaceholder = "Username/Email/Mobile";
		            break;
		          
		        case "Arabic":
		            expForgotLabel = "نسيت البريد الإلكتروني؟";
		            expFindEmail = "ابحث عن عنوان بريدك الإلكتروني";
		            expEnterMobile = "أدخل رقم هاتفك المحمول";
		            expMobilePH = "رقم الهاتف المحمول";
		            expCancel = "إلغاء";
		            expNext = "التالى";
		            expPlaceholder = "اسم المستخدم / البريد الإلكتروني / الجوال";
		            break;    
	            
		        case "Bengali":
		            expForgotLabel = "ইমেল ভুলে গেছেন?";
		            expFindEmail = "আপনার ইমেল ঠিকানা খুঁজুন";
		            expEnterMobile = "আপনার মোবাইল নাম্বার প্রবেশ করুন";
		            expMobilePH = "মোবাইল নম্বর";
		            expCancel = "বাতিল";
		            expNext = "পরবর্তী";
		            expPlaceholder = "ব্যবহারকারীর নাম/ইমেইল/মোবাইল";    
		            break; 
		          
		        case "Panjabi":
		            expForgotLabel = "ਕੀ ਈਮੇਲ ਭੁੱਲ ਗਏ?";
		            expFindEmail = "ਆਪਣਾ ਈਮੇਲ ਪਤਾ ਲੱਭੋ";
		            expEnterMobile = "ਆਪਣਾ ਮੋਬਾਈਲ ਨੰਬਰ ਦਾਖਲ ਕਰੋ";
		            expMobilePH = "ਮੋਬਾਈਲ ਨੰਬਰ";
		            expCancel = "ਰੱਦ ਕਰੋ";
		            expNext = "ਅਗਲਾ";
		            expPlaceholder = "ਉਪਭੋਗੀ/ਈ - ਮੇਲ/ਮੋਬਾਈਲ";    
		            break; 
		            
		        case "Chinese":
		            expForgotLabel = "忘记电邮？";
		            expFindEmail = "找到你的电子邮件地址";
		            expEnterMobile = "输入你的手机号码";
		            expMobilePH = "手机号码";
		            expCancel = "取消";
		            expNext = "下一个";
		            expPlaceholder = "用戶名/電子郵件/手機";
		            break;    
		            
		        case "Gujarati":
		            expForgotLabel = "ઇમેઇલ ભૂલી ગયા છો?";
		            expFindEmail = "તમારું ઇમેઇલ સરનામું શોધો";
		            expEnterMobile = "તમારો મોબાઇલ નંબર દાખલ કરો";
		            expMobilePH = "મોબાઇલ નંબર";
		            expCancel = "રદ કરો";
		            expNext = "આગળ";
		            expPlaceholder = "વપરાશકર્તા નામ/ઈમેલ/મોબાઇલ";
		            break;
					
	           case "Japanese":
		            expForgotLabel = "メールを忘れた？";
		            expFindEmail = "あなたのメールアドレスを探す";
		            expEnterMobile = "携帯番号を入力してください";
		            expMobilePH = "携帯電話番号";
		            expCancel = "キャンセル";
		            expNext = "次へ";
		            expPlaceholder = "ユーザー名/メール/モバイル";
		            break;				
					
	          case "Kannada":
		            expForgotLabel = "ಇಮೇಲ್ ಮರೆತಿರುವಿರಾ?";
		            expFindEmail = "ನಿಮ್ಮ ಇಮೇಲ್ ವಿಳಾಸವನ್ನು ಹುಡುಕಿ";
		            expEnterMobile = "ನಿಮ್ಮ ಮೊಬೈಲ್ ನಂಬರನ್ನು ನಮೂದಿಸಿರಿ";
		            expMobilePH = "ಮೊಬೈಲ್ ಸಂಖ್ಯೆ";
		            expCancel = "ರದ್ದುಮಾಡು";
		            expNext = "ಮುಂದೆ";
		            expPlaceholder = "ಬಳಕೆದಾರಹೆಸರು/ಇಮೇಲ್/ಮೊಬೈಲ್";
		            break;				
					
	          case "Sanskrit":
		            expForgotLabel = "इमेल विस्मृतवान्?";
		            expFindEmail = "स्वस्य इमेल-पत्रं अन्वेषयतु";
		            expEnterMobile = "स्वस्य दूरभाषाङ्कं लिखतु";
		            expMobilePH = "दूरभाषाङ्कः";
		            expCancel = "निरस्यतु";
		            expNext = "अग्रिम";
		            expPlaceholder = "उपयोक्तृनाम/इमेल/दूरभाषाङ्कः";
		            break;

	         case "Odia":
		            expForgotLabel = "ଇମେଲ୍ ଭୁଲିଗଲେ?";
		            expFindEmail = "ଆପଣଙ୍କ ଇମେଲ ଠିକଣା ଖୋଜନ୍ତୁ";
		            expEnterMobile = "ଆପଣଙ୍କ ମୋବାଇଲ ନମ୍ବର ପ୍ରବେଶ କରନ୍ତୁ";
		            expMobilePH = "ମୋବାଇଲ ନମ୍ବର";
		            expCancel = "ବାତିଲ୍ କରନ୍ତୁ";
		            expNext = "ଆଗକୁ ଯାଆନ୍ତୁ";
		            expPlaceholder = "ଉପଯୋଗକର୍ତ୍ତା ନାମ/ଇମେଲ/ମୋବାଇଲ";
		            break;
		            
	         case "Sanatli":
		            expForgotLabel = "ᱤᱢᱮᱞ ᱦᱤᱲᱤᱧ ᱠᱮᱫᱟᱢ?";
		            expFindEmail = "ᱟᱢᱟᱜ ᱤᱢᱮᱞ ᱴᱷᱤᱠᱟᱹᱱᱟ ᱯᱟᱱᱛᱮ ᱢᱮ";
		            expEnterMobile = "ᱟᱢᱟᱜ ᱢᱚᱵᱟᱭᱤᱞ ᱱᱚᱢᱵᱚᱨ ᱮᱢ ᱢᱮ";
		            expMobilePH = "ᱢᱚᱵᱟᱭᱤᱞ ᱱᱚᱢᱵᱚᱨ";
		            expCancel = "ᱵᱟᱛᱤᱞ";
		            expNext = "ᱛᱟᱭᱚᱢ";
		            expPlaceholder = "ᱵᱮᱵᱷᱟᱨᱤᱭᱟᱹ ᱧᱩᱛᱩᱢ/ᱤᱢᱮᱞ/ᱢᱚᱵᱟᱭᱤᱞ";
		            break;
		            
	         case "Tamil":
		            expForgotLabel = "மின்னஞ்சல் மறந்துவிட்டதா?";
		            expFindEmail = "உங்கள் மின்னஞ்சல் முகவரியைக் கண்டறியவும்";
		            expEnterMobile = "உங்களுடைய கைபேசி எண்ணை உள்ளிடவும்";
		            expMobilePH = "மொபைல் எண்";
		            expCancel = "ரத்து";
		            expNext = "தொடர்ந்து";
		            expPlaceholder = "பயனர்பெயர்/மின்னஞ்சல்/மொபைல்";
		            break;
		            
	         case "Russian":
		            expForgotLabel = "Забыли сообщение?";
		            expFindEmail = "Найти свой адрес электронной почты";
		            expEnterMobile = "Введите номер мобильного телефона";
		            expMobilePH = "Мобильный номер";
		            expCancel = "Отмена";
		            expNext = "Далее";
		            expPlaceholder = "Имя пользователя/Электронная почта/Мобильный телефон";
		            break;   
		            
	         case "Telugu":
		            expForgotLabel = "ఇమెయిల్ను మర్చిపోయారా?";
		            expFindEmail = "మీ ఇమెయిల్ చిరునామాను కనుగొనండి";
		            expEnterMobile = "మీ మొబైల్ నెంబర్ ను ఎంటర్ చేయండి";
		            expMobilePH = "మొబైల్ సంఖ్య";
		            expCancel = "రద్దు";
		            expNext = "తరువాత";
		            expPlaceholder = "యూజర్ పేరు/ఇమెయిల్/మొబైల్";
		            break; 
					 
	         case "Korean":
		            expForgotLabel = "이메일을 잊었습니까?";
		            expFindEmail = "이메일 주소 찾기";
		            expEnterMobile = "당신의 휴대폰 번호를 입력하십시오";
		            expMobilePH = "휴대 전화 번호";
		            expCancel = "취소";
		            expNext = "다음";
		            expPlaceholder = "उपयोक्तृनाम/इमेल/दूरभाषाङ्कः";
		            break;
		            
		        default:
		            throw new IllegalArgumentException("Unsupported language: " + lang);
		    }

		    // Verify Forgot label
		    Assert.assertEquals(labelText, expForgotLabel, "Label text is not matching!");
		    forgot.click();

		    // Forgot Page validations
		    Assert.assertEquals(driver.findElement(By.id("findEmailAdd")).getText(), expFindEmail);
		    Assert.assertEquals(driver.findElement(By.id("enterMobile")).getText(), expEnterMobile);
		    
		    WebElement phoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='mobileNumber']")));
		    String phoneNumberText = phoneNumber.getAttribute("placeholder");
		    Assert.assertEquals(phoneNumberText, expMobilePH, "Placeholder mismatch!");
		    phoneNumber.sendKeys(p.getProperty("mobileNumber"));
		    enteredValue = phoneNumber.getAttribute("value");	    
		    
		    Assert.assertEquals(driver.findElement(By.id("mobileCancel")).getAttribute("value"), expCancel);
		    WebElement nxtOtp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("getNxtOtp")));
		    String nxtOtpText = nxtOtp.getAttribute("value");
		    Assert.assertEquals(nxtOtpText, expNext, "'Next' button mismatch!");
		    nxtOtp.click();
		   
	

		    UiVerifyYourIdentityPage vyip = new UiVerifyYourIdentityPage(driver);
		    vyip.baseVerifyYourIdentifyPage(lang);
           
		    // Forgot Page Footer check
		    String footer = wait.until(ExpectedConditions
		            .visibilityOfElementLocated(By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
		    Assert.assertEquals(footer, "Privacy Policy | Terms and Conditions");

		    // Forgot Page Store current window
		    String originalWindow = driver.getWindowHandle();

		    // Forgot Page Privacy Page
		    WebElement privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aPrivacyPolicy")));
		    privacy.click();
		    Thread.sleep(2000);

		    for (String handle : driver.getWindowHandles()) {
		        if (!handle.equals(originalWindow)) {
		            driver.switchTo().window(handle);
		            System.out.println("On Forgot Page's Privacy Tab URL: " + driver.getCurrentUrl());
		            break;
		        }
		    }
		    Assert.assertTrue(wait.until(ExpectedConditions
		            .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Welcome to the XGENPLUS Privacy Policy']")))
		            .isDisplayed(), "Privacy page not loaded!");
		    driver.close();
		    driver.switchTo().window(originalWindow);
		    Thread.sleep(2000);
		    driver.switchTo().frame("topFrame");
		    Assert.assertEquals(labelText, expForgotLabel);

		    // Forgot Page Terms Page
		    WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aTermsCond")));
		    terms.click();
		    Thread.sleep(2000);

		    for (String handle : driver.getWindowHandles()) {
		        if (!handle.equals(originalWindow)) {
		            driver.switchTo().window(handle);
		            System.out.println("On Forgot Page's Terms Tab URL: " + driver.getCurrentUrl());
		            break;
		        }
		    }
		    Assert.assertTrue(wait.until(ExpectedConditions
		            .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='XgenPlus Terms of Service']")))
		            .isDisplayed(), "Terms page not loaded!");
		    driver.close();
		    driver.switchTo().window(originalWindow);
		    Thread.sleep(2000);
		    driver.switchTo().frame("topFrame");
		    Assert.assertEquals(labelText, expForgotLabel);

		    // Cancel back to login
		    WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("mobileCancel")));
		    cancelBtn.click();

		    WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		    Assert.assertTrue(textBox.isDisplayed(), "Input Box is NOT visible!");
		    Assert.assertEquals(textBox.getAttribute("placeholder"), expPlaceholder, "Placeholder mismatch!");
	}
	 
	public void baseToggleButton(String lang) throws InterruptedException 
		{
			WebElement icnbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='off']"))); 
	        Assert.assertTrue(icnbtn.isDisplayed(), "Toggle Icon is NOT visible!");
	        //icnbtn.click();
	        Thread.sleep(3000);
	     // Assert that the enabled toggle button is actually enabled
	        Assert.assertTrue(icnbtn.isEnabled(), "The toggle button should be enabled.");
	        Thread.sleep(3000);
	       // icnbtn.click();
	     // Assert that the disabled toggle button is actually enabled
	        Assert.assertTrue(icnbtn.isEnabled(), "The toggle button should be disabled.");
	      //  Thread.sleep(2000);
	        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='RemeberLabel']")));
	        String labelText = toggle.getText();
			 String expRememberMe;
			    switch (lang) {
			    
			        case "English":			        	
			        	expRememberMe= "Remember me";
			            break;
			        default:
			            throw new IllegalArgumentException("Unsupported language: " + lang);  
			    }
			    Assert.assertEquals(labelText, expRememberMe, "Label text is not matching!");
	}
	 
	public void basenextBtnBackBtn(String lang) throws InterruptedException, IOException {
		    //  Click Next Button
		    WebElement nxtbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signinNxt")));
		    Assert.assertTrue(nxtbtn.isDisplayed(), "Next Button is NOT visible!");
		    nxtbtn.click();
		    
		    
		    UiPasswordPage pg = new UiPasswordPage(driver);
		    pg.basePasswordPage(lang);

		   
	}
	 
	public void baseSecureLoginBackBtn(String lang) throws InterruptedException, IOException 
		{
			  WebElement key = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='svg/images/secure-key.svg']")));
		        Assert.assertTrue(key.isDisplayed(), "Key Icon is NOT visible!");
		      WebElement slv = driver.findElement(By.id("secure_login_Via"));
		      String slvText = slv.getText();
		      WebElement app = driver.findElement(By.xpath("//a[@id='loginVia_App']"));
		        String appText = app.getText();
		      
			 String expSecureLoginVia,expApp;
			    switch (lang) {
			    
			        case "English":			        	
			        	expSecureLoginVia = "Secure Login Via";
			        	expApp= "APP";
			            break;
			        default:
			            throw new IllegalArgumentException("Unsupported language: " + lang);  
			    }
			    Assert.assertEquals(slvText, expSecureLoginVia, "'Secure Login Via' is not matching!");
			    Assert.assertEquals(appText, expApp, " 'APP' is not matching!");
			    app.click();
			    // Calling App Page ------------------------------------------------------------------------------
			    
			    UiAppPage ap = new UiAppPage(driver);
			    ap.baseLoginViaAppPage(lang);
			    
			    
	}
		
	public void basePrivacyAndTerm() throws InterruptedException 
		{
			String pipe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='xgen_line_break'])[1]"))).getText();
	        Assert.assertEquals(pipe, "Privacy Policy | Terms and Conditions", "Footer text does not match expected.");
	        
	        String originalWindowHandle = driver.getWindowHandle();
	        
	       // System.out.println("Original Window : " + originalWindowHandle);
	       // This is for Privacy Policy Page................................................................................................... 
	        WebElement privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='aPrivacyPolicy']")));
	        privacy.click();
	        Thread.sleep(2000);
	     // Get all window handles
	        Set<String> allWindowHandles = driver.getWindowHandles();
	        
	        // Iterate through all handles and switch to the new window
	        for (String handle : allWindowHandles) 
	        {
	            if (!handle.equals(originalWindowHandle))
	            {
	                driver.switchTo().window(handle);
	                System.out.println("Switched to the new tab with URL: " + driver.getCurrentUrl());
	                // You can perform actions on the new tab here, e.g.,
	                // Assert.assertTrue(driver.getCurrentUrl().contains("privacy.com"), "The URL should contain 'privacy.com'");
	                break;
	            }
	        }
	     
	       
	     // Wait for Privacy Page heading
		   WebElement privacyPage = wait.until(ExpectedConditions
		           .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Welcome to the XGENPLUS Privacy Policy']")));
		    Assert.assertTrue(privacyPage.isDisplayed(), "Welcome to the XGENPLUS Privacy Policy");
		 // Close the new tab
		    driver.close();
		    driver.switchTo().window(originalWindowHandle);
		    Thread.sleep(2000);
		    System.out.println("Switched back to the original tab with URL: " + driver.getCurrentUrl());
		 // After navigating back, re-check Login Page elements
		    driver.switchTo().frame("topFrame");
		    WebElement loginBox = wait.until(ExpectedConditions
		           .visibilityOfElementLocated(By.id("username")));
		    Assert.assertTrue(loginBox.isDisplayed(), "Login page did NOT load after clicking Cancel!");
	       
		 // This is for Terms and Conditions Page....................................................................................................   
		    
		    WebElement terms = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='aTermsCond']")));
		    terms.click();
		    Thread.sleep(2000);
	       Set<String> allWindowHandlesTerm = driver.getWindowHandles();
	        
	        // Iterate through all handles and switch to the new window
	        for (String handle : allWindowHandlesTerm) 
	        {
	            if (!handle.equals(originalWindowHandle))
	            {
	                driver.switchTo().window(handle);
	                System.out.println("Switched to the new tab with URL: " + driver.getCurrentUrl());                
	                break;
	            }
	        }
	     
	       
	     // Wait for Terms and Conditions Page heading
		   WebElement termsPage = wait.until(ExpectedConditions
		           .visibilityOfElementLocated(By.xpath("//h1[normalize-space()='XgenPlus Terms of Service']")));
		    Assert.assertTrue(termsPage.isDisplayed(), "XgenPlus Terms of Service");
		 // Close the new tab
		    driver.close();
		    driver.switchTo().window(originalWindowHandle);
		    Thread.sleep(2000);
		    System.out.println("Switched back to the original tab with URL: " + driver.getCurrentUrl());
		 // After navigating back, re-check Login Page elements
		    driver.switchTo().frame("topFrame");
		   // WebElement loginBox1 = wait.until(ExpectedConditions
		         //  .visibilityOfElementLocated(By.id("username")));
		    Assert.assertTrue(loginBox.isDisplayed(), "Login page did NOT load after clicking Cancel!");
	}
		

}
