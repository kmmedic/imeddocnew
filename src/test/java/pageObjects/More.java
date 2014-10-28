package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class More {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public More(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	public static WebElement lblRip(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='mstatus']/table/tbody/tr[3]/td[4]/span/font"));
		
		return element;
		
	}
	
	public static WebElement lblNonPaying(WebDriver wd) throws Exception{
		
		element=wd.findElement(By.xpath("//*[@id='mstatus']/table/tbody/tr[3]/td[2]/label"));
		
		return element;
	}
	
	public static WebElement More_Link(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.linkText("More"));
		
		return element;
		
	}
	
	public static WebElement NonPaying(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("chk_nonpaying"));
				
		return element;
		
	}
	
	public static WebElement Rip(WebDriver wd) throws Exception {
		
		element= wd.findElement(By.id("rip"));
		
		return element;
		
	}
	
	public static WebElement Ok_button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='mstatus']/table/tbody/tr[4]/td[4]/input"));
		
		return element;
	}
	
	
}
