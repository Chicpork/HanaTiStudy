package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 최초 작성 서블릿
 * 
 * @author 정지원
 *
 */
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet /* implements Servlet */ {
	@Override
	protected void doGet(HttpServletRequest reqeust, HttpServletResponse response)
			throws ServletException, IOException {

		// 웹클라이언트로 동적 출력하고자 하는 데이터 생성
		Calendar now = Calendar.getInstance();
		String nowString = String.format("%1$tF %1$tT", now);

		// 응답메시지의 헤더에 컨텐츠 유형 설정
		response.setContentType("text/html; charset=utf-8");

		// 출력하기 위한 클래스 로드
		PrintWriter out = response.getWriter();
		String html = "";

		html += "<html>";
		html += "	<head>";
		html += "		<title>Servlet Programming</title>";
		html += "		<meta charset=\"utf-8\">";
		html += "	</head>";
		html += "	<body>";
		html += "		<h2>오늘은 " + nowString + " 입니다. </h2>";
		html += "	</body>";
		html += "</html>";

		out.println(html);
	}
}
