package com.jsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.dto.MemberDto;

public class MemberDao {
	private static MemberDao mDao;
	private Connection conn;
	private PreparedStatement pstmt;
	private int result;
	private ResultSet rs;

	private MemberDao() {
	}

	public static synchronized MemberDao getInstance() {
		if (mDao == null)
			mDao = new MemberDao();

		return mDao;
	}

	public Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "BIG";
		String pw = "1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
	
	public int join(MemberDto mDto) {
		result = 0;
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO MEMBER");
		query.append("	VALUES(?,?,?,?,?)");

		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, mDto.getId());
			pstmt.setString(2, mDto.getPassword());
			pstmt.setString(3, mDto.getName());
			pstmt.setString(4, mDto.getEmail());
			pstmt.setString(5, mDto.getMarketing());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}
	
	public int login(String id, String password) {
		conn = this.getConnect();
		StringBuffer query = new StringBuffer();
		query.append("SELECT PASSWORD");
		query.append("	FROM MEMBER");
		query.append("	WHERE ID=? ");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				if(rs.getString("PASSWORD").equals(password))
					return 1; 
				else
					return 0;
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, rs);
		}
		
		return 0;
	}

}
