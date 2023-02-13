package com.example.Users.FileHandle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserFileService {

	@Autowired
	private UserFileRepository fileRepository;

	public void save(MultipartFile file) {

		try {
			List<UsersFileEntity> list = FileHepler.convertExcelToList(file);

			this.fileRepository.saveAll(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UsersFileEntity> getall() {
		return this.fileRepository.findAll();

	}
}
