package kr.or.kosta.blog.article.dao;

import kr.or.kosta.blog.article.domain.Article;

/**
 * 게시글을 CRUD 하는 Dao 클래스
 * @author 정지원
 *
 */
public interface ArticleDao {

	/**
	 * 게시글을 받아 DB에 생성하는 메소드
	 * @param article 새롭게 만들 게시글
	 * @throws Exception
	 */
	public abstract void create(Article article) throws Exception;

	/**
	 * DB에 저장된 게시글을 게시글 번호를 통해 받아오는 메소드
	 * @param articleId 게시글 번호
	 * @return 해당 게시글 번호의 게시글
	 * @throws Exception
	 */
	public abstract Article read(String articleId) throws Exception;
	
	/**
	 * 자신이 만든 게시글을 수정할 때 사용하게 되는 메소드
	 * @param articleId 수정할 게시글 번호
	 * @param subject 수정하는 게시글 제목
	 * @param passwd 수정하는 게시글 비밀번호
	 * @param ip 수정하는 아이피
	 * @param content 수정하는 내용
	 * @throws Exception
	 */
	public abstract void update(String articleId, String subject, String passwd, String ip, String content) throws Exception;

	/**
	 * 해당하는 게시글의 내용을 삭제하는 메소드
	 * @param articleId 삭제할 게시글 번호
	 * @throws Exception
	 */
	public abstract void delete(String articleId) throws Exception;
	
	/**
	 * 게시글 작성자를 얻어오기 위한 메소드
	 * @param articleId 게시글 번호
	 * @return
	 * @throws Exception
	 */
	public abstract String getWriter(String articleId) throws Exception;
	
	/**
	 * 게시글을 만든 사람과 수정하는 사람이 일치하는 지 확인 하고, 비밀번호 체크 하는 메소드
	 * @param articleId
	 * @param writer
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public abstract boolean certify(String articleId, String writer, String passwd) throws Exception;
	
	/**
	 * 게시글을 읽을 때 조회수 증가 메소드
	 * @param articleId
	 * @throws Exception
	 */
	public abstract void increaseHitCount(String articleId) throws Exception;

	/**
	 * 답글을 달 때 답글과 연관된 게시글들에서 수정이 필요한 값들을 업데이트 하기 위한 메소드
	 * @param groupNo
	 * @param levelNo
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public abstract String buildOrderNo(String groupNo, String levelNo, String orderNo) throws Exception;

}
