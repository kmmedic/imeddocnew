package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddToWaitingList {
	
	private static WebDriver wd;
	private static WebElement element;
	
	public AddToWaitingList() {
		
		this.wd=wd;
		
	}

	public static WebElement Addto_Link(WebDriver wd) throws Exception {
		
		element=wd.findElement(By.linkText("Add to Waiting List"));
		
		return element;
	}
	
	public static WebElement PatientName(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inPatient"));
		
		return element;
		
	}
	
	public static WebElement WaitingFrom(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.name("indate"));
		
		return element;
	}
	
	public static WebElement WaitingFor(WebDriver wd, String waitingfor) throws Exception {
		
		Select practicelist=new Select(wd.findElement(By.id("waiting")));
		List<WebElement> lst=practicelist.getOptions();
		for(WebElement practice : lst){
			if(practice.getText().equalsIgnoreCase(waitingfor)){
				new Select(wd.findElement(By.id("waiting"))).selectByVisibleText(waitingfor);
			}
		}
		return null;
	}
	
	public static WebElement Priority(WebDriver wd, String priority) throws Exception {
		
		Select practicelist=new Select(wd.findElement(By.id("priority")));
		List<WebElement> lst=practicelist.getOptions();
		for(WebElement practice : lst){
			if(practice.getText().equalsIgnoreCase(priority)){
				new Select(wd.findElement(By.id("priority"))).selectByVisibleText(priority);
			}
		}
		return null;
	}
	
	public static WebElement Surgery_icon(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='div_description1']/img"));
		
		return element;
	}
	
	public static WebElement Proc_select(WebDriver wd) throws Exception {
		
		element = new Select(wd.findElement(By.id("fld1"))).getFirstSelectedOption();
		
		return element;
	}

	public static WebElement txtCode(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("fldval"));
		
		return element;
	}
	
	public static WebElement RecSelect(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[2]/input"));
		
		return element;
	}
	
	public static WebElement Search(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='procedure']/table/tbody/tr[1]/td[3]/button"));
		
		return element;
	}

	public static WebElement Proc_OkButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='procedure']/table/tbody/tr[1]/td[3]/input[2]"));
		
		return element;
	}
	
	public static WebElement Proc_CancelButton(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.xpath("//*[@id='cmd_cancel']"));
		
		return element;
	}

	public static WebElement AppType(WebDriver wd, String apptype) throws Exception {

		Select practicelist=new Select(wd.findElement(By.id("indescription")));
		List<WebElement> lst=practicelist.getOptions();
		for(WebElement practice : lst){
			if(practice.getText().equalsIgnoreCase(apptype)){
				new Select(wd.findElement(By.id("indescription"))).selectByVisibleText(apptype);
			}
		}
		
		return null;
	}
	
	public static WebElement Notes(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("inNotes"));
		
		return element;
	}
	
	public static WebElement Ok_Button(WebDriver wd) throws Exception {
		
		element =wd.findElement(By.id("cmd_submit"));
				
		return element;
		
	}
	
	public static WebElement Cancel_Button(WebDriver wd) throws Exception {
		
		element = wd.findElement(By.id("cmd_cancel"));
		
		return element;
		
	}
}
