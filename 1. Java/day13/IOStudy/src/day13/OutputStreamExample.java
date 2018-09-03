package day13;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamExample {
	
	static final String path = "example.dat";
	
	public static void main(String[] args) throws IOException {
		OutputStream out = new FileOutputStream(path,true);
//		out.write(65);
//		out.close();
//		System.out.println("파일에 1바이트 썻음");
		
		byte[] buffer = new byte[128];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = (byte) i;
		}
		out.write(buffer);
//		out.write(buffer, 0, buffer.length); // 위와 동일한 결과를 가져오게 됨.
		out.close();
		System.out.println("파일에 128바이트 썻음");
	}

}
