package testCases;

import org.testng.annotations.Test;
import java.io.IOException;
import testBase.BaseClass;

public class T001_LoginTest extends BaseClass
{
			
	//@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="Datadriven")// getting data provider from different class
	@Test(priority = 1)
	public void verify_LoginTest() throws InterruptedException, IOException
	{		
		doLogin();				
    }         
			
}

