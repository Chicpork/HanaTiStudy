package day7;

import java.util.Scanner;

public class LottoApp {

	public static void main(String[] args) {
		LottoManager lottoManager = new LottoManager(2000,1);
		Scanner scanner = new Scanner(System.in);
		
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
		lottoManager.autoLottos();
		
		System.out.println("구매한 로또 번호 :");
		for(int[] i : lottoManager.getLottos()) {
			for(int j : i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
		
		scanner.close();
	}
}
