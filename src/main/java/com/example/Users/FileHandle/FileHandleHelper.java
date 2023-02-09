package com.example.Users.FileHandle;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileHandleHelper {

	public final String UPLOAD_DIR = "C:\\Users\\hp\\Downloads\\UserTask\\src\\main\\resources\\static\\Images";

	public Boolean uploadFile(MultipartFile file) {
		Boolean f = false;

		try {

			InputStream is = file.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);

			FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR + "\\" + file.getOriginalFilename());

			fileOutputStream.write(data);

			fileOutputStream.flush();
			fileOutputStream.close();
			f = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return f;
	}

}
