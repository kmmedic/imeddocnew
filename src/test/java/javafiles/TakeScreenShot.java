package javafiles;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
	
	private static WebDriver wd;
	
	public TakeScreenShot(WebDriver  wd) {
		
		this.wd=wd;
		
	}
	
	public static void CaptureScreen() throws Exception {

		File  screen = ( (TakesScreenshot) wd ).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screen, new File(""));
		
	}

}
