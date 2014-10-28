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

import pageObjects.MedicalHistory;
import pageObjects.PersonalHistory;

public class PatientMedicalHistory {
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientMedicalHistory(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "SendMedicalHistoryData")
	public static Object[][] SendMedicalHistoryData() throws Exception {

		Object MedicalHistory[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("MedicalHistory");

			Iterator<Row> rowIterator0 = ws.iterator();

			MedicalHistory = new Object[1][5];	//

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

						MedicalHistory[rowcounter][cellCounter] = data
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
		
		return new Object[][] { new Object[][] { MedicalHistory } };
	}

	@Test(groups={"functional"}, dataProvider="SendMedicalHistoryData")
	public static void ReceiveMedicalHistoryData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		Object[] values=new Object[5];
		Object[] result=new Object[1];
		
		headers[0]="Past Medical History";
		headers[1]="Famil History";
		headers[2]="Smoking History";
		headers[3]="Immunization";
		headers[4]="Allergies";
		headers[5]="Pass/Fail";
		
		boolean passfail=false;

		try {
			
			PersonalHistory.PatientHistoryMenu(wd).click();
			Thread.sleep(2000);
			
			MedicalHistory.MedicalHistorySubMenu(wd).click();
			Thread.sleep(2000);
			
			MedicalHistory.PastMedHistory(wd).clear();
			MedicalHistory.PastMedHistory(wd).sendKeys(data[0][0].toString());
			values[0]=MedicalHistory.PastMedHistory(wd).getAttribute("value");
			
			MedicalHistory.FamilyMedHistory(wd).clear();
			MedicalHistory.FamilyMedHistory(wd).sendKeys(data[0][1].toString());
			values[1]=MedicalHistory.FamilyMedHistory(wd).getAttribute("value");
			
			MedicalHistory.SmokingMedHistory(wd).clear();
			MedicalHistory.SmokingMedHistory(wd).sendKeys(data[0][2].toString());
			values[2]=MedicalHistory.SmokingMedHistory(wd).getAttribute("value");
			
			MedicalHistory.Immunization(wd).clear();
			MedicalHistory.Immunization(wd).sendKeys(data[0][3].toString());
			values[3]=MedicalHistory.Immunization(wd).getAttribute("value");
			
			MedicalHistory.Allergy(wd).clear();
			MedicalHistory.Allergy(wd).sendKeys(data[0][4].toString());
			values[4]=MedicalHistory.Allergy(wd).getAttribute("value");
			
			MedicalHistory.SaveButton(wd).click();
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("html/body/table/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[3]/a/img")).click();
			Thread.sleep(2000);
			
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			passfail=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(passfail){
			
			result[0]="Pass";
			
		} else {
			
			result[0]="Fail";
		}

		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 0, 0, new Object[][]{new Object[]{"Medical History - Addition"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 1, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 2, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 2, 5, new Object[][]{result});
		
	}
	
	@Test(groups={"functional"}, dependsOnMethods="ReceiveMedicalHistoryData")
	public static void EditMedicalHistoryData() throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		Object[] values=new Object[5];
		Object[] result=new Object[1];
		
		headers[0]="Past Medical History";
		headers[1]="Famil History";
		headers[2]="Smoking History";
		headers[3]="Immunization";
		headers[4]="Allergies";
		headers[5]="Pass/Fail";
		
		boolean passfail=false;

		try {
			
			PersonalHistory.PatientHistoryMenu(wd).click();
			Thread.sleep(2000);
			
			MedicalHistory.MedicalHistorySubMenu(wd).click();
			Thread.sleep(2000);
			
			MedicalHistory.PastMedHistory(wd).sendKeys(MedicalHistory.PastMedHistory(wd).getAttribute("value").concat(" text modified"));
			values[0]=MedicalHistory.PastMedHistory(wd).getAttribute("value");
			
			MedicalHistory.FamilyMedHistory(wd).sendKeys(MedicalHistory.FamilyMedHistory(wd).getAttribute("value").concat(" text modified"));
			values[1]=MedicalHistory.FamilyMedHistory(wd).getAttribute("value");
			
			MedicalHistory.SmokingMedHistory(wd).sendKeys(MedicalHistory.SmokingMedHistory(wd).getAttribute("value").concat(" text modified"));
			values[2]=MedicalHistory.SmokingMedHistory(wd).getAttribute("value");
			
			MedicalHistory.Immunization(wd).sendKeys(MedicalHistory.Immunization(wd).getAttribute("value").concat(" text modified"));
			values[3]=MedicalHistory.Immunization(wd).getAttribute("value");
			
			MedicalHistory.Allergy(wd).sendKeys(MedicalHistory.Allergy(wd).getAttribute("value").concat(" text modified"));
			values[4]=MedicalHistory.Allergy(wd).getAttribute("value");
			
			MedicalHistory.SaveButton(wd).click();
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("html/body/table/tbody/tr[3]/td[2]/form/table/tbody/tr[1]/td[3]/a/img")).click();
			Thread.sleep(2000);
			
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			passfail=true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(passfail){
			
			result[0]="Pass";
			
		} else {
			
			result[0]="Fail";
		}

		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 4, 0, new Object[][]{new Object[]{"Medical History - Modification"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 5, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 6, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "MedicalHistory", 6, 5, new Object[][]{result});
		
	}

}
