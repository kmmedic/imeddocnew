package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Diagnosis {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public Diagnosis(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	public static WebElement Diagnosis_Link(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.linkText("Diagnosis"));
		
		return element;
		
	}
	
	public static WebElement PriDiagnosis(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("dianostic"));
		
		return element;
		
	}
	
	public static WebElement SecDiagnosis (WebDriver wd ) throws Exception {
		
		element = wd.findElement(By.id("insecdianostic"));
		
		return element;
		
	}

	public static WebElement NatureSymptoms(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("nature_symptoms"));
		
		return element;
		
	}
	
	public static WebElement Weeks(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("symp_week"));
		
		return element;
		
	}
			
	public static WebElement Months(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("symp_month"));
		
		return element;
		
	}
	
	public static WebElement Years(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("symp_year"));
		
		return element;
		
	}

	public static WebElement Description(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("treatment_desc"));
		
		return element;
	}
	
	public static WebElement Comments(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("comments"));
		
		return element;
	}
	
	public static WebElement Ok_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='divinsurance']/table/tbody/tr[10]/td[3]/input"));
		
		return element;
		
	}
	
	
}
