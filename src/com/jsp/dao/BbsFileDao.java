package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jsp.dto.BbsFileDto;

public class BbsFileDao {
	private static BbsFileDao bbsfDao;
	private Connection conn;
	private PreparedStatement pstmt;
	private int result;
	private ResultSet rs;
	Map<String, Object> map;
	private List<BbsFileDto> bbsfList;
	private ArrayList fileIdList;
	
	private BbsFileDao() {}
	
	public static synchronized BbsFileDao getInstance()	{ // 싱글톤
		if (bbsfDao == null)
			bbsfDao = new BbsFileDao();

		return bbsfDao;
	}
	
	public Connection getConnect() {
		// DB 접속 정보
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
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
	
	public List<BbsFileDto> selectByBbsId(String bbsId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT *");
		query.append("	FROM BBS_FILE");
		query.append("	WHERE BBSID=?");
		query.append("	ORDER BY FILEID");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1,bbsId);
			rs = pstmt.executeQuery();
			
			bbsfList = new ArrayList<>();
			while(rs.next()) {
				BbsFileDto bbsfDto = new BbsFileDto();
				bbsfDto.setFileId(rs.getString("FILEID"));
				bbsfDto.setOrgn_file_nm(rs.getString("ORGN_FILE_NM"));
				bbsfList.add(bbsfDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, rs);
		}
		return bbsfList;
		
	}
	
	public String selectById(String fileId) {
		String bbsFile="";
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT SAVE_FILE_NM");
		query.append("  FROM BBS_FILE");
		query.append("  WHERE FILEID=? ");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, fileId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bbsFile = rs.getString("SAVE_FILE_NM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}

		return bbsFile;
	}

	public int delete(int bbsId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();

		try {
			query.append("DELETE FROM BBS_FILE");
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

	public int update(String orglFileName, String saveFileName, int fileId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();

		try {
			query.append("UPDATE BBS_FILE SET");
			query.append(" ORGL_FILE_NM = ? , SAVE_FILE_NM =?  ");
			query.append("  WHERE FILEID = ? ");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, orglFileName);
			pstmt.setString(2, saveFileName);
			pstmt.setInt(3, fileId);
			int result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}

		return -1;
	}

	public int insert(String orglFileName, String saveFileName) {
		conn = this.getConnect();

		try {
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO BBS_FILE");
			query.append("     (FILEID, BBSID, ORGL_FILE_NM, SAVE_FILE_NM)");
			query.append(" VALUES (NULL , (SELECT MAX(BBSID)");
			query.append("                      FROM BBS), ?, ?)");

			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, orglFileName);
			pstmt.setString(2, saveFileName);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}

	public int insert(List<BbsFileDto> bbsfDtoList) {
		conn = this.getConnect();

		try {
			for (BbsFileDto bbsfDto : bbsfDtoList) {
				StringBuffer query = new StringBuffer();
				query.append("INSERT INTO BBS_FILE");
				query.append("     (FILEID, BBSID, ORGL_FILE_NM, SAVE_FILE_NM)");
				query.append(" VALUES (bbs_file_seq.nextval , (SELECT MAX(BBSID)");
				query.append("                      FROM BBS), ?, ?)");
			
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, bbsfDto.getOrgn_file_nm());
				pstmt.setString(2, bbsfDto.getSave_file_nm());
				result = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}

	public List<String> fileIdByBbsId(String bbsId) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT FILEID");
		query.append("  FROM BBS_FILE");
		query.append("  WHERE BBSID = ?");
		query.append("  ORDER BY FILEID");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsId);
			
			rs = pstmt.executeQuery();
			fileIdList = new ArrayList<>();
			
			while (rs.next()) {
				String fileId = rs.getString("FILEID");
				fileIdList.add(fileId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}

		return fileIdList;
	}
}
