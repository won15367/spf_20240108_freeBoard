package com.won15367.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.won15367.board.dto.FboardDto;

public class FreeBoardDao {
	
	DataSource datasource;
	
	public FreeBoardDao() {
		super();
		Context context;
		try {
			context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/oracledb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String fbtitle, String fbname, String fbcontent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			
			String sql = "INSERT INTO freeboard(fbnum, fbtitle, fbname, fbcontent, fbhit) VALUES (freeboard_seq.nextval, ?, ?, ?, 0)";		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fbtitle);
			pstmt.setString(2, fbname);
			pstmt.setString(3, fbcontent);
			
			pstmt.executeUpdate();
			//select문만 executeQuerry로 실행
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	public ArrayList<FboardDto> list() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		
		try {
			conn = datasource.getConnection();
			
			String sql = "SELECT * FROM freeboard ORDER BY fbnum DESC";		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			//select문만 executeQuerry로 실행
			
			while(rs.next()) {
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
				
				FboardDto fboardDto = new FboardDto(fbnum, fbname, fbtitle, fbcontent, fbhit, fbdate);

				dtos.add(fboardDto);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
	}
	
	
	public FboardDto content_view(String req_fbnum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FboardDto fboardDto = null;
//		FboardDto fboardDto = new FboardDto();
		
		try {
			conn = datasource.getConnection();
			
			String sql = "SELECT * FROM freeboard WHERE fbnum = ?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, req_fbnum);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			int fbnum = rs.getInt("fbnum");
			String fbname = rs.getString("fbname");
			String fbtitle = rs.getString("fbtitle");
			String fbcontent = rs.getString("fbcontent");
			int fbhit = rs.getInt("fbhit");
			Timestamp fbdate = rs.getTimestamp("fbdate");
			
			fboardDto = new FboardDto(fbnum, fbname, fbtitle, fbcontent, fbhit, fbdate);
			
//			fboardDto.setFbnum(fbnum);
//			fboardDto.setFbname(fbname);
//			fboardDto.setFbtitle(fbtitle);
//			fboardDto.setFbcontent(fbcontent);
//			fboardDto.setFbhit(fbhit);
//			fboardDto.setFbdate(fbdate);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return fboardDto;
		
	}
	
}
