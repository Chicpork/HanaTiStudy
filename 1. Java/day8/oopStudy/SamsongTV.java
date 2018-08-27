package day8;

/**
 * 다중구현을 이용한 클래스 정의
 * @author 정지원
 *
 */
public class SamsongTV implements Browsable, RemoteControl {

	private int currentChannel;
	private int currentVolume;
	
	public SamsongTV() {
	}

	public SamsongTV(int currentChannel, int currentVolume) {
		this.currentChannel = currentChannel;
		this.currentVolume = currentVolume;
	}
	

	public int getCurrentChannel() {
		return currentChannel;
	}

	public void setCurrentChannel(int currentChannel) {
		this.currentChannel = currentChannel;
	}

	public int getCurrentVolume() {
		return currentVolume;
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
	}

	@Override
	public void turnOn() {
		System.out.println("전원을 켭니다..");
	}

	@Override
	public void turnOff() {
		System.out.println("전원을 끕니다..");
	}

	@Override
	public void volumeUp() {
		if(!(currentVolume >= MAX_VOLUME)) {
			currentVolume++;
		}
	}

	@Override
	public void volumeDown() {
		currentVolume--;
	}

	@Override
	public void setChannel(int channel) {
		currentChannel = channel;
	}

	@Override
	public void surfing(String url) {
		System.out.println(url+"에 접속합니다..");
	}
	
	public static void main(String[] args) {
		RemoteControl tv = new SamsongTV();
		tv.turnOn();
		tv.setChannel(23);
		
		System.out.println(((SamsongTV)tv).getCurrentChannel());
		tv.volumeUp();
		tv.volumeUp();
		tv.volumeUp();
		
		System.out.println(RemoteControl.MAX_CHANNEL);
		
		tv.turnOff();
	}

}
