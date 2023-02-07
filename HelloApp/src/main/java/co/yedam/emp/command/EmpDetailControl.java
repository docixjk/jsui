package co.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.emp.vo.EmpVO;

public class EmpDetailControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 상세조회: service -> serviceImpl ->dao
		//EmpServiceImpl 마지막 return 수정 후 왔음
		String id =req.getParameter("eid");
		
		//0206 6교시
		//EmpService service = new EmpServiceImpl(); jdbc
		EmpService service = new EmpServiceMybatis();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		req.setAttribute("searchVO", emp);
		//요청정보 req를 searchVO키에 담겨 있는 emp 정보를 찾음.
		req.setAttribute("myAge", 100);
		req.setAttribute("loginId", "user1");
		
		try {
			req.getRequestDispatcher("WEB-INF/result/empDetail.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		//foward는 위치 재지정 req, resp는 재지정 전에 받은 값.
	}

}
