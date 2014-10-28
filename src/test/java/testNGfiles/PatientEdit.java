package testNGfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafiles.ExcelFormats;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Selenium;

public class PatientEdit{

	private static Logger patientlog=Logger.getLogger("Patientmenu");  // logger for each class is initialized
	private static WebDriver wd;
	private static Selenium selenium;
	private static FileInputStream filein1, filein2, filein3;
	private static FileOutputStream fileout2;
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet1, sheet2;
	
	public PatientEdit(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	@Test(dataProvider="sendpatientid", priority=1, dataProviderClass=testNGfiles.RCSampleTest.class)
	public static void editPatient(Object[] patientid) throws Exception{

		System.out.println("Inside editpatient method "+patientid[0].toString());
		
		PageFactory.initElements(wd, testNGfiles.PatientEdit.class);
		
    	patientlog.info("---------------------------------------------------------------------------------------------");
    	patientlog.info("**************** Test "+Thread.currentThread().getStackTrace()[1].getMethodName()+" [ Date Stamp :"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" ] ****************");
    	patientlog.info("---------------------------------------------------------------------------------------------");

    	patientlog.info("Patient Edit Test started executing...");
    	
    	patientlog.info("Opening fileinputstream with Testcases.xls...");
    	
		// getting excel file for data reading
		filein1 = new FileInputStream(new File(testNGfiles.FilesAndPaths.TestData()+"\\"+"TestCases.xls"));
		// Get the workbook instance for XLS file
		workbook = new HSSFWorkbook(filein1);
		sheet1 = workbook.getSheetAt(2);

    	
		String data2[]=new String[19]; // to store web elements
		String edValues[]=new String[19];
		data2[0]="intitle";
		data2[1]="insurname";
		data2[2]="inname";
		data2[3]="inaddr1";
		data2[4]="inaddr2";
		data2[5]="inaddr3";
		data2[6]="inaddr4";
		data2[7]="GP";
		data2[8]="refdate_validity";
		data2[9]="concession_card";
		data2[10]="concession_card_no";
		data2[11]="inphone";
		data2[12]="inmobile";
		data2[13]="occupation";
		data2[14]="inemail";
		data2[15]="maritial_status";
		data2[16]="religion";
		data2[17]="inpatienttype";
		data2[18]="incase";
		
		int r1;
		
		try {
			
			outer:
			for ( r1 = 1; r1 <=1; r1++) {
				
				System.out.print("First frame from editpatient");
					
				new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText("Patient ID");
				Thread.sleep(2000);
				wd.findElement(By.xpath("//*[@id='insearch']")).clear();
				Thread.sleep(6000);
				wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(patientid[0].toString());
				Thread.sleep(2000);
				wd.findElement(By.xpath("//*[@id='img_search']")).click();

				if(isElementPresent(By.xpath("//form[@id='frm_search']"))){
							
					int rowCount = selenium.getXpathCount("//*[@id='frm_hpreport']/table/tbody/tr").intValue();
					
					System.out.println(rowCount);
										
					pageloop:
					for(int tr=2; tr<=rowCount; tr++){	// this loop to iterate through all rows
											
						WebElement patient_webtable = wd.findElement(By.className("tableBorder"));
						List<WebElement> total_rows = patient_webtable.findElements(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]"));
						
						if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]"))!=null){
										  	
							for(WebElement rowElement : total_rows) { // iterate each row
										  		
								wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]")).click();
										  		
								List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
										  		
								int ColumnIndex=1;
										  		
								for(WebElement colElement: TotalColumnCount) {    // loop for column iteration

									String pid=colElement.getText();
											           
									if (pid.equals(patientid[0].toString())) {
											        	   
										wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr["+tr+"]/td[4]/div")).click();   // click the record
										Thread.sleep(2000);
										wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font")).click();  // click on open
										Thread.sleep(6000);
										wd.findElement(By.id("cmd_cancel")).click(); // then cancel
										continue outer;

									}
											           
									ColumnIndex=ColumnIndex+1;
								}

						    }  // end of a single row loop
										  	
					   } // if end bracket
										  	
					  if (tr==rowCount-1) {  // -1 in total no.of rows 
										    	  
						tr++;  // incremented one row
						if(isElementPresent(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b"))) {
							
							selenium.highlight("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b");
							wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b")).click();
							Thread.sleep(2000);
							tr=2;
							continue pageloop;
						}
										    	  	
					 }	

				  } // end of all rows loop
					
				 }
					
				new Select(wd.findElement(By.id("intitle"))).selectByVisibleText("Mrs");
				wd.findElement(By.id("insurname")).sendKeys(wd.findElement(By.id("insurname")).getText()+" E");
				wd.findElement(By.id("inname")).sendKeys(wd.findElement(By.id("inname")).getText()+" E");
				wd.findElement(By.id("inaddr1")).sendKeys(wd.findElement(By.id("inaddr1")).getText()+" E");
				wd.findElement(By.id("inaddr2")).sendKeys(wd.findElement(By.id("inaddr2")).getText()+" E");
				wd.findElement(By.id("inaddr3")).sendKeys(wd.findElement(By.id("inaddr2")).getText()+" E");
				wd.findElement(By.id("inaddr4")).sendKeys(wd.findElement(By.id("inaddr4")).getText()+" E");
				wd.findElement(By.xpath("//div[@id='divPhone1']/img")).click();
				wd.findElement(By.id("inphone")).sendKeys(wd.findElement(By.id("inphone")).getText()+" E");
				wd.findElement(By.xpath("//div[@id='divmobile1']/img")).click();
				wd.findElement(By.id("inmobile")).click();
				wd.findElement(By.id("inmobile")).sendKeys(wd.findElement(By.id("inmobile")).getText()+" E");
				wd.findElement(By.id("occupation")).sendKeys(wd.findElement(By.id("occupation")).getText()+ " E");
				wd.findElement(By.id("inemail")).sendKeys(wd.findElement(By.id("inemail")).getText()+".mahe");
				new Select(wd.findElement(By.id("maritial_status"))).selectByVisibleText("separated");
				wd.findElement(By.id("religion")).sendKeys(wd.findElement(By.id("religion")).getText()+" E");
				new Select(wd.findElement(By.id("inpatienttype"))).selectByVisibleText("POST OPERATIONAL");
				wd.findElement(By.id("incase")).sendKeys("23423423 caseno.");
				wd.findElement(By.id("cmd_submit")).click();
				wd.findElement(By.id("cmd_cancel")).click();
					
/*				Thread.sleep(1000);
				System.out.print("second frame from editpatient, about to select");
				selenium.selectFrame("iframe1");
				wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();
*/				
				// the following code to view the edited patient
				Thread.sleep(1000);
				new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText("Patient ID");
				Thread.sleep(2000);
				wd.findElement(By.xpath("//*[@id='insearch']")).clear();
				Thread.sleep(6000);
				wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(patientid[0].toString());
				Thread.sleep(2000);
				wd.findElement(By.xpath("//*[@id='img_search']")).click();
					
				if(isElementPresent(By.xpath("//form[@id='frm_search']"))){
					
					int rowCount = selenium.getXpathCount("//*[@id='frm_hpreport']/table/tbody/tr").intValue();
					
					System.out.println(rowCount);
										
					pageloop:
					for(int tr=2; tr<=rowCount; tr++){	// this loop to iterate through all rows
											
						WebElement patient_webtable = wd.findElement(By.className("tableBorder"));
						List<WebElement> total_rows = patient_webtable.findElements(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]"));
						
						if(wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]"))!=null){
										  	
							for(WebElement rowElement : total_rows) { // iterate each row
										  		
								wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]")).click();
										  		
								List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
										  		
								int ColumnIndex=1;
										  		
								for(WebElement colElement: TotalColumnCount) {    // loop for column iteration

									String pid=colElement.getText();
											           
									if (pid.equals(patientid[0].toString())) {
											        	   
										wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr["+tr+"]/td[4]/div")).click();   // click the record
										Thread.sleep(2000);
										wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font")).click();  // click on open
										Thread.sleep(6000);
										wd.findElement(By.id("cmd_cancel")).click(); // then cancel
										continue outer;

									}
											           
									ColumnIndex=ColumnIndex+1;
								}

						    }  // end of a single row loop
										  	
					   } // if end bracket
										  	
					  if (tr==rowCount-1) {  // -1 in total no.of rows 
										    	  
						tr++;  // incremented one row
						if(isElementPresent(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b"))) {
							
							selenium.highlight("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b");
							wd.findElement(By.xpath("//*[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td/table/tbody/tr/td[2]/a/font/b")).click();
							Thread.sleep(2000);
							tr=2;
							continue pageloop;
						}
										    	  	
					 }	

				  } // end of all rows loop
					
				 }
					
				edValues[0]=new Select (wd.findElement(By.id("intitle"))).getFirstSelectedOption().getText();
				edValues[1]=wd.findElement(By.id("insurname")).getText();
				edValues[2]=wd.findElement(By.id("inname")).getText();
				edValues[3]=wd.findElement(By.id("inaddr1")).getText();
				edValues[4]=wd.findElement(By.id("inaddr2")).getText();
				edValues[5]=wd.findElement(By.id("id=inaddr3")).getText();
				edValues[6]=wd.findElement(By.id("id=inaddr4")).getText();
				edValues[7]="";
				edValues[8]="";  //selenium.getSelectedLabel("id=refdate_validity");
				edValues[9]="";  //selenium.getSelectedLabel("id=concession_card");
				edValues[10]=""; //selenium.getValue("id=concession_card_no");
				edValues[11]=wd.findElement(By.id("inphone")).getText();
				edValues[12]=wd.findElement(By.id("inmobile")).getText();
				edValues[13]=wd.findElement(By.id("occupation")).getText();
				edValues[14]=wd.findElement(By.id("inemail")).getText();
				edValues[15]=new Select (wd.findElement(By.id("maritial_status"))).getFirstSelectedOption().getText();
				edValues[16]=wd.findElement(By.id("religion")).getText();
				edValues[17]=new Select (wd.findElement(By.id("inpatienttype"))).getFirstSelectedOption().getText();
				edValues[18]=wd.findElement(By.id("incase")).getText();
/*					
				//writing values to array object
				for(int c1=0; c1<=18; c1++){
					
					data1[r1][c1]=row.getCell(c1).getStringCellValue().toString();
						
				}
*/
				patientlog.info("Test data being written to Testreport.xls...");

				// opening input file stream for writing values to 'Patient Data'  
				filein3=new FileInputStream(new File(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls"));
				HSSFWorkbook wb=new HSSFWorkbook(filein3);
				sheet2=wb.getSheet("Patient Edit");
					
				// writing column headings and cell values
				if (sheet2.getSheetName().equals("Patient Edit") & sheet2.getFirstRowNum() == 0) {

					for (int i = 0; i<=1; i++  ) {
						Row row1 = sheet2.createRow(i);

						//writing cell heading
						for (int j = 0; j<=data2.length; j++) {
							// Create a new cell
							Cell cell = row1.createCell(j);
							cell.setCellValue(data2[j].toString());
							cell.setCellStyle(new ExcelFormats().setFontBold(wb));
						}
								
					}

					//writing values to cell
					for (int i = r1; i<=r1; i++  ) {
						Row row1 = sheet2.createRow(i);

						for (int j = 0; j<=edValues.length; j++) {
							System.out.println("Array Position is: "+data2[j]+" "+edValues[j]);
							if (edValues[j]!="" || edValues[j]!=null){
								Cell cell = row1.createCell(j);
								cell.setCellValue(edValues[j].toString());
								cell.setCellStyle(new ExcelFormats().setFontNormal(wb));
							}
						}
								
					}

				}

				fileout2 = new FileOutputStream(new File(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls"));
				wb.write(fileout2);
						
				filein2.close();
				filein3.close();
				fileout2.close();
			}
					
				filein1.close();
				patientlog.info("edit patient test case completed successfully...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			patientlog.info("Error in edit patient test case...", e);

		}

	}

	@Test(priority=2)
	public static void stopServer() throws Exception {

		try {
			selenium.stop();
			selenium.close();
			wd.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean AlertPresent() {
		  try {
		   wd.switchTo().alert();
		   return true;
		  } catch (NoAlertPresentException ex) {
		   return false;
		  }
	}
	
	private static boolean isElementPresent(By by) {
	    try {
	      wd.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

}
