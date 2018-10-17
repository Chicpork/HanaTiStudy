package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.dao.JdbcUserDao;
import kr.or.kosta.dao.User;

/**
 * 최초 작성 서블릿
 * 
 * @author 정지원
 *
 */
@SuppressWarnings("serial")
public class DatabaseServlet2 extends HttpServlet /* implements Servlet */ {

	JdbcUserDao dao;

	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";
	private static final int MAX_COUNT = 10;

	@Override
	public void init() throws ServletException {
		dao = new JdbcUserDao();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(MAX_COUNT);
		dataSource.setMaxIdle(7);

		dao.setDatasource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest reqeust, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> list;
		try {
			list = dao.listAll();

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String html = "";
			html += "<html>";
			html += "	<head>";
			html += "		<title>Servlet Programming</title>";
			html += "		<meta charset=\"utf-8\">";
			html += "	</head>";
			html += "	<body>";
			html += "		<table border='1' width='50'>";
			for (User user : list) {
				html += "			<tr>";
				html += "				<td>" + user.getId() + "</td><td>" + user.getName() + "</td><td>"
						+ user.getPasswd() + "</td>";
				html += "			</tr>";
			}
			html += "		</table>";
			html += "	</body>";
			html += "</html>";
			out.println(html);
		} catch (Exception e) {
		}
	}
}
