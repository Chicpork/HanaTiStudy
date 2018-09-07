import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * DNS와의 통신을 통해 IP <-> Domain 정보 제공
 * 
 * @author 정지원
 *
 */
public class InetAddressExample {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(ia.getHostAddress());
		
		
//		String domainName = "www.daum.net";
		String domainName = "www.naver.com";
		InetAddress ia2 = InetAddress.getByName(domainName);
		System.out.println(ia2.getHostAddress());
		
		InetAddress[] ia3 = InetAddress.getAllByName(domainName);
		for (InetAddress inetAddress : ia3) {
			System.out.println(inetAddress);
		}
	}

}
