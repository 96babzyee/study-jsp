package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.BbsDto3;
import com.jsp.dto.BbsFileDto;

public class TransactionDao {
	private static TransactionDao tDao;
	private Connection conn;
	private PreparedStatement pstmt;
	private int result;
	private ResultSet rs;
	private List<BbsDto3> bbsList;
	
	private TransactionDao() {}
	
	public static synchronized TransactionDao getInstance()	{
		if (tDao == null)
			tDao = new TransactionDao();

		return tDao;
	}
	
	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("core.log.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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

	public int insertAll(BbsDto3 bbsDto, List<BbsFileDto> bbsfDtoList) {
		conn = this.getConnect();
		int result = 0;
		int result2 = 1;
		
		try {
			conn.setAutoCommit(false);
			
			int result1 = this.insertBbs(conn, bbsDto);
			if(bbsfDtoList !=null) {
				 result2 = this.insertFile(conn, bbsfDtoList);
			}
			if(result1>0 && result2>0) {
				conn.commit();
				result = 1;
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, null, null);
		}
		return result;
	}

	private int insertFile(Connection conn, List<BbsFileDto> bbsfDtoList) {
		try {
			for(BbsFileDto bbsfDto : bbsfDtoList) {
				StringBuffer query = new StringBuffer();
				query.append("INSERT INTO BBS_FILE");
				query.append("	(FILEID, BBSID, ORGN_FILE_NM, SAVE_FILE_NM)");
				query.append("	VALUES (bbs_file_seq.nextval, (SELECT MAX(BBSID)");
				query.append("			FROM BBS), ?, ?)");
				
				pstmt =  conn.prepareStatement(query.toString());
				
				pstmt.setString(1, bbsfDto.getOrgn_file_nm());
				pstmt.setString(2, bbsfDto.getSave_file_nm());
				
				result = pstmt.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, null);
		}
		return result;
	}

	private int insertBbs(Connection conn, BbsDto3 bbsDto) {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO BBS(BBSID, BBSCATEGORY, ID, BBSTITLE, BBSCONTENT, BBSDATE, BBSHIT)");
		query.append("	VALUES (bbs_seq.nextval, ?, ?, ?, ?,sysdate,0)");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, bbsDto.getBbsCategory());
			pstmt.setString(2, bbsDto.getId());
			pstmt.setString(3, bbsDto.getBbsTitle());
			pstmt.setString(4, bbsDto.getBbsContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, null);
		}
		return result;
	}

	public int updateAll(BbsDto3 bbsDto) {
		conn = this.getConnect();
		try {
			conn.setAutoCommit(false);
			int result1 = this.updateBbs(conn,bbsDto);
			
			if(result1>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		}  catch (SQLException e1) {
			
			e1.printStackTrace();
		} finally {
			this.close(conn, null, null);
		}
		return result;
	}

	public int updateFile(Connection conn, List<BbsFileDto> bbsfDtoList) {
		try {
			for (BbsFileDto bbsfDto : bbsfDtoList) {
				StringBuffer query = new StringBuffer();
				query.append("UPDATE BBS_FILE SET");
				query.append("    ORGL_FILE_NM = ?, SAVE_FILE_NM =?");
				query.append("  WHERE FILEID = ? ");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, bbsfDto.getOrgn_file_nm());
				pstmt.setString(2, bbsfDto.getSave_file_nm());
				pstmt.setString(3, bbsfDto.getFileId());
				int result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pstmt, null);
		}

		return result;
	}
	
	private int updateBbs(Connection conn, BbsDto3 bbsDto) {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE BBS SET ");
		query.append(" BBSTITLE=? ,BBSCATEGORY=?,BBSCONTENT=?");
		query.append(" WHERE BBSID=?");
			try {
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, bbsDto.getBbsTitle());
				pstmt.setString(2, bbsDto.getBbsCategory());
				pstmt.setString(3, bbsDto.getBbsContent());
				pstmt.setString(4, bbsDto.getBbsId());

				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		finally {
			this.close(null, pstmt, null);
		}
		return result;
	}
	
	
}
