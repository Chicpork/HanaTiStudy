package day7;

public class LottoManager {
	private int[][] lottos;
	private int money;
	private int manualNum;
	private int totalNum;
	private final int lottoPrice = 1000;

	public LottoManager() {
		this(2000, 1);
		this.totalNum = money / lottoPrice;
		this.lottos = new int[totalNum][7];
		System.out.println(totalNum);
	}

	public LottoManager(int money, int manualNum) {
		this.money = money;
		this.manualNum = manualNum;
	}

	public int[][] getLottos() {
		return lottos;
	}

	public void manualLottos(int[] lottoNum) {
		lottos[0]=lottoNum;
	}

	public void autoLottos() {
		int temp = 0;
		for (int k = manualNum; k < totalNum; k++) {
			for (int i = 0; i < lottos[k].length; i++) {
				temp = (int) (Math.random() * 45) + 1;
				HERE: for (int j = 0; j < i; j++) {
					if (lottos[k][j] == temp) {
						temp = (int) (Math.random() * 45) + 1;
						break HERE;
					}
				}
				lottos[k][i] = temp;
			}
			ArrayUtil.sort(lottos[k]);
		}
	}
}
