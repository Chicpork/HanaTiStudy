package kr.or.kosta.shoppingmall.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 브라우저 요청에 대한 단일 진입점 역할의 프론트 컨트롤러 서블릿(메인 컨트롤러)
 * @author 김기정
 */
public class SimpleFrontControllerServlet_V1 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		process(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		
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
		
		
		// #2. 웹 클라이언트 요청에 대한 모델 비즈니스 메서드 실행 및 응답
		switch (uri) {
			case "/hello":
				handleHello(request, response);
				break;
			/*
			case "/some" :
				handleSome(request, response);
				break;
			*/
		}
	}
	
	private void handleHello(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
		// 모델영역의 비즈니스 메소드 호출 및 데이터 반환
		//String message = xxxService.bizMethod();
		String message = "모델2 기반 웹애플리케이션 개발";
		
		List<String> list = new ArrayList<String>();
		list.add("Doosan 타이거즈");
		list.add("LG 베어즈");
		list.add("Samsung 트윈즈");
		
		// request 컨텍스트 객체에 View에서 필요로 하는 결과정보 저장
		request.setAttribute("message", message);
		request.setAttribute("list", list);
		
		// RequestDispatcher를 사용하여 뷰(JSP)로 디스패치
		request.getRequestDispatcher("/demo/hello.jsp").forward(request, response);
	}
	
}










