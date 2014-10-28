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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AddToWaitingList;

public class AddToWaiting {

	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public AddToWaiting(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	@DataProvider(name = "sendData")
	public static Object[][] sendData() throws Exception {

		Object waitinglist[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("AddtoWaitingList");

			Iterator<Row> rowIterator0 = ws.iterator();

			waitinglist = new Object[2][6];

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

						waitinglist[rowcounter][cellCounter] = String.valueOf(data.get(cellCounter));

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

	
	@Test(groups={"functional"}, dataProvider = "sendData")
	public static void receiveData(Object[][] data) throws Exception {

		testNGfiles.GotoPatient.PatientPage();
		
		String pname=null;
		
		Object[] header=new Object[6];
		Object[] values=new Object[6];
		
		header[0]="Patient Name";
		header[1]="Waiting From";
		header[2]="Waiting for";
		header[3]="Priority";
		header[4]="Notes";
		header[5]="ProCode";
		
		Thread.sleep(2000);
		AddToWaitingList.Addto_Link(wd).click();
		Thread.sleep(2000);
		
		wd.switchTo().frame("ifrmdocuments");

		pname=wd.findElement(By.id("inPatient")).getAttribute("value");
		values[0]=pname;
		values[1]=AddToWaitingList.WaitingFrom(wd).getAttribute("value");
		
		if (data[0][1].toString().equalsIgnoreCase("Surgery")) {

			// if surgery
			
			AddToWaitingList.WaitingFor(wd, data[0][1].toString());
			values[2]=new Select(wd.findElement(By.id("waiting"))).getFirstSelectedOption().getText();

			AddToWaitingList.Surgery_icon(wd).click();
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			AddToWaitingList.Proc_select(wd);
			AddToWaitingList.txtCode(wd).sendKeys(data[0][5].toString());
			values[5]=AddToWaitingList.txtCode(wd).getAttribute("value");
			AddToWaitingList.Search(wd).click();
			Thread.sleep(2000);
			wd.switchTo().frame("ifrm_procedure");
			Thread.sleep(2000);
			AddToWaitingList.RecSelect(wd).click();
			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
			Thread.sleep(2000);
			AddToWaitingList.Proc_OkButton(wd).click();
			Thread.sleep(2000);

		} else {

			// if appointment

			AddToWaitingList.WaitingFor(wd, data[0][1].toString());
			AddToWaitingList.AppType(wd, data[0][2].toString());
//			values[1]=new Select(wd.findElement(By.id("indescription"))).getFirstSelectedOption().getText();

		}

		wd.switchTo().frame("ifrmdocuments");

		AddToWaitingList.Priority(wd, data[0][3].toString());
		values[3]=new Select(wd.findElement(By.id("priority"))).getFirstSelectedOption().getText();
		AddToWaitingList.Notes(wd).sendKeys(data[0][4].toString());
		values[4]=AddToWaitingList.Notes(wd).getAttribute("value");

		AddToWaitingList.Ok_Button(wd).click();
		
/*
		if (wd.switchTo().alert() != null) {

			wd.switchTo().alert().accept();

			AddToWaitingList.Proc_CancelButton(wd).click();

		}
*/
		wd.switchTo().defaultContent();
		Thread.sleep(2000);
		wd.switchTo().frame("iframe1");

		wd.findElement(By.id("cmd_submit")).click();
		Thread.sleep(2000);
		wd.findElement(By.id("cmd_cancel")).click();
		Thread.sleep(2000);
		
		
		wd.switchTo().defaultContent();
		wd.switchTo().frame("iframe1");
		Thread.sleep(2000);
		wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[4]/a/span")).click();
		
		if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[2]/div"))!=null){
			
			if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[2]/div")).getText().equals(pname)){
				
				wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[2]/div")).click();
				
				JavascriptExecutor je=(JavascriptExecutor)wd;
				je.executeScript(" alert('Patient name found');");
				Thread.sleep(4000);
				wd.switchTo().alert().accept();
				
			}
			
		}

		wd.switchTo().defaultContent();
		wd.switchTo().frame("iframe1");
		Thread.sleep(2000);
		wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();
		
		try {
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "AddtoWaitingList", 0, 0, new Object[][]{new Object[]{"Add to WaitingList"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "AddtoWaitingList", 1, 0, new Object[][]{header});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "AddtoWaitingList", 2, 0,  new Object[][]{values});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
