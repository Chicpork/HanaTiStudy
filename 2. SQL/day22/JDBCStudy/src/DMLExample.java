import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC를 이용해 오라클 연동 및 데이터 인출 연습
 * 
 * @author 정지원
 *
 */
public class DMLExample {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";

	public void create(String departmentName, Integer managerId, Integer locationId) {

		String sql = "INSERT INTO departments \r\n" + "            (department_id, \r\n"
				+ "             department_name, \r\n" + "             manager_id, \r\n"
				+ "             location_id) \r\n" + "VALUES(departments_seq.NEXTVAL, \r\n" + "            '"
				+ departmentName + "', \r\n" + "            " + managerId + ", \r\n" + "            " + locationId
				+ ")";
		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			stmt = con.createStatement();

			int count = stmt.executeUpdate(sql);
			con.commit();
			System.out.println(count + " 행이 추가되었습니다.");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
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

	public void create2(String departmentName, String managerId, String locationId) {
		create2(new Department(0,departmentName,managerId,locationId));
	}
	
	public void create2(Department department) {

		String sql = "INSERT INTO departments \r\n" + "            (department_id, \r\n"
				+ "             department_name, \r\n" + "             manager_id, \r\n"
				+ "             location_id) \r\n" + "VALUES(departments_seq.NEXTVAL, \r\n" + "            ?, \r\n"
				+ "            ?, \r\n" + "            ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
			pstmt.setString(2, department.getManagerId());
			pstmt.setString(3, department.getLocationId());

			int count = pstmt.executeUpdate();
			con.commit();
			System.out.println(count + " 행이 추가되었습니다.");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public void delete(int departmentId) {
		String sql ="DELETE\r\n" + 
				"FROM departments\r\n" + 
				"WHERE department_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, departmentId);
			int count = pstmt.executeUpdate();
			con.commit();
			System.out.println(count + " 행이 삭제되었습니다.");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e) {
			}
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void main(String[] args) {
		DMLExample dmlExample = new DMLExample();
		dmlExample.create2("코스타3", "100", "1700");
		dmlExample.delete(320);
	}

}
