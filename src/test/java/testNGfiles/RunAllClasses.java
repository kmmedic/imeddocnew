package testNGfiles;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class RunAllClasses {

	public static void main(String[] args) {
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { testNGfiles.RunTests.class });
		testng.addListener(tla);
		testng.run();
		
		/*
		XmlSuite suite = new XmlSuite();  

		suite.setName("Suite1");

		XmlTest test = new XmlTest(suite);  

		test.setName("test1");  

		List<XmlClass> classes = new ArrayList<XmlClass>();  

		classes.add(new XmlClass("testNGfiles.RunTests"));  

		test.setXmlClasses(classes) ; 
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();  

		suites.add(suite);  

		TestNG tng = new TestNG();  

		tng.setXmlSuites(suites);  

		tng.run(); 
		*/
		}
}
