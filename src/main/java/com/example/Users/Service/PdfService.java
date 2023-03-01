package com.example.Users.Service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import javax.swing.GroupLayout.Alignment;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.Users.Interface.UsersTask;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Status;
import com.example.Users.entity.UsersTaskEntity;
import com.itextpdf.text.DocumentException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private SpringTemplateEngine engine;
	
	private Logger logger=org.slf4j.LoggerFactory.getLogger(PdfService.class);
	
	
	public  String readHtml(Model  model)
	{
		Context context=new Context();
		System.err.println("1231928371289371238912731938");
		context.setVariables(model.asMap());
		return engine.process("generatePdf", context);
		
		
	}
	
//	public void generatePdf(Element html, HttpServletResponse response) throws Exception {
//		
////		System.err.println("dasondasnaodinasdansdasiod");
////		
////        ITextRenderer renderer = new ITextRenderer();
////        System.err.println("aklasdkasdksdklsdkasdkas;lksd;klasd;klasd");
////        
////        renderer.setDocumentFromString(html);
////        renderer.layout();
////        response.setContentType("application/pdf");
////        
////        response.setHeader("Content-Disposition", "attachment; filename=certificate.pdf");
////        OutputStream outputStream = response.getOutputStream();
////        System.err.println("///////////////sfjwifjsdofjsdofsfho//////////////");
////        renderer.createPDF(outputStream);
////        
////        outputStream.close();
//		
//			ITextRenderer iTextRenderer=new ITextRenderer();
//			
//			Document document=new Document();
//			document.add(html);
//			//iTextRenderer.setDocument(html);
//			response.setContentType("APPLICATION_PDF");
//			response.setHeader("Content-Disposition", "inline;file=usertask.pdf");
//			
//			ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
//			
//			PdfWriter.getInstance(document, arrayOutputStream);
//			
//			
//			
//      
//    }
	
	
	public void generatePdf() throws IOException, DocumentException
	{

        File htmlFile = new File("/src/main/resources/generatePdf.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlFile,"UTF-8");
        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        try (OutputStream os = new FileOutputStream("/src/main/java/com/example/Users/a.pdf")){
        	ITextRenderer renderer = new ITextRenderer();
        	SharedContext cntxt = renderer.getSharedContext();
        	cntxt.setPrint(true);
        	cntxt.setInteractive(false);
        	String baseUrl = FileSystems.getDefault().getPath("/src/main/resources/generatePdf.html")
        			         .toUri().toURL().toString();
        	renderer.setDocumentFromString(doc.html(), baseUrl);
        	renderer.layout();
        	renderer.createPDF(os);
        	System.out.println("done");
        }
	}
	
	
	
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
			paragraph2.setAlignment(Element.TABLE);
			document.add(paragraph2);
			
			document.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
				
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	
	public static void wtiterHeader(PdfPTable pTable)
	{
		PdfPCell cell=new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(10);
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.green);
		
		cell.setPhrase(new Phrase("Task",font));
		pTable.addCell(cell);
		cell.setPhrase(new Phrase("Status",font));
		pTable.addCell(cell);
		
		
		
		
	}
	
//	public void writeDetails(PdfPTable pdfPTable, List<UsersTask> list)
//	{
//		for(UsersTask task: list)
//		{
//			
//		}
//	}
	
	
	
	public ByteArrayInputStream generateUserTaskPdf(int id)
	{
		List<UsersTask> list=this.repository.findByid(id, UsersTask.class);
	
		
		logger.info("Generating pdf.....");
		String name = null;
		String tasks=null;
		Status status=null;
		ArrayList<String> allTask=new ArrayList<>();
		ArrayList<Status> statusTask=new ArrayList<>();
		for(UsersTask task:list)
		{
			name=task.getName();
			tasks=task.getTask();
			status=task.getStatus();
			allTask.add(tasks);
			statusTask.add(status);
			
		}
		
		Object forEachTask[]=allTask.toArray();
		String eachTask[]=new String [forEachTask.length];
	
		Object statusForTask[]=statusTask.toArray();
		Status[] statusOfTask=new Status [statusForTask.length];
		
		for(int i=0;i<forEachTask.length;i++)
		{
			eachTask[i]=(String) forEachTask[i];
		}

		for(int i=0;i<statusForTask.length;i++)
		{
			statusOfTask[i]=(Status) statusForTask[i];
		}
		
		ByteArrayOutputStream arrayOutputStream=new 
				ByteArrayOutputStream();
		
		Document  document=new Document();
//		
//		Table table=new Table(2);
//		table.setBorder(5);
//		table.setAlignment(Element.ALIGN_CENTER);
		
		
		PdfPTable table=new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(15);
		PdfService.wtiterHeader(table);
			String title=name+" All Tasks";	
			try {
				
				PdfWriter.getInstance(document, arrayOutputStream);
				
				
				document.open();
				
				Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				Paragraph paragraph=new Paragraph(title,font);
				
				paragraph.setAlignment(Element.ALIGN_CENTER);
				//paragraph.add(Element.HEADER, paragraph.get(id));
				document.add(paragraph);
				
				
			for(int i=0,j=0;i<eachTask.length||j<statusOfTask.length;i++,j++)
			{
				String task=eachTask[i];
				String statusof =" "+statusOfTask[i];
			
				try {
					Font taskfont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
//					Paragraph paragraph2=new Paragraph(comments,taskfont);
//					paragraph2.setAlignment(Element.TABLE);
//					
//					table.addCell("Task = "+eachTask[i]);
//					
//					table.addCell(" Status of task=  "+statusOfTask[i]);
					
					
					
					table.addCell(task);
					table.addCell(statusof);
					
					
					String tablehead="Task";
					String Status="Status";
					Paragraph paragraph2=new Paragraph();
					paragraph2.add(tablehead+ " "+ status);
					
					
					
					
				}
				catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			
			document.add(table);
			
	
				document.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ByteArrayInputStream(arrayOutputStream.toByteArray());
	}
	
	
//	public void generateNewPdf(String html,int id, HttpServletResponse response)
//	{
//		
//	}

	
}
