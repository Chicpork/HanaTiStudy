package kr.or.kosta.blog.board.dao;

import java.util.List;

import kr.or.kosta.blog.board.domain.Board;

/**
 * 게시판 내용을 출력하기 위한 Dao 클래스
 * @author 정지원
 *
 */
public interface BoardDao {

	/**
	 * 게시판에 몇 개의 게시글이 존재하는지 보여주는 메소드
	 * 조회 기능도 포함되어 있음
	 * @param searchType 조회할 타입 (제목, 작성자)
	 * @param searchInput 조회할 내용
	 * @return
	 * @throws Exception
	 */
	public int countArticles(String searchType, String searchInput) throws Exception;
	
	/**
	 * 게시판을 페이지 별로 보여주기 위한 메소드
	 * @param pageNum 보여줄 페이지
	 * @param listNum 한 페이지에 몇 개의 게시글이 보여지는지 정하는 변수
	 * @param searchType 조회할 타입 (제목, 작성자)
	 * @param searchInput 조회할 내용
	 * @return
	 * @throws Exception
	 */
	public List<Board> listByPage(String pageNum, String listNum, String searchType, String searchInput) throws Exception;
	
	/**
	 * 최신 게시글을 얻어오는 메소드
	 * @param number 몇 개의 게시글을 얻어올 지 정해주는 변수
	 * @return
	 * @throws Exception
	 */
	public List<Board> newArticles(int number) throws Exception;
	
	/**
	 * 가장 조회수가 높은 게시글을 얻어오는 메소드
	 * @param number 몇 개의 게시글을 얻어올 지 정해주는 변수
	 * @return
	 * @throws Exception
	 */
	public List<Board> hotArticles(int number) throws Exception;
}
