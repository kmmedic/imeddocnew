package testNGfiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafiles.ReadExcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.MC;

public class MedicalCertificate {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;

	public MedicalCertificate(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "sendData")
	public static Object[][] sendData() throws Exception {

		Object[][] waitinglist = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("MC");

			Iterator<Row> rowIterator0 = ws.iterator();

			waitinglist = new Object[3][6];

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

						waitinglist[rowcounter][cellCounter] = data.get(cellCounter);

					}

					rowcounter++;

				}

			}

			filein.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Object[][] { new Object[][] { waitinglist } };

	}
	
	@Test(groups={"functional"}, dataProvider="sendData")
	public static void receiveData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		
		headers[0]="Patient Name";
		headers[1]="CurDate";
		headers[2]="Description";
		headers[3]="FromDate";
		headers[4]="ToDate";
		headers[5]="Reason";
		
		Object[] values=new Object[6];
		
		
		Thread.sleep(2000);
		MC.MC_Link(wd).click();
		Thread.sleep(2000);
		
		wd.switchTo().frame("ifrmdocuments");
		
		MC.Add_Button(wd).click();
		
		Thread.sleep(2000);

		MC.CurDate(wd).sendKeys(data[0][0].toString().split("-"));

		values[0]=MC.PatientName(wd).getAttribute("value");
		values[1]=MC.CurDate(wd).getAttribute("value");
		
		MC.Description(wd).sendKeys(data[0][1].toString());

		values[2]=MC.Description(wd).getAttribute("value");
		
		MC.FromDate(wd).sendKeys(data[0][2].toString().split("-"));
		
		values[3]=MC.FromDate(wd).getAttribute("value");
							
		MC.ToDate(wd).sendKeys(data[0][3].toString().split("-"));
		
		values[4]=MC.ToDate(wd).getAttribute("value");
		
		MC.Reasons(wd).sendKeys(data[0][4].toString());
		
		values[5]=MC.Reasons(wd).getAttribute("value");
		
		MC.SaveButton(wd).click();
		
		Thread.sleep(2000);
		
		MC.InnerClose(wd).click();
		
		Thread.sleep(2000);
		
		wd.switchTo().defaultContent();
		
		wd.switchTo().frame("iframe1");
		
		wd.findElement(By.id("cmd_submit")).click();
		
		Thread.sleep(2000);
		
		wd.findElement(By.id("cmd_cancel")).click();
		
		Thread.sleep(2000);

		
		try {
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 0, 0, new Object[][]{new Object[]{"MC Addition"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 1, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 2, 0, new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(groups={"functional"}, dataProvider="sendData", dependsOnMethods="receiveData", alwaysRun=true)
	public static void editData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		
		headers[0]="Patient Name";
		headers[1]="CurDate";
		headers[2]="Description";
		headers[3]="FromDate";
		headers[4]="ToDate";
		headers[5]="Reason";

		Object[] values=new Object[6];
		
		Thread.sleep(2000);
		MC.MC_Link(wd).click();
		Thread.sleep(2000);
		
		wd.switchTo().frame("ifrmdocuments");
		
		Thread.sleep(2000);
		
		wd.findElement(By.xpath("//*[@id='frm_hp']/table/tbody/tr[3]/td[2]")).click();
		
		MC.EditButton(wd).click();
		
		Thread.sleep(2000);
		
		String curdate="25-11-2014";
								
		MC.CurDate(wd).clear();
		MC.CurDate(wd).sendKeys(curdate.split("-"));
		
		values[0]=MC.PatientName(wd).getAttribute("value");
		
		values[1]=MC.CurDate(wd).getAttribute("value");
		
		MC.Description(wd).clear();
		MC.Description(wd).sendKeys(data[0][1].toString());
		values[2]=MC.Description(wd).getAttribute("value");
		
		String fromdate="20-11-2014";
		
		MC.FromDate(wd).clear();
		MC.FromDate(wd).sendKeys(fromdate.split("-"));

		values[3]=MC.FromDate(wd).getAttribute("value");

		String todate="22-11-2014";
		
		MC.ToDate(wd).clear();
		MC.ToDate(wd).sendKeys(todate.split("-"));
		
		values[4]=MC.ToDate(wd).getAttribute("value");
		
		MC.Reasons(wd).sendKeys(data[0][4].toString());
		
		values[5]=MC.Reasons(wd).getAttribute("value");
		
		MC.SaveButton(wd).click();
		
		Thread.sleep(2000);
		
		MC.InnerClose(wd).click();
		
		Thread.sleep(2000);
		
		wd.switchTo().defaultContent();
		
		wd.switchTo().frame("iframe1");
		
		wd.findElement(By.id("cmd_submit")).click();
		
		Thread.sleep(2000);
		
		wd.findElement(By.id("cmd_cancel")).click();
		
		Thread.sleep(2000);
		
		try {
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 4, 0, new Object[][]{new Object[]{"MC Modification"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 5, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MC", 6, 0, new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/*	
	@Test(dependsOnMethods="editData")
	public static void ToPrint() throws Exception {
		
			testNGfiles.GotoPatient.PatientPage();

			MC.MC_Link(wd).click();
			
			wd.switchTo().frame("ifrmdocuments");

			Thread.sleep(2000);
			
			wd.findElement(By.xpath("//*[@id='frm_hp']/table/tbody/tr[3]/td[2]")).click();
			
			javafiles.SwitchToUrl.getPDFWindow(wd, By.name("cmd_print"));  // click the print button
			
			javafiles.PDFRobot.PDFProcess("MedicalCertificate.pdf");  //  robot processing the pdf
			
			wd.switchTo().window(wd.getWindowHandle()).close();  // after print PDF window closed
			
			wd.switchTo().frame("ifrmdocuments").close();

			wd.switchTo().defaultContent();
			
			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_cancel")).click();

			javafiles.PdfVerification.verifyPatientid("MedicalCertificate.pdf");
			
	}
*/	
	@Test(groups={"functional"}, dependsOnMethods="editData", alwaysRun=true)
	private static void DeleteRecord() throws Exception {
		
			testNGfiles.GotoPatient.PatientPage();
			
			try {
				
				MC.MC_Link(wd).click();
				
				wd.switchTo().frame("ifrmdocuments");
				
				Thread.sleep(2000);

				wd.findElement(By.xpath("//*[@id='frm_hp']/table/tbody/tr[3]/td[2]")).click();
				
				MC.DeleteButton(wd).click();
				
				wd.switchTo().alert().accept();
				
				Thread.sleep(2000);
				
				MC.InnerClose(wd).click();
				
				wd.switchTo().defaultContent();
				
				wd.switchTo().frame("iframe1");
				
				wd.findElement(By.id("cmd_submit")).click();
				
				Thread.sleep(2000);
				
				wd.findElement(By.id("cmd_cancel")).click();
				
				Thread.sleep(2000);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
