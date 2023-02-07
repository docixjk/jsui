package co.yedam.emp.command;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;

public class EmpForm implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//0207 1교시-2 EmpServiceImpl()에서 EmpServiceMybatis()로 수정
		EmpService service = new EmpServiceImpl();
		//EmpServiceImpl에 jobList 추가하기 0206 1교시
		Map<String, String> jobList = service.jobList();
		//empdao에 joblist 추가하기 0206 1교시
		req.setAttribute("jobList", jobList);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/emp.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
