import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureCallExample {
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "hr";
	String password = "hr";
	
	public void callProcedure(int employeeId) {
		Connection con = null;
		CallableStatement cstmt = null;
		String sql = "call getEmployee(?,?,?,?)";
		
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			cstmt = con.prepareCall(sql);
			cstmt.setInt(1, employeeId);
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);
			
			cstmt.execute();
			
			int employeeId2 = cstmt.getInt(2);
			String firstName = cstmt.getString(3);
			int salary = cstmt.getInt(4);
			
			System.out.println(employeeId2 + "\t"+firstName+"\t"+salary);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			if (cstmt != null)
				cstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}
	}
	
	public static void main(String[] args) {
		ProcedureCallExample callExample = new ProcedureCallExample();
		callExample.callProcedure(100);
	}
	
}
