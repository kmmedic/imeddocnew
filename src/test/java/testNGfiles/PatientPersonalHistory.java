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

import pageObjects.PersonalHistory;

public class PatientPersonalHistory {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientPersonalHistory(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "SendPersonalHistoryData")
	public static Object[][] SendPersonalHistoryData() throws Exception {

		Object PersonalHistory[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("PersonalHistory");

			Iterator<Row> rowIterator0 = ws.iterator();

			PersonalHistory = new Object[1][6];	//

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

						PersonalHistory[rowcounter][cellCounter] = data
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
		
		return new Object[][] { new Object[][] { PersonalHistory } };
	}
	
	@Test(groups={"functional"}, dataProvider="SendPersonalHistoryData")
	public static void ReceivePersonalHistoryData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		Object[] values=new Object[5];
		Object[] result=new Object[1];
		
		
		headers[0]="Health Habits";
		headers[1]="Smoking";
		headers[2]="Travel";
		headers[3]="Family History";
		headers[4]="Social History";
		headers[5]="Pass/Fail";
		
		boolean passfail=false;
		
		try {
			
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			PersonalHistory.PatientHistoryMenu(wd).click();
			Thread.sleep(2000);

			PersonalHistory.PersonalHistorySubMenu(wd).click();
			Thread.sleep(2000);
			
			PersonalHistory.HealthHabits(wd).clear();
			PersonalHistory.HealthHabits(wd).sendKeys(data[0][0].toString());
			values[0]=PersonalHistory.HealthHabits(wd).getAttribute("value");
			
			PersonalHistory.SmokingHistory(wd).clear();
			PersonalHistory.SmokingHistory(wd).sendKeys(data[0][1].toString());
			values[1]=PersonalHistory.HealthHabits(wd).getAttribute("value");
			
			PersonalHistory.Travel(wd).clear();
			PersonalHistory.Travel(wd).sendKeys(data[0][2].toString());
			values[2]=PersonalHistory.Travel(wd).getAttribute("value");
			
			PersonalHistory.FamilyHistory(wd).clear();
			PersonalHistory.FamilyHistory(wd).sendKeys(data[0][3].toString());
			values[3]=PersonalHistory.FamilyHistory(wd).getAttribute("value");
			
			PersonalHistory.SocialHistory(wd).clear();
			PersonalHistory.SocialHistory(wd).sendKeys(data[0][4].toString());
			values[4]=PersonalHistory.SocialHistory(wd).getAttribute("value");
			
			PersonalHistory.SaveButton(wd).click();
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
		
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 0, 0, new Object[][]{new Object[]{"Personal History - Addition"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 1, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 2, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 2, 5, new Object[][]{result});
	}
	
	@Test(groups={"functional"}, dataProvider="SendPersonalHistoryData", dependsOnMethods="ReceivePersonalHistoryData")
	public static void EditPersonalHistory(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[6];
		Object[] values=new Object[5];
		Object[] result=new Object[1];
		
		headers[0]="Health Habits";
		headers[1]="Smoking";
		headers[2]="Travel";
		headers[3]="Family History";
		headers[4]="Social History";
		headers[5]="Pass/Fail";
		
		boolean passfail=false;
		
		try {
			
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			wd.findElement(By.xpath("//tr[4]/td/table/tbody/tr/td[2]/a/font")).click();
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("//td/table/tbody/tr/td[2]/font")).click();
			Thread.sleep(4000);
			
			PersonalHistory.HealthHabits(wd).sendKeys(PersonalHistory.HealthHabits(wd).getAttribute("value").concat(" Text Modified"));
			values[0]=PersonalHistory.HealthHabits(wd).getAttribute("value");
			
			PersonalHistory.SmokingHistory(wd).sendKeys(PersonalHistory.SmokingHistory(wd).getAttribute("value").concat(" Text Modified"));
			values[1]=PersonalHistory.SmokingHistory(wd).getAttribute("value");
			
			PersonalHistory.Travel(wd).sendKeys(PersonalHistory.Travel(wd).getAttribute("value").concat(" Text Modified"));
			values[2]=PersonalHistory.Travel(wd).getAttribute("value");
			
			PersonalHistory.FamilyHistory(wd).sendKeys(PersonalHistory.FamilyHistory(wd).getAttribute("value").concat(" Text Modified"));
			values[3]=PersonalHistory.FamilyHistory(wd).getAttribute("value");
			
			PersonalHistory.SocialHistory(wd).sendKeys(PersonalHistory.SocialHistory(wd).getAttribute("value").concat(" Text Modified"));
			values[4]=PersonalHistory.SocialHistory(wd).getAttribute("value");
			
			PersonalHistory.SaveButton(wd).click();
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

		if(passfail) {
			
			result[0]="Pass";
			
		} else {
			
			result[0]="Fail";
		}
		
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 4, 0, new Object[][]{new Object[]{"Personal History - Modification"}});
		ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 5, 0, new Object[][]{headers});
		ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 6, 0, new Object[][]{values});
		ReadExcel.updateCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "PersonalHistory", 6, 5, new Object[][]{result});
		
	}
	
}
