package day14.character;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {

	public static void main(String[] args) throws IOException {
		String path = "src/day13/BufferedInputStreamExample.java";
		FileReader in = new FileReader(path);
		System.out.println((char)in.read());
		char[] buffer = new char[1024];
		int count = 0;
		while ((count = in.read(buffer)) != -1) {
			for (char c : buffer) {
				System.out.print(c);
			}
		}
		System.out.println(count);

		FileInputStream fis = new FileInputStream(path);
		byte[] buffer2 = new byte[1024];
		count = 0;
		while ((count = fis.read(buffer2)) != -1) {
			for (byte b : buffer2) {
				System.out.print((char)b);
			}
		}
		in.close();
		fis.close();
	}

}
