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

public class EmpModFormControl implements Command {
	//0203 마지막 8교시
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 수정화면: WEB-INF/views/modify.jsp 호출.

		String id = req.getParameter("id");
		//0207 2교시 수정 EmpServiceImpl();을 EmpServiceMybatis();
		EmpService service = new EmpServiceMybatis();
		EmpVO emp = service.getEmp(Integer.parseInt(id));

		req.setAttribute("svo", emp);

		try {
			req.getRequestDispatcher("WEB-INF/views/modify.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
				
			
	}

}
