import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC를 이용해 오라클 연동 및 데이터 인출 연습
 * @author 정지원
 *
 */
public class JDBCExample {

	public static void main(String[] args) {
		//#1. JDBC 드라이버 로딩(객체 생성)
//		Driver driver = new OracleDriver();
		
		
		// Class 클래스를 이용한 동적 객체 생성
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";
		
		String sql = "SELECT employee_id, last_name, salary\r\n" + 
				"FROM employees";
		
		
//		try {
//			// 이렇게 newInstance()를 이용해 객체를 생성해야 하는 게 맞지만 오라클에선 이 과정을 이미 만들어 놓아 생략해도 된다.
////			Class.forName(driver).newInstance(); 
//			Class.forName(driver);
////			System.out.println("JDBC 드라이버 생성 완료...");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		//#2. DBMS 연결
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, username, password);
//			System.out.println("DBMS 연결 완료..." + con);
			
			//#3. SQL 서버 전송 및 결과집합 수신
			stmt = con.createStatement();
//			System.out.println(stmt);
			rs = stmt.executeQuery(sql);
//			System.out.println(rs);
			
			//#4. ResultSet에서 데이터 인출
			while(rs.next()) {
				String employeeId = rs.getString("employee_id");
				String lastName = rs.getString("last_name");
				int salary = rs.getInt("salary");
				System.out.println(employeeId +", "+lastName+", "+salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
