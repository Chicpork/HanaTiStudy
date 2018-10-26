package kr.or.kosta.shoppingmall.user.service;

import java.util.List;

import kr.or.kosta.shoppingmall.common.dao.DaoFactory;
import kr.or.kosta.shoppingmall.common.dao.JdbcDaoFactory;
import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

public class UserServiceImpl implements UserService {
	
	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User search(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() throws Exception {
		return userDao.listAll();
	}

}
