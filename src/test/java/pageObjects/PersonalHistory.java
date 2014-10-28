package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalHistory {

	private static WebDriver wd;
	private static WebElement element;
	
	public PersonalHistory(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	/*
	 * Page element(s) for Patient History - Personal History
	 */
	
	public static WebElement PatientHistoryMenu(WebDriver wd) throws Exception{
		
		element = wd.findElement(By.xpath("//tr[4]/td/table/tbody/tr/td[2]/a/font"));
		
		return element;
	}
	
	public static WebElement PersonalHistorySubMenu(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//td/table/tbody/tr/td[2]/font"));
		
		return element;
	}
	
	public static WebElement HealthHabits(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txthealthhabits"));
		
		return element;
		
	}
	
	public static WebElement SmokingHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtsmoking"));
		
		return element;
	}
	
	public static WebElement Travel(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txttravel"));
		
		return element;
	}
	
	public static WebElement FamilyHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtfamilyhistory"));
		
		return element;
	}
	
	public static WebElement SocialHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtsocialhistory"));
		
		return element;
		
	}
	
	public static WebElement SaveButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_submit"));
		
		return element;
	}

	
}
