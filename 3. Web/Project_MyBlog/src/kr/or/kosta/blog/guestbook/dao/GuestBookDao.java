package kr.or.kosta.blog.guestbook.dao;

import java.util.List;

import kr.or.kosta.blog.guestbook.domain.GuestBook;

public interface GuestBookDao {
	
	public void create(GuestBook guestbook) throws Exception;
	
	public void delete(GuestBook guestbook) throws Exception;
	
	public List<GuestBook> listAll() throws Exception;
	
}
