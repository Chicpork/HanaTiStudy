package kr.or.kosta.blog.guestbook.domain;

public class GuestBook {
	private String writer;
	private String content;
	private String regdate;
	
	public GuestBook() {
	}

	public GuestBook(String writer, String content, String regdate) {
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "GuestBook [writer=" + writer + ", content=" + content + ", regdate=" + regdate + "]";
	}
	
}
