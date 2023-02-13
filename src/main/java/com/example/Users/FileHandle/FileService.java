package com.example.Users.FileHandle;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public String uploadFile(MultipartFile file) throws IOException {
		FileEntity entity = new FileEntity();
		entity.setName(file.getOriginalFilename());
		entity.setType(file.getContentType());
		entity.setImagedata(FileUtils.compressImage(file.getBytes()));
		this.fileRepository.save(entity);

		if (entity != null) {
			return "file Uploaded successfully";
		} else {
			throw new IOException("File not found");
		}

	}

	public byte[] downloadfile(String filename) throws IOException {
		System.err.println(filename);

		FileEntity file = this.fileRepository.findByName(filename);

		System.out.println("sssss");

		System.err.println(file);

		byte[] imageData = FileUtils.decompressImage(file.getImagedata());

		System.err.println("sss");
		return imageData;

	}

}
