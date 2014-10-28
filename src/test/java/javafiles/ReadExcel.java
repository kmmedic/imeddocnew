package javafiles;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javafiles.ExcelFormats;

public class ReadExcel {

    private static HSSFSheet ws=null;
    private static HSSFWorkbook wb=null;
    private static HSSFRow Row=null;
    
//  static List sheetData = new ArrayList();
	String func = null;
	static int columindex = 0;
	static int xlsheet = 0;
	static HSSFSheet selectedsheet;
//	static HSSFCell cell;
	static Object data1[][] = new Object[20][20];
    
	
	
    //This method is to write in the Excel cell heading
	public static void setCellHeading(String FPath, HSSFWorkbook wb,
			HSSFSheet ws, String sname, int RowNum, int ColNum, Object[][] data)
			throws Exception {

		try {

			// This stream used to open a file
			FileInputStream filein = new FileInputStream(new File(FPath));
			
			// getting workbook instance
			wb = new HSSFWorkbook(filein);
			
			// getting sheet instance
			ws = wb.getSheet(sname);

			// creating a row
			Row = ws.createRow(RowNum);

			// writing cell heading
			for (int j = 0; j < data.length; j++) {
				
				for (int k = 0; k <= data[j].length-1; k++) {
					
					// Create a new cell
					Row.createCell(ColNum + k).setCellValue(data[j][k].toString());
					
					ws.autoSizeColumn(ColNum+k);
					
					// formatting a cell content
					Row.getCell(ColNum+k).setCellStyle(new ExcelFormats().setFontBold(wb));
					
				}
			}
			
			filein.close();
			
			// writing column headings and cell values
			FileOutputStream fileout = new FileOutputStream(new File(FPath));
			
			// writing to file
			wb.write(fileout);
			
			// closing stream
			
			fileout.close();
			
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void setCellValue(String FPath, HSSFWorkbook wb, HSSFSheet ws, String sname, int RowNum, int ColNum, Object[][] data) throws Exception{

		try {

			// This stream used to open a file
			FileInputStream filein = new FileInputStream(new File(FPath));
			
			// getting workbook instance
			wb = new HSSFWorkbook(filein);
			
			// getting sheet instance
			ws = wb.getSheet(sname);

			// creating a row
			Row = ws.createRow(RowNum);
			
			// writing cell heading
			for (int j = 0; j < data.length; j++) {
				
				for (int k = 0; k <= data[j].length-1; k++) {
					
					// Create a new cell
					Row.createCell(ColNum + k).setCellValue(data[j][k].toString());

					ws.autoSizeColumn(ColNum+k);
					
					// formatting a cell content
					Row.getCell(ColNum+k).setCellStyle(new ExcelFormats().setFontNormal(wb));
					
				}
			}
			
			filein.close();

			// writing column headings and cell values with file output stream
			FileOutputStream fileout = new FileOutputStream(new File(FPath));
			
			// writing to file
			wb.write(fileout);
			
			// closing stream
			fileout.close();
			
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public static void updateCellValue(String FPath, HSSFWorkbook wb, HSSFSheet ws, String sname, int RowNum, int ColNum, Object[][] data) throws Exception{

		try {

			// This stream used to open a file
			FileInputStream filein = new FileInputStream(new File(FPath));
			
			// getting workbook instance
			wb = new HSSFWorkbook(filein);
			
			// getting sheet instance
			ws = wb.getSheet(sname);

			for (int j = 0; j < data.length; j++) {
				
				for (int k = 0; k <= data[j].length-1; k++) {

					// checking for cell is null
					if(ws.getRow(RowNum).getCell(ColNum+k)==null){
						
						// creating a new cell
						ws.getRow(RowNum).createCell(ColNum+k);
						
					}
					
					// updating value in new cell
					ws.getRow(RowNum).getCell(ColNum+k).setCellValue(data[j][k].toString());

					ws.autoSizeColumn(ColNum+k);

					ws.getRow(RowNum).getCell(ColNum+k).setCellStyle(new ExcelFormats().setFontNormal(wb));
					
				}
				
			}
			
			filein.close();

			// writing column headings and cell values with file output stream
			FileOutputStream fileout = new FileOutputStream(new File(FPath));
			
			// writing to file
			wb.write(fileout);
			
			// closing stream
			fileout.close();
			
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void WriteResult(String file, HSSFWorkbook wb, HSSFSheet ws, String sheetname, String username, String password, String result)throws Exception{

		FileInputStream filein = new FileInputStream(new File(file));
		wb = new HSSFWorkbook(filein);
		ws = wb.getSheet(sheetname);

		Iterator<Row> rowIterator0 = ws.iterator();
		HSSFRow row0 = (HSSFRow) rowIterator0.next();
		int no_of_rows = ws.getLastRowNum();
		int no_of_cols = row0.getLastCellNum();

		Object[][] data = new Object[no_of_rows][no_of_cols];

		int i = 0;
		
		String uname=null, pword=null;
		
		try {
			while (rowIterator0.hasNext()) {
				HSSFRow row1 = (HSSFRow) rowIterator0.next();

				Iterator<Cell> cells = row1.cellIterator();

				HSSFCell cell=null;
				
				int j = 0;

				while (cells.hasNext()) {
					cell = (HSSFCell) cells.next();
					data[i][j] = cell.getStringCellValue();
//				System.out.println("i=" + i + " j=" + j);
//				System.out.println(data[i][j]);
					j = j + 1;
				}
				
				if (data.length !=0){
					
					for (int k=0; k<=data.length-1; k++){
						if (k==0){
							uname=data[i][k].toString();
						}else if (k==1){
							pword=data[i][k].toString();
						}else if (k==2){
							break;
						}
					}
					
				}else{
					
					System.out.println("empty array");
				}
				
				if (!uname.equals(username) & !pword.equals(password)){

					row1.createCell(2).setCellValue(result.toString());
//				cell.setCellValue(result.toString());
					cell.setCellStyle(new ExcelFormats().setFontNormal(wb));
					ws.setDefaultColumnWidth(30);
					
				}else if((uname.equals(username) & pword.equals(password))){

					row1.createCell(2).setCellValue(result.toString());
//				cell.setCellValue(result.toString());
					cell.setCellStyle(new ExcelFormats().setFontNormal(wb));
					ws.setDefaultColumnWidth(30);
				}

				i = i + 1;
				
			}

			filein.close();
			FileOutputStream fileout=new FileOutputStream(new File(file));
			wb.write(fileout);
			fileout.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
}
