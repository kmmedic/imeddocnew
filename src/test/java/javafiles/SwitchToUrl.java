package javafiles;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwitchToUrl {
	
	private static WebDriver wd;
	
	public SwitchToUrl(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	public static void getPDFWindow(WebDriver wd, By by) throws Exception{
		
		String firstWinHandle=null, secondWinHandle=null;
		
//		String windowurl=null;
		
		firstWinHandle = wd.getWindowHandle();
		
		wd.findElement(by).click();
		
		Thread.sleep(3000);
		
		Set<String> availableWindows = wd.getWindowHandles();
		
		availableWindows.remove(firstWinHandle);
		
		String winHandle=availableWindows.iterator().next();
		
		if (winHandle!=firstWinHandle){ //To retrieve the handle of second window, extracting the handle which does not match to first window handle
			
			secondWinHandle=winHandle; //Storing handle of second window handle //Switch control to new window //Control is switched now }
			 //Switch control to new window 
			wd.switchTo().window(secondWinHandle);  //Control is switched now 
			
			Thread.sleep(3000);

		}
		
	}


}