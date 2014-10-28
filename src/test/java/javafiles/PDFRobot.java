package javafiles;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PDFRobot {

	private static WebDriver wd;
	
	public PDFRobot(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	public static void PDFProcess(String filename) throws Exception {
		
		try {
			
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
			
			StringSelection stringSelection = new StringSelection(testNGfiles.FilesAndPaths.PDFDirectory().getAbsolutePath()+"\\"+filename);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			
			robot.setAutoDelay(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.setAutoDelay(2000);
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
			
//			wd.getWindowHandle();  // print label window handle
//			wd.close();
			
//			javafiles.PdfVerification.verifyPatientid(filename);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
