package kr.or.kosta.blog.board.dao;

import java.util.List;

import kr.or.kosta.blog.board.domain.Board;

public interface BoardDao {

	public List<Board> listAll() throws Exception;
	
	public List<Board> listByPage(String pageNum, String listNum, String searchType, String searchInput) throws Exception;
}
