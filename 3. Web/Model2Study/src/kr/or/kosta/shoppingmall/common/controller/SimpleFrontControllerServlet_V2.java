package kr.or.kosta.shoppingmall.common.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.shoppingmall.demo.controller.HelloController;
import kr.or.kosta.shoppingmall.demo.controller.TodayController;

/**
 * 모든 브라우저 요청에 대한 단일 진입점 역할의 프론트 컨트롤러 서블릿(메인 컨트롤러)
 * 
 * @author 김기정
 */
public class SimpleFrontControllerServlet_V2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		/** 모든 세부 컨트롤러들에 대한 공통 기능 처리 */

		// 모든 요청파라메터에 대한 한글 인코딩 처리
//		request.setCharacterEncoding("utf-8");

		// #1.웹 클라이언트 요청(브라우저 명령) 분석
		String uri = request.getRequestURI();

		// 확장자 형식 매핑시..
		// /appName/some.mall -> /some
		// /appName/board/some.do -> /board/some
		String contextPath = request.getContextPath();
		uri = uri.substring(contextPath.length(), uri.lastIndexOf("."));
		System.out.println("[Info] : 요청 URI: " + uri);

		// #2. 웹 클라이언트 요청에 대한 모델 세부 컨트롤러 실행 및 응답 - Command Pattern 적용
		Controller controller = null;
		ModelAndView mav = null;

		switch (uri) {
			case "/hello":
				controller = new HelloController();
				break;
			case "/today":
				controller = new TodayController();
				break;
		}
		
		if (controller == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 일관된 메소드 호출을 통한 세부컨트롤러 실행(커맨드패턴)
		mav = controller.handleRequest(request, response);

		// request 컨텍스트 객체에 View에서 필요로 하는 결과정보 저장
		Map<String, Object> map = mav.getModel();
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			Object value = map.get(key);
			request.setAttribute(key, value);
		}
		
		// RequestDispatcher를 사용하여 뷰(JSP)로 디스패치
		request.getRequestDispatcher(mav.getView()).forward(request, response);
	}

}
