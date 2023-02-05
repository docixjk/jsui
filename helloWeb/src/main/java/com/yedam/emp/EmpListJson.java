//http://localhost:8081/helloWeb/empListJson
//webapp 폴더 안에 파일은 없지만 empListJson파일로 있다고 생각하면 된다.
//왜냐하면 url 상으로 webapp이 http://localhost:8081/helloWeb url과 같다고 생각
package com.yedam.emp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/empListJson") //컨트롤 시프트 O
public class EmpListJson extends HttpServlet{
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");	//헷갈리지 않게 파라미터이름과 변수이름 같게
		String name = req.getParameter("name");
		String job = req.getParameter("job");
		String hire = req.getParameter("hire");
		String mail = req.getParameter("mail");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setJobId(job);
		vo.setHireDate(hire);
		vo.setEmail(mail);
		
		System.out.println(vo);
		
		resp.getWriter().print("complete");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//입력값의 한글처리
		req.setCharacterEncoding("utf-8");
		
		String parm = req.getParameter("param");
		String id = req.getParameter("id");	//헷갈리지 않게 파라미터이름과 변수이름 같게
		String name = req.getParameter("name");
		String job = req.getParameter("job");
		String hire = req.getParameter("hire");
		String mail = req.getParameter("mail");
		
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setJobId(job);
		vo.setHireDate(hire);
		vo.setEmail(mail);
		
		EmpDAO dao = new EmpDAO();
		
		// param=update => DB update.
		// param = xxx  => DB insert.
		
		if(parm != null && parm.equals("update")) {
			if(dao.updateEmp(vo)>0){
				resp.getWriter().print("{\"retCode\": \"Success\"}");
			} else{
				resp.getWriter().print("{\"retCode\": \"Fail\"}");
			}
		} else {					
			if (dao.addEmp(vo)>0) {
				resp.getWriter().print("{\"retCode\": \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\": \"Fail\"}");
			}
		}
	}
	
	
	// 제어의 역전(IOC);
	//제어를 개발자가 아니라 외부에 위임(servlet에게)
	//doD 컨트롤 스페이스 엔터
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id =req.getParameter("del_id"); //요청 페이지에서 del_id로 파라미터 요청
		
		// {id:101, retCode:Success, Fail}
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		EmpDAO dao = new EmpDAO();
		if(dao.deleteEmp(Integer.parseInt(id))>0){
			//{"returnCode": "Success"}
//			resp.getWriter().print("{\"retCode\": \"Success\"}");
			map.put("retCode", "Success");
		}else{
			// {"retrunCode": "Fail"}
//			resp.getWriter().print("{\"retCode\": \"Fail\"}");
			map.put("retCode", "Fail");
		};
		 // map => json
		Gson gson = new GsonBuilder().create();
		//gson.toJson() 을 쓰면 어떤것이든 json 형식으로 바꾸어줌
		resp.getWriter().print(gson.toJson(map));


	}
	
	//doget 컨트롤 스페이스 엔터
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");//json형식이고 한글이 포함되어있으면 utf-8로 처리 하십시오.
		
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();//컨트롤 시프트 O
		// [{"id": 100, "firstName": "Hong", "email": "Hong" ...},{},{}]
		String json = "[";
		for(int i=0; i<list.size(); i++) {
			json += "{\"id\":"+list.get(i).getEmployeeId()+
					", \"firstName\": \"" +list.get(i).getFirstName()+
					"\", \"lastName\": \"" +list.get(i).getLastName()+
					"\", \"email\": \"" +list.get(i).getEmail()+
					"\", \"hireDate\": \"" +list.get(i).getHireDate().substring(0,10)+
					"\", \"job\": \"" +list.get(i).getJobId()+
					"\"}"; // \뒤의 "은 " 문자자체로 인식하겠다.
			//마지막이면 ,를 넣지 않겠다.
			if(i + 1 != list.size()) { // i는 0부터 시작하고 size는 1부터 시작
				json += ",";
			}
		}
		json += "]";
		
		//응답 정보의 출력 스트림에다가
		resp.getWriter().print(json);
	}
}
