package com.example.Users.FileHandle;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.Success;
import com.example.Users.Responce.SuccessFileMessage;
import com.example.Users.Responce.SuccessMessageConstant;
import com.example.Users.Responce.SuccessMessageKey;

@RestController
@CrossOrigin("*")
public class UserFileController {

	@Autowired
	private UserFileService fileService;

	@PostMapping("/file")
	public ResponseEntity<?> upload(@RequestParam("File") MultipartFile file) {
		if (FileHepler.checkExcelFormet(file)) {

			try {

				this.fileService.save(file);

				return new ResponseEntity<>(
						new SuccessFileMessage(SuccessMessageConstant.FILE_UPLOAD, SuccessMessageKey.FILE_M032001),
						HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return new ResponseEntity<>(
				new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001),
				HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/file/list")
	public ResponseEntity<?> getAllFromFile()

	{
		try {

			List<UsersFileEntity> list = this.fileService.getall();

			return new ResponseEntity(
					new Success(SuccessMessageConstant.FILE_FETCHED, SuccessMessageKey.FILE_M012002, list),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ErrorMessage(ErrorMessageConstant.FILE_DATA_NOT_FETCHED, ErrorMessageKey.FILE_E032002),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws IOException {

		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";

		response.setHeader(headerKey, headerValue);

		fileService.exportToExcel(response);
	}

	@GetMapping("/userfile")
	public void userExcel(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.setContentType("appication/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";

		httpServletResponse.setHeader(headerKey, headerValue);

		fileService.generatExcel(httpServletResponse);
	}
}
