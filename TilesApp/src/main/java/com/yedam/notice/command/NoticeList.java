package com.yedam.notice.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		NoticeService service = new NoticeServiceImpl();
		List<NoticeVO> vo = service.noticeList();
		int pagingNum = req.getParameter("pagingNum") != null ? Integer.parseInt( req.getParameter("pagingNum").toString()):1;
		req.setAttribute("list", vo.subList((pagingNum-1)*10,pagingNum*10));
		int r = ((vo.size()-1) / 10) + 1;
		req.setAttribute("pageLength",r);

		return "notice/noticeList.tiles";
	}

}
