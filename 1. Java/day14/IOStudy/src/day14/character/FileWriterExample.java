package day14.character;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

	public static void main(String[] args) throws IOException {
		String path = "example5.txt";
		
		String message = "아 배고파...";
		
		FileWriter out = new FileWriter(path);
		
//		char[] chars = new char[100];
//		message.getChars(0, message.length(), chars, 0);
//		out.write(chars);
		
		BufferedWriter bw= new BufferedWriter(out);
		bw.write(message);
		bw.close();
		out.close();
	}

}
