package testNGfiles;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PrintLabel {

	private static WebDriver wd; 
	
	public PrintLabel(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@Test(groups={"functional"})
	public static void ReadPDFContent() throws Exception{
		
		testNGfiles.GotoPatient.PatientPage();
		
		try {
			String beforehandle=wd.getWindowHandle();
			
			javafiles.SwitchToUrl.getPDFWindow(wd, By.linkText("Print Labels"));
			
			Robot robot = new Robot();
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_F);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.setAutoDelay(1000);
			
			StringSelection stringSelection = new StringSelection(testNGfiles.FilesAndPaths.PDFDirectory().getAbsolutePath()+"\\"+"PrintLabel.pdf");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.mouseMove(657, 625);
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_S);
			robot.setAutoDelay(2000);
			robot.keyRelease(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.setAutoDelay(2000);

			wd.getWindowHandle();  // print label window handle
			wd.close();

			wd.switchTo().window(beforehandle);  // switch to previous window found in frame1
			wd.switchTo().frame("iframe1");
			wd.findElement(By.id("cmd_cancel")).click();

			javafiles.PdfVerification.verifyPatientid("PrintLabel.pdf");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
