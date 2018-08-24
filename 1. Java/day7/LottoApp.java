package day7;

import java.util.Scanner;

public class LottoApp {

	public static void main(String[] args) {
		LottoManager lottoManager = new LottoManager(2000,1);
		Scanner scanner = new Scanner(System.in);
		String[] arr = scanner.nextLine().split(" ");
		int[] number = new int[arr.length];
		for(int i=0;i<arr.length;i++) {
			number[i] = Integer.parseInt(arr[i]);
		}
		
	}
}
