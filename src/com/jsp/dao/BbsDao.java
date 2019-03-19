package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.jsp.dto.BbsDto3;

public class BbsDao {
	private static BbsDao bbsDao;
	private Connection conn;
	private PreparedStatement pstmt;
	private int result;
	private ResultSet rs;
	private List<BbsDto3> bbsList;

	private BbsDao() {}

	public static synchronized BbsDao getInstance() { // 싱글톤
		if (bbsDao == null)
			bbsDao = new BbsDao();

		return bbsDao;
	}

	public Connection getConnect() {
		// DB 접속 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("core.log.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		// JDBC 자원닫기 (사용의 역순으로 닫음)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<BbsDto3> selectAll(int startNum, int perpage) {
		bbsList = new ArrayList<>();
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();

		query.append("SELECT T2.*");
		query.append("	FROM (SELECT ROWNUM R2, T.*");
		query.append("	FROM (SELECT BBSID, BBSCATEGORY, ID, substr(BBSTITLE,1,10) BBSTITLE,");
		query.append(" substr(BBSCONTENT,1,15) BBSCONTENT, BBSDATE, BBSHIT ");
		query.append("	FROM BBS ORDER BY BBSID DESC ) T) T2");
		query.append("	WHERE T2.R2 BETWEEN ? AND ?");

		try {
			pstmt = conn.prepareStatement(query.toString());

			pstmt.setInt(1, startNum - 1);
			pstmt.setInt(2, perpage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				BbsDto3 bbsDto = new BbsDto3();

				bbsDto.setBbsId(rs.getString("BBSID"));
				bbsDto.setBbsCategory(rs.getString("BBSCATEGORY"));
				bbsDto.setId(rs.getString("ID"));
				bbsDto.setBbsTitle(rs.getString("BBSTITLE"));
				bbsDto.setBbsContent(rs.getString("BBSCONTENT"));
				bbsDto.setBbsDate(rs.getString("BBSDATE"));
				bbsDto.setBbsHit(rs.getString("BBSHIT"));

				bbsList.add(bbsDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, rs);
		}
		return bbsList;
	}

	public int hitUpdate(String id) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("UPDATE BBS SET BBSHIT = BBSHIT +1 WHERE BBSID=?");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}

		return result;
	}

	public BbsDto3 selectById(String id) {
		BbsDto3 bbsDto = new BbsDto3();
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();

		query.append("SELECT *");
		query.append("  FROM BBS");
		query.append("  WHERE BBSID=? ");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				bbsDto.setId(rs.getString("ID"));
				bbsDto.setBbsId(rs.getString("BBSID"));
				bbsDto.setBbsTitle(rs.getString("BBSTITLE"));
				bbsDto.setBbsDate(rs.getString("BBSDATE"));
				bbsDto.setBbsCategory(rs.getString("BBSCATEGORY"));
				bbsDto.setBbsContent(rs.getString("BBSCONTENT"));
				bbsDto.setBbsHit(rs.getString("BBSHIT"));

			}
		} catch (SQLException e) {

		} finally {
			this.close(conn, pstmt, null);
		}

		return bbsDto;
	}

	public int delete(int bbsId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();

		try {
			query.append("DELETE FROM BBS");
			query.append(" WHERE BBSID = ? ");
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, bbsId);
			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return -1;
	}

}
