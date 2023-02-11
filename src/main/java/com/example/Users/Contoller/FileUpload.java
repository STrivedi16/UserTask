package com.example.Users.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.FileHandle.FileHandleHelper;
import com.example.Users.FileHandle.FileService;
import com.example.Users.Responce.ErrorMessage;

@RestController
public class FileUpload {

	@Autowired
	private FileHandleHelper fileHandleHelper;

	@Autowired
	private FileService fileService;

	@PostMapping("/upload-file")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

		try {

			System.err.println(file.getOriginalFilename());
			System.err.println(file.isEmpty());
			System.err.println(file.getSize());
			System.err.println(file.getContentType());
			System.err.println(file.getName());

			if (file.isEmpty() == true) {
				return new ResponseEntity<>(new ErrorMessage("file is empty", "error"), HttpStatus.BAD_REQUEST);
			}

			Boolean f = this.fileHandleHelper.uploadFile(file);

			if (f == true) {

////				return new ResponseEntity<>(new Success("Success", "Success", ServletUriComponentsBuilder
////						.fromCurrentContextPath().path("/Images/").path(file.getOriginalFilename().toString())),
////						HttpStatus.OK);
//				return ResponseEntity.ok("file srored in your folder");

				String s = this.fileService.uploadFile(file);

				return ResponseEntity.ok(s);

			} else {
				throw new Exception("not upload exception");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("not uploaded", "not upload"), HttpStatus.BAD_REQUEST);
		}
	}
}