package testNGfiles;

import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafiles.ReadExcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AddToWaitingList;
import pageObjects.Diagnosis;
import pageObjects.GeneralNotes;
import pageObjects.MC;
import pageObjects.MRN;
import pageObjects.MedicalHistory;
import pageObjects.More;
import pageObjects.PatientGP;
import pageObjects.PatientReport;
import pageObjects.ExaminationHistory;
import pageObjects.PersonalHistory;

public class Sample {
	
	Robot robot; 
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	@BeforeMethod
	public void setup() throws Exception {
		/*
		 * File file = new
		 * File("C:\\Users\\kmmuser\\Downloads\\IEDriverServer.exe");
		 * System.setProperty("webdriver.firefox.driver",
		 * file.getAbsolutePath()); WebDriver wd = new InternetExplorerDriver();
		 */

		wd = new FirefoxDriver();

		wd.manage().window().maximize();
		wd.get("http://demo.imeddoc.net");
		new Select(wd.findElement(By.id("in_practice")))
				.selectByVisibleText("DEMO");
		wd.findElement(By.id("in_username")).sendKeys("admin");
		wd.findElement(By.id("in_password")).sendKeys("a");
		wd.findElement(By.name("but_signin")).click();
		wd.switchTo().defaultContent();
		wd.switchTo().frame("iframe2");
		wd.findElement(By.xpath("//table[@id='tmp']/tbody/tr/td/table[1]/tbody/tr[1]/td[5]/a/img")).click();
		wd.switchTo().defaultContent();
		Thread.sleep(2000);
		wd.switchTo().frame("iframe1");
		wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();

		new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']")))
				.selectByVisibleText("Patient ID");
		wd.findElement(By.xpath("//*[@id='insearch']")).clear();
		wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys("21755");
		wd.findElement(By.xpath("//*[@id='img_search']")).click();
		
		Thread.sleep(2000);

	}

	
	@DataProvider(name = "SendGeneralNotesData")
	public static Object[][] SendGeneralNotesData() throws Exception {

		Object generalnotes[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("MedicalHistory");

			Iterator<Row> rowIterator0 = ws.iterator();

			generalnotes = new Object[1][5];	//

			int rowcounter = 0;
			int maxNumOfCells = 0;

			while (rowIterator0.hasNext()) {

				HSSFRow row1 = (HSSFRow) rowIterator0.next();

				if (row1.getRowNum() > 0) {

					List<HSSFCell> data = new ArrayList<HSSFCell>();

					maxNumOfCells = ws.getRow(row1.getRowNum())
							.getLastCellNum(); // The maximum number

					for (int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter++) { // Loop
																							// through
																							// cells

						HSSFCell cell;

						if (row1.getCell(cellCounter) == null) {

							cell = row1.createCell(cellCounter);

						} else {

							cell = row1.getCell(cellCounter);

						}

						data.add(cell);

						generalnotes[rowcounter][cellCounter] = data
								.get(cellCounter);

					}

					rowcounter++;

				}

			}

			filein.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Object[][] { new Object[][] { generalnotes } };
	}

	@Test
	public static void EditGeneralNotes() throws Exception {
		
		Object[] headers=new Object[3];
		Object[] values=new Object[2];
		Object[] result=new Object[1];
		
		headers[0]="Date";
		headers[1]="Notes";
		headers[2]="Pass/Fail";
		
		boolean passfail=false;
		
		try {
			
			GeneralNotes.GeneralNotesSubMenu(wd).click();
			Thread.sleep(2000);
			
			for(int i=1; i<=5; i++){

				if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+ i + "]")) !=null ){
					
					wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+ i +"]/td/table/tbody/tr[2]/td")).click();
					Thread.sleep(2000);
					
