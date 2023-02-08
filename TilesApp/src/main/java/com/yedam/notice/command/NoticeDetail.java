package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeDetail implements command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nid = req.getParameter("nid"); // 무조건 하나는 들고와야됨
		
		NoticeService service = new NoticeServiceImpl();
		
		// 요청 정보를 페이지에 담아서 바로 줄거임
		req.setAttribute("vo", service.getNotice(Integer.parseInt(nid)));
		
		return "notice/noticeDetail.tiles";
	}

}
