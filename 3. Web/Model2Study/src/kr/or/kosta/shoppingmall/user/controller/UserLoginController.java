package kr.or.kosta.shoppingmall.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;
import kr.or.kosta.shoppingmall.common.service.XMLObjectFactory;
import kr.or.kosta.shoppingmall.user.dao.JdbcUserDao;
import kr.or.kosta.shoppingmall.user.dao.UserDao;
import kr.or.kosta.shoppingmall.user.domain.User;

/**
 * /hello.mall 요청에 대한 처리 클래스
 * 
 * @author 정지원
 *
 */
public class UserLoginController implements Controller {

	private UserDao userDao;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		XMLObjectFactory factory = (XMLObjectFactory) request.getServletContext().getAttribute("objectFactory");
		userDao = (UserDao) factory.getDao(JdbcUserDao.class);
		
		User user = null;
		String id = null;
		String passwd = null;
		id = request.getParameter("id");
		passwd = request.getParameter("passwd");
		if (id != null && id.length() != 0 && passwd != null && passwd.length() != 0) {
			try {
				user = userDao.certify(id, passwd);
			} catch (Exception e) {
				throw new ServletException("userDao.certify() 에러 발생", e);
			}
		} 
		if (user == null) {
			mav.addObject("isLogin", false);
			mav.setView("/index.jsp");
		} else {
			Cookie cookie = new Cookie("userId", id);
	    	cookie.setPath("/");
	    	response.addCookie(cookie);
	    	mav.setView("redirect:/index.jsp");
		}
		return mav;
	}

}
