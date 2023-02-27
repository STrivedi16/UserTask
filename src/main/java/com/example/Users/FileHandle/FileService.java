package com.example.Users.FileHandle;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Exception.ResourceNotFoundException;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public String uploadFile(MultipartFile file) throws IOException {
		FileEntity entity = new FileEntity();
		entity.setName(file.getOriginalFilename());
		entity.setType(file.getContentType());
		//entity.setImagedata(FileUtils.compressImage(file.getBytes()));
		
		entity.setImagedata(file.getBytes());
		this.fileRepository.save(entity);

		if (entity != null) {
			return "file Uploaded successfully";
		} else {
			throw new IOException("File not found");
		}

	}

	public byte[] downloadfile(int filename) throws IOException {
		

		FileEntity file = this.fileRepository.findById(filename);

		

		byte[] imageData = FileUtils.decompressImage(file.getImagedata());

		
		return imageData;

	}
	
//	public FileEntity download(int fileid)
//	{
//		FileEntity entity=this.fileRepository.findById(fileid).orElseThrow(()-> new ResourceNotFoundException());
//	
//	
//		return entity;
//	}

}
