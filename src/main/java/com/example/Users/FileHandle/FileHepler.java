package com.example.Users.FileHandle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class FileHepler {

	@Autowired
	private UsersFileEntity entity;

	// this will check that file is of excel format
	public static boolean checkExcelFormet(MultipartFile file) {
		String contenttype = file.getContentType();

		if (contenttype.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;

		}

	}

	// convert excel to list

	public static List<UsersFileEntity> convertExcelToList(MultipartFile multipartFile) throws Exception {
		List<UsersFileEntity> list = new ArrayList<>();
		try {

			XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = sheet.iterator();

			int rowNumber = 0;

			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cid = 0;
				UsersFileEntity entity = new UsersFileEntity();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cid) {
					case 0:
						entity.setId((int) cell.getNumericCellValue());

						break;

					case 1:
						entity.setName(cell.getStringCellValue());
						break;
					case 2:
						entity.setDiscription(cell.getStringCellValue());
						break;

					case 3:
						entity.setSalary((long) cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;

				}
				list.add(entity);

			}

		} catch (Exception e) {
			throw new Exception("Not stored");
		}
		return list;

	}

	public void exportToExcel(HttpServletResponse httpServletResponse) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("User info");

	}
}
