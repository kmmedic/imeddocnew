package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExaminationHistory {

	private static WebDriver wd;
	private static WebElement element;
	
	public ExaminationHistory(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	/*
	 * Page element(s) for Examination History - Current Complaints
	 */
	
	public static WebElement PatientSummarySubmenu(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//tr[3]/td/table/tbody/tr/td[2]/a/font"));
		
		return element;
	}


	public static WebElement AddExam(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.linkText("Add New Examination"));
		
		return element;
	}

	public static WebElement CurComp_Button(WebDriver wd) throws Exception {

		element = wd.findElement(By.xpath("//*[@id='Layer_Links']/table/tbody/tr/td[1]/a"));
		
		return element;
	}
	
	public static WebElement Pres_Comp(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtcomplaints"));
		
		return element;
	}
	

	public static WebElement Pres_Hist(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txthistory"));
		
		return element;
		
	}

	public static WebElement Objective(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtobjective"));
		
		return element;
	}
	
	public static WebElement Investigation(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtnature"));
		
		return element;
		
	}
	
	public static WebElement Treatment(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txttreatment"));
		
		return element;
		
	}
	
	public static WebElement Ok_button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_submit"));
		
		return element;
	}
	
	public static WebElement Cancel_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
	}
	
	public static WebElement CurDate(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("txtdate"));
		
		return element;
	}
	
	public static WebElement Edit(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[5]/a/font"));
		
		return element;
	}
	
	public static WebElement Delete(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[8]/a/font"));
		
		return element;
		
	}
	
	public static WebElement CloseImageIcon(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.cssSelector("css=a > img"));
		
		return element;
	}
	
}
