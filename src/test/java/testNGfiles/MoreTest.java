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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.More;

public class MoreTest {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public MoreTest(WebDriver wd){
		
		this.wd=wd;
	}
	
	@DataProvider(name = "moretest")
	public static Object[][] login() throws Exception {
		
		FileInputStream file = new FileInputStream(new File(testNGfiles.FilesAndPaths.TestData()+"\\"+"TestCases.xls"));
		
		wb = new HSSFWorkbook(file);
		ws = wb.getSheet("More");
		
		Iterator<Row> rowIterator0 = ws.iterator();
		HSSFRow row0 = (HSSFRow) rowIterator0.next();
		int no_of_rows = ws.getLastRowNum();
		int no_of_cols = row0.getLastCellNum();

	    Object[][] data= new Object[no_of_rows][no_of_cols];

		int i = 0;

		while (rowIterator0.hasNext()) {
			HSSFRow row1 = (HSSFRow) rowIterator0.next();

			Iterator<Cell> cells = row1.cellIterator();

			int j = 0;

			while (cells.hasNext()) {
				HSSFCell cell = (HSSFCell) cells.next();
				data[i][j] = cell.getStringCellValue();
				System.out.println("i=" + i + " j=" + j);
				System.out.println(data[i][j]);
				j = j + 1;
			}

			i = i + 1;

		}

		file.close();
		
		return data;
	}
	
	@Test(groups={"functional"}, dataProvider="moretest")
	public static void receiveData(String patientid, String nonpay, String rip) throws Exception {

		try {
			testNGfiles.GotoPatient.PatientPage();
			
			Object[] header=new Object[3];
			header[0]="Patient id";
			header[1]="Non pay";
			header[2]="Rip";
			
			Object[] value=new Object[3];
			value[0]=patientid;
			
			Thread.sleep(2000);
			More.More_Link(wd).click();
			
			if(patientid.equals(patientid)){
					
				if(More.lblNonPaying(wd).getText().contains("Non Paying")){
					
					if(nonpay.equalsIgnoreCase("yes")){

						if(More.NonPaying(wd).isSelected()==false){
							
							More.NonPaying(wd).click();
						}
						
						value[1]="Yes";
						
					}else {
						
						value[1]="No";
						
					}
					
				}
					
				if(More.lblRip(wd).getText().contains("RIP")){
					
					if(rip.equalsIgnoreCase("yes")){

						if(More.Rip(wd).isSelected()==false){
							
							More.Rip(wd).click();
							
						}
						
						value[2]="Yes";
						
					}else{
						
						value[2]="No";
						
					}
							
				}
					
			}
			
			More.Ok_button(wd).click();
			
			wd.findElement(By.id("cmd_submit")).click();
			wd.findElement(By.id("cmd_cancel")).click();
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "More", 0, 0, new Object[][]{new Object[]{"More Addition"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "More", 1, 0, new Object[][]{header});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "More", 2, 0, new Object[][]{value});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
