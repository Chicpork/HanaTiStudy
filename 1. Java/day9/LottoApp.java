package day9;

import java.util.Scanner;

import day7.ArrayUtil;

public class LottoApp {
	public static void main(String[] args) {
		LottoManager lottoManager;
		OUT:
		while(true) {
			System.out.println("========= 로또 머신  ========");
			System.out.println("1. 자동");
			System.out.println("2. 수동");
			System.out.println("3. 수동과 자동 혼합");
			System.out.println("4. 종료");
			System.out.println("=========================");
			Scanner scanner = new Scanner(System.in);
			int menu = scanner.nextInt();
			switch(menu) {
			case 1:
				System.out.println("돈을 입력하세요");
				lottoManager = new LottoManager(scanner.nextInt(),0);
				lottoManager.autoLottos();
				lottoManager.printLotto();
				break;
			case 2:
				System.out.println("돈을 입력하세요");
				lottoManager = new LottoManager(scanner.nextInt());
				for(int i=0;i<lottoManager.getManualNum();i++) {
					System.out.println("로또 번호를 입력하세요");
					String str = scanner.nextLine();
					int[] number = ArrayUtil.stringToInt(str, " ");
					boolean[] error = lottoManager.checkLotto(number);
					if(error[0]) {
						System.out.println("=== 숫자가 잘못 입력되었습니다 ===");
						System.out.println("오류 정보 :");
						if(error[1]) {
							System.out.println("입력된 숫자 개수를 잘못 입력하엿습니다.");
						}
						if(error[2]) {
							System.out.println("숫자 범위가 1 ~ 45 안이어야 합니다.");
						}
						if(error[3]) {
							System.out.println("중복된 숫자가 존재합니다.");
						}
						System.out.println();
						i--;
						continue;
					}
					lottoManager.manualLottos(number);
				}
				break;
			case 3:
				System.out.println("돈과 수동으로 입력할 로또 개수를 입력하세요");
			
			case 4:
				break OUT;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		System.out.println("로또 프로그램 종료..");
	}
}
