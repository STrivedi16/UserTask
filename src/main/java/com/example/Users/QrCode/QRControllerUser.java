package com.example.Users.QrCode;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@RestController
public class QRControllerUser {

	@Autowired
	private QrGenerator generator;
	
	@GetMapping(value = "/qrUser/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] qrGenerate(@PathVariable("id") int id ) throws Exception
	{
		return this.generator.generateQR(id);
	}
	
	@RequestMapping("/qrcode")
	public void generateQrCode(HttpServletResponse response) throws IOException, WriterException {
	    String videoUrl = "https://www.youtube.com/watch?v=hwNWx1GTSKo"; // Replace with your video URL
	   // String qrCodeData = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=" + videoUrl;

	    BitMatrix bitMatrix = new MultiFormatWriter().encode(videoUrl, BarcodeFormat.QR_CODE, 300, 300);
	    MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());

	    response.setContentType(MediaType.IMAGE_PNG_VALUE);
	}

}
