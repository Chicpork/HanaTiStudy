package day14.character;

import java.io.IOException;
import java.io.StringReader;

public class MemoryStreamExample {

	public static void main(String[] args) throws IOException {
		String message = "187기 하나금유 TI";
		StringReader sr = new StringReader(message);
		System.out.println((char)sr.read());
	}

}
