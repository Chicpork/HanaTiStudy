package day14;

import java.io.IOException;
import java.util.Scanner;

public class ScannerExample {

	public static void main(String[] args) throws IOException {

		System.out.print("당신의 이름 : ");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println(scanner.nextLine());
		
		System.out.print("당신의 나이 : ");
		System.out.println(scanner.nextInt());

		scanner.close();
	}

}
