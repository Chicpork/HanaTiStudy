package day14.character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {

	public static void main(String[] args) throws IOException {
		String path = "src/day13/BufferedInputStreamExample.java";
		FileReader in = new FileReader(path);

//		System.out.println((char) in.read());
//		char[] buffer = new char[1024];
//		int count = 0;
//		while ((count = in.read(buffer)) != -1) {
//			for (char c : buffer) {
//				System.out.print(c);
//			}
//		}

		BufferedReader br = new BufferedReader(in);
//		String txt = br.readLine();
//		System.out.println(txt);

		String txt = null;
		int number = 0;
		while ((txt = br.readLine()) != null) {
			System.out.println(++number+":"+txt);
		}
		br.close();
	}

}
