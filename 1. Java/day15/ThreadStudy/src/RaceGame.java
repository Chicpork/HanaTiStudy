public class RaceGame {

	public static void main(String[] args) {
		System.out.println("메인 스레드 시작");
		GameFrame frame = new GameFrame("경주게임");
		
		frame.setSize(1200, 400);
		frame.eventRegist();
		frame.setVisible(true);
		
		System.out.println("메인 스레드 끝");
	}

}
