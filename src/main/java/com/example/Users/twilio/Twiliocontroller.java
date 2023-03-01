package com.example.Users.twilio;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.function.ServerRequest;
//import org.springframework.web.servlet.function.ServerResponse;
//
//import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
//
//import reactor.core.publisher.Mono;

//@RestController
//public class Twiliocontroller {
//
//	
//	@Autowired
//	private TwilioOtoService otoService;
//
//	
//	PasswordResetResponceDto responceDto=null;
//	
//	
//	@PostMapping("/sendOtp")
//	public PasswordResetResponceDto sendOtp(@RequestBody PasswordResetDto dto)
//	{
//		try{
//			
//			System.err.println(dto.getUserName()+" "+dto.getUserNumber());
//			
//			this.otoService.sentOtp(dto);
//			
//			 responceDto=new PasswordResetResponceDto(OtpStatus.DELIVERED, "Successfull");
//			 
//			 return responceDto;
//		}
//		catch (Exception e) {
//			responceDto=new PasswordResetResponceDto(OtpStatus.FAILED, e.getMessage());
//
//			return responceDto;
//		}
//	}
//
//}
