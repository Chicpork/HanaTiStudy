package day13;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStreamExample {

	static final String path = "C:\\KOSTA187\\설치프로그램\\staruml-5.0-with-cm.exe";

	public static void main(String[] args) throws IOException {
		// Node Stream
		InputStream fin = null;
		fin = new FileInputStream(path);
		
		// Filter Stream
		BufferedInputStream in = null;
		in = new BufferedInputStream(fin); // 512바이트 배열
		
		in.mark(0);
		System.out.println(in.read());
		System.out.println(in.read());
		System.out.println(in.read());
		in.skip(10);
		System.out.println(in.read());
		System.out.println("=======");
        // 아까 지정한 위치로 돌아감.
		in.reset();
        
        // 다시 깃발 꽃은 곳에서 한 칸 이동 하고
		System.out.println(in.read());
        // 이동한 위치에서 깃발을 꽃아 놓는다.
		in.mark(0);
		System.out.println(in.read());
		System.out.println(in.read());
		System.out.println("=======");
		in.reset();
		System.out.println(in.read());
		in.close();
	}

}
