package WebTechAssignment.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import WebTechAssignment.AbstractComponents.AbstractComponents;

public class OrangeHrmAdminPanelPage extends AbstractComponents{
	
	WebDriver driver;
	
	private static Logger log = LogManager.getLogger(OrangeHrmLogInPage.class.getName());
	
	public OrangeHrmAdminPanelPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//h6")
	WebElement headertitle;
	
	@FindBy(xpath="//div[@class='oxd-grid-3 orangehrm-dashboard-grid']")
	WebElement dashboardgrid;
	
	@FindBy(xpath="//a[contains(@href,'viewMyDetails')]")
	WebElement myinfo;
	
	@FindBy(xpath="//span[@class='oxd-userdropdown-tab']")
	WebElement userdropdown;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement logout;
	
	
	public void validateHeaderTitle()
	{
		Assert.assertEquals(headertitle.getText(), "Dashboard");
		log.info("Logged In Successfully");
		System.out.println(headertitle.getText()+" Header Title Validated Successfully");
		log.info("Header Title Validated Successfully");
	}

	public void validateDashboardGridDisplayed()
	{
		
		Assert.assertTrue(dashboardgrid.isDisplayed());
		log.info("Dashboardgrid is displayed");
	}
	
	public OrangeHrmMyInfoPage clickMyInfo()
	{
		myinfo.click();
		log.info("Navigated to My Info Page");
		OrangeHrmMyInfoPage mip = new OrangeHrmMyInfoPage(driver);
		return mip;
	}
	
	public OrangeHrmLogInPage logOut()
	{
		userdropdown.click();
		logout.click();
		OrangeHrmLogInPage lp=new OrangeHrmLogInPage(driver);
		return lp;
		
	}
	
	
		
}
