package testNGfiles;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;


public class PatientModify extends SeleneseTestBase{

	
	static Logger patientlog=Logger.getLogger("Patientmenu");  // logger for each class is initialized

	//variable declarations for server, selenium
    private static Selenium selenium;
    private static String xlarray[][]=new String[20][20]; 
    private static String paramUsername, patientid;
    private static String pusername=null, pid=null;
    
    //variable declarations for file input/output streams
    private static FileInputStream filein1,filein2, filein3 ;
    private static FileOutputStream fileout1, fileout2, fileout3;
    
    //variable declarations for poi.apache excel api
    private static HSSFWorkbook workbook;
    private static HSSFSheet sheet1, sheet2, sheet3;
    
    //		starting services
    //    	@Parameters({"browser","node1url"})
    
	private static WebDriver wd;
	
	public PatientModify(WebDriver wd){
		
		this.wd=wd;
		
	}
	
	public static void editPatient(String ptname, String pid) throws Exception{

    	patientlog.info("---------------------------------------------------------------------------------------------");
    	patientlog.info("**************** Test "+Thread.currentThread().getStackTrace()[1].getMethodName()+" [ Date Stamp :"+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+" ] ****************");
    	patientlog.info("---------------------------------------------------------------------------------------------");

    	patientlog.info("Test started executing...");
    	
    	patientlog.info("Opening fileinputstream with Testcases.xls...");
    	
		// getting excel file for data reading
		filein1 = new FileInputStream(new File(
				"C:/Users/kmmuser/imeddocnew/imeddocnew/testdata/TestCases.xls"));
		// Get the workbook instance for XLS file
		workbook = new HSSFWorkbook(filein1);
		sheet1 = workbook.getSheetAt(2);
		
		Object data1[][] = new Object[sheet1.getLastRowNum() + 1][21];  // to store values of input
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
		
		int r1, lastrow=sheet1.getLastRowNum();
		
		Row row=null;
		
		try {
			// asserting / verifying page elements
//			verifyEquals(selenium.getTitle(), "iMedDoc - Patient");
//			verifyEquals("//img[@src='images/logomain.png']","//img[@src='images/logomain.png']");
//			System.out.println("presence of logomain.png verified on the main page");
			outer:
			for ( r1 = 1; r1 <=1; r1++) {
				
					row = sheet1.getRow(r1);

					selenium.selectFrame("iframe1");
					selenium.click("//div[@id='tabsJ']/ul/li[1]/a/span");
					selenium.waitForPageToLoad("3000");
					
					selenium.select("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[3]/div/select", "Patient ID");
					selenium.type("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[4]/input[2]",pid);
					selenium.click("id=img_search");
					
						
						boolean matchfound=false;
						
						int rowcount=0;
						
						if(selenium.isElementPresent("//form[@id='frm_hpreport']/table/tbody/tr[2]/td[4]")){
							
							inner:
							for(int tr=2; tr<=15; tr++){
								
								rowcount++;
								
								if (rowcount==15 & matchfound==false){
									if(selenium.isElementPresent("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b")){
										selenium.highlight("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b");
										selenium.click("//form[@id='frm_hpreport']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/a/font/b");
										selenium.waitForPageToLoad("1000");
									}
								}
								
								selenium.click("//form[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td[4]");
								if (selenium.getText("//form[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td[5]").equals(pid)){
										matchfound=true;
										patientid=selenium.getText("//form[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td[5]");
										selenium.click("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font");  // click on open
										break;
								}else if(rowcount==15){
									continue inner;
								}
							}
						}
					
					selenium.select("id=intitle",
							"label=Mrs");
					selenium.typeKeys("id=insurname", selenium.getText("id=insurname")+" E");
					selenium.typeKeys("id=inname", selenium.getText("id=inname")+" E");
					selenium.typeKeys("id=inaddr1", selenium.getText("id=inaddr1")+" E");
					selenium.typeKeys("id=inaddr2", selenium.getText("id=inaddr2")+" E");
					selenium.typeKeys("id=inaddr3", selenium.getText("id=inaddr2")+" E");
//					selenium.select("id=state",
//							"label=" + row.getCell(6).getStringCellValue());
					selenium.typeKeys("id=inaddr4", selenium.getText("id=inaddr4")+" E");
					// checking for alert present
					
/*					if (!selenium.isAlertPresent()){
						
						if(selenium.isElementPresent("//input[@value='"+row.getCell(7).getStringCellValue()+"']")){
							selenium.click("//input[@value='"+row.getCell(7).getStringCellValue()+"']");
							selenium.click("id=but_cancel2");

						}else{
							selenium.keyPressNative("27");						}
						checkForVerificationErrors();
					}
					
					selenium.click("//table[@id='table_id']/tbody/tr[13]/td[2]/a/img");
					selenium.click("css=b > a > font");
					selenium.select("id=refdate_validity", "12 months");
					selenium.select("id=concession_card", "Health Care Card");
					selenium.typeKeys("id=concession_card_no","12552C");
//					selenium.type("//form[id='frm_hp']/table/tbody/tr[1]/td[4]/input[1]","12-05-1985");				
					boolean yesflag=false;  // flag defined for patient already exists...
					// checking for confirmation
					if(selenium.isConfirmationPresent()){
						yesflag=true;
						String conmessage=selenium.getConfirmation();
						selenium.click("id=cmd_cancel");
						System.out.print("");
						System.out.println("Patient : "+paramUsername+" already exists...");
						System.out.print("");
						continue;
					}
*/
					
					boolean yesflag=false;
					
					selenium.click("//div[@id='divPhone1']/img");
					selenium.click("id=inphone");
					selenium.typeKeys("id=inphone", selenium.getText("id=inphone")+" E");
					selenium.click("//div[@id='divmobile1']/img");
					selenium.click("id=inmobile");
					selenium.typeKeys("id=inmobile", selenium.getText("id=inmobile")+" E");
					selenium.typeKeys("id=occupation",selenium.getText("id=occupation")+ " E");
					selenium.typeKeys("id=inemail", ".mahe");
					selenium.select("id=maritial_status", "label=separated");
					selenium.typeKeys("id=religion", selenium.getText("id=religion")+" E");
					selenium.select("id=inpatienttype",
							"label=POST OPERATIONAL");
					selenium.typeKeys("id=incase", "23423423 caseno.");
					selenium.click("id=cmd_submit");
/*
					if(selenium.isConfirmationPresent()){
						yesflag=true;
						String conmessage=selenium.getConfirmation();
						selenium.click("id=cmd_cancel");
						continue;
					}
*/					
					// following code snippet stores values in array
					selenium.click("id=cmd_cancel");
//					selenium.selectFrame("iframe1");
					selenium.click("//div[@id='tabsJ']/ul/li[1]/a/span");
					
					selenium.select("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[3]/div/select","Patient ID");
					selenium.type("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[4]/input[2]",pid);
					selenium.click("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[5]/table/tbody/tr/td/div[2]/font/input");
					
					if(selenium.isElementPresent("//form[@id='frm_hpreport']/table/tbody/tr[2]/td[3]/div")){

						for(int tr=2; tr<=15; tr++){
							
							selenium.click("//form[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td[3]/div");
							
							if (selenium.getText("//form[@id='frm_hpreport']/table/tbody/tr["+tr+"]/td[5]").equals(pid)){
								selenium.click("//form[@id='frm_search']/table[1]/tbody/tr[2]/td[1]/table/tbody/tr/td[4]/a/font");  // click on open
								break;
							}
						}
					}
					
					edValues[0]=selenium.getSelectedLabel("id=intitle");
					edValues[1]=selenium.getValue("id=insurname");
					edValues[2]=selenium.getValue("id=inname");
					edValues[3]=selenium.getValue("id=inaddr1");
					edValues[4]=selenium.getValue("id=inaddr2");
					edValues[5]=selenium.getValue("id=inaddr3");
					edValues[6]=selenium.getValue("id=inaddr4");
					edValues[7]="";
					edValues[8]="";  //selenium.getSelectedLabel("id=refdate_validity");
					edValues[9]="";  //selenium.getSelectedLabel("id=concession_card");
					edValues[10]=""; //selenium.getValue("id=concession_card_no");
					edValues[11]=selenium.getValue("id=inphone");
					edValues[12]=selenium.getValue("id=inmobile");
					edValues[13]=selenium.getValue("id=occupation");
					edValues[14]=selenium.getValue("id=inemail");
					edValues[15]=selenium.getSelectedLabel("id=maritial_status");
					edValues[16]=selenium.getValue("id=religion");
					edValues[17]=selenium.getSelectedLabel("id=inpatienttype");
					edValues[18]=selenium.getValue("id=incase");
					
					//writing values to array object
					for(int c1=0; c1<=18; c1++){
					
						data1[r1][c1]=row.getCell(c1).getStringCellValue().toString();
						
					}

					patientlog.info("Test data being written to Testreport.xls...");

					// opening input file stream for writing values to 'Patient Data'  
					filein2=new FileInputStream(new File("C:/Users/kmmuser/imeddocnew/imeddocnew/testreport/TestReport.xls"));
					HSSFWorkbook wb=new HSSFWorkbook(filein2);
					sheet2=wb.getSheet("Patient Edit");
					
					// writing column headings and cell values
						if (sheet2.getSheetName().equals("Patient Edit") & sheet2.getFirstRowNum() == 0) {

							for (int i = 0; i<=1; i++  ) {
								Row row1 = sheet2.createRow(i);

								//writing cell heading
								for (int j = 0; j<data2.length; j++) {
									// Create a new cell
									Cell cell = row1.createCell(j);
									cell.setCellValue(data2[j].toString());
									cell.setCellStyle(new javafiles.ExcelFormatters().setFontBold(wb));
								}
								
							}

							//writing values to cell
							for (int i = r1; i<=r1; i++  ) {
								Row row1 = sheet2.createRow(i);

								for (int j = 0; j<edValues.length; j++) {
									System.out.println("Array Position is: "+data2[j]+" "+edValues[j]);
									if (edValues[j]!="" || edValues[j]!=null){
										Cell cell = row1.createCell(j);
										cell.setCellValue(edValues[j].toString());
										cell.setCellStyle(new javafiles.ExcelFormatters().setFontNormal(wb));
									}
								}
								
							}

						}

					if (!yesflag){
						fileout2 = new FileOutputStream(new File("C:/Users/kmmuser/imeddocnew/imeddocnew/testreport/TestReport.xls"));
						wb.write(fileout2);
						
						filein2.close();
						fileout2.close();
					}

					r1=r1+1;

					selenium.click("id=cmd_cancel");
					
			}
					
				filein1.close();

				patientlog.info("edit patient test case completed successfully...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			patientlog.info("Error in edit patient test case...", e);

		}
	}

	@AfterClass
	public static void stopServer() throws Exception {

		try {
			selenium.stop();
			selenium.close();
			wd.close();
			WebDriver driverInstance = ((WebDriverBackedSelenium) selenium).getWrappedDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
