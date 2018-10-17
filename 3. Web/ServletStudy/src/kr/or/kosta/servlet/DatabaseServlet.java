package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DatabaseServlet extends HttpServlet /* implements Servlet */ {

	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";

	String sql = "SELECT employee_id, last_name, salary\r\n" + "FROM employees";

	Connection con;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest reqeust, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
//			while (rs.next()) {
//				String employeeId = rs.getString("employee_id");
//				String lastName = rs.getString("last_name");
//				int salary = rs.getInt("salary");
//				System.out.println(employeeId + ", " + lastName + ", " + salary);
//			}
		} catch (SQLException e) {
		}

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
		try {
			while (rs.next()) {
				String employeeId = rs.getString("employee_id");
				String lastName = rs.getString("last_name");
				int salary = rs.getInt("salary");
				html += "			<tr>";
				html += "				<td>"+employeeId + "</td><td>" + lastName + "</td><td>" + salary+"</td>";
				html += "			</tr>";
			}
		} catch (SQLException e) {
		}
		html += "		</table>";
		html += "	</body>";
		html += "</html>";
		out.println(html);

	}
	
	@Override
	public void destroy() {
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
		}
	}
}
