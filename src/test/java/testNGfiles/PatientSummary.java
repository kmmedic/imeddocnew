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

import pageObjects.ExaminationHistory;

public class PatientSummary {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientSummary(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "SendComplaintData")
	public static Object[][] SendComplaintData() throws Exception {

		Object curcomplaint[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("CurrentComplaint");

			Iterator<Row> rowIterator0 = ws.iterator();

			curcomplaint = new Object[1][6];	//

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

						curcomplaint[rowcounter][cellCounter] = data
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
		return new Object[][] { new Object[][] { curcomplaint } };
	}
	
	@Test(groups={"functional"}, dataProvider="SendComplaintData")
	public static void ReceiveComplaintData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[7];
		Object[] values=new Object[6];
		Object[] result=new Object[1];

		headers[0]="Cur_Date";
		headers[1]="Presenting Complaint";
		headers[2]="History of Complaint";
		headers[3]="Objective";
		headers[4]="Investigation";
		headers[5]="Treatment";
		headers[6]="Pass/Fail";

		boolean passfail = false;
		
		try {

			Thread.sleep(2000);

			ExaminationHistory.PatientSummarySubmenu(wd).click();
			
			// focusing summary window
			wd.switchTo().defaultContent();
			// switching to window frame
			wd.switchTo().frame("ifrmdocuments");
			
			Thread.sleep(2000);

			// click on Add new Examination
			ExaminationHistory.AddExam(wd).click();
			
			Thread.sleep(2000);

			// focusing on top window
			wd.switchTo().defaultContent();
			
			// click close icon in the window 
			wd.findElement(By.xpath("//*[@id='close']/img")).click();
			
			Thread.sleep(2000);
			
			// focusing on 'current complaints' window
			wd.switchTo().defaultContent();
			
			wd.switchTo().frame("iframe1");
			
			wd.switchTo().frame("ifrmdocuments");
			
			ExaminationHistory.CurComp_Button(wd).click();
			
			ExaminationHistory.CurDate(wd).sendKeys(data[0][0].toString().split("-"));
			values[0]=ExaminationHistory.CurDate(wd).getAttribute("value");
			
			ExaminationHistory.Pres_Comp(wd).sendKeys(data[0][1].toString());
			values[1]=ExaminationHistory.Pres_Comp(wd).getAttribute("value");
			
			ExaminationHistory.Pres_Hist(wd).sendKeys(data[0][2].toString());
			values[2]=ExaminationHistory.Pres_Hist(wd).getAttribute("value");
			
			ExaminationHistory.Objective(wd).sendKeys(data[0][3].toString());
			values[3]=ExaminationHistory.Objective(wd).getAttribute("value");
			
			ExaminationHistory.Investigation(wd).sendKeys(data[0][4].toString());
			values[4]=ExaminationHistory.Investigation(wd).getAttribute("value");
			
			ExaminationHistory.Treatment(wd).sendKeys(data[0][5].toString());
			values[5]=ExaminationHistory.Treatment(wd).getAttribute("value");
			
			ExaminationHistory.Ok_button(wd).click();
			
			wd.switchTo().defaultContent();
			
			passfail = true;
			
		} catch(Exception e) {
			
			System.out.println("Test fails   :  ".concat(e.getMessage())); 
			
		}
		
		if(passfail){
			
			result[0]="Pass";
			
			
		} else {
			
			result[0]="Fail";

		}
		
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 0, 0, new Object[][]{new Object[]{"Current Complaint - Addition"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 1, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 2, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 2, 6, new Object[][]{result});
	}
	
	@Test(groups={"functional"}, dataProvider="SendComplaintData", dependsOnMethods="ReceiveComplaintData", alwaysRun=true)
	public static void EditRecord(Object[][] data) throws Exception {
		
		Object[] headers=new Object[7];
		Object[] values=new Object[6];
		Object[] result=new Object[1];

		headers[0]="Cur_Date";
		headers[1]="Presenting Complaint";
		headers[2]="History of Complaint";
		headers[3]="Objective";
		headers[4]="Investigation";
		headers[5]="Treatment";
		headers[6]="Pass/Fail";

		boolean passfail=false;
		
		try {
			
			wd.switchTo().frame("iframe1");
			
			// Click on Record
			wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[3]")).click();
			
			// Click on Edit
			ExaminationHistory.Edit(wd).click();
			
			wd.switchTo().defaultContent();
			
			wd.switchTo().frame("iframe1");
			
			wd.switchTo().frame("ifrmdocuments");
			
			ExaminationHistory.CurComp_Button(wd).click();
			
			ExaminationHistory.CurDate(wd).sendKeys("20-11-2014".split("-"));
			values[0]=ExaminationHistory.CurDate(wd).getAttribute("value");
			
			ExaminationHistory.Pres_Comp(wd).clear();
			ExaminationHistory.Pres_Comp(wd).sendKeys(data[0][1].toString()+" Modified");
			values[1]=ExaminationHistory.Pres_Comp(wd).getAttribute("value");
			
			ExaminationHistory.Pres_Hist(wd).clear();
			ExaminationHistory.Pres_Hist(wd).sendKeys(data[0][2].toString()+" Modified");
			values[2]=ExaminationHistory.Pres_Hist(wd).getAttribute("value");
			
			ExaminationHistory.Objective(wd).clear();
			ExaminationHistory.Objective(wd).sendKeys(data[0][3].toString()+" Modified");
			values[3]=ExaminationHistory.Objective(wd).getAttribute("value");
			
			ExaminationHistory.Investigation(wd).clear();
			ExaminationHistory.Investigation(wd).sendKeys(data[0][4]+" Modified");
			values[4]=ExaminationHistory.Investigation(wd).getAttribute("value");
			
			ExaminationHistory.Treatment(wd).clear();
			ExaminationHistory.Treatment(wd).sendKeys(data[0][5]+" Modified");
			values[5]=ExaminationHistory.Treatment(wd).getAttribute("value");
			
			ExaminationHistory.Ok_button(wd).click();
			
			wd.switchTo().defaultContent();
			
			passfail=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Test Fails due to ".concat(e.getMessage()));
		}

		if (passfail){
			
			result[0]="Pass";
			
			System.out.println("Result is "+result[0].toString());
			
			
		} else {

			result[0]="Fail";
			
			System.out.println("Result is "+result[0].toString());
			
		}
		
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 4, 0, new Object[][]{ new Object[]{"Current Complaints - Modification"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 5, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 6, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 6, 6, new Object[][]{result});		
	}
	
	@Test(groups={"functional"}, dependsOnMethods="EditRecord", alwaysRun=true)
	public static void DeleteRecord() throws Exception {

		boolean passfail = false;
		
		try {
			
			wd.switchTo().frame("iframe1");
			
			// Click on Record
			wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[3]")).click();

			// Click on Delete
			ExaminationHistory.Delete(wd).click();
			
			wd.switchTo().defaultContent();
			
			wd.switchTo().frame("iframe1");

			wd.switchTo().frame("ifrmdocuments");

			// confirm to delete
			wd.findElement(By.id("cmd_submit")).click();

			wd.switchTo().defaultContent();
			
			passfail = true;
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			System.out.println("Test Fails due to ".concat(e.getMessage()));

		}
		
		if(passfail){
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 8, 0, new Object[][]{ new Object[]{"Record Deletion Process success"}});
			
		}else {
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "CurrentComplaint", 8, 0, new Object[][]{ new Object[]{"Record Deletion Process failed"}});
			
		}
		
	}

}
