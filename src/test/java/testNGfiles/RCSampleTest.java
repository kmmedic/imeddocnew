package testNGfiles;


import com.thoughtworks.selenium.*;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.tools.ant.taskdefs.Concat;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafiles.ExcelFormats;

public class RCSampleTest{

	static Logger patientlog=Logger.getLogger("Patientmenu");  // logger for each class is initialized

	//variable declarations for server, selenium
    private static Selenium selenium;
    //variable declarations for file input/output streams
    private static FileInputStream filein1,filein2, filein3;
    private static FileOutputStream fileout1, fileout2;
    //variable declarations for poi.apache excel api
    private static HSSFWorkbook workbook;
    private static HSSFSheet sheet1, sheet2;
    private static String patientid;
	private static WebDriver wd;
	public static String pusername=null;
	public static String pname=null;

	public RCSampleTest(WebDriver wd) {

		this.wd=wd;

	}

	@DataProvider(name="addPatient")
	public static Object[][] sendPatientData() throws Exception {
		
		System.out.println("inside addpatient");

		// getting excel file for data reading
		filein1 = new FileInputStream(new File(testNGfiles.FilesAndPaths.TestData()+"\\"+"TestCases.xls"));
		// Get the workbook instance for XLS file
		workbook = new HSSFWorkbook(filein1);
		sheet1 = workbook.getSheetAt(2);
		
		Object data1[] = new Object[21];  // to store values of input
		
		Row row=null;
		
			try {
				
				for ( int r1 = 1; r1 <=1; r1++) {
					
						row = sheet1.getRow(r1);
						
					for(int c1=0; c1<=20; c1++){
						
						data1[c1]=row.getCell(c1).getStringCellValue().toString();

					}
					
				}
				
				filein1.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			}
			
		return new Object[][] {new Object[]{data1}};

	}

