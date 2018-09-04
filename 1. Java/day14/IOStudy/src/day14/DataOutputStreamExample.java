package day14;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample {

	static final String path = "example3.dat";
	
	public static void main(String[] args) throws IOException {
		boolean flag = false;
		char c = '김';
		int age = 30;
		double weight = 77.7;
		String message = "입출력 프로그램입니다.";
		
		DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
//		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
		
		out.writeBoolean(flag);
		out.writeChar(c);
		out.writeInt(age);
		out.writeDouble(weight);
		out.writeUTF(message);
		
		out.close();
		System.out.println("썻음");
	}

}
