package com.example.Users.FileHandle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;

@Service
public class UserFileService {

	@Autowired
	private UserFileRepository fileRepository;

	@Autowired
	private UsersRepository usersRepository;

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

	public void exportToExcel(HttpServletResponse httpServletResponse) throws IOException {

		List<UsersFileEntity> entities = fileRepository.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("User info");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Discription");
		row.createCell(3).setCellValue("Salary");
		int dataRowIndex = 1;

		for (UsersFileEntity fileEntity : entities) {
			HSSFRow datarow = sheet.createRow(dataRowIndex);

			datarow.createCell(0).setCellValue(fileEntity.getId());
			datarow.createCell(1).setCellValue(fileEntity.getName());
			datarow.createCell(2).setCellValue(fileEntity.getDiscription());
			datarow.createCell(3).setCellValue(fileEntity.getSalary());
			dataRowIndex++;
		}

		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	public void generatExcel(HttpServletResponse httpServletResponse) throws IOException {
		List<Users> list = this.usersRepository.findAll();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();

		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Email");
		row.createCell(3).setCellValue("City");
		row.createCell(4).setCellValue("Password");
		row.createCell(5).setCellValue("Address");

		int dataRowNumber = 1;

		for (Users users : list) {
			XSSFRow datarow = sheet.createRow(dataRowNumber);
			datarow.createCell(0).setCellValue(users.getId());
			datarow.createCell(1).setCellValue(users.getName());
			datarow.createCell(2).setCellValue(users.getEmail());
			datarow.createCell(3).setCellValue(users.getCity());
			datarow.createCell(4).setCellValue(users.getPassword());
			datarow.createCell(5).setCellValue(users.getAdd());
			dataRowNumber++;
		}
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();

		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}
}
