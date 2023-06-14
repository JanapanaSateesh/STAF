package com.testCases;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.utilities.TakeScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.Readconfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static WebDriver driver;
	Readconfig read = new Readconfig();
	String url = read.getUrl();
	String username = read.getUsername();
	String password = read.getPassword();
	
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ExtentSparkReporter spark;
	
	@BeforeSuite
	public void startReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String curDate = dtf.format(now).toString();
		
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("./Reports/report "+ " "+ curDate+".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Summary Report");
		extent.attachReporter(spark);
		
	}
	
	
	
   @BeforeMethod
	public void Setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*", "--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(url);

	}

   
   @AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		//driver.quit();
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case is Passed  " + result.getName());
			test.pass("Test Case is passed");	        
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case is Failed--" + result.getName());
			test.log(Status.FAIL, "Test Case is Failed --" + result.getThrowable().getMessage());
			TakeScreenshot TS = new TakeScreenshot();
			String path = TS.TakeSnapShotshot(driver);
			test.addScreenCaptureFromBase64String(path);


		}
	}
	
	//AFTER ----------------------- SUITE  ///
	 @AfterSuite
		public void endReport() {
			extent.flush();
		}
}
