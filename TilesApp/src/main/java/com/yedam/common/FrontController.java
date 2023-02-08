package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.notice.command.*;

public class FrontController extends HttpServlet {
	
	private Map<String, command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<String, command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		// config에 설정한 charset 값, 즉 UTF-8을 가져오는거
		charset = config.getInitParameter("charset");
		
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		
		// 공지사항
		map.put("/noticeList.do", new NoticeListControl());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm()); // 글 등록 화면
		map.put("/noticeAdd.do", new NoticeAdd()); // 글 등록
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding(charset);
		// http://localhost:8088/TilesApp/*.do
		// 			uri 부분 		/  path  / page
		String uri = req.getRequestURI();
		String context = req.getContextPath(); // /TilesApp/*.do 
		String page = uri.substring(context.length()); // /*.do
		System.out.println(page);
		
		// *.do 를 불러와 command에 넣어 처리 하려고
		command command = map.get(page);
		
		// 얘가 위에 req와 resp에 주는거
		String viewPage = command.exec(req, resp);
		// main.do이면 main/main.tiles가 들어옴
		
		// .tiles와 .do를 구분해서 사용해야함
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			// tiles에 거쳐 오면서 WEB-INF/views/main/*.jsp 들어옴
			rd.forward(req, resp);
			// 그리고 그 값을 뱉어냄
		} else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		}
	}
	
}
