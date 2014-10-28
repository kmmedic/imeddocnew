package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PatientGP {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public PatientGP(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	public static WebElement GP_AddIcon(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='table_id']/tbody/tr[10]/td[2]/a[2]/img"));
		
		return element;
				
	}
	
	public static WebElement GP_DeleteIcon(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='table_id']/tbody/tr[10]/td[2]/a[4]/img"));
		
		return element;
		
		
	}
	
	public static WebElement Title(WebDriver wd, String title) throws Exception {
		
		Select gptitle = new Select(wd.findElement(By.id("intitle")));
		List<WebElement> allvalues=gptitle.getOptions();
		for(WebElement option : allvalues){
			if(option.getText().equalsIgnoreCase(title)){
				new Select ( wd.findElement(By.id("intitle"))).selectByVisibleText(title);
			}
		}
		
		return null;
	}
	
	public static WebElement Surname(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("insurname"));
		
		return element;
		
	}
	
	public static WebElement Firstname(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inname"));
		
		return element;
		
	}
	
	public static WebElement Displayname(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("insurname2"));
		
		return element;
		
	}
	
	public static WebElement Entity(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inentity"));
		
		return element;
		
	}
	
	public static WebElement Address1(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inaddr1"));
		
		return element;
	}
	
	public static WebElement Address2(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inaddr2"));
		
		return element;
	}
	
	public static WebElement Address3(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inaddr3"));
		
		return element;
		
	}
	
	public static WebElement Address4(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inaddr4"));
		
		return element;
		
	}
	
	public static WebElement WorkCountryCode(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inworkcountrycode"));
		
		return element;
	}
	
	public static WebElement WorkPhone(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inworkphone"));
		
		return element;
		
	}
	
	public static WebElement HomeCountryCode(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inhomecountrycode"));
		
		return element;
				
	}
	
	public static WebElement HomePhone(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inhomephone"));
		
		return element;
		
	}
	
	public static WebElement MobileCountryCode(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inmobilecountrycode"));
		
		return element;
		
	}
	
	public static WebElement MobileNumber(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inmobile"));
		
		return element;
	}
	
	public static WebElement Email(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inemail"));
		
		return element;
		
	}
	
	public static WebElement Web(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inwebsite"));
		
		return element;
		
	}
	
	public static WebElement Fax(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("infax"));
		
		return element;
		
	}
	
	public static WebElement GP_Type(WebDriver wd, String gpname) throws Exception {
		
		Select gp=new Select(wd.findElement(By.id("intype")));
		List<WebElement> opt=gp.getOptions();
		for(WebElement option : opt){
			if(option.getText().equalsIgnoreCase(gpname)){
				new Select (wd.findElement(By.id("intype"))).selectByVisibleText(gpname);
			}
		}
		return null;
	}

	public static WebElement Ok_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_submit"));
		
		return element;
		
	}
	
	public static WebElement Cancel_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
	}
	
	public static WebElement GP_txtBox(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inconsult"));
		
		return element;
		
	}
	
}
