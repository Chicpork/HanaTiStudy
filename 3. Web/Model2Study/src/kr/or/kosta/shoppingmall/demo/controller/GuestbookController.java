package kr.or.kosta.shoppingmall.demo.controller;

import javax.servlet.ServletException;
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
public class GuestbookController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		String message = "방명록 목록입니다.";
		request.getSession().setAttribute("message", message);

//		mav.addObject("message", message);

		mav.setView("redirect:/model2/demo/guestbook.jsp");
		return mav;
	}

}
