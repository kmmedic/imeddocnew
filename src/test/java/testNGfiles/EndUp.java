package testNGfiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;


public class EndUp {
	
	private static WebDriver wd;
	
	public EndUp(WebDriver wd){

		this.wd=wd;
		
	}
	
	@Test(groups={"functional"}, alwaysRun=true)
	public static void stopServer() throws Exception {
		
		String filepath=testNGfiles.FilesAndPaths.showTestNGReport().getAbsolutePath();
		wd.get("file:///"+filepath+"\\"+"emailable-report.html");
		Thread.sleep(50000);
		
		javafiles.SendReports.sendTestReport();
		
		wd.quit();
	
	}

}
