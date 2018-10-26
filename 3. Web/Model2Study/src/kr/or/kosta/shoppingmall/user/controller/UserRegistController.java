package kr.or.kosta.shoppingmall.user.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.util.StringUtils;

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
public class UserRegistController implements Controller {

	private UserDao userDao;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		XMLObjectFactory factory = (XMLObjectFactory) request.getServletContext().getAttribute("objectFactory");
		userDao = (UserDao) factory.getDao(JdbcUserDao.class);
		User user = new User();

		Class aClass = user.getClass();
		Class[] paramTypes = new Class[1];
		paramTypes[0] = String.class; // get the actual param type

		Enumeration<String> users = request.getParameterNames();
		while (users.hasMoreElements()) {
			String fieldName = (String) users.nextElement();
			String methodName = "set" + StringUtils.capitalize(fieldName); // fieldName String
			Method m = null;
			try {
				m = aClass.getMethod(methodName, paramTypes);
				String result = (String) m.invoke(user, request.getParameter(fieldName)); // field value
				System.out.println(result);
			} catch (NoSuchMethodException nsme) {
				nsme.printStackTrace();
			} catch (IllegalAccessException iae) {
				iae.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		try {
			userDao.create(user);
			System.out.println(user);
			System.out.println("유저 생성 완료");
			mav.setView("redirect:/index.jsp");
		} catch (Exception e) {
			System.out.println("유저 생성 실패");
			mav.setView("redirect:/user/regist_form.jsp");
			return mav;
		}
		return mav;
	}

}
