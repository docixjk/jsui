package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import co.yedam.emp.dao.EmpDAO;
import co.yedam.emp.vo.EmpVO;

public class EmpServiceImpl implements EmpService {
	// jdbc 활용 db처리.
	EmpDAO dao = EmpDAO.getInstance();

	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}	

	@Override
	public int addEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return dao.insertEmp(emp);
	}
	//EmpServiceImpl 빨간줄 우클릭 add 하고 empdao수정 후 return 수정
	@Override
	public EmpVO getEmp(int empId) {
		// TODO Auto-generated method stub
		return dao.searchEmp(empId);
	}
		
	//0206 1교시
	@Override
	public Map<String, String> jobList() {
		// TODO Auto-generated method stub
		return dao.jobList();
	}
	
	//0206 2교시
	@Override
	public int modEmp(EmpVO emp) {
		return dao.updateEmp(emp);
	}

	//0206 3교시
	@Override
	public int deleteEmp(int empId) {
		return dao.deleteEmp(empId);
	}	
}
