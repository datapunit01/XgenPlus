package testCases;


import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.UiLoginPage;
import testBase.BaseClass;

public class UiOfLoginPageEnglish extends BaseClass
{ 
      
    @Test(priority = 1)
    public void verifyLogo() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseVerifyLogo();
    }

    @Test(priority = 2)
    public void verifydefaultDropdown() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseDefaultDropdown();
    }

    @Test(priority = 3)
    public void verifyLanguageDropdown() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseVerifyLanguageDropdown();
    }

    @Test(priority = 4)
    public void verifySignIn() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseSighIn("English");
    }

    @Test(priority = 5)
    public void verifyinputBox() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseInputBox("English");
    }

    @Test(priority = 6)
    public void verifyforgotEmailCancelButton() throws InterruptedException, IOException 
    {    	   
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseForForgotEmail("English");
    }

    @Test(priority = 7)
    public void verifytoggleButton() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseToggleButton("English");
    }

    @Test(priority = 8)
    public void verifynextBtnBackBtn() throws InterruptedException, IOException 
    {
    		UiLoginPage lp = new UiLoginPage(driver);
    	    lp.basenextBtnBackBtn("English");             
    }

    @Test(priority = 9)
    public void verifysecureLoginBackBtn() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.baseSecureLoginBackBtn("English");
       
        
    }

    @Test(priority = 10)
    public void verifyprivacyAndTerm() throws InterruptedException, IOException 
    {
    	UiLoginPage lp = new UiLoginPage(driver);
    	lp.basePrivacyAndTerm();
    }

   
}
