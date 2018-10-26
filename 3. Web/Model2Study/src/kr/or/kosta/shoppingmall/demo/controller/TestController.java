package kr.or.kosta.shoppingmall.demo.controller;

import java.util.Calendar;

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
public class TestController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ModelAndView mav = new ModelAndView();
		
		Calendar calendar = Calendar.getInstance();
		String test = String.format("%1$tF %1$tT", calendar);
		
		// 모델 앤 뷰에 저장
		mav.addObject("test", test);

		// RequestDispatcher를 사용하여 뷰(JSP)로 디스패치
		mav.setView("/demo/test.jsp");
		return mav;
	}

}
