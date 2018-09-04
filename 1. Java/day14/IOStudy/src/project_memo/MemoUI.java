package project_memo;

public class MemoUI {
	private int memoNum = 0;
	private int width;
	private int height;

	public MemoUI(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getMemoNum() {
		return memoNum;
	}

	public void makeFrame() {
		MemoFrame memoFrame = new MemoFrame(this, width, height);
		memoFrame.setInit();
		memoFrame.setContents();
		memoFrame.eventRegist();
		memoFrame.setVisible(true);
		memoNum++;
	}

}
