package testNGfiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class GotoPatient {
	
	private static final int count = 0;
	private static WebDriver wd;
	
	public GotoPatient(WebDriver wd) throws Exception{
		
		this.wd=wd;
		
	}
	
	@Test(groups={"functional"})
	public static void PatientPage() throws Exception {
		
		try {
			
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();
			Thread.sleep(2000);
			
			new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText("Patient ID");
			wd.findElement(By.xpath("//*[@id='insearch']")).clear();
			wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys("21755");
			wd.findElement(By.xpath("//*[@id='img_search']")).click();
			Thread.sleep(2000);
											
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
}
