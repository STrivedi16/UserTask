package com.example.Users.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

	
	private Logger logger=org.slf4j.LoggerFactory.getLogger(PdfService.class);
	
	public ByteArrayInputStream createPdf()
	{
		String title="Welcome to this appplication pdf ";
		String content="this is the first pdf that we made ";
		
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		
		Document document=new Document();
		
		try {
			
			PdfWriter.getInstance(document, outputStream);
			
			document.open();
			
			Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
			Paragraph paragraph=new Paragraph(title,font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			
			document.add(paragraph);
			
			Font peraFont=FontFactory.getFont(FontFactory.HELVETICA);
			Paragraph paragraph2=new Paragraph(content,peraFont);
			document.add(paragraph2);
			
			document.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
				
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
}
