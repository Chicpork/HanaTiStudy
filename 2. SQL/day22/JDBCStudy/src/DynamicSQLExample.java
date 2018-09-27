import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * 동적 SQL
 * 
 * @author 정지원
 *
 */
public class DynamicSQLExample {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";
	
	public void excuteSQL(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(sql);
			boolean existRS = pstmt.execute();
			if (existRS) {
				rs = pstmt.getResultSet();
				ResultSetMetaData rsm = rs.getMetaData();

				int numberOfColumns = rsm.getColumnCount();

				for (int i = 1; i <= numberOfColumns; i++) {
					System.out.print(rsm.getColumnLabel(i) + "\t");
				}
				System.out.println();

				while (rs.next()) {
					for (int i = 1; i <= numberOfColumns; i++) {
						System.out.print(rs.getString(i) + "\t");
					}
					System.out.println();
				}
			} else {
				int count = pstmt.getUpdateCount();
				System.out.println(count + " 행이 변경 되었습니다..");
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}

	}

	public static void main(String[] args) {
		DynamicSQLExample dynamicSQLExample = new DynamicSQLExample();
		String sql = "DELETE FROM departments WHERE department_id = 320";
		dynamicSQLExample.excuteSQL(sql);

	}
}
