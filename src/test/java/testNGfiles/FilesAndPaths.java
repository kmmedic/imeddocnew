package testNGfiles;

import java.io.File;

public class FilesAndPaths {
	
	
	public static File PDFDirectory() throws Exception{
		
		File docdir = new File("PatientDocuments\\");

		try {
			if(!docdir.exists()){
				
				docdir.mkdir();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docdir;
		
	}
	
	public static File Testreport() throws Exception{
		
		File exceldir = new File("ExcelReports\\");

		try {
			if(!exceldir.exists()){
				
				exceldir.mkdir();				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exceldir;
		
	}
	
	public static File Testlog() throws Exception{
		
		File logdir = new File("TestLogs\\");

		try {
			if(!logdir.exists()){
				
				logdir.mkdir();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return logdir;
		
	}

	public static File TestData() throws Exception{
		
		File datadir = new File("TestData\\");
		
		try {
			if(!datadir.exists()){
				
				datadir.mkdir();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return datadir;
		
	}

	public static File showTestNGReport() throws Exception{
		
		File report = new File("test-output\\");
		
		return report;
		
	}

	
}
