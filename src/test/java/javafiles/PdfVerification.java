package javafiles;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.WebDriver;

import testNGfiles.RCSampleTest;

public class PdfVerification {

	@SuppressWarnings("unchecked")
	public static void verifyPatientid(String filename) throws Exception {

		try {
			
			PDDocument document = PDDocument.load(new File(testNGfiles.FilesAndPaths.PDFDirectory().getAbsolutePath() + "\\" + filename));
			PDFTextStripper textStripper = new PDFTextStripper();

			//		System.out.println(textStripper.getText(document).contains(RCSampleTest.pusername) + ", Patient id " + RCSampleTest.pusername + " found in the Print Label PDf");

			List<PDPage> allPages = document.getDocumentCatalog().getAllPages();	

			for (int i = 0; i < allPages.size(); i++) {
				
				PDPage pages = allPages.get(i);

				PDPageContentStream contentStream = new PDPageContentStream(document, pages, true, true, true);
				PDFont font = PDType1Font.HELVETICA_BOLD;

				if (textStripper.getText(document).contains(RCSampleTest.pusername) || textStripper.getText(document).contains(RCSampleTest.pname) ) {

					contentStream.beginText();
					contentStream.setFont(font, 16);
					contentStream.moveTextPositionByAmount(50, 100);
					contentStream.setNonStrokingColor(156, 192, 137);
					contentStream.drawString("Patient Verified in this document");
					contentStream.endText();
					contentStream.close();

				}else {
					
					contentStream.beginText();
					contentStream.setFont(font, 16);
					contentStream.moveTextPositionByAmount(50, 100);
					contentStream.setNonStrokingColor(170, 17, 17);
					contentStream.drawString("Patient ".concat(RCSampleTest.pname).concat(" not found in this document"));
					contentStream.endText();
					contentStream.close();
					
				}

			}

			document.save(testNGfiles.FilesAndPaths.PDFDirectory().getAbsolutePath() + "\\" + filename);
			Thread.sleep(2000);
			document.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
