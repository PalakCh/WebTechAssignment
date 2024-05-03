package WebTechAssignment.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import WebTechAssignment.PageObjects.OrangeHrmLogInPage;


public class baseTest {
	
	public WebDriver driver;
	public OrangeHrmLogInPage lp;
	
	public String getPropertyValue(String property) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//WebTechAssignment//resources/globaldata.properties");
		prop.load(fis);
		return prop.getProperty(property);
		
	}
	
	public WebDriver initializeDriver() throws IOException
	{
	
		String browser=System.getProperty("browser")!=null ? System.getProperty("browser") : getPropertyValue("browser");
		
		if(browser.contains("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			
			if (browser.contains("headless"))
			{
			options.addArguments("headless");
			} 
			driver=new ChromeDriver(options);
			
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			EdgeOptions options = new EdgeOptions();
			
			if (browser.contains("headless"))
			{
			options.addArguments("headless");
			} 
			driver=new EdgeDriver(options);

		}
		
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			
			if (browser.contains("headless"))
			{
			options.addArguments("headless");
			} 
			driver=new FirefoxDriver(options);

		}
		
		driver.manage().window().setSize(new Dimension(1440,900));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public OrangeHrmLogInPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		lp=new OrangeHrmLogInPage(driver);
		lp.goToUrl(getPropertyValue("url"));	
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver()
	{
		driver.close();
	}
	
	
	public String takeScreenshort(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot s=(TakesScreenshot)driver;
		File source=s.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		
		FileHandler.copy(source, destination);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		
	}
	
	public ArrayList<String> getData(String TCName) throws IOException
	{
	
	FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//WebTechAssignment//resources//data.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(fis1);
	ArrayList<String> a = new ArrayList<String>();
	
	int sheetcount = workbook.getNumberOfSheets();
	
	for(int i=0;i<sheetcount;i++)
	{
		if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
		{
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			Iterator<Row> rows=sheet.iterator();

			while(rows.hasNext())
			{
				Row r=rows.next();
				Iterator<Cell> ce=r.cellIterator();
				
					Cell value=ce.next();
					if(value.getStringCellValue().equalsIgnoreCase(TCName))
					{
						while(ce.hasNext())
						{
							Cell c=ce.next();
							if(c.getCellType()==CellType.STRING)
							{
								a.add(c.getStringCellValue());
							}
							else
							{
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						
						}
						
					}
			}	
		}	
	}
	return a;
	}
}
