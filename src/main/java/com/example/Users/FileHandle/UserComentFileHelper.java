package com.example.Users.FileHandle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UserComentFileHelper {

//	@Autowired
//	private UserComent coment;

	public static boolean checkFileFormat(MultipartFile file) {
		if (file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	public static List<UserComent> storeToDb(MultipartFile file) throws Exception {
		List<UserComent> coments = new ArrayList<>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

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
				UserComent coment = new UserComent();
				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:

						coment.setId((int) cell.getNumericCellValue());
						break;
					case 1:
						coment.setName(cell.getStringCellValue());
						break;
					case 2:
						coment.setComent(cell.getStringCellValue());
						break;
					default:
						break;
					}
					cid++;
				}

				coments.add(coment);
			}

		} catch (Exception e) {
			throw new Exception("not stored");
		}
		return coments;
	}
}
