package com.example.Users.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.Alignment;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Users.Interface.UsersTask;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Status;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.FontMapper;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {

	@Autowired
	private UsersRepository repository;
	
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
		Status statusOfTask[]=new Status [statusForTask.length];
		
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
		
		
		
			String title=name+" All Tasks";	
			try {
				
PdfWriter.getInstance(document, arrayOutputStream);
				
				document.open();
				
				Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				Paragraph paragraph=new Paragraph(title,font);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				
				document.add(paragraph);
				
				
			for(int i=0,j=0;i<eachTask.length||j<statusOfTask.length;i++,j++)
			{
				String comments="Task = "+eachTask[i]+"   "+" Status of task=  "+statusOfTask[i];
			
				try {
					Font taskfont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
					Paragraph paragraph2=new Paragraph(comments,taskfont);
					paragraph2.setAlignment(Element.ALIGN_MIDDLE);
					
					document.add(paragraph2);
				}
				catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
			
			
			
			
			
			
				
				
				
				
				
				
				
				document.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ByteArrayInputStream(arrayOutputStream.toByteArray());
	}
	
}
