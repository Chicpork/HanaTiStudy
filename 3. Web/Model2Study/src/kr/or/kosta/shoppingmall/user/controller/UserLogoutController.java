package kr.or.kosta.shoppingmall.user.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.common.controller.Controller;
import kr.or.kosta.shoppingmall.common.controller.ModelAndView;

/**
 * /hello.mall 요청에 대한 처리 클래스
 * 
 * @author 정지원
 *
 */
public class UserLogoutController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					userCookie = cookie;
					break;
				}
			}
		}
		if (userCookie != null) {
			userCookie.setPath("/");
			userCookie.setMaxAge(0);
			response.addCookie(userCookie);
		}
		mav.setView("redirect:/index.jsp");
		return mav;
	}

}
