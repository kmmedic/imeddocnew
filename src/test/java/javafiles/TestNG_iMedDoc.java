package javafiles;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNG_iMedDoc {
	
	public static void main(String[] args) {
		
		try {
			
			XmlSuite suite = new XmlSuite();
			suite.setName("Suite1");
			 
			XmlTest test = new XmlTest(suite);
			test.setName("test1");
			List<XmlClass> classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("testNGfiles.RunTests"));
			classes.add(new XmlClass("testNGfiles.Loadurl"));
			classes.add(new XmlClass("testNGfiles.Sign_In"));
			classes.add(new XmlClass("testNGfiles.RCSampleTest"));
			classes.add(new XmlClass("testNGfiles.GotoPatient"));
			classes.add(new XmlClass("testNGfiles.NextofKin"));
			classes.add(new XmlClass("testNGfiles.PrintLabel"));
			classes.add(new XmlClass("testNGfiles.Hospital"));
			classes.add(new XmlClass("testNGfiles.MoreTest"));
			classes.add(new XmlClass("testNGfiles.AddToWaiting"));
			classes.add(new XmlClass("testNGfiles.MedicalCertificate"));
			classes.add(new XmlClass("testNGfiles.Patient_Report"));
			classes.add(new XmlClass("testNGfiles.PatientDiagnosis"));
			classes.add(new XmlClass("testNGfiles.PatientGPAddition"));
			classes.add(new XmlClass("testNGfiles.PatientSummary"));
			classes.add(new XmlClass("testNGfiles.PatientPersonalHistory"));
			classes.add(new XmlClass("testNGfiles.PatientMedicalHistory"));
			classes.add(new XmlClass("testNGfiles.EndUp"));
			test.setXmlClasses(classes);
			
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG tng = new TestNG();
			tng.setXmlSuites(suites);
			tng.run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
}
