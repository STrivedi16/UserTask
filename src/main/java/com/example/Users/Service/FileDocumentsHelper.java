package com.example.Users.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Responce.ResourceNotFoundException;
import com.example.Users.entity.FileDocumentsEntity;
import com.lowagie.text.Row;


public class FileDocumentsHelper {

	@Autowired
	private FileDocumentsEntity documentsEntity;
	
	public  static Boolean checkFileFormate(MultipartFile file)
	{
		if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		{
			return true;
		}
		else {
			return false;
		}
	}
	public 	 static List<FileDocumentsEntity> importExcel(MultipartFile file) throws IOException
	{
		List<FileDocumentsEntity> list=new ArrayList<>();
		
		try {
			
			XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
			
			XSSFSheet sheet=workbook.getSheetAt(0);
			
			Iterator<org.apache.poi.ss.usermodel.Row> iterator=sheet.iterator();
			
			int rowNumber=0;
			while(iterator.hasNext())
			{
				org.apache.poi.ss.usermodel.Row row=iterator.next();
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cells=row.iterator();
				
				int cid=0;
				
				FileDocumentsEntity documentsEntity=new FileDocumentsEntity();
				
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					
					switch (cid) {
					case 0:
						
						documentsEntity.setId((int) cell.getNumericCellValue());
						break;
					case 1:
						documentsEntity.setName(cell.getStringCellValue());
						break;
						
					case 2:
						documentsEntity.setComments(cell.getStringCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(documentsEntity);
				
			}
			
			
			
		}catch (IOException e) {
			System.out.println("Some Error in file ");
			throw new IOException("not Strored");
		}
		
		return list;
	}
	
	
}
