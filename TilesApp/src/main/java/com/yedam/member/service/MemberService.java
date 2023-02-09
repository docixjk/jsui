package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface  MemberService {
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member);
	public List<MemberVO> memberList();
	public MemberVO getMember(String id); //회원정보조회용.
}
