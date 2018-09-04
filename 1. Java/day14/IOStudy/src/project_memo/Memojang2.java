package project_memo;

public class Memojang2 {

	public static void main(String[] args) {
		MemoUI2 memoui = new MemoUI2(500,500);
		FileDao fileDao = new FileDao();
		memoui.setFileDao(fileDao);
	}

}
