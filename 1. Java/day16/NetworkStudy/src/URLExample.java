import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class URLExample {

	public static void main(String[] args) {
//		String urlString = "http://www.daum.net:80/index.html";
		String urlString = "https://news.v.daum.net/v/20180907095347180?rcmd=rn";

		try {
			URL url = new URL(urlString);
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());

			InputStream in = url.openStream();
//			System.out.println(in);
//			System.out.println(in.read());
			
			/*byte[] buffer = new byte[1024];
			int count = 0;
			while((count = in.read(buffer)) != -1) {
				String text = new String(buffer, 0, count);
				System.out.println(text);
				
			}*/
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
			String text = null;
			while((text = reader.readLine()) != null) {
				System.out.println(text);
			}
			
		
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
