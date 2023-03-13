package com.example.Users.QrCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import io.swagger.v3.oas.annotations.servers.Server;



@Component
public class QrCodeGenerater {

	private static final int WIDTH=300;
	
	private static final int HEIGHT=300;
	
	private static final String FORMAT="png";
	
	// above is for image size and foramt.
	
	public byte[] generateQrCode(String text) throws WriterException, IOException
	{
		
		
		QRCodeWriter codeWriter=new QRCodeWriter();	// this class is form com.google.zxing to write the qr code
		
		BitMatrix bitMatrix=codeWriter.encode(text, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
		
		/// this class is used givwe the format of Qr 
		
		ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
		
		MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, arrayOutputStream);
		// this class is used to convert the BitMatrix 	Object into PNG, JPG, or gif format 
		
		return arrayOutputStream.toByteArray();
	}
}
