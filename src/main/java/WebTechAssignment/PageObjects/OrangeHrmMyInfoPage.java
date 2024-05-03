package WebTechAssignment.PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import WebTechAssignment.AbstractComponents.AbstractComponents;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.awt.*;

public class OrangeHrmMyInfoPage extends AbstractComponents{
	
	WebDriver driver;
	private static Logger log = LogManager.getLogger(OrangeHrmLogInPage.class.getName());
	
	public OrangeHrmMyInfoPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath="//*[contains(text(),'Date of Birth')]/parent::div/following-sibling::div//input")
	WebElement dateofbirth;
	
	String newdate="";
	
	public void validateDOBisfilled() throws UnsupportedFlavorException, IOException
	{
		
		String select = Keys.chord(Keys.CONTROL, "a");
		String copy = Keys.chord(Keys.CONTROL, "c");
		waitForElementToClickable(dateofbirth);
		dateofbirth.click();
		dateofbirth.sendKeys(select);
		dateofbirth.sendKeys(copy);
		
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		System.out.println("Date of birth is filled with value " +c.getData(DataFlavor.stringFlavor));
		log.info("Date of Birth is filled in");
	}
	
	public void updateDOB()
	{
		
		dateofbirth.sendKeys(Keys.BACK_SPACE);
		newdate=generateRandomDate();
		dateofbirth.sendKeys(newdate);
		dateofbirth.sendKeys(Keys.ENTER);
		log.info("Entered new Date of Birth");
	}
	
	public void validateupdatedDOB() throws UnsupportedFlavorException, IOException
	{
		waitForElementToClickable(dateofbirth);
		String select = Keys.chord(Keys.CONTROL, "a");
		String copy = Keys.chord(Keys.CONTROL, "c");
		dateofbirth.sendKeys(select);
		dateofbirth.sendKeys(copy);
		
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		Assert.assertEquals(c.getData(DataFlavor.stringFlavor), newdate);
		System.out.println("Date is now updated to "+newdate);
		log.info("New Date of Birth validated successfully");
		
	}
		
}
