package javafiles;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelFormatters {

	public HSSFCellStyle setFontBold(HSSFWorkbook wb) throws Exception {

		HSSFCellStyle fontbold = wb.createCellStyle();

		// style.setBorderTop((short) 6); // double lines border
		// style.setBorderBottom((short) 1); // single line border

		HSSFFont font1 = wb.createFont();
		font1.setFontName(HSSFFont.FONT_ARIAL);
		font1.setFontHeightInPoints((short) 12);
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// font.setColor(HSSFColor.BLACK.index);

		fontbold.setAlignment(HSSFCellStyle.ALIGN_GENERAL);
		fontbold.setFillBackgroundColor(HSSFColor.AUTOMATIC.index);
		fontbold.setFont(font1);

		return fontbold;

	}

	public HSSFCellStyle setFontNormal(HSSFWorkbook wb) throws Exception {

		HSSFCellStyle fontnormal = wb.createCellStyle();
		HSSFFont font2 = wb.createFont();
		font2.setFontName(HSSFFont.FONT_ARIAL);
		font2.setFontHeightInPoints((short) 12);

		fontnormal.setAlignment(HSSFCellStyle.ALIGN_GENERAL);
		fontnormal.setFillBackgroundColor(HSSFColor.AUTOMATIC.index);
		fontnormal.setFont(font2);

		return fontnormal;
	}

}
