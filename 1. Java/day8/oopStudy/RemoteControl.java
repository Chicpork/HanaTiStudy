package day8;

public interface RemoteControl {
	public static final int MAX_VOLUME = 50;
	public static final int MIN_VOLUME = 0;
	public static final int MAX_CHANNEL = 100;
	public static final int MIN_CHANNEL = 100;
	
	public void turnOn();
	public void turnOff();
	public void volumeUp();
	public void volumeDown();
	public void setChannel(int channel);

}
