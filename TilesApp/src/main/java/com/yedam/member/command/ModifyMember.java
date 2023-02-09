package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("mid");
		String nm = req.getParameter("mname");
		String pw = req.getParameter("mpass");
		String ph = req.getParameter("mphone");
		String ad = req.getParameter("maddr");
		
		MemberVO vo = new MemberVO();
		
		vo.setMemberId(id);
		vo.setMemberName(nm);
		vo.setMemberPw(pw);
		vo.setMemberPhone(ph);
		vo.setMemberAddr(ad);
		
		MemberService service = new MemberServiceMybatis();
		
		
		MemberVO mvo = service.getMember(id);
		
		if( nm.equals(mvo.getMemberName()) && 
				pw.equals(mvo.getMemberPw()) &&
				ph.equals(mvo.getMemberPhone()) &&
				ad.equals(mvo.getMemberAddr())) 
		{
			return "member/mypage.tiles";
		} else {
			service.modifyMember(vo);
			return "noticeList.do";
		}
		
	}

}
