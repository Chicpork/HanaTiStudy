package day13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamExample {
	static final String path = "C:\\KOSTA187\\설치프로그램\\staruml-5.0-with-cm.exe";

	public static void main(String[] args) {
		InputStream in=null;
		try {
			in = new FileInputStream(path);
			System.out.println(in.available());
			
//			int b = in.read(); // 읽을 게 없을 땐 -1 반환
//			System.out.println(b);
			
//			int b = 0;
//			while((b=in.read()) != -1) {
//				System.out.println(b);
//			}
			
			
			// byte[](버퍼) 단위로 입력
			byte[] buffer = new byte[4*1024];
//			int count = in.read(buffer); // 데이터는 buffer에 저장이 되고 count에는 읽어온 바이트 수를 반환한다.
//			System.out.println(count);
//			for (byte b : buffer) {
//				System.out.println(b);
//			}
			
			int count = 0;
			int totalCount = 0;
			while((count=in.read(buffer)) != -1) {
				System.out.println(count);
				totalCount += count;
			}
			
			System.out.println(in.available());
			System.out.println(totalCount+" 파일 다 읽었음");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(in != null) in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
