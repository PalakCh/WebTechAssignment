package WebTechAssignment.AbstractComponents;

import java.time.Duration;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import WebTechAssignment.PageObjects.OrangeHrmLogInPage;


public class AbstractComponents {
	
	WebDriver driver;
	private static Logger log = LogManager.getLogger(OrangeHrmLogInPage.class.getName());
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}
	String pageloadstatus="";
	
	public void goToUrl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void waitForElementToAppear(WebElement we1)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(we1));
	
	}
	public void waitForElementToDisappear(WebElement we2)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(we2));
	}
	
	public void waitForElementToClickable(WebElement we1)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(we1));
	
	}
	
	public int generateRandomNumber()
	{
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return n;
	
	}
	
	public String generateRandomAlphnumericString(int length)
	{
		String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder builder = new StringBuilder(length);
		 for (int i = 0; i < length; i++) {
		        builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
		    }

		    return builder.toString();
		    
	
	}
	
	public static String generateRandomDate()
	{
		GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1900, 2010);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
	}
	
	
	public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
	public void waitforPagetoFullyLoad()
	{
		
		while(!pageloadstatus.equals("complete"))
		{
		JavascriptExecutor j = (JavascriptExecutor)driver;
		 pageloadstatus=j.executeScript("return document.readyState").toString();
		}
		
		log.info("Page is loaded successfully");
   
	}
	
	
}
