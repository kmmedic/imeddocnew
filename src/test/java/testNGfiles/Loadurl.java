package testNGfiles;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Loadurl {
	
	private static WebDriver wd;
	
	public Loadurl(WebDriver wd){
		this.wd=wd;
	}
	
	@Test(groups={"functional"})
	public static void getUrl() throws Exception {
		
		try {
			
			wd.get("http://demo.imeddoc.net");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
