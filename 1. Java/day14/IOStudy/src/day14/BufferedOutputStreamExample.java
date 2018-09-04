package day14;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {

	static final String path = "example2.dat";
	
	public static void main(String[] args) throws IOException {

		FileOutputStream fos = new FileOutputStream(path);
		
		// 기본 512 바이트가 가득 차면 자동으로 저장한다.
		BufferedOutputStream out = new BufferedOutputStream(fos);
		out.write(10);
		out.write(10);
		byte[] data = {5,6,7,8,9};
		out.write(data);
				
		out.flush(); // 이걸 통해 중간 저장소가 가득 차지 않아도 데이터를 저장하라고 명령할 수 있다.
		System.out.println("파일에 데이터 썻음..");
		
		if(out != null) out.close();
		if(fos != null) fos.close();
	}

}
