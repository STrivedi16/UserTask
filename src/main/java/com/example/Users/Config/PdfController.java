package com.example.Users.Config;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Service.PdfService;

@RestController
public class PdfController {

	
	@Autowired
	private PdfService pdfService;
	
	@GetMapping("/generatePdf")
	public ResponseEntity<?> generatePdf()
	{
		ByteArrayInputStream arrayInputStream=this.pdfService.createPdf();
		
		HttpHeaders headers =new HttpHeaders();
		          
		headers.add("Content-Disposition","inline;file=firstPdf.pdf");
		
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(arrayInputStream));
	}
	
	@GetMapping("/userTaskPdf/{id}")
	public ResponseEntity<?> generatePdfUserTask(@PathVariable("id") int id)
	{
		try {
			ByteArrayInputStream arrayInputStream=this.pdfService.generateUserTaskPdf(id);
			
			HttpHeaders headers=new HttpHeaders();
			headers.add("Content-Disposition", "inline;file=usertask.pdf");
			
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(arrayInputStream));
		}catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("error in genrating pdf", "pdf not generateed"),HttpStatus.BAD_REQUEST);
			
			
		}
	}
	
	@GetMapping("/genrate")
	public void generate( Model model, HttpServletResponse httpServletResponse)
	{
		try {
			
			
			
			model.addAttribute("image","classpath:/templates/smt-logo.jpg");
			model.addAttribute("name", "Shubham Trivedi");
			model.addAttribute("task", "Making crud of application ");
			model.addAttribute("status", "IN_PROCESS");
			
			
			System.out.println("pdf generated");
			
			String html=this.pdfService.readHtml(model);
			
			System.err.println("pdf generatedadadjasdasdadiladjasildjasdliajaildj");
		this.pdfService.generatePdf();
		
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
