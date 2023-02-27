package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.FileHandle.FileEntity;
import com.example.Users.FileHandle.FileHandleHelper;
import com.example.Users.FileHandle.FileService;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessMessageConstant;

@RestController
public class FileUpload {

	@Autowired
	private FileHandleHelper fileHandleHelper;

	@Autowired
	private FileService fileService;

	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("File") MultipartFile file) {

		try {

			System.err.println(file.getOriginalFilename());
			System.err.println(file.isEmpty());
			System.err.println(file.getSize());
			System.err.println(file.getContentType());
			System.err.println(file.getName());
			if (file.isEmpty() == true) {
				return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001), HttpStatus.BAD_REQUEST);
			}

			Boolean f = this.fileHandleHelper.uploadFile(file);
			String upload=this.fileService.uploadFile(file);

			if (f == true || upload.isEmpty()==false) {

//				return new ResponseEntity<>(new Success("Success", "Success", ServletUriComponentsBuilder
//						.fromCurrentContextPath().path("/Images/").path(file.getOriginalFilename().toString())),
//						HttpStatus.OK);
				return ResponseEntity.ok(SuccessMessageConstant.FILE_UPLOAD);

//				String s = this.fileService.uploadFile(file);
//
//				return ResponseEntity.ok(s);

			} else {
				throw new Exception("not upload exception");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/file/{id}")
	public ResponseEntity<?> downloadfile(@PathVariable("id") int id) {
		try {
		
			byte[] imagedata = this.fileService.downloadfile(id);
			
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).body(new ByteArrayResource(imagedata));
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001), HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("/file/{id}")
//	public ResponseEntity<?> download(@PathVariable("id") int id)
//	{
//		try {
//			
//			FileEntity  entity=this.fileService.download(id);
//			
//			return ResponseEntity.ok().contentType(MediaType.ALL).body(new ByteArrayResource(entity.getImagedata()));
//			
//		} catch (Exception e) {
//			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001), HttpStatus.NOT_FOUND);
//
//		}
//	}
}
