package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class MemberModify implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 수정하고 싶은 회원을 더블클릭하면 인풋으로 바뀌고 그 값들을 읽어서 수정버튼을 누르면 입력된 값을 서버에 넣으면서 다시 원래 모습으로 돌아가야 됨
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String addr = req.getParameter("addr");
		String auth = req.getParameter("resp");
		
		MemberService service = new MemberServiceMybatis();
		MemberVO m = new MemberVO();
		
		m.setMemberId(id);
		m.setMemberName(name);
		m.setMemberPhone(phone);
		m.setMemberAddr(addr);
		m.setResponsibility(auth);
		System.out.println(m);
		
		//결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", m);
		Gson gson = new GsonBuilder().create();

		//System.out.println(mid);
		if(service.modifyMember(m) > 0) {
			resultMap.put("retCode", "Success");
		} else {
			resultMap.put("retCode", "Fail");
		}
		return gson.toJson(resultMap) + ".json";
	}

}
