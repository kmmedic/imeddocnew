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

import pageObjects.Diagnosis;

public class PatientDiagnosis {
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientDiagnosis(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "SendDiagnosisData")
	public static Object[][] SendDiagnosisData() throws Exception {

		Object diagnosis[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("Diagnosis");

			Iterator<Row> rowIterator0 = ws.iterator();

			diagnosis = new Object[1][8];	//

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

						diagnosis[rowcounter][cellCounter] = data
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
		return new Object[][] { new Object[][] { diagnosis } };
	}

	@Test(groups={"functional"}, dataProvider="SendDiagnosisData")
	public static void PatientDiagnosisAddition(Object[][] data) throws Exception {

		testNGfiles.GotoPatient.PatientPage();
		
		try {
			
			Object[] headers=new Object[8];
			Object[] values=new Object[8];
			
			headers[0]="Primary Diagnosis";
			headers[1]="Secondary Diagnosis";
			headers[2]="Nature of Symptoms";
			headers[3]="Weeks";
			headers[4]="Months";
			headers[5]="Years";
			headers[6]="Treatment";
			headers[7]="Comments";
			
			Diagnosis.Diagnosis_Link(wd).click();
			
			Diagnosis.PriDiagnosis(wd).sendKeys(data[0][0].toString());
			values[0]=Diagnosis.PriDiagnosis(wd).getAttribute("value");
			
			Diagnosis.SecDiagnosis(wd).sendKeys(data[0][1].toString());
			values[1]=Diagnosis.SecDiagnosis(wd).getAttribute("value");
			
			Diagnosis.NatureSymptoms(wd).sendKeys(data[0][2].toString());
			values[2]=Diagnosis.NatureSymptoms(wd).getAttribute("value");

			Diagnosis.Weeks(wd).sendKeys(data[0][3].toString());
			values[3]=Diagnosis.Weeks(wd).getAttribute("value");
			
			Diagnosis.Months(wd).sendKeys(data[0][4].toString());
			values[4]=Diagnosis.Months(wd).getAttribute("value");
			
			Diagnosis.Years(wd).sendKeys(data[0][5].toString());
			values[5]=Diagnosis.Years(wd).getAttribute("value");
			
			Diagnosis.Description(wd).sendKeys(data[0][6].toString());
			values[6]=Diagnosis.Description(wd).getAttribute("value");
			
			Diagnosis.Comments(wd).sendKeys(data[0][7].toString());
			values[7]=Diagnosis.Comments(wd).getAttribute("value");
			
			Diagnosis.Ok_Button(wd).click();
			
			wd.switchTo().defaultContent();

			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_submit")).click();
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);

			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 0, 0, new Object[][]{new Object[]{"Diagnosis Addition"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 1, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 2, 0, new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(dataProvider="SendDiagnosisData", dependsOnMethods="PatientDiagnosisAddition")
	public static void PatientDiagnosisModification(Object[][] data) throws Exception {

		testNGfiles.GotoPatient.PatientPage();
		
		try {
			
			Object[] headers=new Object[8];
			Object[] values=new Object[8];
			
			headers[0]="Primary Diagnosis";
			headers[1]="Secondary Diagnosis";
			headers[2]="Nature of Symptoms";
			headers[3]="Weeks";
			headers[4]="Months";
			headers[5]="Years";
			headers[6]="Treatment";
			headers[7]="Comments";
			
			Diagnosis.Diagnosis_Link(wd).click();
			
			Diagnosis.PriDiagnosis(wd).clear();
			Diagnosis.PriDiagnosis(wd).sendKeys(data[0][0].toString()+" "+RCSampleTest.pname);
			values[0]=Diagnosis.PriDiagnosis(wd).getAttribute("value");
			
			Diagnosis.SecDiagnosis(wd).clear();
			Diagnosis.SecDiagnosis(wd).sendKeys(data[0][1].toString()+" "+RCSampleTest.pname);
			values[1]=Diagnosis.SecDiagnosis(wd).getAttribute("value");
			
			Diagnosis.NatureSymptoms(wd).clear();
			Diagnosis.NatureSymptoms(wd).sendKeys(data[0][2].toString()+" "+RCSampleTest.pname);
			values[2]=Diagnosis.NatureSymptoms(wd).getAttribute("value");

			Diagnosis.Weeks(wd).clear();
			Diagnosis.Weeks(wd).sendKeys(data[0][3].toString()+" "+RCSampleTest.pname);
			values[3]=Diagnosis.Weeks(wd).getAttribute("value");
			
			Diagnosis.Months(wd).clear();
			Diagnosis.Months(wd).sendKeys(data[0][4].toString()+" "+RCSampleTest.pname);
			values[4]=Diagnosis.Months(wd).getAttribute("value");
			
			Diagnosis.Years(wd).clear();
			Diagnosis.Years(wd).sendKeys(data[0][5].toString()+" "+RCSampleTest.pname);
			values[5]=Diagnosis.Years(wd).getAttribute("value");
			
			Diagnosis.Description(wd).clear();
			Diagnosis.Description(wd).sendKeys(data[0][6].toString()+" "+RCSampleTest.pname);
			values[6]=Diagnosis.Description(wd).getAttribute("value");
			
			Diagnosis.Comments(wd).clear();
			Diagnosis.Comments(wd).sendKeys(data[0][7].toString()+" "+RCSampleTest.pname);
			values[7]=Diagnosis.Comments(wd).getAttribute("value");
			
			Diagnosis.Ok_Button(wd).click();
			
			wd.switchTo().defaultContent();

			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_submit")).click();
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);

			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 5, 0, new Object[][]{new Object[]{"Diagnosis Modification"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 6, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Diagnosis", 7, 0, new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
