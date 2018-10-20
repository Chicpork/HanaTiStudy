package kr.or.kosta.blog.article.dao;

import kr.or.kosta.blog.article.domain.Article;

public interface ArticleDao {

	public abstract void create(Article article) throws Exception;

	public abstract Article read(String articleId) throws Exception;
	
	public abstract void update(String articleId, String subject, String passwd, String ip, String content) throws Exception;

	public abstract void delete(String articleId) throws Exception;
	
	public abstract String getWriter(String articleId) throws Exception;
	
	public abstract boolean certify(String articleId, String writer, String passwd) throws Exception;
	
	public abstract void increaseHitCount(String articleId) throws Exception;

	public abstract String buildOrderNo(String groupNo, String levelNo, String orderNo) throws Exception;

}
