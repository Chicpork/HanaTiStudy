package kr.or.kosta.blog.guestbook.dao;

import java.util.List;

import kr.or.kosta.blog.guestbook.domain.GuestBook;

/**
 * 방명록을 추상화한 클래스
 * @author 정지원
 *
 */
public interface GuestBookDao {
	
	/**
	 * 새 방명록을 추가해주는 메소드
	 * @param guestbook 추가할 방명록 내용
	 * @throws Exception
	 */
	public void create(GuestBook guestbook) throws Exception;
	
	/**
	 * 방명록에 존재하는 모든 방명록 리스트를 반환하는 메소드
	 * @param pageNum
	 * @param listNum
	 * @return
	 * @throws Exception
	 */
	public List<GuestBook> listAll(String pageNum, String listNum) throws Exception;
	
	/**
	 * 전체 방명록 개수를 반환하는 메소드
	 * @return
	 * @throws Exception
	 */
	public int countGuestBooks() throws Exception;
	
	/**
	 * 가장 최근에 만들어진 방명록 리스트를 number만큼 List로 반환하는 메소드
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public List<GuestBook> newGuestBook(int number) throws Exception;
}
