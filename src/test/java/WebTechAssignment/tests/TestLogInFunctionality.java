package WebTechAssignment.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import WebTechAssignment.PageObjects.OrangeHrmAdminPanelPage;
import WebTechAssignment.testcomponents.baseTest;

public class TestLogInFunctionality extends baseTest{
	
	
	@Test
	public void loginPageValidCredentials() throws IOException
	{
		OrangeHrmAdminPanelPage hpp=lp.logInApp(getData("Valid Credentials"));
		hpp.validateHeaderTitle();
		hpp.validateDashboardGridDisplayed();
	}
	
	@Test
	public void loginPageInvalidCredentials() throws IOException
	{
		OrangeHrmAdminPanelPage hpp=lp.logInApp(getData("Invalid Credentials"));
		lp.validateNotLoggedIn();
		
	}

	
}
