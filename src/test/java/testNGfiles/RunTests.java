package testNGfiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class RunTests{
	
	private static WebDriver wd;
	
	@Test(groups={"functional"})
	public static void startServer() throws Exception{
		
		try {
			
			wd=new FirefoxDriver();
			
			wd.manage().window().maximize();

			PageFactory.initElements(wd, testNGfiles.Loadurl.class);
			PageFactory.initElements(wd, testNGfiles.Sign_In.class);
			PageFactory.initElements(wd, testNGfiles.RCSampleTest.class);
			PageFactory.initElements(wd, testNGfiles.GotoPatient.class);
			PageFactory.initElements(wd, testNGfiles.NextofKin.class);
			PageFactory.initElements(wd, testNGfiles.PrintLabel.class);
			PageFactory.initElements(wd, testNGfiles.Hospital.class);
			PageFactory.initElements(wd, testNGfiles.MoreTest.class);
			PageFactory.initElements(wd, testNGfiles.AddToWaiting.class);
			PageFactory.initElements(wd, testNGfiles.MedicalCertificate.class);
			PageFactory.initElements(wd, testNGfiles.Patient_Report.class);
			PageFactory.initElements(wd, testNGfiles.PatientDiagnosis.class);
			PageFactory.initElements(wd, testNGfiles.PatientGPAddition.class);
			PageFactory.initElements(wd, testNGfiles.PatientSummary.class);
			PageFactory.initElements(wd, testNGfiles.PatientPersonalHistory.class);
			PageFactory.initElements(wd, testNGfiles.PatientMedicalHistory.class);
			PageFactory.initElements(wd, testNGfiles.EndUp.class);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
}
