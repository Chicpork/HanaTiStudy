import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 클라이언트의 데이터 수신 및 처리
 * 
 * @author 정지원
 *
 */
public class Client extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;

	public Client(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	@Override
	public void run() {
		receive();
	}

	public void receive() {
		while (running) {
			System.out.println("Client start");
			String clientMessage = null;
			try {
				clientMessage = in.readLine();
				System.out.println("클라이언트 수신 데이터:" + clientMessage);

				if (clientMessage.equalsIgnoreCase("exit")) {
					break;
				}
				out.println(clientMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Client end");
		}
		
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	public void echo() {

	}
}
