package co.yedam.emp.command;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpModControl implements Command {
//0206 2교시
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// service: int modEmp(EmpVO) -> serviceImpl: modEmp(EmpVO) -> dao: updateEmp(EmpVO)
		// eid=?&fname=?&lname=?&email=?%job=?로 넘어옴 ?에 수정에서 입력한 값
		String id = req.getParameter("eid");
		String fn = req.getParameter("fname");
		String ln = req.getParameter("lname");
		String ma = req.getParameter("email");
		String jb = req.getParameter("job");
		String hd = req.getParameter("hire");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id));		
		emp.setFirstName(fn);
		emp.setLastName(ln);
		emp.setEmail(ma);
		emp.setJobId(jb);
		emp.setHireDate(hd);
		
		EmpService service = new EmpServiceImpl();
		int r = service.modEmp(emp);
		
		if(r>0) { // 정상처리 되면 목록이동,
			try {
				resp.sendRedirect("empList.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				resp.sendRedirect("errorPage.do");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
