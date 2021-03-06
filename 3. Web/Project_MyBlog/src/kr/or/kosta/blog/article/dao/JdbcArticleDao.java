package kr.or.kosta.blog.article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import kr.or.kosta.blog.article.domain.Article;

public class JdbcArticleDao implements ArticleDao {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Article article) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO article \r\n" + "            (article_id, \r\n" + "             board_id, \r\n"
				+ "             writer, \r\n" + "             subject, \r\n" + "             content, \r\n"
				+ "             ip, \r\n" + "             passwd, \r\n" + "             group_no, \r\n"
				+ "             level_no, \r\n" + "             order_no) \r\n"
				+ "VALUES      (article_id_seq.NEXTVAL, \r\n" + "             1, \r\n" + "             ?, \r\n"
				+ "             ?, \r\n" + "             ?, \r\n" + "             ?, \r\n" + "             ?, \r\n";

		boolean isGruop = (article.getGroupNo() == null);
		if (!isGruop) {
			sql += "             ?, \r\n" + "             ?, \r\n" + "             ?)";
		} else {
			sql += "             article_id_seq.CURRVAL, \r\n" + "             0, \r\n" + "             0)";
		}
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getIp());
			pstmt.setString(5, article.getPasswd());
			if (!isGruop) {
				pstmt.setString(6, article.getGroupNo());
				pstmt.setString(7, article.getLevelNo());
				pstmt.setString(8, article.getOrderNo());
			}
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
	public Article read(String articleId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Article article = null;
		String sql = "SELECT subject, \r\n" + "       writer, \r\n"
				+ "       To_char(regdate, 'YYYY-MM-DD HH24:MI') regdate, \r\n" + "       ip, \r\n"
				+ "       hitcount, \r\n" + "       passwd, \r\n" + "       content, \r\n" + "       group_no, \r\n"
				+ "       level_no, \r\n" + "       order_no \r\n" + "FROM   article \r\n" + "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = createArticle(rs);
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
		return article;
	}

	@Override
	public void update(String articleId, String subject, String passwd, String ip, String content) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + "SET    subject = ?, \r\n" + "       passwd = ?, \r\n"
				+ "       ip = ?, \r\n" + "       content = ? \r\n" + "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, passwd);
			pstmt.setString(3, ip);
			pstmt.setString(4, content);
			pstmt.setString(5, articleId);
			pstmt.executeQuery();
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
	public void delete(String articleId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + "SET    subject = '삭제된 게시글입니다.', \r\n"
				+ "       content = '삭제된 게시글입니다.(@$^*)[DELETED]' \r\n" + "WHERE  article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, articleId);
			pstmt.executeQuery();
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
	public String getWriter(String articleId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT writer \r\n" + "FROM   article \r\n" + "WHERE  article_id = ?";
		String result = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("writer");
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
		return result;
	}

	@Override
	public boolean certify(String articleId, String writer, String passwd) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT writer, \r\n" + "       passwd \r\n" + "FROM   article \r\n" + "WHERE  article_id = ?";
		boolean result = false;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, articleId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = (rs.getString("writer").equals(writer) && rs.getString("passwd").equals(passwd));
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
		return result;
	}

	@Override
	public void increaseHitCount(String articleId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + "SET    hitcount = hitcount + 1 \r\n" + "WHERE  board_id = 1 \r\n"
				+ "       AND article_id = ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, articleId);
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

	private Article createArticle(ResultSet rs) throws SQLException {
		Article article = new Article();
		article.setSubject(rs.getString("subject"));
		article.setWriter(rs.getString("writer"));
		article.setRegdate(rs.getString("regdate"));
		article.setIp(rs.getString("ip"));
		article.setHitcount(rs.getString("hitcount"));
		article.setPasswd(rs.getString("passwd"));
		article.setContent(rs.getString("content"));
		article.setGroupNo(rs.getString("group_no"));
		article.setLevelNo(rs.getString("level_no"));
		article.setOrderNo(rs.getString("order_no"));
		return article;
	}

	public String buildOrderNo(String groupNo, String levelNo, String orderNo) throws Exception {
		int result = 99999;
		int orderNoTemp = result;
		for (int i = Integer.parseInt(levelNo); i >= 1; i--) {
			orderNoTemp = searchOrderNo(groupNo, i, orderNo);
			if(orderNoTemp > 0) {
				result = orderNoTemp < result ? orderNoTemp : result;
			}
		}
		if (result < 0 || result == 99999) {
			result = searchMaxOrderNo(groupNo);
		} else {
			updateOrderNo(groupNo, String.valueOf(result));
		}
		return String.valueOf(result);
	}

	private int searchOrderNo(String groupNo, int levelNo, String orderNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Min(order_no) order_no \r\n" + "FROM   article \r\n" + "WHERE  group_no = ? \r\n"
				+ "       AND level_no = ? \r\n" + "       AND order_no > ?";
		int result = 99999;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			pstmt.setInt(2, levelNo);
			pstmt.setString(3, orderNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("order_no");
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

	private int searchMaxOrderNo(String groupNo) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Max(order_no) + 1 order_no \r\n" + "FROM   article \r\n" + "WHERE  group_no = ?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("order_no");
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return result;
	}

	private void updateOrderNo(String groupNo, String orderNo) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE article \r\n" + "SET    order_no = order_no + 1 \r\n" + "WHERE  board_id = 1 \r\n"
				+ "       AND group_no = ? \r\n" + "       AND order_no >= ?";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groupNo);
			pstmt.setString(2, orderNo);
			pstmt.executeQuery();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
	}
}
