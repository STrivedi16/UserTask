package com.example.Users.Contoller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;
import com.example.Users.Service.FileDocumentsHelper;
import com.example.Users.Service.FileDocumentsService;

@RestController
@CrossOrigin("*")
public class FileDocumentsController {

	@Autowired
	private FileDocumentsService documentsService;
	
	@PostMapping("/userfile")
	public ResponseEntity<?> upload(@RequestParam("File") MultipartFile file)
	{
		if(file.isEmpty()) {
			
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001),HttpStatus.BAD_REQUEST);
		}
		else {
			if(FileDocumentsHelper.checkFileFormate(file).equals(true)) {
				try {
					
					this.documentsService.save(file);
					
					return new ResponseEntity<>(new SuccessFileMessage(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M032001),HttpStatus.OK);
				}
				catch (Exception e) {
					return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001),HttpStatus.BAD_REQUEST);
				}
				}
				
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND ,ErrorMessageKey.FILE_E032001),HttpStatus.BAD_REQUEST);

		}
		
		
	}
	
	@GetMapping("/userPermissionFile")
	public void getExcel(HttpServletResponse response) throws IOException
	{
		response.setContentType("appication/octet-stream");
		
		String headerKey="Content-Disposition";
		String headerValue="attachment;filename=UserPermissions.xls";
		
		response.setHeader(headerKey, headerValue);
		
		this.documentsService.getToExcel(response);
	}
}
