package com.yedam.emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmpDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	//@ 뒤에 ip주소나 내pc에서만 접속하고 싶으면 localhost 치고 뒤에 포트정보 그 뒤에 어떤 db를 선택할지 선택(sid)	
	String user = "hr";
	String pass = "hr";
	
	Connection conn;	//연결하기 위해
	Statement stmt = null;	// 쿼리실행, 처리결과 객체.
	PreparedStatement psmt = null; // Statement와 조금 다름.
	ResultSet rs = null;	//처리된 결과를 받아오기 위해
	String sql;
	
	public void connect(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생.");
			e.printStackTrace();
		}
	}
//C(reate), R(ead), U(pdate), D(elete) 처리.
	
	// 목록 조회
	public List<Map<String, Object>> empList() {
		sql = "select * from emp_temp";
		connect();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			stmt = conn.createStatement();
			rs =  stmt.executeQuery(sql);
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("emp_id", rs.getInt("employee_id"));
				map.put("first_name", rs.getString("first_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("salary", rs.getInt("salary"));
				
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//List<Map<String, Object>> 비교.
	public List<EmpVO> empVoList(){
		connect(); //메소드
		sql = "select * from emp_temp"; //실행할 쿼리
		List<EmpVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id")); //EmpVO 의 필드 employeeId를 채우기 위해
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				
				list.add(emp);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return list;
	}
	// 단건 조회
	public EmpVO getEmp(int id) {
		sql = "select * from emp_temp where employee_id = " + id;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//단건조회여서 반복문 안 쓰고 if
			if(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				return emp;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null; //조회된 데이터 없음.
	}
	//입력
	public int addEmp(EmpVO emp) { //execute로 넣기 때문에 정상으로 들어가면 1 이 나오게
		connect();
		//Statement stmt 는 파라미터 처리하기 어려움
		//sql 쿼리 만들기
		sql = "insert into emp_temp (employee_id, last_name, email, hire_date, job_id)"
				+ "values(?, ?, ?, ?, ?)";
		int r = 0; //정상적으로 처리 안 되면 return r;에서 0으로 나오게
		try {
			psmt = conn.prepareStatement(sql); //psmt메소드로 prepareStatement 객체 만들기
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());
			
			r = psmt.executeUpdate(); //처리된 건수. 정상적으로 처리하면 1이라고 반환.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
		
	// 수정1
	public int updateEmp(int id, int sal) { //객체로 안 받고 정수로 받음
		connect();
		sql = "update emp_temp set salary = ? where employee_id =?";
		int r= 0;
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, sal);
			psmt.setInt(2, id);
			r = psmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return r;
	}
	
	//수정2
	public int updateEmp(EmpVO emp) { //객체로 안 받고 정수로 받음
		connect();
		sql = "update emp_temp set hire_date = ?, email=? , job_id=?, last_name=? where employee_id =?";
		int r= 0;
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setString(1, emp.getHireDate());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getJobId());
			psmt.setString(4, emp.getLastName());
			psmt.setInt(5,  emp.getEmployeeId());
			r = psmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return r;
	}
	
	// 삭제
	public int deleteEmp(int id) { //객체로 안 받고 정수로 받음
		connect();
		sql = "delete emp_temp where employee_id =?";
	
		int r= 0;
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, id);
			r = psmt.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return r;
	}
}
