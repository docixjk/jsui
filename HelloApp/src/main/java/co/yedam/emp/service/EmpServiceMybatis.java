package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.common.DataSource;
import co.yedam.emp.vo.EmpVO;

// EmpServiceImpl : jdbc
// EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService{

	//0207 1교시 addEmp 하면서 true를 매개값으로 넣어줘야 함
	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(true); //자동커밋 하기 위해 true
	//empMapper 가서 쿼리 만들어서 session을 쓸 수 있게 함.
	
	@Override
	public List<EmpVO> empList() { 
		return session.selectList("co.yedam.emp.mapper.EmpMapper.empList");
		//empMapper.xml의 name.id 를 넣어준다.(EmpMapper.xml namespace id resulttype를 먼저 수정함.)
	}
	
	//0207 1교시
	@Override
	public int addEmp(EmpVO emp) {
		// A -> B 송금 시에 둘 다 확인되면 commit
		int r = session.insert("co.yedam.emp.mapper.EmpMapper.addEmp",emp);
		if(r>0) {
			session.commit();
		} else {
			session.rollback();
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) {
		// 0206 6교시 mybatis 하면서 수정.
		return session.selectOne("co.yedam.emp.mapper.EmpMapper.getEmp", empId);
	}

	@Override
	public Map<String, String> jobList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modEmp(EmpVO emp) {
		// 0207 2교시-1
		return session.update("co.yedam.emp.mapper.EmpMapper.modEmp",emp);
	}

	public int deleteEmp(int id) {
		// 0207 2교시-1
		return session.delete("co.yedam.emp.mapper.EmpMapper.deleteEmp",id);
	}

}
