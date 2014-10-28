package testNGfiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.NOK;

public class NextofKin {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	private static Object nokelments[]=new String [11];
	
	public NextofKin(WebDriver wd){
		this.wd=wd;
	}
	
	@DataProvider(name="sendnokdata")
	public static Object[][] sendNOKData() throws Exception{
		
		Object nokdata[] = null;
		
		try {
			
			FileInputStream filein=new FileInputStream(new File(testNGfiles.FilesAndPaths.TestData()+"\\"+"TestCases.xls"));
			
			wb=new HSSFWorkbook(filein);
			ws=wb.getSheet("NOK");
			
			Iterator<Row> rowIterator0 = ws.iterator();
			
			nokdata = new Object[11];
			
			int maxNumOfCells = ws.getRow(0).getLastCellNum(); // The the maximum number 

			while(rowIterator0.hasNext()){
				
				HSSFRow row1 = (HSSFRow) rowIterator0.next();
				
				if (row1.getRowNum()==1){
					
					List<HSSFCell> data = new ArrayList<HSSFCell>();
					
	                for( int cellCounter = 0
	                        ; cellCounter < maxNumOfCells
	                        ; cellCounter ++){ // Loop through cells

	                    HSSFCell cell;

	                    if( row1.getCell(cellCounter ) == null ){
	                    	
	                        cell = row1.createCell(cellCounter);
	                        
	                    } else {
	                    	
	                        cell = row1.getCell(cellCounter);
	                        
	                    }

	                    data.add(cell);
	                    
	                    nokdata[cellCounter]=data.get(cellCounter);
	                    
	                }
					
					break;
					
				}

			}
			
			filein.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Object[][]{new Object[]{nokdata}};
	}
	
	@Test(groups={"functional"}, dataProvider="sendnokdata", priority=1)
	public static void receiveNOKData(Object[] nokdata) throws Exception{
		
		testNGfiles.GotoPatient.PatientPage();

		Object beforevalue[]=new Object [11]; // to store value
		
		nokelments[0]="Title";
		nokelments[1]="Surname";
		nokelments[2]="Firstname";
		nokelments[3]="Address1";
		nokelments[4]="Address2";
		nokelments[5]="Address3";
		nokelments[6]="Address4";
		nokelments[7]="Phone";
		nokelments[8]="Mobile";
		nokelments[9]="Email";
		nokelments[10]="Relationship";
		
		try {
			
			Thread.sleep(2000);
			wd.findElement(By.linkText("Next of Kin")).click();
			
			Thread.sleep(3000);
			NOK.Title(wd, nokdata[0].toString());
			beforevalue[0]=wd.findElement(By.id("inkintitle")).getAttribute("value");
			
			NOK.Firstname(wd).clear();
			NOK.Firstname(wd).sendKeys(nokdata[1].toString());
			beforevalue[1]=NOK.Firstname(wd).getAttribute("value");
			
			NOK.Surname(wd).clear();
			NOK.Surname(wd).sendKeys(nokdata[2].toString());
			beforevalue[2]=NOK.Surname(wd).getAttribute("value");
			
			NOK.Address1(wd).clear();
			NOK.Address1(wd).sendKeys(nokdata[3].toString());
			beforevalue[3]=NOK.Address1(wd).getAttribute("value");
			
			NOK.Address2(wd).clear();
			NOK.Address2(wd).sendKeys(nokdata[4].toString());
			beforevalue[4]=NOK.Address2(wd).getAttribute("value");
			
			NOK.Address3(wd).clear();
			NOK.Address3(wd).sendKeys(nokdata[5].toString());
			beforevalue[5]=NOK.Address3(wd).getAttribute("value");
			
			NOK.Address4(wd).clear();
			NOK.Address4(wd).sendKeys(nokdata[6].toString());
			beforevalue[6]=NOK.Address4(wd).getAttribute("value");
			
			NOK.Phone(wd).clear();
			NOK.Phone(wd).sendKeys(nokdata[7].toString());
			beforevalue[7]=NOK.Phone(wd).getAttribute("value");
			
			NOK.Mobile(wd).clear();
			NOK.Mobile(wd).sendKeys(nokdata[8].toString());
			beforevalue[8]=NOK.Mobile(wd).getAttribute("value");
			
			NOK.Mail(wd).clear();
			NOK.Mail(wd).sendKeys(nokdata[9].toString());
			beforevalue[9]=NOK.Mail(wd).getAttribute("value");
			
			NOK.Relation(wd).clear();
			NOK.Relation(wd).sendKeys(nokdata[10].toString());
			beforevalue[10]=NOK.Relation(wd).getAttribute("value");
			NOK.Ok_Button(wd).click();
			
			wd.findElement(By.id("cmd_submit")).click();
			wd.findElement(By.id("cmd_cancel")).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// writing values to excel file
		
		try {
			javafiles.ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 1, 5, new Object[][]{new Object[]{"NOK Addition"}});
			javafiles.ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 2, 0, new Object[][]{nokelments});
			javafiles.ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 3, 0, new Object[][]{beforevalue});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Data values cannot be written in excel file, due to "+e.getMessage());
		}
		
	}
	
	@Test(groups={"functional"}, priority=2)
	public static void editNOKDetails() throws Exception{
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object editedvalue[]=new Object [11]; // to store value
		
		try {
			
			Thread.sleep(2000);
			wd.findElement(By.linkText("Next of Kin")).click();
			
			Thread.sleep(3000);
			NOK.Title(wd, "Mrs");
			editedvalue[0]=wd.findElement(By.id("inkintitle")).getAttribute("value");
			
			NOK.Firstname(wd).sendKeys(NOK.Firstname(wd).getText()+" E");
			editedvalue[1]=NOK.Firstname(wd).getAttribute("value");
			
			NOK.Surname(wd).sendKeys(NOK.Surname(wd).getText()+" E");
			editedvalue[2]=NOK.Surname(wd).getAttribute("value");
			
			NOK.Address1(wd).sendKeys(NOK.Address1(wd).getText()+" E");
			editedvalue[3]=NOK.Address1(wd).getAttribute("value");
			
			NOK.Address2(wd).sendKeys(NOK.Address2(wd).getText()+" E");
			editedvalue[4]=NOK.Address2(wd).getAttribute("value");
			
			NOK.Address3(wd).sendKeys(NOK.Address3(wd).getText()+" E");
			editedvalue[5]=NOK.Address3(wd).getAttribute("value");
			
			NOK.Address4(wd).sendKeys(NOK.Address4(wd).getText()+" E");
			editedvalue[6]=NOK.Address4(wd).getAttribute("value");
			
			NOK.Phone(wd).sendKeys(NOK.Phone(wd).getText()+" E");
			editedvalue[7]=NOK.Phone(wd).getAttribute("value");
			
			NOK.Mobile(wd).sendKeys(NOK.Mobile(wd).getText()+" E");
			editedvalue[8]=NOK.Mobile(wd).getAttribute("value");
			
			NOK.Mail(wd).clear();
			NOK.Mail(wd).sendKeys("mahendran@imeddoc.ie");
			editedvalue[9]=NOK.Mail(wd).getAttribute("value");
			
			NOK.Relation(wd).sendKeys(NOK.Relation(wd).getText()+" E");
			editedvalue[10]=NOK.Relation(wd).getAttribute("value");
			
			NOK.Ok_Button(wd).click();
			
			wd.findElement(By.id("cmd_submit")).click();
			wd.findElement(By.id("cmd_cancel")).click();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// writing values to excel file
		
		try {
			
			javafiles.ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 6, 5, new Object[][]{new Object[]{"NOK Modification"}});
			javafiles.ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 7, 0, new Object[][]{nokelments});
			javafiles.ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "NOK", 8, 0, new Object[][]{editedvalue});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Data values cannot be written in excel file" + e.getMessage());
		}
		
	}
	
}
