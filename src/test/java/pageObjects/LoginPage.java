package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testNGfiles.RunTests;

public class LoginPage {
	
	private static WebDriver wd;
	
	private static WebElement element;
	
	public LoginPage(WebDriver wd){
		this.wd=wd;
	}
	
	public static WebElement select_practiceName(WebDriver wd) throws Exception{
		Select practicelist=new Select(wd.findElement(By.id("in_practice")));
		List<WebElement> lst=practicelist.getOptions();
		for(WebElement practice : lst){
			if(practice.getText().equalsIgnoreCase("DEMO")){
				new Select(wd.findElement(By.id("in_practice"))).selectByVisibleText("DEMO");
			}
		}
		return null;
	}
	public static WebElement txt_userName(WebDriver wd) throws Exception{
		element=wd.findElement(By.id("in_username"));
		return element;
	}
	public static WebElement txt_passWord(WebDriver wd) throws Exception{
		element=wd.findElement(By.id("in_password"));
		return element;
	}
	public static WebElement bt_Signin(WebDriver wd) throws Exception{
		element=wd.findElement(By.name("but_signin"));
		return element;
		
	}
	
}
