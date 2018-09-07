import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP/IP 기반의 Socket 프로그래밍 원리
 * 
 * @author 정지원
 *
 */
public class SocketExample {

//	public static final String domain = "www.naver.com";
//	public static final String domain = "127.0.0.1";
//	public static final String domain = "localhost";
	public static final String domain = "192.168.0.113";
	public static final int port = 7777;

	public static void main(String[] args) {

		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		try {
//			Socket socket = new Socket(InetAddress.getByName(domain), port);
			socket = new Socket(domain, port);

			System.out.println("서버와 연결됨...");
			in = socket.getInputStream();
			out = socket.getOutputStream();
			/*
			 * out.write(10); System.out.println("서버에 데이터 전송"); int data = in.read();
			 * System.out.println("서버로부터 에코된 데이터: " + data);
			 */

			PrintWriter pw = new PrintWriter(out);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			Scanner scanner = new Scanner(System.in);
			while (true) {
				String inputMessage = scanner.nextLine();
				pw.println(inputMessage);
				pw.flush();
				if (inputMessage.equalsIgnoreCase("exit")) {
					break;
				}
				String serverMessage = br.readLine();
				System.out.println("서버로부터 에코된 데이터: " + serverMessage);

			}

		} catch (IOException e) {
			System.out.println("서버를 연결할 수 없습니다...");
		} finally {
			try {
//				out.close(); // out은 socket이 닫힐때 같이 닫히게 된다. 명시적으로 닫아주지 않아도 된다.
//				in.close(); // in은 socket이 닫힐때 같이 닫히게 된다. 명시적으로 닫아주지 않아도 된다.
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}