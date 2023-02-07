package co.yedam.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.emp.vo.EmpVO;

public class EmpRemove implements Command {
	//0206 3교시
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
        //삭제처리 Empservice -> EmpServiceImpl -> EmpDAO
		//0207 2교시 수정 EmpServiceImpl();을 EmpServiceMybatis();
		EmpService service = new EmpServiceMybatis();
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