					String dt=wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+ i + "]/td/table/tbody/tr[1]/td[1]/font")).getText().substring(7, 17);

					System.out.println(dt.length());
					
					if(dt=="23-10-2010") {
						
						GeneralNotes.Edit_Link(wd).click();
						
						Thread.sleep(2000);
						
						wd.switchTo().frame("ifrmdocuments");
						
						GeneralNotes.Notes(wd).sendKeys(GeneralNotes.Notes(wd).getAttribute("value").concat(" E"));

						values[0]=new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
						
						values[1]=GeneralNotes.Notes(wd).getAttribute("value");
						
						GeneralNotes.OkButton(wd).click();
						
						wd.switchTo().defaultContent();
						
						wd.switchTo().frame("iframe1");

						wd.findElement(By.xpath("//td[2]/font")).click();
						Thread.sleep(2000);
						
						wd.findElement(By.id("cmd_cancel")).click();
						Thread.sleep(2000);
						
						passfail=true;
						
						break;
						
					}
					
					
				}

				
				if(i==5){
					
					
					String pagecount=wd.findElement(By.xpath("//*[@id='frm_search']/table[2]/tbody/tr/td/font[1]/b"));
					
					if(wd.findElement(By.xpath("//*[@id='frm_search']/table[2]/tbody/tr/td/a["+ i +"]"))!=null){

						wd.findElement(By.xpath("//*[@id='frm_search']/table[2]/tbody/tr/td/a["+ i +"]")).click();

						i = 0;  // re initializing the counter variable

					}
					
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(passfail) {
			
			result[0]="Pass";
			
		}else{
			
			result[0]="Fail";
			
		}
		
		
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "General Notes", 4, 0, new Object[][]{new Object[]{"General Notes - Modification"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "General Notes", 5, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "General Notes", 6, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "General Notes", 6, 2, new Object[][]{result});
		
		
	}
	
	@AfterMethod
	public static void stopDriver() throws Exception {
		
		wd.quit();
		
	}

	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		   return;
	}
	
	public static void switchWindows(WebDriver driver, String title) {
		String winh = driver.getWindowHandle();
		Set<String> allwins = driver.getWindowHandles();
		for (String win : allwins) {
			driver.switchTo().window(win);
			if(driver.getTitle().contains(title)){
				driver.manage().window().maximize();
				return;
			}
		}
	}

	public static void switchToUrl(By by) throws Exception{
		
		String firstWinHandle=null, secondWinHandle=null;
		
//		String windowurl=null;
		
		firstWinHandle = wd.getWindowHandle();
		
		wd.findElement(by).click();
		
		Thread.sleep(3000);
		
		Set<String> availableWindows = wd.getWindowHandles();
		
		availableWindows.remove(firstWinHandle);
		
		String winHandle=availableWindows.iterator().next();
		
		if (winHandle!=firstWinHandle){ //To retrieve the handle of second window, extracting the handle which does not match to first window handle
			
			secondWinHandle=winHandle; //Storing handle of second window handle //Switch control to new window driver.switchTo().window(secondWinHandle);//Control is switched now }
			 //Switch control to new window 
			wd.switchTo().window(secondWinHandle);

			Thread.sleep(3000);

		}
		
	}

	
    public void Keyboard(Robot robot) {
        this.robot = robot;
    }

    public void type(CharSequence characters) {
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }
    }

    public void type(char character) {
        switch (character) {
        case 'a':  break;
        case 'b':  break;
        case 'c':  break;
        case 'd':  break;
        case 'e':  break;
        case 'f':  break;
        case 'g':  break;
        case 'h':  break;
        case 'i':  break;
		case 'j':
			break;
		case 'k':
			break;
		case 'l':
			break;
		case 'm':
			break;
		case 'n':
			break;
		case 'o':
			break;
		case 'p':
			break;
		case 'q':
			break;
		case 'r':
			break;
		case 's':
			break;
		case 't':
			break;
		case 'u':
			break;
		case 'v':
			break;
		case 'w':
			break;
		case 'x':
			break;
		case 'y':
			break;
		case 'z':
			break;
		case 'A':
			break;
		case 'B': // doType(VK_SHIFT, VK_B); break;
		case 'C': // doType(VK_SHIFT, VK_C); break;
		case 'D': // doType(VK_SHIFT, VK_D); break;
		case 'E': // doType(VK_SHIFT, VK_E); break;
		case 'F': // doType(VK_SHIFT, VK_F); break;
		case 'G': // doType(VK_SHIFT, VK_G); break;
		case 'H': // doType(VK_SHIFT, VK_H); break;
		case 'I': // doType(VK_SHIFT, VK_I); break;
		case 'J': // doType(VK_SHIFT, VK_J); break;
		case 'K': // dotyper(VK_SHIFT, VK_K); break;
		case 'L': // doType(VK_SHIFT, VK_L); break;
		case 'M': // doType(VK_SHIFT, VK_M); break;
		case 'N': // doType(VK_SHIFT, VK_N); break;
		case 'O': // doType(VK_SHIFT, VK_O); break;
		case 'P': // doType(VK_SHIFT, VK_P); break;
		case 'Q': // doType(VK_SHIFT, VK_Q); break;
		case 'R': // doType(VK_SHIFT, VK_R); break;
		case 'S': // doType(VK_SHIFT, VK_S); break;
		case 'T': // doType(VK_SHIFT, VK_T); break;
		case 'U': // doType(VK_SHIFT, VK_U); break;
		case 'V': // doType(VK_SHIFT, VK_V); break;
		case 'W': // doType(VK_SHIFT, VK_W); break;
		case 'X': // doType(VK_SHIFT, VK_X); break;
		case 'Y': // doType(VK_SHIFT, VK_Y); break;
		case 'Z': // doType(VK_SHIFT, VK_Z); break;
		case '`': // doType(VK_BACK_QUOTE); break;
		case '0': // doType(VK_0); break;
		case '1': // doType(VK_1); break;
		case '2': // doType(VK_2); break;
		case '3': // doType(VK_3); break;
		case '4': // doType(VK_4); break;
		case '5': // doType(VK_5); break;
		case '6': // doType(VK_6); break;
		case '7': // doType(VK_7); break;
		case '8': // doType(VK_8); break;
		case '9': // doType(VK_9); break;
		case '-': // doType(VK_MINUS); break;
		case '=': // doType(VK_EQUALS); break;
		case '~': // doType(VK_SHIFT, VK_BACK_QUOTE); break;
		case '!': // doType(VK_EXCLAMATION_MARK); break;
		case '@': // doType(VK_AT); break;
		case '#': // doType(VK_NUMBER_SIGN); break;
		case '$': // doType(VK_DOLLAR); break;
		case '%': // doType(VK_SHIFT, VK_5); break;
		case '^': // doType(VK_CIRCUMFLEX); break;
		case '&': // doType(VK_AMPERSAND); break;
		case '*': // doType(VK_ASTERISK); break;
		case '(': // doType(VK_LEFT_PARENTHESIS); break;
		case ')': // doType(VK_RIGHT_PARENTHESIS); break;
		case '_': // doType(VK_UNDERSCORE); break;
		case '+': // doType(VK_PLUS); break;
		case '\t': // doType(VK_TAB); break;
		case '\n': // doType(VK_ENTER); break;
		case '[': // doType(VK_OPEN_BRACKET); break;
		case ']': // doType(VK_CLOSE_BRACKET); break;
		case '\\': // doType(VK_BACK_SLASH); break;
		case '{': // doType(VK_SHIFT, VK_OPEN_BRACKET); break;
		case '}': // doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
		case '|': // doType(VK_SHIFT, VK_BACK_SLASH); break;
		case ';': // doType(VK_SEMICOLON); break;
		case ':': // doType(VK_COLON); break;
		case '\'': // doType(VK_QUOTE); break;
		case '"': // doType(VK_QUOTEDBL); break;
		case ',': // doType(VK_COMMA); break;
		case '<': // doType(VK_LESS); break;
		case '.': // doType(VK_PERIOD); break;
		case '>': // doType(VK_GREATER); break;
		case '/': // doType(VK_SLASH); break;
		case '?': // doType(VK_SHIFT, VK_SLASH); break;
		case ' ': // doType(VK_SPACE); break;
		default:
			throw new IllegalArgumentException("Cannot type character "
					+ character);
		}
	}

	private void doType(int... keyCodes) {
		doType(keyCodes, 0, keyCodes.length);
	}

	private void doType(int[] keyCodes, int offset, int length) {
		if (length == 0) {
			return;
		}

		robot.keyPress(keyCodes[offset]);
		doType(keyCodes, offset + 1, length - 1);
		robot.keyRelease(keyCodes[offset]);
	}

	// running exe
	void run() {

		try {
			Runtime rt = Runtime.getRuntime();
			Process p = rt.exec("vbex.exe");

			// or
			// String command = "C:\\setup.exe";
			// Runtime.getRuntime().exec("cmd /c "+command);

		} catch (Exception e) {

		}

	}

}
