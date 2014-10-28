package testNGfiles;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class PatientGeneralNotes {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientGeneralNotes(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	
}
