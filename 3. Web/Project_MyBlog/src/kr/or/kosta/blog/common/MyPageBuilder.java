package kr.or.kosta.blog.common;

public class MyPageBuilder {
	private int totalArticles; // 전체 게시글 개수

	private int maxPageRowNum; // 한 페이지 게시글 최대 개수(기본 5개)
	private int maxArticleColNum; // 한 페이지 게시판 목록 최대 개수(기본 10개)

	private int totalPageNum; // 게시판 전체 목록 개수

	// 한 페이지 게시판 목록 시작과 마지막 값 계산
	private int startPageRowNum;
	private int lastPageRowNum;

	private int articleNum; // 해당 페이지 게시글 시작 번호

	public MyPageBuilder() {
		this(0, 5, 10);
	}

	public MyPageBuilder(int totalArticles, int maxPageRowNum, int maxArticleColNum) {
		this.totalArticles = totalArticles;
		this.maxPageRowNum = maxPageRowNum;
		this.maxArticleColNum = maxArticleColNum;
	}

	public void build(int pageNum) {
		totalPageNum = (int) Math.ceil((double) totalArticles / maxArticleColNum);
		startPageRowNum = maxPageRowNum * ((pageNum - 1) / maxPageRowNum) + 1;
		lastPageRowNum = maxPageRowNum * ((pageNum - 1) / maxPageRowNum + 1) > totalPageNum ? totalPageNum
				: maxPageRowNum * ((pageNum - 1) / maxPageRowNum + 1);
		articleNum = totalArticles - (pageNum - 1) * maxArticleColNum;
	}

	public int getTotalArticles() {
		return totalArticles;
	}

	public void setTotalArticles(int totalArticles) {
		this.totalArticles = totalArticles;
	}

	public int getMaxPageRowNum() {
		return maxPageRowNum;
	}

	public void setMaxPageRowNum(int maxPageRowNum) {
		this.maxPageRowNum = maxPageRowNum;
	}

	public int getMaxArticleColNum() {
		return maxArticleColNum;
	}

	public void setMaxArticleColNum(int maxArticleColNum) {
		this.maxArticleColNum = maxArticleColNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getStartPageRowNum() {
		return startPageRowNum;
	}

	public void setStartPageRowNum(int startPageRowNum) {
		this.startPageRowNum = startPageRowNum;
	}

	public int getLastPageRowNum() {
		return lastPageRowNum;
	}

	public void setLastPageRowNum(int lastPageRowNum) {
		this.lastPageRowNum = lastPageRowNum;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

}
