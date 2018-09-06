package synchronize;

/**
 * 멀티스레드에 의해 공유되는 객체
 * @author 김기정
 */
public class MovieReserveSystem {
	
	private boolean[] tickets;

	public MovieReserveSystem() {
		this(10);
	}

	public MovieReserveSystem(int count) {
		tickets = new boolean[count];
	}
	
	// 동기화 처리 되지 않은 경우...
	public /*synchronized*/ boolean reserve(Member member) {
		System.out.println("reserve() Called...");
		synchronized (MovieReserveSystem.class) {
			System.out.println(member.getUserName() + "님 예약 신청!!!");
			// 원격의 데이터베이스 서버를 체크 한다는 의미로 1초 동안 대기
			try {
				System.out.println(member.getUserName() + "님 예약 진행중 .............");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < tickets.length; i++) {
				// 예약되어 있지 않았으면
				if (!tickets[i]) {
					// 예약으로 변경
					tickets[i] = true;
					System.out.println("▶ " + member.getUserName() + "님 "
							+ (i + 1) + "번 좌석 예약처리 완료~~");
					return tickets[i];
				}
			}
		}
		
		// 좌석이 없으면
		return false;
	}
	
	// 동기화 처리된 경우...
	/*
	public boolean reserve(Member member) {
		System.out.println("reserve() Called...");
		// 구체적 동기화 처리
		//synchronized (this) {
		synchronized (MovieReserveSystem.class) {
			System.out.println(member.getUserName() + "님 예약 신청!!!");
			// 원격의 데이터베이스 서버를 체크 한다는 의미로 1초 동안 대기
			try {
				System.out.println(member.getUserName() + "님 예약 진행중 .............");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
			for (int i = 0; i < tickets.length; i++) {
				// 예약되어 있지 않았으면
				if (!tickets[i]) {
					// 예약으로 변경
					tickets[i] = true;
					System.out.println("▶ " + member.getUserName() + "님 "
							+ (i + 1) + "번 좌석 예약처리 완료~~");
					return tickets[i];
				}
			}
		}
		// 좌석이 없으면
		return false;
	}
	*/
}