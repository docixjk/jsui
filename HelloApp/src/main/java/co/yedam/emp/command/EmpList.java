package co.yedam.emp.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.emp.vo.EmpVO;

public class EmpList implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		//0206 5교시 
		EmpService service = new EmpServiceMybatis();
		//호출하는 객체가 EmpServiceMybatis()로 바뀜.
		List<EmpVO> list = service.empList();
		for(EmpVO emp : list) {
			System.out.println(emp);
		}
		req.setAttribute("empList", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/result/empList.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
