package kr.or.kosta.shoppingmall.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import kr.or.kosta.shoppingmall.common.web.Params;
import kr.or.kosta.shoppingmall.user.domain.User;

public class JdbcUserDao implements UserDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(User user) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO users \r\n" + 
				     "VALUES     (?, \r\n" + 
				     "            ?, \r\n" + 
				     "            ?, \r\n" + 
				     "            ?, \r\n" + 
				     "            SYSDATE)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPasswd());
			pstmt.setString(4, user.getEmail());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
	}

	@Override
	public User read(String id) throws Exception {
		User user = null;
		
		Connection con =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, \r\n" + 
				     "       name, \r\n" + 
				     "       passwd, \r\n" + 
				     "       email, \r\n" + 
				     "       TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + 
				     "FROM   users \r\n" + 
				     "WHERE  id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				user = new User();
//				user.setId(rs.getString("id"));
//				user.setName(rs.getString("name"));
//				user.setPasswd(rs.getString("passwd"));
//				user.setEmail(rs.getString("email"));
//				user.setRegdate(rs.getString("regdate"));
				user = createUser(rs);
			}
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		Connection con =  null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE users \r\n" + 
				     "SET    passwd = ?, \r\n" + 
				     "       email = ? \r\n" + 
				     "WHERE  id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPasswd());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getId());
			pstmt.executeUpdate();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		

	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> listAll() throws Exception {
		List<User> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, \r\n" + 
				     "       name, \r\n" + 
				     "       passwd, \r\n" + 
				     "       email, \r\n" + 
				     "       TO_CHAR(regdate, 'YYYY/MM/DD HH24:MI:SS') regdate\r\n" + 
				     "FROM   users";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			while(rs.next()) {
				User user = createUser(rs);
				list.add(user);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}
	

	@Override
	public User certify(String id, String passwd) throws Exception {
		User user = null;
		
		Connection con =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, \r\n" + 
			     "       name, \r\n" + 
			     "       passwd, \r\n" + 
			     "       email, \r\n" + 
			     "       TO_CHAR(regdate, 'YYYY\"년\" MM\"월\" DD\"일\" DAY') regdate \r\n" + 
			     "FROM   users \r\n" + 
			     "WHERE  id = ? AND passwd = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = createUser(rs);
			}
			
		}finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return user;
	}
	
	private User createUser(ResultSet rs) throws SQLException{
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPasswd(rs.getString("passwd"));
		user.setEmail(rs.getString("email"));
		user.setRegdate(rs.getString("regdate"));
		return user;
	}
	
	
	@Override
	public List<Map<String, String>> employeeList() throws Exception {
		List<Map<String, String>> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT e.employee_id     eid, \r\n" + 
				     "       e.last_name       ename, \r\n" + 
				     "       e.salary          salary, \r\n" + 
				     "       d.department_name dname, \r\n" + 
				     "       l.city            city, \r\n" + 
				     "       e2.last_name      mname \r\n" + 
				     "FROM   employees e \r\n" + 
				     "       left outer join departments d \r\n" + 
				     "                    ON e.department_id = d.department_id \r\n" + 
				     "       left outer join locations l \r\n" + 
				     "                    ON d.location_id = l.location_id \r\n" + 
				     "       left outer join employees e2 \r\n" + 
				     "                    ON e.manager_id = e2.employee_id \r\n" + 
				     "ORDER  BY eid ASC";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Map<String, String>>();
			ResultSetMetaData rsd = rs.getMetaData();
			int columCount = rsd.getColumnCount();
			while(rs.next()) {
				Map<String, String> row = new HashMap<String, String>();
				for(int i=1; i<=columCount; i++) {
					String columName = rsd.getColumnLabel(i);
					String columValue = rs.getString(i);
					row.put(columName, columValue);
				}
				list.add(row);				
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<User> listByPage(int page) throws Exception {
		List<User> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT id, \r\n" + 
					"       name, \r\n" + 
					"       passwd, \r\n" + 
					"       email, \r\n" + 
					"       regdate \r\n" + 
					"FROM   (SELECT CEIL(rownum / 10) request_page, \r\n" + 
					"               id, \r\n" + 
					"               name, \r\n" + 
					"               passwd, \r\n" + 
					"               email, \r\n" + 
					"               regdate \r\n" + 
					"        FROM   (SELECT id, \r\n" + 
					"                       name, \r\n" + 
					"                       passwd, \r\n" + 
					"                       email, \r\n" + 
					"                       TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate\r\n" + 
					"                FROM   users \r\n" + 
					"                ORDER  BY regdate DESC)) \r\n" + 
					"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			while(rs.next()) {
				User user = createUser(rs);
				list.add(user);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<User> listByPage(int page, int listSize) throws Exception {
		List<User> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT id, \r\n" + 
					"       name, \r\n" + 
					"       passwd, \r\n" + 
					"       email, \r\n" + 
					"       regdate \r\n" + 
					"FROM   (SELECT CEIL(rownum / ?) request_page, \r\n" + 
					"               id, \r\n" + 
					"               name, \r\n" + 
					"               passwd, \r\n" + 
					"               email, \r\n" + 
					"               regdate \r\n" + 
					"        FROM   (SELECT id, \r\n" + 
					"                       name, \r\n" + 
					"                       passwd, \r\n" + 
					"                       email, \r\n" + 
					"                       TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate\r\n" + 
					"                FROM   users \r\n" + 
					"                ORDER  BY regdate DESC)) \r\n" + 
					"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, listSize);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			while(rs.next()) {
				User user = createUser(rs);
				list.add(user);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<User> listByPage(int page, int listSize, String searchType, String searchValue) throws Exception {
		List<User> list = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT id, \r\n" + 
					"       name, \r\n" + 
					"       passwd, \r\n" + 
					"       email, \r\n" + 
					"       regdate \r\n" + 
					"FROM   (SELECT CEIL(rownum / ?) request_page, \r\n" + 
					"               id, \r\n" + 
					"               name, \r\n" + 
					"               passwd, \r\n" + 
					"               email, \r\n" + 
					"               regdate \r\n" + 
					"        FROM   (SELECT id, \r\n" + 
					"                       name, \r\n" + 
					"                       passwd, \r\n" + 
					"                       email, \r\n" + 
					"                       TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI') regdate\r\n" + 
					"                FROM   users \r\n";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null){
			switch (searchType) {
				case "id":
					sql += "WHERE  id = ? \r\n";
					break;
				case "name":  
					sql += " WHERE  name LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
			}
		}
		sql += "                ORDER BY regdate DESC)) \r\n" +
		       "WHERE  request_page = ?";
		
		
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, listSize);
			
			// 전체검색이 아닌경우 경우
			if(searchType != null){
				pstmt.setString(2, searchValue);
				pstmt.setInt(3, page);
			}else{// 전체검색인 경우
				pstmt.setInt(2, page);
			}
			
			rs = pstmt.executeQuery();
			list = new ArrayList<User>();
			while(rs.next()) {
				User user = createUser(rs);
				list.add(user);
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return list;
	}

	@Override
	public List<User> listByPage(Params params) throws Exception {
		return listByPage(params.getPage(), params.getListSize(),  params.getSearchType(), params.getSearchValue());
	}

	@Override
	public int countBySearch(String searchType, String searchValue) throws Exception {
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT COUNT(id) count\r\n" + 
					"FROM   users\r\n";
		
		// 검색 유형별 WHERE 절 동적 추가
		if(searchType != null){
			switch (searchType) {
				case "id":
					sql += "WHERE  id = ? \r\n";
					break;
				case "name":  
					sql += " WHERE  name LIKE ? \r\n";
					searchValue = "%" + searchValue + "%";
					break;
			}
		}
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			
			// 전체검색이 아닌경우 경우
			if(searchType != null){
				pstmt.setString(1, searchValue);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
		} finally {
			try {
				if(rs != null)    rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			}catch (Exception e) {}
		}
		return count;
	}

	@Override
	public int countBySearch(Params params) throws Exception {
		return countBySearch(params.getSearchType(), params.getSearchValue());
	}
	
	
	
}










