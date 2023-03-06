package com.example.Users.FileHandle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	
	public String uploadMultiFile(MultipartFile[] file) throws Exception {

		
			
			int a=0;
			
				for(MultipartFile file2: file)
				{
					FileEntity entity=new FileEntity();
					entity.setImagedata(file2.getBytes());
					entity.setName(file2.getName());
					entity.setType(file2.getContentType());
					
					this.fileRepository.save(entity);
					a++;
				}
			
			
			if(a!=0)
			{
				return "File Has been stored";
			}
			else {
				throw new Exception("File not stored");
			}
			
			
		


	}
	
	public byte[] getMultipleFile() throws IOException {
		
		List<FileEntity> fileEntities=this.fileRepository.findAll();
		
		
		
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		
		
		
		ZipOutputStream outputStream=new ZipOutputStream(arrayOutputStream);
		
		
		for(FileEntity file: fileEntities)
		{
			ZipEntry entry=new ZipEntry(file.getName());
			
			outputStream.putNextEntry(entry);
			
			outputStream.write(file.getImagedata());
			
			outputStream.closeEntry();
			
		}
		
		
		
		
		return arrayOutputStream.toByteArray();
		
		
		
	}
	
	public String uploadVideo(MultipartFile file) throws IOException
	{
		FileEntity entity=new FileEntity();
		entity.setName(file.getName());
		entity.setImagedata(file.getBytes());
		entity.setType(file.getContentType());
		
		this.fileRepository.save(entity);
		
		return "Success";
		
	}

	public byte[] downloadfile(int filename) throws Exception {
		

		FileEntity file = this.fileRepository.findById(filename);

		

		byte[] imageData = FileUtils.decompressImage(file.getImagedata());

		
		return imageData;

	}
	
	
	public byte[] getImage(int id )
	{
		FileEntity entity=this.fileRepository.findById(id);
		
		byte [] imageData= entity.getImagedata();
		
		return imageData;
	}
	
	public byte[] getVideo(int id)
	{
		FileEntity entity=this.fileRepository.findById(id);
		
		byte [] videoData=entity.getImagedata();
		
		return videoData;
	}
	
//	public FileEntity download(int fileid)
//	{
//		FileEntity entity=this.fileRepository.findById(fileid).orElseThrow(()-> new ResourceNotFoundException());
//	
//	
//		return entity;
//	}

}
