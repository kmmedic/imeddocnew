package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MRN {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public MRN(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	public static WebElement MRN_Link(WebDriver wd ) throws Exception{
		element=wd.findElement(By.linkText("MRN"));
		return element;
	}
	
	public static WebElement Hospital1(WebDriver wd, String hospital) throws Exception {
		
		Select hospital1=new Select(wd.findElement(By.id("inhosp")));
		List<WebElement> opt=hospital1.getOptions();
		for(WebElement option : opt){
			if(option.getText().equalsIgnoreCase(hospital)){
				new Select (wd.findElement(By.id("inhosp"))).selectByVisibleText(hospital);
			}
		}
		return null;
	}
	
	public static WebElement Hospital2(WebDriver wd, String hospital) throws Exception {

		Select hospital2=new Select(wd.findElement(By.id("inhosp2")));
		List<WebElement> opt=hospital2.getOptions();
		for(WebElement option : opt){
			if(option.getText().equalsIgnoreCase(hospital)){
				new Select (wd.findElement(By.id("inhosp2"))).selectByVisibleText(hospital);
			}
		}
		return null;
	}
	
	public static WebElement Hospital3(WebDriver wd, String hospita1) throws Exception {
		
		Select hospital3=new Select(wd.findElement(By.id("inhosp3")));
		List<WebElement> opt=hospital3.getOptions();
		for(WebElement option : opt){
			if(option.getText().equalsIgnoreCase(hospita1)){
				new Select (wd.findElement(By.id("inhosp3"))).selectByVisibleText(hospita1);
			}
		}
		return null;
	}

	public static WebElement HospitalValue1(WebDriver wd) throws Exception {
		element=wd.findElement(By.id("inillness"));
		return element;
	}
	
	public static WebElement HospitalValue2(WebDriver wd) throws Exception {
		element=wd.findElement(By.id("mr_no2"));
		return element;
	}

	public static WebElement HospitalValue3(WebDriver wd) throws Exception {
		element=wd.findElement(By.id("mr_no3"));
		return element;
	}
	
	public static WebElement Ok_Button(WebDriver wd) throws Exception {
		element=wd.findElement(By.xpath("//*[@id='mrn_details']/table/tbody/tr[6]/td[2]/input"));
		return element;
		
	}
}
