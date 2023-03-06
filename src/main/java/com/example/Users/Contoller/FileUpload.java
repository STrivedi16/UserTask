package com.example.Users.Contoller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.FileHandle.FileHandleHelper;
import com.example.Users.FileHandle.FileService;
import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.ErrorMessageConstant;
import com.example.Users.Responce.ErrorMessageKey;
import com.example.Users.Responce.SuccessFileMessage;
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
			//String upload=this.fileService.uploadFile(file);

			String upload=this.fileService.uploadVideo(file);
			
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
		
			byte[] imagedata = this.fileService.getImage(id);
			System.out.println(imagedata);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(new ByteArrayResource(imagedata));
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/video/{id}")
	public ResponseEntity<?> getVideo(@PathVariable("id") int id )
	{
		try {
			byte[] video=this.fileService.getVideo(id);
			System.out.println(video);
			
				
			
			InputStreamResource inputStreamResource= new InputStreamResource(new ByteArrayInputStream(video));
			
			HttpHeaders headers=new HttpHeaders();
			
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
			headers.add(HttpHeaders.CONTENT_DISPOSITION,   "attachment; filename=\"video.mp4\"");
			headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
			headers.add(HttpHeaders.PRAGMA,  "no-cache");
			headers.add(HttpHeaders.EXPIRES, "0");
			
			return ResponseEntity.ok().headers(headers).contentLength(video.length).body(inputStreamResource);
		
		
		}
		
		
		catch (Exception e) {
						return new ResponseEntity<>(new ErrorMessage("Video not get", "Video not get"),HttpStatus.BAD_REQUEST);
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
	
	@PostMapping("/uploadfiles")
	public ResponseEntity<?> uploadMultipleFile(@RequestParam("files") MultipartFile[] files)
	{
		try {
			
			String list=this.fileService.uploadMultiFile(files);
			
			return new ResponseEntity<>(new SuccessFileMessage(SuccessMessageConstant.FILE_UPLOAD, list),HttpStatus.OK);
			
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_UPLOADED, ErrorMessageKey.FILE_E032001), HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/multiplefiles")
	public ResponseEntity<?> getMultipleFile()
	{
		try {
			
			
		
			byte[] bs=this.fileService.getMultipleFile();
			HttpHeaders  headers=new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename("files.zip").build());
			
			
			
			return new ResponseEntity<>(bs,headers,HttpStatus.OK);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ErrorMessage(ErrorMessageConstant.FILE_NOT_FOUND, ErrorMessageKey.FILE_E032001),HttpStatus.BAD_REQUEST);
		}
	}
	
}
