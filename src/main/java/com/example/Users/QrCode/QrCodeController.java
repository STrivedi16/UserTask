package com.example.Users.QrCode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

@RestController
public class QrCodeController {

	@Autowired
	private QrCodeGenerater codeGenerater;

	@GetMapping(value = "/qrcode/{text}" ,produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getQRCode(@PathVariable("text") String text) throws WriterException, IOException
	{
		
			
		return	this.codeGenerater.generateQrCode(text);
		
	}

}
