package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NOK {
	
	private static WebDriver wd;
	private static WebElement element;
	private static String title;
	
	public NOK(WebDriver wd){
		this.wd=wd;
	}
/*	
	public static WebElement NOKLink(WebDriver wd) throws Exception{
		element=wd.findElement(By.xpath(".//*[@id='table_id']/tbody/tr[15]/td/table/tbody/tr[1]/td[2]/a/font"));
		return element;
	}
*/	
	public static WebElement Title(WebDriver wd, String title) throws Exception {
		Select practicelist=new Select(wd.findElement(By.id("inkintitle")));
		List<WebElement> lst=practicelist.getOptions();
		for(WebElement practice : lst){
			if(practice.getText().equalsIgnoreCase(title)){
				new Select(wd.findElement(By.id("inkintitle"))).selectByVisibleText(title);
			}
		}
		return null;
	}
	
	public static WebElement Surname(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinsurname"));
		return element;
	}
	
	public static WebElement Firstname(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinname"));
		return element;
	}
	
	public static WebElement Address1(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinaddr1"));
		return element;
	}

	public static WebElement Address2(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinaddr2"));
		return element;
	}

	public static WebElement Address3(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinaddr3"));
		return element;
	}

	public static WebElement Address4(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinaddr4"));
		return element;
	}

	public static WebElement Phone(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinphone"));
		return element;
	}
	
	public static WebElement Mobile(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinmobile"));
		return element;
	}
	
	public static WebElement Mail(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inkinemail"));
		return element;
	}

	public static WebElement Relation(WebDriver wd) throws Exception {
		element=wd.findElement(By.name("inKinRelation"));
		return element;
	}
	
	public static WebElement Ok_Button(WebDriver wd) throws Exception {
		element=wd.findElement(By.xpath("//*[@id='Layer1']/table/tbody/tr[9]/td[5]/div/input"));
		return element;
	}

}
