package day13;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {

	public static long copy(String srcPath, String destPath) throws IOException {
		InputStream input = null;
		OutputStream output = null;

		try {
			input = new FileInputStream(srcPath);
			output = new FileOutputStream(destPath);
			byte[] buffer = new byte[4 * 1024];
			int count = 0;
			long totalCount = 0;
			
			while ((count = input.read(buffer)) != -1) {
				output.write(buffer, 0, count);
				totalCount += count;
			}
			return totalCount;
		} finally {
			if (output != null) output.close();
			if (input != null) input.close();
		}
	}

	public static void main(String[] args) {

		String src = "C:\\KOSTA187\\설치프로그램\\jdk-8u181-windows-x64.exe";
		String dest = "eclipse.zip";

		long copySize = 0;
		try {
			copySize = copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(copySize + " 바이트 파일 복사 완료...");
	}

}
