package kr.or.kosta.blog.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosta.blog.board.domain.Board;

public class JdbcBoardDao implements BoardDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int countArticles(String searchType, String searchInput) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Count(article_id) count \r\n" + 
				"FROM   article \r\n" + 
				"WHERE  board_id = 1 \r\n";
		
		int result = 0;
		if (!(searchType == null || searchInput == null)) {
			sql += "       AND "+searchType+" LIKE \'%"+searchInput+"%\'";
		}
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
	public List<Board> listByPage(String pageNum, String listNum, String searchType, String searchInput) throws Exception {
		List<Board> lists = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		String sql = "SELECT article_id, \r\n" + 
				"       subject, \r\n" + 
				"       writer, \r\n" + 
				"       regdate, \r\n" + 
				"       ip, \r\n" + 
				"       hitcount, \r\n" + 
				"       level_no \r\n" + 
				"FROM   (SELECT Ceil(ROWNUM / ?) request_page, \r\n" + 
				"               article_id, \r\n" + 
				"               subject, \r\n" + 
				"               writer, \r\n" + 
				"               To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + 
				"               ip, \r\n" + 
				"               hitcount, \r\n" + 
				"               level_no \r\n" + 
				"        FROM   (SELECT article_id, \r\n" + 
				"                       subject, \r\n" + 
				"                       writer, \r\n" + 
				"                       regdate, \r\n" + 
				"                       ip, \r\n" + 
				"                       hitcount, \r\n" + 
				"                       level_no \r\n" + 
				"                FROM   article \r\n" + 
				"                WHERE  board_id = 1 \r\n"; 

		if(!(searchType == null || searchInput == null)) {
			sql += "                    AND "+searchType+" LIKE \'%"+searchInput+"%\'\r\n";
		}
		sql +=	"                ORDER  BY group_no DESC, \r\n" + 
				"                          order_no ASC)) \r\n" + 
				"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, listNum);
			pstmt.setString(2, pageNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = createBoard(rs);
				lists.add(board);
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
	public List<Board> newArticles(int number) throws Exception {
		List<Board> lists = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		String sql = "SELECT article_id, \r\n" + 
				"       subject, \r\n" + 
				"       writer, \r\n" + 
				"       To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate \r\n" + 
				"FROM   (SELECT article_id, \r\n" + 
				"               subject, \r\n" + 
				"               writer, \r\n" + 
				"               regdate \r\n" + 
				"        FROM   article \r\n" + 
				"        WHERE  level_no = 0 \r\n" + 
				"        ORDER  BY regdate DESC) \r\n" + 
				"WHERE  ROWNUM <= ?";

		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = new Board();
				board.setArticleId(rs.getString("article_id"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setRegdate(rs.getString("regdate"));
				lists.add(board);
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
	public List<Board> hotArticles(int number) throws Exception {
		List<Board> lists = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		String sql = "SELECT article_id, \r\n" + 
				"       subject, \r\n" + 
				"       writer, \r\n" + 
				"       To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate \r\n" + 
				"FROM   (SELECT article_id, \r\n" + 
				"               subject, \r\n" + 
				"               writer, \r\n" + 
				"               regdate \r\n" + 
				"        FROM   article \r\n" + 
				"        ORDER  BY hitcount DESC) \r\n" + 
				"WHERE  ROWNUM <= ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				board = new Board();
				board.setArticleId(rs.getString("article_id"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setRegdate(rs.getString("regdate"));
				lists.add(board);
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

	private Board createBoard(ResultSet rs) throws SQLException {
		Board board = new Board();
		board.setArticleId(rs.getString("article_id"));
		board.setSubject(rs.getString("subject"));
		board.setWriter(rs.getString("writer"));
		board.setRegdate(rs.getString("regdate"));
		board.setIp(rs.getString("ip"));
		board.setHitcount(rs.getString("hitcount"));
		board.setLevelNo(rs.getInt("level_no"));
		return board;
	}
	
}
