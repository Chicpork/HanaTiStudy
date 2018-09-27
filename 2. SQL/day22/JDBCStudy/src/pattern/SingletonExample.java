package pattern;

import java.io.IOException;

public class SingletonExample {

	public static void main(String[] args) throws IOException {
		Logger logger = Logger.getInstance();
		logger.log("야");
		
		Runtime runtime = Runtime.getRuntime();
		runtime.exec("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

	}

}
