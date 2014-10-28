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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.PatientGP;

public class PatientGPAddition {
	
	private static WebDriver wd;
	private static HSSFWorkbook wb;
	private static HSSFSheet ws;
	
	public PatientGPAddition(WebDriver wd) {
		
		this.wd=wd;
		
	}
	
	
	@DataProvider(name = "SendGpData")
	public static Object[][] SendGpData() throws Exception {

		Object diagnosis[][] = null;

		try {

			FileInputStream filein = new FileInputStream(new File(
					testNGfiles.FilesAndPaths.TestData().getAbsolutePath()
							+ "\\" + "TestCases.xls"));

			wb = new HSSFWorkbook(filein);
			ws = wb.getSheet("GPDetails");

			Iterator<Row> rowIterator0 = ws.iterator();

			diagnosis = new Object[1][19];	//

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
	
	@Test(groups={"functional"}, dataProvider="SendGpData")
	public static void ReceiveGpData(Object[][] data) throws Exception {
		
		testNGfiles.GotoPatient.PatientPage();
		
		Object[] headers=new Object[20];
		Object[] values=new Object[20];

		headers[0]="GP Type";
		headers[1]="GP Title";
		headers[2]="GP Surname";
		headers[3]="GP Firstname";
		headers[4]="Displayname ";
		headers[5]="Entity Name";
		headers[6]="Address1";
		headers[7]="Address2";
		headers[8]="Address3";
		headers[9]="Address4";
		headers[10]="W_Code";
		headers[11]="WorkPhone";
		headers[12]="H_Code";
		headers[13]="HomePhone";
		headers[14]="M_Code";
		headers[15]="MobileNo.";
		headers[16]="Email";
		headers[17]="Website";
		headers[18]="Fax No.";
		headers[19]="Pass/Fail";

		try {
			
			PatientGP.GP_AddIcon(wd).click();

			wd.switchTo().frame("ifrmdocuments");
			
			PatientGP.GP_Type(wd, data[0][0].toString());
			values[0]=new Select(wd.findElement(By.id("intype"))).getFirstSelectedOption().getText();
			
			PatientGP.Title(wd,data[0][1].toString());
			values[1]=new Select(wd.findElement(By.id("intitle"))).getFirstSelectedOption().getText();
			
			PatientGP.Surname(wd).sendKeys(data[0][2].toString());
			PatientGP.Firstname(wd).sendKeys(data[0][3].toString());
			PatientGP.Entity(wd).sendKeys(data[0][5].toString());
			PatientGP.Address1(wd).sendKeys(data[0][6].toString());
			PatientGP.Address2(wd).sendKeys(data[0][7].toString());
			PatientGP.Address3(wd).sendKeys(data[0][8].toString());
			PatientGP.Address4(wd).sendKeys(data[0][9].toString());
			PatientGP.WorkCountryCode(wd).sendKeys(data[0][10].toString());
			PatientGP.WorkPhone(wd).sendKeys(data[0][11].toString());
			PatientGP.HomeCountryCode(wd).sendKeys(data[0][12].toString());
			PatientGP.HomePhone(wd).sendKeys(data[0][13].toString());
			PatientGP.MobileCountryCode(wd).sendKeys(data[0][14].toString());
			PatientGP.MobileNumber(wd).sendKeys(data[0][15].toString());
			PatientGP.Email(wd).sendKeys(data[0][16].toString());
			PatientGP.Web(wd).sendKeys(data[0][17].toString());
			PatientGP.Fax(wd).sendKeys(data[0][18].toString());
			
			/*
			 * A new approach to write input box values to an array
			 *  
			 */
			
			List<WebElement> getValues=wd.findElements(By.tagName("input"));
			

			int i = 2;
			
			for(WebElement inputbox : getValues){
				
				if(inputbox.getAttribute("type").equals("text")){
						
					values[i]=inputbox.getAttribute("value");
					
				}
				
				i++;
			}
			
			PatientGP.Ok_Button(wd).click();
			
			wd.switchTo().defaultContent();
			
			wd.switchTo().frame("iframe1");
			
			wd.findElement(By.id("cmd_submit")).click();
			
			Thread.sleep(2000);
			
			wd.findElement(By.id("cmd_cancel")).click();
			
			Thread.sleep(2000);
			
			wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[2]/a/span")).click();
			
			new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).getFirstSelectedOption();
			wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(data[0][4].toString());
			wd.findElement(By.xpath("//*[@id='img_search']")).click();

			String contact=null;
			
			if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr[2]/td[3]/div"))!=null){

				for (int j=2; j<=14; j++ ){

					wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+j+"]/td[3]/div")).click();

					contact=wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+j+"]/td[3]/div")).getText();
					
					if (contact.equals(data[0][4].toString())){
						
						System.out.println("match found");
						
						JavascriptExecutor je=(JavascriptExecutor)wd;
						je.executeScript("alert('Contact name found');");
						Thread.sleep(3000);

					}
					
				}
				
			}
			
			values[19]="Pass";
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "GPDetails", 0, 0, new Object[][]{ new Object[]{"GP Addition from Patient Details Page"}});
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "GPDetails", 1, 0, new Object[][]{headers});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "GPDetails", 2, 0, new Object[][]{values});
		
		} catch(Exception e) {
			
			System.out.println("Test fails ".concat(e.getMessage())); 
			
		}
		

	}

}
