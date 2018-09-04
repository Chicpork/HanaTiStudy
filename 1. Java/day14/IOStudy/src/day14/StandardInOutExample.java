package day14;

import java.io.IOException;

public class StandardInOutExample {

	public static void main(String[] args) throws IOException {
//		System.out.println(System.in);
//		System.out.println(System.out);
		
		System.out.print("당신의 이름 : ");
		
		// 키보드로부터 받는 입력을 저장할 공간 생성
		byte[] buffer = new byte[100];
		int count = System.in.read(buffer); // count를 이용해 입력받은 크기를 저장함.
		
		// 문자 디코딩 처리;
		String name = new String(buffer, 0, count-2); // 입력 후 엔터를 인식하기 때문에 count에서 2를 뺌
		System.out.println("당신의 입력 이름은 "+name+" 입니다..");
		
		System.out.print("당신의 나이 : ");
		count = System.in.read(buffer);
		String age = new String(buffer, 0, count-2);
		System.out.println(Integer.parseInt(age));

	}

}
