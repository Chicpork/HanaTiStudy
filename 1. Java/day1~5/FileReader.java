/**
 * 원하는 파일을 읽기<br>
 * java FileReader [읽고자 하는 파일명1] [읽고자 하는 파일명2]
 * 
 * @author 정지원
 *
 */
public class FileReader {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("사용법 : java FileReader [읽고자 하는 파일명1] [읽고자 하는 파일명2]");
			return;
		} else if (args.length == 1) {
			String fileName1 = args[0];
			System.out.println(fileName1);
		} else if (args.length == 2) {
			String fileName1 = args[0];
			String fileName2 = args[1];
			System.out.println(fileName1);
			System.out.println(fileName2);
		} else {
			System.out.println("사용법 : java FileReader [읽고자 하는 파일명1] [읽고자 하는 파일명2]");
			return;
		}
	}

}
