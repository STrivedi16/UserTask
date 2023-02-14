package com.example.Users.FileHandle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserComentsService {

	@Autowired
	private UserComentRepository comentRepository;

	public void save(MultipartFile file) {
		try {

			List<UserComent> list = UserComentFileHelper.storeToDb(file);

			this.comentRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dataToExcel(HttpServletResponse response) throws IOException {
		List<UserComent> list = this.comentRepository.findAll();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("data1");

		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Coments");
		int dataIndexRow = 1;

		for (UserComent coment : list) {
			XSSFRow datarow = sheet.createRow(dataIndexRow);
			datarow.createCell(0).setCellValue(coment.getId());
			datarow.createCell(1).setCellValue(coment.getName());
			datarow.createCell(2).setCellValue(coment.getComent());
			dataIndexRow++;

		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
