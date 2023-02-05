package com.yedam.java.project;


import java.sql.*;
import java.util.*;


public class TBL_USER_DAO {
	Connection conn; // DB와 연결된 객체
	PreparedStatement pstm = null; 
	Statement stmt = null; // SQL문을 담는 객체
	ResultSet rs = null; // SQL문 결과를 담는 객체
	

	public List<TBL_USER_VO> empVolist() { // 클래스를 하나 만들어야됨
		String sql = "SELECT * FROM TBL_USER ORDER BY usernumber";
		List<TBL_USER_VO> list = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TBL_USER_VO emp = new TBL_USER_VO();
				emp.setId(rs.getString("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoneNumber(rs.getString("phonenumber"));
				emp.setSubject(rs.getString("subject"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 아이디 중복검사
	public boolean checkId(String id) {
		String query = "SELECT COUNT(ID) FROM TBL_USER WHERE ID = ?";
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(query);
			pstm.setString(1, id);
			rs = pstm.executeQuery();

			rs.next(); // 행
			if (rs.getInt(1)/* 열 */ == 1) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println("checkId SQL문 오류");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// 회원가입
	// USERNUMBER NUMBER NOT NULL,
	// ID VARCHAR2(300),
	// PW VARCHAR2(300),
	// NAME VARCHAR2(300),
	// AGE NUMBER,
	// PHONENUMBER VARCHAR2(20),
	public int join(TBL_USER_VO user) {
		String query = "INSERT INTO TBL_USER(USERNUMBER, ID, PW, NAME, AGE, PHONENUMBER, SUBJECT) VALUES(USER_SEQ.NEXTVAL, ?, ?, ?, ?, ?,?)";
		int r = 0;
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(query);

			pstm.setString(1, user.getId());
			pstm.setString(2, user.getPw());
			pstm.setString(3, user.getName());
			pstm.setInt(4, user.getAge());
			pstm.setString(5, user.getPhoneNumber());
			pstm.setString(6, user.getSubject());

			r = pstm.executeUpdate();

		} catch (SQLException e) {
			System.out.println("join SQL문 오류");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return r;
	}

	// 로그인
	public boolean login(String id, String pw) {
		
		if(id.equals("yedam") && pw.equals("yedam")) {
			return true;
		}
		return false;
	}

	// 수강생 정보 ( 해당 )
	public TBL_USER_VO student(String id) {
		String sql = "SELECT * FROM TBL_USER WHERE id='" + id + "'"; // db에서는 ' '만 됨
		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				TBL_USER_VO tuv = new TBL_USER_VO();
				tuv.setId(rs.getString("id"));
				tuv.setName(rs.getString("name"));
				tuv.setAge(rs.getInt("age"));
				tuv.setPhoneNumber(rs.getString("phonenumber"));
				tuv.setSubject(rs.getString("subject"));
				return tuv;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updEmp(TBL_USER_VO emp) {
		String sql = "UPDATE tbl_user SET pw = ?, phonenumber = ?, subject = ? WHERE id = ?";
		int r = 0;
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(sql); // << 파라미터 값을 입력할때 씀
			pstm.setString(1, emp.getPw());
			pstm.setString(2, emp.getPhoneNumber());
			pstm.setString(3, emp.getSubject());
			pstm.setString(4, emp.getId());
			
			r = pstm.executeUpdate(); // 처리된 건수
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// 수강생 탈퇴
	public int delEmp(TBL_USER_VO emp) {
		String sql = "DELETE FROM TBL_USER WHERE id = ?";
		int x = 0;
		try {
			conn = DBConnection.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, emp.getId());

			x = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return x;
	}
}