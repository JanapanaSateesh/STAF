package com.utilities;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testCases.Baseclass;

public class GenericMethod extends Baseclass {
	public static WebDriver driver;

	// Explicit Wait
	public static void dynamicWait(WebElement element) {
		int x = 0;
		while (true) {
			boolean flag = element.isDisplayed();
			x = x + 1;
			if (flag == true) {
				break;
			}
			if (x == 100) {
				break;
			}
		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// jsClick
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// jsType
	public static void jsType(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(" arguments[0].value= '" + value + "' ", element);
	}

	// Hit Enter
	public static void hitEnter(WebElement element) {
		element.sendKeys(Keys.RETURN);
	}

	// clear
	public static void Clear(WebElement element) {
		element.sendKeys(Keys.CLEAR);
	}

	//This will scroll the page Horizontally till the element is found
	public static void scroll(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	//Perform Mouse Hover Action on the Web Element
	public static void MouseOver(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).build().perform();
		
	}
	
	////Right Click the button to display Context Menu
	public static void RightClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element).perform();
	}
	
	//Double Click
	public static void DoubleClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick(element).perform();
	}
	
	
	
	
	
}
