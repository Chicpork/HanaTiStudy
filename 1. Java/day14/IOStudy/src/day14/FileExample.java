package day14;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class FileExample {

	public static void main(String[] args) throws IOException {
		String path = "c:/some.dat";
		File file = new File(path);

		System.out.println("존재여부 : " + file.exists());

		path = "C:\\KOSTA187\\설치프로그램\\staruml-5.0-with-cm.exe";
		file = new File(path);

		System.out.println("파일용량: " + file.length());

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(file.lastModified());
		System.out.println(String.format("변경날짜: %1$tF %1$tp %1$tI:%1$tM:%1$tS", cal));

		path = "c:/KOSTA187";
		file = new File(path);
		/*
		 * if(file.isDirectory()) { System.out.println("디렉토리");
		 * System.out.println(path); for (String string : file.list()) {
		 * System.out.println("\t"+string); } }else if(file.isFile()) {
		 * System.out.println("파일"); }
		 */

		File[] lists = file.listFiles();
		System.out.println(path);
		for (File file2 : lists) {
			if (file2.isDirectory()) {
				System.out.println("<DIR>\t" + file2.getName());
			} else {
				System.out.println(file2.getName() + "\t" + file2.length());
			}

		}

		path = "example.dat";
		file = new File(path);
		System.out.println("절대경로 : " + file.getAbsolutePath());

//		System.out.println(file.toURL()); // 이제 이건 사용하지 않고 toURI()를 사용한다.
		System.out.println(file.toURI());

		// 조작관련 기능들..
		path = "xxx.dat";
		file = new File(path);
		System.out.println(file.createNewFile()); // 이건 임시 파일이 아니라 실제 파일이 만들어지는 것

		// 임시 파일을 만들고 데이터 저장하기
		File tempF = File.createTempFile("some", ".dat");
		System.out.println(tempF.getAbsolutePath());
		FileOutputStream out = new FileOutputStream(tempF);
		out.write(10);
		out.close();
		tempF.deleteOnExit(); // 종료시 임시 파일 삭제

		// 지정 경로에 폴더 만들기
		File dir = new File("C:/KOSTA187/xxx");
		System.out.println(dir.mkdirs());
		dir.delete();

		File[] drives = File.listRoots();
		for (File file2 : drives) {
			System.out.println(file2);
		}
	}

}
