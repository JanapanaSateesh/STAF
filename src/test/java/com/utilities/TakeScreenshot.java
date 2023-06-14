package com.utilities;

import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {

	public String TakeSnapShotshot(WebDriver driver ) {
		
		byte[] screenshotBytes=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		String base64screenshot=Base64.getEncoder().encodeToString(screenshotBytes);
		return base64screenshot;
	}
}
