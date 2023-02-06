package com.yedam.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;

public class EmpRemove implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		 String id = req.getParameter("id");

	        EmpService service = new EmpServiceImpl();
	        int r = service.deleteEmp(Integer.parseInt(id));

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
