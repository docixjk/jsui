package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import co.yedam.emp.vo.EmpVO;

//업무에 대한 정의: interface에 정의하고 구현하는 클래스를 통해 실현.
public interface EmpService {
	public List<EmpVO> empList(); // 목록.
	public int addEmp(EmpVO emp); // 등록.
	public EmpVO getEmp(int empId); //조회
	public Map<String, String> jobList(); //직무목록 0206 1교시
	public int modEmp(EmpVO emp); //수정 0206 2교시
	public int deleteEmp(int empId); //삭제 0206 3교시
}
