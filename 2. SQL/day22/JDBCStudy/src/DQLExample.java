import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC를 이용해 오라클 연동 및 데이터 인출 연습
 * 
 * @author 정지원
 *
 */
public class DQLExample {

	public static void main(String[] args) {

		// Class 클래스를 이용한 동적 객체 생성
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "hr";
		String password = "hr";

		String sql = "INSERT INTO departments \r\n" + "            (department_id, \r\n"
				+ "             department_name, \r\n" + "             manager_id, \r\n"
				+ "             location_id) \r\n" + "VALUES     (departments_seq.NEXTVAL, \r\n"
				+ "            'kosta', \r\n" + "            NULL, \r\n" + "            NULL)";

		// #2. DBMS 연결
		Connection con = null;
		Statement stmt = null;
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			stmt = con.createStatement();

			int count = stmt.executeUpdate(sql);
			con.commit();
			System.out.println(count + " 행이 추가되었습니다.");

		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

}
