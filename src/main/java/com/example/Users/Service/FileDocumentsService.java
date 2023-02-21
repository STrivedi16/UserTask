package com.example.Users.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.Interface.UsersPermission;
import com.example.Users.Repository.FileDocumentsRepository;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.FileDocumentsEntity;

@Service
public class FileDocumentsService {

	
	
	@Autowired
	private FileDocumentsRepository documentsRepository;
	
	@Autowired
	private UsersRepository repository;

	public void save(MultipartFile file) {
		try {
			System.err.println("in file service");

			List<FileDocumentsEntity> list =FileDocumentsHelper.importExcel(file);

			this.documentsRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getToExcel(HttpServletResponse response) throws IOException
	{
		
		List<UsersPermission> list=this.repository.findall(UsersPermission.class);
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet =workbook.createSheet("UserPermission");
		XSSFRow row=sheet.createRow(0);

		//Stream<UsersPermission> stream=list.stream().sorted();

	//UsersPermission	a[]=new UsersPermission [list.size()];
		

		//List<UsersPermission> list2=list.stream().sorted().collect(Collectors.toList());
		
		
		
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Role");
		row.createCell(3).setCellValue("Permissions");

		int dataRowIndex=1;
		
		for(UsersPermission permission:list )
		{
			XSSFRow dataRow=sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(permission.getId());
			dataRow.createCell(1).setCellValue(permission.getName());
			dataRow.createCell(2).setCellValue(permission.getRole());
			dataRow.createCell(3).setCellValue(permission.getPermissions());
			dataRowIndex++;
		}
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	
		
		
	}

}
