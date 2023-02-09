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

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 로그인 성공하면 mypage로 이동하고
		// 로그인 실패하면 다시 로그인화면으로 이동할때 "아이디와패스워드를 확인"하도록.
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);

		MemberService service = new MemberServiceMybatis();
		String page = "";
		if (service.login(vo) != null) {
			// session 에 로그인 정보를 담아서..
			HttpSession session = req.getSession();
			MemberVO mvo = service.getMember(id);
			session.setAttribute("logName", mvo.getMemberName());
			session.setAttribute("logId", mvo.getMemberId());
			session.setAttribute("vo", mvo);
			// 이동할 페이지를 지정.
			page = "mypage";
		} else {
			req.setAttribute("result", "아이디와 패스워드를 확인!!");
			page = "login";
		}
		return "member/" + page + ".tiles";
	}

}
