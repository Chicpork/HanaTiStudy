package day9;

import day7.ArrayUtil;

public class LottoManager {
	private int[][] lottos;
	private int money;
	private int manualNum;
	private int manualCount=0;
	private int totalNum;
	private final int lottoPrice = 1000;

	public LottoManager() {
		this(2000, 1);
		this.totalNum = money / lottoPrice;
		this.lottos = new int[totalNum][7];
	}
	
	public LottoManager(int money) {
		this.money = money;
		this.totalNum = money / lottoPrice;
		this.manualNum = totalNum;
		this.lottos = new int[totalNum][7];
	}

	public LottoManager(int money, int manualNum) {
		this.money = money;
		this.manualNum = manualNum;
		this.totalNum = money / lottoPrice;
		this.lottos = new int[totalNum][7];
	}

	public int getManualNum() {
		return manualNum;
	}
	public int[][] getLottos() {
		return this.lottos;
	}

	public void manualLottos(int[] lottoNum) {
		ArrayUtil.sort(lottoNum);
		lottos[manualCount++]=lottoNum;
	}

	public void autoLottos() {
		int temp = 0;
		for (int k = manualNum; k < totalNum; k++) {
			for (int i = 0; i < lottos[k].length; i++) {
				temp = (int) (Math.random() * 45) + 1;
				HERE: for (int j = 0; j < i; j++) {
					if (lottos[k][j] == temp) {
						temp = (int) (Math.random() * 45) + 1;
						j=-1;
						continue HERE;
					}
				}
				lottos[k][i] = temp;
			}
			ArrayUtil.sort(lottos[k]);
		}
	}
	
	public boolean[] checkLotto(int[] lotto) {
		boolean[] error= {false,false,false,false};
		if(lotto.length != 7) {
			error[1]=true;
			error[0]=true;
		}
		for(int i=0;i<lotto.length;i++) {
			if(lotto[i]<0 || lotto[i]>45) {
				error[2]=true;
				error[0]=true;
			}
			for(int j=i+1;j<lotto.length;j++) {
				if(lotto[i]==lotto[j]) {
					error[3]=true;
					error[0]=true;
				}
			}
		}
		return error;
	}
	
	public void printLotto() {
		System.out.println("구매한 로또 번호 :");
		for(int[] i : lottos) {
			for(int j : i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
}