	@Test(groups={"functional"}, dataProvider="addPatient", priority=1)
	public static void receivePatientData(Object[] data) throws Exception {
		
		String baseurl="http://demo.imeddoc.net";
				
		selenium = new WebDriverBackedSelenium(wd, baseurl);
		
		boolean rec_add=false;
				
		String data2[]=new String [19]; // to store web elements
				
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

				/*		storing patient web table column names
				 *		There is another way to check, using verifyEquals 
				 */
				
		String actualcolnames[]=new String[10];
		actualcolnames[0]="S.No";
		actualcolnames[1]="Doctor";
		actualcolnames[2]="Title";
		actualcolnames[3]="Display Name";
		actualcolnames[4]="Patient ID";
		actualcolnames[5]="Phone";
		actualcolnames[6]="Mobile";
		actualcolnames[7]="Date Of Birth";
		actualcolnames[8]="Address1";
		actualcolnames[9]="Address2";
				
		try {
						
						
			Thread.sleep(1000);
			selenium.selectFrame("iframe1");
			wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();
			Thread.sleep(1000);
								
			//		retrieving column labels found in the patient list page
								
			String expectedcolnames[]=new String[10];
								
			int td=1;
								
			for (int m=0; m<expectedcolnames.length; m++) {
				selenium.highlight("//form[@id='frm_hpreport']/table/tbody/tr[1]/td["+td+"]/div/font/strong");
				expectedcolnames[m]=selenium.getText("//form[@id='frm_hpreport']/table/tbody/tr[1]/td["+td+"]/div/font/strong");
				System.out.print("The expected column(s): "+expectedcolnames[m].toString()+", ");
				td++;
										
			}
								
			//comparing actual column names with expected column names
			for(int j=0; j<expectedcolnames.length; j++) {
				if(!expectedcolnames[j].toString().equals(actualcolnames[j].toString())) {
					System.out.println("The expected column name "+expectedcolnames+" does not match with actual column name.");
				}
			}
								
			System.out.println("Comparing column names successfull...");
								
			selenium.highlight("//table[2]/tbody/tr[1]/td[2]/strong/font");
								
			// checking 'Next' and 'Previous' link navigation
			if (selenium.isElementPresent("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b")) {

				selenium.highlight("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b");
				wd.findElement(By.xpath("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b")).click();
				Thread.sleep(1000);
				selenium.highlight("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[1]/a/font/b");
				wd.findElement(By.xpath("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[1]/a/font/b")).click();
				Thread.sleep(1000);
									
			}
								
			// checking presence of action buttons - with verifyEquals
			selenium.highlight("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[2]/a/font");
			selenium.highlight("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font");
			selenium.highlight("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[6]/a/font");
								
			// adding patient details
			selenium.highlight("//select[@id='in_fld_srch']");
			wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[2]/a/font")).click();   // click on add link
								
			new Select(wd.findElement(By.id("intitle"))).selectByVisibleText(data[0].toString());
								
			wd.findElement(By.id("insurname")).sendKeys(data[1].toString()); // surname
			wd.findElement(By.id("inname")).sendKeys(data[2].toString());
			wd.findElement(By.id("inaddr1")).sendKeys(data[3].toString());
			wd.findElement(By.id("inaddr2")).sendKeys(data[4].toString());
			wd.findElement(By.id("inaddr3")).sendKeys(data[5].toString());
			wd.findElement(By.id("inaddr4")).sendKeys(data[6].toString());
			wd.findElement(By.id("but_consult")).click();
			wd.switchTo().frame("ifrmdocuments");
			wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(data[7].toString());
			wd.findElement(By.xpath("//*[@id='img_search']")).click();   
								
			if (AlertPresent()) {
						
				Alert alert1=wd.switchTo().alert();
				alert1.dismiss();
									
			}else if(wd.findElement(By.xpath("//*[@id='ifrmdocuments']")).isDisplayed()){
									
				  if(wd.findElement(By.xpath("//*[@value='"+data[7].toString()+"']")).isDisplayed()){
										
					wd.findElement(By.xpath("//*[@value='"+data[7].toString()+"']")).click();
					wd.findElement(By.id("but_cancel2")).click();

				  }
									
			}

			wd.switchTo().defaultContent();
			wd.switchTo().frame("iframe1");
								
			String str=data[13].toString();
								
			for (String retval: str.split("-")) {
									
				wd.findElement(By.id("indob")).sendKeys(retval);

			}

			wd.findElement(By.id("inphone")).click();
			
			Thread.sleep(2000);
			
			if (AlertPresent()) {
									
				Alert alert2=wd.switchTo().alert();
				alert2.dismiss();
				Thread.sleep(2000);
				if(wd.findElement(By.xpath("//form[@id='frm_hp']"))!=null) {  // if it is inside patient details page. 
					Thread.sleep(2000);
					
					pname=wd.findElement(By.id("insurname2")).getAttribute("value");

					wd.findElement(By.id("cmd_cancel")).click(); // then cancel
				}
									
			} else {

			Thread.sleep(2000);
			wd.findElement(By.id("inphone")).sendKeys(data[11].toString());
			Thread.sleep(2000);
			wd.findElement(By.id("inmobile")).click();
			wd.findElement(By.id("inmobile")).sendKeys(data[12].toString());
			Thread.sleep(1000);
			wd.findElement(By.id("occupation")).sendKeys(data[14].toString());
			Thread.sleep(1000);
			wd.findElement(By.id("inemail")).sendKeys(data[15].toString());
			Thread.sleep(1000);			
			new Select(wd.findElement(By.id("maritial_status"))).selectByVisibleText(data[16].toString());
			Thread.sleep(1000);
			wd.findElement(By.id("religion")).sendKeys(data[17].toString());
			Thread.sleep(1000);
			new Select(wd.findElement(By.id("inpatienttype"))).selectByVisibleText(data[18].toString());
			Thread.sleep(1000);
			wd.findElement(By.id("incase")).sendKeys(data[19].toString());
			Thread.sleep(1000);
			wd.findElement(By.id("cmd_submit")).click();
			Thread.sleep(2000);

			pname=data[1].toString()+" "+data[2].toString();

			patientid=wd.findElement(By.xpath("//table/tbody/tr[3]/td/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td[1]/strong[3]/strong/strong/font")).getText();
			
			wd.findElement(By.id("cmd_cancel")).click();
			Thread.sleep(2000);
			
			rec_add=true;
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		patientlog.info("Test data being written to Testreport.xls...");
					
		// opening input file stream for writing values to 'Patient Data'  
		filein2=new FileInputStream(new File(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls"));
		HSSFWorkbook wb=new HSSFWorkbook(filein2);
		sheet2=wb.getSheet("Patient Data");

		// writing column headings and cell values
		if (sheet2.getSheetName().equals("Patient Data") & sheet2.getFirstRowNum() == 0) {

			for (int n = 0; n<=1; n++  ) {
				Row row1 = sheet2.createRow(n);

				//writing cell heading
				for (int p = 0; p<=18; p++) {
					// Create a new cell
					Cell cell = row1.createCell(p);
					cell.setCellValue(data2[p].toString());
					cell.setCellStyle(new ExcelFormats().setFontBold(wb));
					sheet2.setDefaultColumnWidth(30);
				}
									
			}

			//writing values to cell
			for (int q = 1; q<=1; q++  ) {
				Row row1 = sheet2.createRow(q);

				for (int r = 0; r<=18; r++) {
										
					Cell cell = row1.createCell(r);
					cell.setCellValue(data[r].toString());
					cell.setCellStyle(new ExcelFormats().setFontNormal(wb));
					sheet2.setDefaultColumnWidth(30);
										
				}
									
			}

		}
							
		filein2.close();
		fileout2 = new FileOutputStream(new File(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls"));
		wb.write(fileout2);
		fileout2.close();
					
		String searchkeys1[]=new String[11];
		searchkeys1[0]="Display Name";
		searchkeys1[1]="Title";
		searchkeys1[2]="Patient ID";
		searchkeys1[3]="MRN";
		searchkeys1[4]="Phone";
		searchkeys1[5]="Mobile";
		searchkeys1[6]="Date Of Birth";
		searchkeys1[7]="Address1";
		searchkeys1[8]="Address2";
		searchkeys1[9]="Shared Doctor";
		searchkeys1[10]="Patient Type";
							
		String searchval[]=new String[11];
		searchval[0]=data[1].toString()+" "+data[2].toString();
		searchval[1]=data[0].toString();
		System.out.println(searchval[1].toString());
							
							
		if (rec_add){
			searchval[2]=patientid;
		}else{
			searchval[2]="21780";
		}
							
		searchval[3]="2556";
		searchval[4]=data[11].toString();
		searchval[5]=data[12].toString();
		searchval[6]=data[13].toString();
		searchval[7]=data[3].toString();
		searchval[8]=data[4].toString();
		searchval[9]="Dr John Doe";
		searchval[10]=data[18].toString();
							
		try {
								
			outer:
			for (int key=0; key<=10; key++) {  // loop for search values
									
				System.out.println("inside search loop");
				Thread.sleep(2000);
				new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText(searchkeys1[key].toString());
				Thread.sleep(2000);
				String searchtext=new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).getFirstSelectedOption().getText().toString();
				wd.findElement(By.xpath("//*[@id='insearch']")).clear();
				wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(searchval[key].toString());
				Thread.sleep(2000);
				selenium.highlight("//*[@id='img_search']");
				wd.findElement(By.xpath("//*[@id='img_search']")).click();
									
				Thread.sleep(2000);

				if(isElementPresent(By.xpath("//form[@id='frm_search']"))) {
										
					System.out.println("inside if element found condition");
										
					System.out.println("inside inner loop");
												
					switch(key)	{
											
						case 0: searchtext = "Display Name";
											
							System.out.println(searchval[2].toString());
												
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
													           
											if (pid.equals(searchval[2].toString())) {
													        	   
												wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr["+tr+"]/td[4]/div")).click();   // click the record
												Thread.sleep(2000);
												wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font")).click();  // click on open
												Thread.sleep(2000);
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
									tr--;
									continue pageloop;
								}
												    	  	
							 }	

						  } // end of all rows loop
												
												
					} // ending switch here
										
				}else if(wd.findElement(By.xpath("//form[@id='frm_hp']"))!=null) {  // if it is inside patient details page. 
					Thread.sleep(2000);
					wd.findElement(By.id("cmd_cancel")).click(); // then cancel
											
				}
				
				continue outer;

			} // end of outer loop

			// checking goto page functionality
			for (int swap=1; swap<=3; swap++) {  // forward loop
				wd.findElement(By.id("gotopage")).clear();
				wd.findElement(By.id("gotopage")).sendKeys(String.valueOf(swap));
				wd.findElement(By.xpath("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[5]/table/tbody/tr/td/div[2]/font/input")).click();
				Thread.sleep(2000);
			}

			// checking A.B.C...links
			for(int swap=1; swap<=27; swap++) {
				selenium.highlight("//form[@id='frm_search']/table[2]/tbody/tr/td[1]/font/a["+swap+"]");
				wd.findElement(By.xpath("//form[@id='frm_search']/table[2]/tbody/tr/td[1]/font/a["+swap+"]")).click();
				Thread.sleep(2000);
			}
									
			patientlog.info("add patient test case completed successfully...");
								
			if(rec_add) {
				
				pusername=patientid;
				
			}else {
				
				pusername="21755";
				
			}
								
			System.out.println("Patient ID : ".concat(pusername).concat(" Patient Name : ").concat(pname));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		return new Object[][] {new Object[]{pusername}};

	}
	
	@Test(groups={"functional"}, priority=2)
	public static void editPatient() throws Exception{
		
		System.out.println("Inside editpatient method "+pusername.toString());
		
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
		Object edValues[]=new Object[19]; // to store field values
		
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
				
				
				new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText("Patient ID");
				wd.findElement(By.xpath("//*[@id='insearch']")).clear();
				wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(pusername.toString());
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
											           
									if (pid.equals(pusername.toString())) {
											        	   
										wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr["+tr+"]/td[4]/div")).click();   // click the record
										Thread.sleep(2000);
										wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font")).click();  // click on open
										Thread.sleep(2000);
										wd.findElement(By.id("cmd_cancel")).click(); // then cancel
//										continue outer;

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
				
				
				// storing values of edit page fields into an array
				
				edValues[0]=new Select (wd.findElement(By.id("intitle"))).getFirstSelectedOption().getAttribute("value");
				edValues[1]=wd.findElement(By.id("insurname")).getAttribute("value");
				edValues[2]=wd.findElement(By.id("inname")).getAttribute("value");
				edValues[3]=wd.findElement(By.id("inaddr1")).getAttribute("value");
				edValues[4]=wd.findElement(By.id("inaddr2")).getAttribute("value");
				edValues[5]=wd.findElement(By.id("inaddr3")).getAttribute("value");
				edValues[6]=wd.findElement(By.id("inaddr4")).getAttribute("value");
				edValues[7]="";
				edValues[8]="";  //selenium.getSelectedLabel("id=refdate_validity");
				edValues[9]="";  //selenium.getSelectedLabel("id=concession_card");
				edValues[10]=""; //selenium.getValue("id=concession_card_no");
				edValues[11]=wd.findElement(By.id("inphone")).getAttribute("value");
				edValues[12]=wd.findElement(By.id("inmobile")).getAttribute("value");
				edValues[13]=wd.findElement(By.id("occupation")).getAttribute("value");
				edValues[14]=wd.findElement(By.id("inemail")).getAttribute("value");
				edValues[15]=new Select (wd.findElement(By.id("maritial_status"))).getFirstSelectedOption().getText();
				edValues[16]=wd.findElement(By.id("religion")).getAttribute("value");
				edValues[17]=new Select (wd.findElement(By.id("inpatienttype"))).getFirstSelectedOption().getText();
				edValues[18]=wd.findElement(By.id("incase")).getAttribute("value");

				
				wd.findElement(By.id("cmd_cancel")).click();
					
/*				Thread.sleep(1000);
				System.out.print("second frame from editpatient, about to select");
				selenium.selectFrame("iframe1");
				wd.findElement(By.xpath("//*[@id='tabsJ']/ul/li[1]/a/span")).click();
*/				
				// the following code to view the edited patient
				
				new Select(wd.findElement(By.xpath("//*[@id='in_fld_srch']"))).selectByVisibleText("Patient ID");
				wd.findElement(By.xpath("//*[@id='insearch']")).clear();
				wd.findElement(By.xpath("//*[@id='insearch']")).sendKeys(pusername.toString());
				Thread.sleep(2000);
				wd.findElement(By.xpath("//*[@id='img_search']")).click();
					
				if(isElementPresent(By.xpath("//form[@id='frm_search']"))){   // if this is a patient list page
					
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
											           
									if (pid.equals(pusername.toString())) {
											        	   
										wd.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr["+tr+"]/td[4]/div")).click();   // click the record
										Thread.sleep(2000);
										wd.findElement(By.xpath("//*[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font")).click();  // click on open
										Thread.sleep(2000);
										wd.findElement(By.id("cmd_cancel")).click(); // then cancel
//										continue outer;

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
				
				wd.findElement(By.id("cmd_cancel")).click(); // if it is in details page then click cancel
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
				sheet2=wb.getSheet("Patient Data");
					
				// writing column headings and cell values
				if (sheet2.getSheetName().equals("Patient Data")) {

					// writing row heading
					for(int g=4; g<=4; g++){
						Row row1 = sheet2.createRow(g);
						for(int h=5; h<=5; h++){
							Cell cell=row1.createCell(h);
							cell.setCellValue("Modified Data");
							cell.setCellStyle(new ExcelFormats().setFontBold(wb));
						}
					}
					
					for (int i = 5; i<=5; i++  ) {		// writing at the 5th row
						Row row1 = sheet2.createRow(i);

						//writing cell heading
						for (int j = 0; j<=18; j++) {
							// Create a new cell
							Cell cell = row1.createCell(j);
							if(data2[j].isEmpty()){
								cell.setCellValue("null");
							}else{
								cell.setCellValue(data2[j].toString());
							}
							cell.setCellStyle(new ExcelFormats().setFontBold(wb));
						}
								
					}

					//writing values to cell
					for (int i = 6; i<=6; i++  ) {		// writing at the 6th row
						Row row1 = sheet2.createRow(i);

						for (int j = 0; j<=18; j++) {
							System.out.println("Array Position is: "+data2[j]+" "+edValues[j]);
							if (edValues[j]!="" || edValues[j]!=null){
								Cell cell = row1.createCell(j);
								try {
									if (edValues[j]==null){
										cell.setCellValue("null");
									}else{
										cell.setCellValue(edValues[j].toString());
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									throw new NullPointerException("edValue array is empty" + e);
								}
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
