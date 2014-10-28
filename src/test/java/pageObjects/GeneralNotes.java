package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralNotes {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public GeneralNotes(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	public static WebElement GeneralNotesSubMenu(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//tr[5]/td/table/tbody/tr/td[2]/a/font"));
		
		return element;
		
	}
	
	public static WebElement AddNew_Link(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.linkText("Add New"));
		
		return element;
	}

	public static WebElement Edit_Link(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.linkText("Edit"));
		
		return element;
		
	}
	
	public static WebElement Notes(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("innotes"));
		
		return element;
	}
	
	public static WebElement OkButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_submit"));
		
		return element;
	}
	
	public static WebElement CancelButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
		
	}
	
}
