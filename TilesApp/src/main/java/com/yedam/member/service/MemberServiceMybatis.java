package com.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.mapper.MemberMapper;
import com.yedam.member.vo.MemberVO;

public class MemberServiceMybatis implements MemberService {
	SqlSession session = DataSource.getInstance().openSession(true);
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	@Override
	public MemberVO login(MemberVO member) {
		// TODO Auto-generated method stub
		return mapper.login(member); // session.selectOne("네임스페이스.id값");
	}
	@Override
	public int addMember(MemberVO member) {
		// TODO Auto-generated method stub
		return mapper.addMember(member);
	}
	@Override
	public List<MemberVO> memberList() {
		// TODO Auto-generated method stub
		return mapper.memberList();
	}
	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return mapper.getMember(id);
	}

}
