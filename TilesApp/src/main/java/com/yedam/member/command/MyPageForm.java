package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class MyPageForm implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("logId");
		
		MemberService service = new MemberServiceMybatis();
		
		MemberVO mvo = service.getMember(id);
		req.setAttribute("vo", mvo); // 로그인된 ID의 정보
		
		return "member/mypage.tiles";
	}

}
