package testNGfiles;


import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import javafiles.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class Sign_In {

    private static HSSFSheet ws=null;
    private static HSSFWorkbook wb=null;
    private static HSSFRow Row=null;
    private static Boolean result=false;
    
	private static WebDriver wd;
	
//    private static JavascriptExecutor js = (JavascriptExecutor) wd;
    
    public Sign_In(WebDriver wd){
    	this.wd=wd;
    }
/*
	@Test
	public static void setup() throws Exception{
		wd=new FirefoxDriver();
		wd.get("http://demo.imeddoc.net");
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.manage().window().maximize();

	}
*/	
	@DataProvider(name = "login")
	public static Object[][] login() throws Exception {
		
		FileInputStream file = new FileInputStream(new File(testNGfiles.FilesAndPaths.TestData()+"\\"+"TestCases.xls"));
		
		wb = new HSSFWorkbook(file);
		ws = wb.getSheet("Login");
		
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

	
	@Test(groups={"functional"}, dataProvider = "login")
	public static void getValue(String uname, String pword, String result) throws Exception {
		
		String sendresult=null;
		
		try {

			LoginPage.select_practiceName(wd);
			LoginPage.txt_userName(wd).clear();
			LoginPage.txt_userName(wd).sendKeys(uname);
			LoginPage.txt_passWord(wd).clear();
			LoginPage.txt_passWord(wd).sendKeys(pword);
			LoginPage.bt_Signin(wd).click();
			
			if(wd.getPageSource().contains("Invalid Login Details")){
				sendresult="Fail";
			}else{
				sendresult="Pass";
			}

			
/*			
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
	
*/
			
			ReadExcel.setCellHeading(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Login Data", 0, 0, new Object[][]{{"Username", "Password", "Result"}});
			ReadExcel.setCellValue(testNGfiles.FilesAndPaths.Testreport()+"\\"+"TestReport.xls", wb, ws, "Login Data", 1, 0, new Object[][]{{uname, pword, sendresult}});
			
			
			wd.switchTo().frame("iframe2");
			wd.findElement(By.xpath("//table[@id='tmp']/tbody/tr/td/table[1]/tbody/tr[1]/td[5]/a/img")).click();
			wd.switchTo().defaultContent();
			
//			js.executeScript("alert('username : ');"+uname);
//			Thread.sleep(3000);
			

/*			
			Actions builder=new Actions(wd); 
			WebElement cl=wd.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td/table/tbody/tr/td[5]/a/img")); 
			builder.moveToElement(cl).click();
			
*/			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
