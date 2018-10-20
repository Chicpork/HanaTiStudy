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
	public List<Board> listAll() throws Exception {
		return null;
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

		boolean issearch = (searchType == null || searchInput == null);
		if(!issearch) {
			sql += "                    AND ?  LIKE '%?%'\r\n";
		}
		sql +=	"                ORDER  BY group_no DESC, \r\n" + 
				"                          order_no ASC)) \r\n" + 
				"WHERE  request_page = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, listNum);
			if (!issearch) {
				pstmt.setString(2, searchType);
				pstmt.setString(3, searchInput);
				pstmt.setString(4, pageNum);
			} else {
				pstmt.setString(2, pageNum);
			}
			pstmt.executeUpdate();
			rs = pstmt.getResultSet();
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
