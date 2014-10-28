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

import pageObjects.PatientReport;

public class Patient_Report {
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public Patient_Report(WebDriver wd) {
		
		this.wd=wd;
		
	}

	@DataProvider(name = "sendReportData")
	public static Object[][] sendReportData() throws Exception {

		Object hospital[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("PatientReport");

			Iterator<Row> rowIterator0 = ws.iterator();

			hospital = new Object[1][4];	//

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

						hospital[rowcounter][cellCounter] = data
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
		return new Object[][] { new Object[][] { hospital } };
	}

	@Test(groups={"functional"}, dataProvider="sendReportData")
	public static void PrintReport(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		try {
			
			Object[] headers=new Object[4];
			Object[] values=new Object[4];
			
			headers[0]="Patient History";
			headers[1]="Appointment";
			headers[2]="Accounts";
			headers[3]="Prescription";
			
			PatientReport.Report_Link(wd).click();

			Thread.sleep(2000);
			
			wd.switchTo().frame("ifrmdocuments");
			
			if(data[0][0].toString().equalsIgnoreCase("yes")){

				PatientReport.Chkbox_History(wd).click();
				
				Thread.sleep(2000);
				
				values[0]="Yes";
				
			} 
			
			if (data[0][1].toString().equalsIgnoreCase("yes")){
				
				PatientReport.Chkbox_Appointment(wd).click();
				
				Thread.sleep(2000);
				
				values[1]="Yes";
				
			} 

			if (data[0][2].toString().equalsIgnoreCase("yes")){
				
				PatientReport.Chkbox_Accounts(wd).click();
				
				Thread.sleep(2000);
				
				values[2]="Yes";
				
			}
			
			if(data[0][3].toString().equalsIgnoreCase("Yes")){
				
				PatientReport.Chkbox_Prescription(wd).click();
				
				Thread.sleep(2000);
				
				values[3]="Yes";
				
			}
			
			/*
			 * The following loop to store the null values with "No" String
			 * as the above condition will store only certain values.  
			 * This causes NullPointer Exception to be thrown while processing and writing array.
			 * 
			*/
			for (int i=0; i<values.length; i++){

				if(values[i]==null){
					
					values[i]="No";
					
				}
				
			}

			// get the current window handle
			String currentwindow=wd.getWindowHandle();

			// click the print button
			javafiles.SwitchToUrl.getPDFWindow(wd, By.name("cmd_submit"));  
			
			// storing the PDF window handle into an variable
			String pdfwindow=wd.getWindowHandle();
			
			// now switch to pdf window to process the PDF content
			wd.switchTo().window(pdfwindow);
			
			// this static method process the PDF
			javafiles.PDFRobot.PDFProcess("PatientReport.pdf");
			
			// after the PDF process, the PDF window closed
			wd.switchTo().window(pdfwindow).close();
			
			// now the focus is switched back to current window handle
			wd.switchTo().window(currentwindow);
			
			wd.switchTo().defaultContent();

			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);

			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PatientReport", 0, 0, new Object[][]{new Object[]{"Patient Report"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PatientReport", 1, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PatientReport", 2, 0, new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
