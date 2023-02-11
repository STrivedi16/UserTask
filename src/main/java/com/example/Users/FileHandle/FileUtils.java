package com.example.Users.FileHandle;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public class FileUtils {

	public static byte[] compressImage(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] temp = new byte[4 * 1024];
		while (!deflater.finished()) {
			int size = deflater.deflate(temp);
			outputStream.write(temp, 0, size);

		}
		try {
			outputStream.close();
		} catch (Exception ingnore) {

		}

		return outputStream.toByteArray();
	}
}
