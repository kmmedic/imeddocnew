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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.MRN;

public class Hospital {
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;

	public Hospital(WebDriver wd){
		
		this.wd=wd;
		
	}

	@DataProvider(name = "sendhospitaldata")
	public static Object[][] sendHospitalData() throws Exception {

		Object hospital[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("MRN");

			Iterator<Row> rowIterator0 = ws.iterator();

			hospital = new Object[3][2];

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

	@Test(groups={"functional"}, dataProvider = "sendhospitaldata")
	public static void receiveHospitalData(Object[][] hospitaldata)
			throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();

		Object[] hospitalelments = new Object[2]; // to store web elements

		hospitalelments[0] = "Hospital Name";
		hospitalelments[1] = "MRN No.";
		
		Object[][] addhospitalvalue=new Object[3][2];

		try {

			MRN.MRN_Link(wd).click();
			Thread.sleep(2000);
			MRN.Hospital1(wd, hospitaldata[0][0].toString()); 	// Hospital 1
			addhospitalvalue[0][0]=new Select(wd.findElement(By.id("inhosp"))).getFirstSelectedOption().getText();

			MRN.HospitalValue1(wd).clear();						// Hospital1 value
			MRN.HospitalValue1(wd).sendKeys(hospitaldata[0][1].toString());
			addhospitalvalue[0][1]=MRN.HospitalValue1(wd).getAttribute("value");

			MRN.Hospital2(wd, hospitaldata[1][0].toString()); 	// Hospital 2
			addhospitalvalue[1][0]=new Select(wd.findElement(By.id("inhosp2"))).getFirstSelectedOption().getText();

			MRN.HospitalValue2(wd).clear();					 	// Hospital value 2
			MRN.HospitalValue2(wd).sendKeys(hospitaldata[1][1].toString());
			addhospitalvalue[1][1]=MRN.HospitalValue2(wd).getAttribute("value");

			MRN.Hospital3(wd, hospitaldata[2][0].toString()); 	// Hospital 3
			addhospitalvalue[2][0]=new Select(wd.findElement(By.id("inhosp3"))).getFirstSelectedOption().getText();
			
			MRN.HospitalValue3(wd).clear();						// Hospital value 3
			MRN.HospitalValue3(wd).sendKeys(hospitaldata[2][1].toString());
			addhospitalvalue[2][1]=MRN.HospitalValue3(wd).getAttribute("value");
			
			MRN.Ok_Button(wd).click();
			
			wd.findElement(By.id("cmd_submit")).click();
			
			Thread.sleep(2000);
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);

			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 0, 0, new Object[][]{new Object[]{"MRN Addition"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 1, 0, new Object[][]{hospitalelments});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 2, 0, new Object[][]{addhospitalvalue});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		

	@Test(groups={"functional"}, dataProvider="sendhospitaldata", dependsOnMethods="receiveHospitalData", alwaysRun=true)
	public static void editHospitalData(Object[][] hospitaldata) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] hospitalelments = new Object[2]; // to store web elements

		hospitalelments[0] = "Hospital Name";
		hospitalelments[1] = "MRN No.";
		
		Object[][] editvalue=new Object[3][2];

		try {

			MRN.MRN_Link(wd).click();
			Thread.sleep(2000);
			MRN.Hospital1(wd, hospitaldata[2][0].toString()); 	// Hospital 1
			editvalue[0][0]=new Select(wd.findElement(By.id("inhosp"))).getFirstSelectedOption().getText();

			MRN.HospitalValue1(wd).clear();
			MRN.HospitalValue1(wd).sendKeys(hospitaldata[2][1].toString());
			editvalue[0][1]=MRN.HospitalValue1(wd).getAttribute("value");

			MRN.Hospital2(wd, hospitaldata[1][0].toString()); 	// Hospital 2
			editvalue[1][0]=new Select(wd.findElement(By.id("inhosp2"))).getFirstSelectedOption().getText();

			MRN.HospitalValue2(wd).clear();					 	// Hospital value 2
			MRN.HospitalValue2(wd).sendKeys(hospitaldata[1][1].toString());
			editvalue[1][1]=MRN.HospitalValue2(wd).getAttribute("value");

			MRN.Hospital3(wd, hospitaldata[0][0].toString()); 	// Hospital 3
			editvalue[2][0]=new Select(wd.findElement(By.id("inhosp3"))).getFirstSelectedOption().getText();
			
			MRN.HospitalValue3(wd).clear();						// Hospital value 3
			MRN.HospitalValue3(wd).sendKeys(hospitaldata[0][1].toString());
			editvalue[2][1]=MRN.HospitalValue3(wd).getAttribute("value");

			MRN.Ok_Button(wd).click();

			wd.findElement(By.id("cmd_submit")).click();
			
			Thread.sleep(2000);
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);

			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 4, 0, new Object[][]{new Object[]{"MRN Modification"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 5, 0, new Object[][]{hospitalelments});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MRN", 6, 0, new Object[][]{editvalue});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
