package project_memo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDao {

	public static String loadFile(String directory, String filename) throws MemojangException, IOException {
		File file = new File(directory + filename);
		if (file.isDirectory()) {
			throw new MemojangException("폴더를 선택하였습니다.");
		}
		if (!file.exists()) {
			throw new MemojangException("해당하는 파일이 존재하지 않습니다.");
		}
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String main = "";
		String line = null;
		while ((line = reader.readLine()) != null) {
			main += line + "\n";
		}
		if (reader != null)	reader.close();
		return main;
	}

	public static void saveFile(String source, String directory, String filename) throws IOException {
		File file = new File(directory + filename);
		PrintWriter writer = new PrintWriter(new FileWriter(file));
		source = source.replaceAll("\n", "\r\n");
		writer.print(source);
		if(writer != null) writer.close();
	}
}
