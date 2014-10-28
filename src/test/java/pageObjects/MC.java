package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MC {

	private static WebDriver wd;
	private static WebElement element;
	
	public MC(WebDriver wd){
		
		this.wd=wd;
		
	}

	public static WebElement MC_Link(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.linkText("Medical Certificate"));
		
		return element;
	}
	
	public static WebElement Add_Button(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("cmd_add"));
		
		return element;
		
	}
	
	public static WebElement EditButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_edit"));
		
		return element;
		
	}

	public static WebElement DeleteButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.name("cmd_delete"));
		
		return element;
		
	}

	public static WebElement PrintButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.name("cmd_print"));
		
		return element;
		
	}

	public static WebElement InnerClose(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
		
	}
	
	public static WebElement OuterClose(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
		
	}
	

	public static WebElement PatientName(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("inPatient"));
		
		return element;
		
	}

	public static WebElement CurDate(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("indate"));
		
		return element;
		
	}

	public static WebElement Description(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("txt_re"));
		
		return element;
		
	}

	public static WebElement FromDate(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("infdob"));
		
		return element;
		
	}

	public static WebElement ToDate(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("intdob"));
		
		return element;
		
	}

	public static WebElement Reasons(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.id("mcreasons"));
		
		return element;
		
	}
	
	public static WebElement SaveButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_save"));
		
		return element;
		
	}
	
	public static WebElement CancelButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
		
	}
	
}