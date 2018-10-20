package kr.or.kosta.blog.common.dao;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.dao.ArticleDao;
import kr.or.kosta.blog.article.dao.JdbcArticleDao;
import kr.or.kosta.blog.board.dao.BoardDao;
import kr.or.kosta.blog.board.dao.JdbcBoardDao;
import kr.or.kosta.blog.guestbook.dao.GuestBookDao;
import kr.or.kosta.blog.guestbook.dao.JdbcGuestBookDao;
import kr.or.kosta.blog.user.dao.JdbcUserDao;
import kr.or.kosta.blog.user.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			if(DaoFactory.getDataSource() == null) {
				method.invoke(dao, createDataSource());
			} else {
				method.invoke(dao, getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	@Override
	public GuestBookDao getGuestBookDao() {
		GuestBookDao dao = new JdbcGuestBookDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			if(DaoFactory.getDataSource() == null) {
				method.invoke(dao, createDataSource());
			} else {
				method.invoke(dao, getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	@Override
	public BoardDao getBoardDao() {
		BoardDao dao = new JdbcBoardDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			if(DaoFactory.getDataSource() == null) {
				method.invoke(dao, createDataSource());
			} else {
				method.invoke(dao, getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
	@Override
	public ArticleDao getArticleDao() {
		ArticleDao dao = new JdbcArticleDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			if(DaoFactory.getDataSource() == null) {
				method.invoke(dao, createDataSource());
			} else {
				method.invoke(dao, getDataSource());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
}
