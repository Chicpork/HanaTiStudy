package kr.or.kosta.blog.article.domain;

/**
 * 게시판 게시글을 추상화한 클래스
 * @author 정지원
 *
 */
public class Article {
	private String subject;
	private String writer;
	private String regdate;
	private String ip;
	private String hitcount;
	private String content;
	private String passwd;
	private String groupNo;
	private String levelNo;
	private String orderNo;
	
	public Article() {
	}

	public Article(String subject, String writer, String regdate, String ip, String hitcount, String content,
			String passwd, String groupNo, String levelNo, String orderNo) {
		this.subject = subject;
		this.writer = writer;
		this.regdate = regdate;
		this.ip = ip;
		this.hitcount = hitcount;
		this.content = content;
		this.passwd = passwd;
		this.groupNo = groupNo;
		this.levelNo = levelNo;
		this.orderNo = orderNo;
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
		String[] ips = ip.split("\\.");
		return ips[0] + "." + ips[1] + "." + ips[2] + ".xxx";
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "Article [subject=" + subject + ", writer=" + writer + ", regdate=" + regdate + ", ip=" + ip
				+ ", hitcount=" + hitcount + ", content=" + content + ", passwd=" + passwd + ", groupNo=" + groupNo
				+ ", levelNo=" + levelNo + ", orderNo=" + orderNo + "]";
	}

}
