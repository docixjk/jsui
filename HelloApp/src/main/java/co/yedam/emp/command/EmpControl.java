package co.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		RequestDispatcher rd = null;
		
		String method = req.getMethod();
		PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		if(method.equals("GET")) {			
			try {
				resp.sendRedirect("result/empList.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (method.equals("POST")) {
			// 입력.
			String eid = req.getParameter("eid");
			String lName = req.getParameter("Last_name");
			String job = req.getParameter("job");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("email");
			
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);
			
			// 서비스 로직
			EmpService service = new EmpServiceImpl();
			int  result = service.addEmp(emp);
			System.out.println("cn:"+result);
			// 요청 재지정.
			try {
				if (result > 0) {
					//resp.sendRedirect("WEB-INF/result/addResult.jsp");
					rd = req.getRequestDispatcher("empList.do");
					rd.forward(req, resp); // 페이지 지정
				} else {
					//resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp); // 페이지 지정
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("알 수 없는 요청입니다!");
//			try {
//				resp.sendRedirect("https://www.daum.net");
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
		}
	}

}
