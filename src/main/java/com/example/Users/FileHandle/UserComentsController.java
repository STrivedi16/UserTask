package com.example.Users.FileHandle;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
public class UserComentsController {

	@Autowired
	private UserComentsService comentsService;

	@PostMapping("/userFile")
	public ResponseEntity<?> saveData(@RequestParam("File") MultipartFile file) {
		try {
			if (UserComentFileHelper.checkFileFormat(file)) {
				this.comentsService.save(file);

				return new ResponseEntity<>(
						new SuccessFileMessage(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M032001),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001),
					HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping("/usernewfile")
	public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setContentType("appication/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";

		httpServletResponse.setHeader(headerKey, headerValue);

		comentsService.dataToExcel(httpServletResponse);
	}

}
