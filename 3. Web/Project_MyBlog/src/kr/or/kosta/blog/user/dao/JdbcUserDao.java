package kr.or.kosta.blog.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.or.kosta.blog.user.domain.User;

public class JdbcUserDao implements UserDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
	}

	@Override
	public void create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO users \r\n" + "VALUES(?, ?, ?, ?, SYSDATE) ";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
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

	@Override
	public User read(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT id, \r\n" + 
				"       name, \r\n" + 
				"       passwd, \r\n" + 
				"       email, \r\n" + 
				"       to_char(regdate,'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = createUser(rs);
			}
		} finally {
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
		return user;
	}
	
	@Override
	public boolean isExistEmail(String email) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = true;
		String sql = "SELECT Count(email) count \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  email = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("count") == 0) {
					result = false;
				}
			}
		} finally {
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
		
		return result;
	}

	@Override
	public void update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE users \r\n" + 
				"SET    name = ?, \r\n" + 
				"       passwd = ?, \r\n" + 
				"       email = ? \r\n" + 
				"WHERE  id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPasswd());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getId());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				throw new Exception("해당하는 아이디가 존재하지 않습니다.");
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

	@Override
	public void delete(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE users \r\n" + 
				"WHERE  id = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int count = pstmt.executeUpdate();
			if (count == 0) {
				throw new Exception("해당하는 아이디가 존재하지 않습니다.");
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

	@Override
	public List<User> listAll() throws Exception {
		List<User> lists = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT id, \r\n" + 
				"       name, \r\n" + 
				"       passwd, \r\n" + 
				"       email, \r\n" + 
				"       to_char(regdate,'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + 
				"FROM   users";
		
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				user = createUser(rs);
				lists.add(user);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return lists;
	}

	@Override
	public User certify(String id, String passwd) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		String sql = "SELECT id, \r\n" + 
				"       name, \r\n" + 
				"       passwd, \r\n" + 
				"       email, \r\n" + 
				"       regdate \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  id = ? \r\n" + 
				"       AND passwd = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPasswd(rs.getString("passwd"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
			}
		} finally {
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
		return user;
	}

	@Override
	public List<Map<String, String>> employeeList() throws Exception {
		List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT e.employee_id eid, \r\n" + 
				"       e.last_name eln, \r\n" + 
				"       e.salary es, \r\n" + 
				"       d.department_name ddn, \r\n" + 
				"       l.city lc \r\n" + 
				"FROM   employees e \r\n" + 
				"       left outer join departments d USING(department_id) \r\n" + 
				"       left outer join locations l USING(location_id)";
		
		try {
			con = dataSource.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()) {
				Map<String, String> map = new HashMap<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnLabel(i), rs.getString(rsmd.getColumnLabel(i)));
				}
				lists.add(map);
			}
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		
		return lists;
	}
	
	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPasswd(rs.getString("passwd"));
		user.setEmail(rs.getString("email"));
		user.setRegdate(rs.getString("regdate"));
		return user;
	}

}
