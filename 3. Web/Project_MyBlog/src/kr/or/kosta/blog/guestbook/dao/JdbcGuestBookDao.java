package kr.or.kosta.blog.guestbook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.guestbook.domain.GuestBook;

public class JdbcGuestBookDao implements GuestBookDao {

	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;
	}
	
	@Override
	public void create(GuestBook guestbook) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO guestbook \r\n" + 
				"            (guestbook_id, \r\n" + 
				"             board_id, \r\n" + 
				"             writer, \r\n" + 
				"             content) \r\n" + 
				"VALUES      (guestbook_seq.NEXTVAL, \r\n" + 
				"             2, \r\n" + 
				"             ?, \r\n" + 
				"             ?)";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestbook.getWriter());
			pstmt.setString(2, guestbook.getContent());
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
	public List<GuestBook> listAll(String pageNum, String listNum) throws Exception {
		List<GuestBook> lists = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestBook guestBook = null;
		String sql = "SELECT writer, \r\n" + 
				"       content, \r\n" + 
				"       To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate \r\n" + 
				"FROM   (SELECT Ceil(ROWNUM / ?) request_page, \r\n" + 
				"               writer, \r\n" + 
				"               content, \r\n" + 
				"               regdate \r\n" + 
				"        FROM   (SELECT writer, \r\n" + 
				"                       content, \r\n" + 
				"                       regdate \r\n" + 
				"                FROM   guestbook \r\n" + 
				"                ORDER  BY regdate DESC)) \r\n" + 
				"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, listNum);
			pstmt.setString(2, pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				guestBook = createGuestBook(rs);
				lists.add(guestBook);
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
		return lists;
	}
	
	@Override
	public int countGuestBooks() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Count(guestbook_id) count \r\n" + 
				"FROM   guestbook";
		int result = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
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
	public List<GuestBook> newGuestBook(int number) throws Exception {
		List<GuestBook> lists = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GuestBook guestBook = null;
		String sql = "SELECT writer, \r\n" + 
				"       content, \r\n" + 
				"       To_char(regdate, 'YYYY/MM/DD HH24:MI') regdate \r\n" + 
				"FROM   (SELECT writer, \r\n" + 
				"               content, \r\n" + 
				"               regdate \r\n" + 
				"        FROM   guestbook \r\n" + 
				"        ORDER  BY regdate DESC) \r\n" + 
				"WHERE  ROWNUM <= ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				guestBook = createGuestBook(rs);
				lists.add(guestBook);
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
		return lists;
	}

	public GuestBook createGuestBook(ResultSet rs) throws SQLException {
		GuestBook guestBook = new GuestBook();
		guestBook.setWriter(rs.getString("writer"));
		guestBook.setContent(rs.getString("content"));
		guestBook.setRegdate(rs.getString("regdate"));
		return guestBook;
	}
}
