//http://localhost:8081/helloWeb/myinfo
package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;
//@webs 컨트롤 스페이스 엔터 치면 @WebServlet() 입력됨.
//톰캣에 /myinfo 요청 그러면 톰캣이 서버에 요청
@WebServlet("/myinfo")
//자바코드 실행
public class FirstServlet extends HttpServlet{ //http통신 요청들어오면 응답.
	
	//컨트롤 스페이스 눌러서 doPost 입력해서 엔터
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청방식: post 요청일 경우에 tomcat 서버가 실행해줌
		req.setCharacterEncoding("utf-8");//한글포함되어 있으면
		
		String id = req.getParameter("emp_id"); //form태그의 name속성의 값을 읽어들일 때, getParameter라는 메소드 사용
		String name = req.getParameter("last_name");
		String mail = req.getParameter("email");
		String job = req.getParameter("job_id");
		String hdate = req.getParameter("hire_date");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id)); // setEmployeeId(변수 int) 문자열을 int 타입으로 파싱
		emp.setLastName(name);
		emp.setEmail(mail);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		System.out.println(emp);
		
		EmpDAO dao = new EmpDAO();
		dao.addEmp(emp); //DB에 입력
		
		doGet(req, resp); //doGet 메소드 호출(입력한 값 포함 전체 출력)
		
		
	}
	//컨트롤 스페이스 눌러서 doGet 입력해서 엔터
	//doGet메소드로 자바영역 실행
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		//super.doGet(req, resp);
		resp.setContentType("text/html;charset=utf-8");//컨텐트 타입 지정		
		resp.getWriter().print("<h3>servlet page<h3>"); //client 출력 스트림. resp.getWriter() 위에 컨텐트 타입을 안 하면 <h3>가 설정 안되고 출력됨
		resp.getWriter().print("<a href='info/resume.html'>이력서</a>");
		resp.getWriter().print("<a href='index.html'>첫페이지로 이동</a>");
		
		//jdbc 연결처리.
		//WEB-INF의 lib에 라이브러리 복사하기.
		//D드라이버에 Dev폴더에 ojdbc6_g.jar를 복사해서 좌측 WEB-INF의 lib 클릭후 컨트롤 v
		//com.yedam 아래에 com.yedam.emp 패키지 만들기
		//C:\DEV\jdbcExe\src\com\yedam에서 EmpDAO와 EmpVO를 복사해서 com.yedam.emp 패키지에 붙여넣기
		//패키지 주소를 com.yedam.emp로 다 바꾸기
		EmpDAO dao = new EmpDAO();
		//EmpDAO import하기
		List<EmpVO> list =dao.empVoList();
		//import java.util.List;
		
		resp.getWriter().print("<ul>");
		for(EmpVO emp : list) {
			resp.getWriter().print("<li>"
					+ emp.getEmployeeId() + "/ "
					+ emp.getLastName() + "/ " 
					+ emp.getEmployeeId() + "</li>");
			resp.getWriter().print("</li>");
		}
		resp.getWriter().print("</ul>");
		
	}
}
