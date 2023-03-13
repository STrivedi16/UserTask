package com.example.Users.QrCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.Users.FileHandle.FileService;
import com.example.Users.Redis.ResponceEntityDeserializer;
import com.example.Users.Repository.UsersRepository;
import com.example.Users.entity.Users;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Component
public class QrGenerator {

	
	public static final int WIDTH=300;
	public static final int LENGTH=300;
	public static final String FORMAT="png";
	
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private FileService fileService;
	
	public byte[] generateQR(int id) throws Exception
	{
		QRCodeWriter  codeWriter=new QRCodeWriter();
		
		Users users=this.repository.findById(id).orElseThrow(()-> new Exception("User not found"));		
		
		
		
		BitMatrix  bitMatrix=codeWriter.encode(users.toString(), BarcodeFormat.QR_CODE, WIDTH, LENGTH);
		
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		
		MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, arrayOutputStream);
		
		return arrayOutputStream.toByteArray();
		
	}
	
	public byte[] videoInQR() 
	{
		QRCodeWriter  codeWriter=new QRCodeWriter();
		
//		byte[] bs=this.fileService.getVideo(id);
//		
		
		
		String url="https://www.youtube.com/watch?v=hwNWx1GTSKo";
		String qrCodeData = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=" + url;
		
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, WIDTH, LENGTH);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		
		try {
			MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, arrayOutputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arrayOutputStream.toByteArray();
		
	}
}
