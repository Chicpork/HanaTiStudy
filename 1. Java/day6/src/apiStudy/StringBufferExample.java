package apiStudy;
public class StringBufferExample {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("Java");
		System.out.println(sb.toString());
		sb.append("Programming");
		System.out.println(sb.toString());
		
		StringBuffer sb2 = new StringBuffer("정지원");
		sb2.append("바보").append("AAAA").append("BBBB");
		System.out.println(sb2.toString());
		
		sb.replace(0, 4, "C++");
		System.out.println(sb);
		
		sb.reverse();
		System.out.println(sb);
		
		String str = sb.substring(11);
		System.out.println(str);
	}

}
