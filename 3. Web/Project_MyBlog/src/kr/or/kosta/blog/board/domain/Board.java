package kr.or.kosta.blog.board.domain;

/**
 * 게시판을 추상화한 클래스
 * @author 정지원
 *
 */
public class Board {

	private String articleId;
	private String subject;
	private String writer;
	private String regdate;
	private String ip;
	private String hitcount;
	private int levelNo;
	
	public Board() {
	}
	
	public Board(String articleId, String subject, String writer, String regdate, String ip, String hitcount,
			int levelNo) {
		super();
		this.articleId = articleId;
		this.subject = subject;
		this.writer = writer;
		this.regdate = regdate;
		this.ip = ip;
		this.hitcount = hitcount;
		this.levelNo = levelNo;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHitcount() {
		return hitcount;
	}

	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	@Override
	public String toString() {
		return "Board [articleId=" + articleId + ", subject=" + subject + ", writer=" + writer + ", regdate=" + regdate
				+ ", ip=" + ip + ", hitcount=" + hitcount + ", levelNo=" + levelNo + "]";
	}

}
