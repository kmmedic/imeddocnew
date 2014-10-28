package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PatientReport {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public PatientReport(WebDriver wd) {
		
		this.wd=wd;
	}
	
	public static WebElement Report_Link(WebDriver wd) throws Exception {
		
		element= wd.findElement(By.linkText("Patient Report"));
		
		return element;
				
	}

	public static WebElement Chkbox_History(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("chk_his"));
		
		return element;
	}

	public static WebElement Chkbox_Appointment(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("chk_app"));
		
		return element;
	}
	
	public static WebElement Chkbox_Accounts(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("hk_acc"));
		
		return element;
		
	}
	
	public static WebElement Chkbox_Prescription(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("chk_pre"));
		
		return element;
		
	}
	
	public static WebElement Print_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[6]/tbody/tr/td[1]/input"));
		
		return element;
	}
	
	public static WebElement Close_Buttohn(WebDriver wd)  throws Exception {
		
		element = wd.findElement(By.xpath("html/body/form/table/tbody/tr/td/table[6]/tbody/tr/td[2]/input"));
		
		return element;
		
	}
	
	
}
