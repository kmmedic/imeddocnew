package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MedicalHistory {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public MedicalHistory(WebDriver wd) {
		
		this.wd=wd;
		
	}

	/*
	 * Page element(s) for Examination History - Medical History
	 * 
	 */
	
	public static WebElement MedicalHistorySubMenu(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//tr[3]/td/table/tbody/tr/td[2]/font"));
		
		return element;
	}
	
	public static WebElement PastMedHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtpmhistory"));
		
		return element;
	}
	
	public static WebElement FamilyMedHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtfamilyhistory"));
		
		return element;
		
	}
	
	public static WebElement SmokingMedHistory(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtcconditions"));
		
		return element;
	}
	
	public static WebElement Immunization(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtimmunizations"));
		
		return element;
	}
	
	public static WebElement Allergy(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("textallergies"));
		
		return element;
		
	}

	public static WebElement SaveButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_submit"));
		
		return element;
		
	}
	
}
