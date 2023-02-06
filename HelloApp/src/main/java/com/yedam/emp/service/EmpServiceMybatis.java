package com.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.emp.vo.EmpVO;

// EmpServiceImpl : jdbc
// EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService{

	SqlSessionFactory sessionFactory = DataSource.getInstance(); 
	SqlSession session = sessionFactory.openSession();
	
	
	@Override
	public List<EmpVO> empList() {
		// session 이 mapper 를 활용
		// sessionFactory 에서 openSession()메소드를 가져옴
		return session.selectList("com.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		return 0;
	}

	@Override
	public EmpVO getEmp(int empId) {
		return session.selectOne("com.yedam.emp.mapper.EmpMapper.getEmp",empId);
	}

	@Override
	public Map<String, String> jobList() {
		return null;
	}

	@Override
	public int modEmp(EmpVO emp) {
		return 0;
	}

	@Override
	public int deleteEmp(int empId) {
		return 0;
	}
	
}
