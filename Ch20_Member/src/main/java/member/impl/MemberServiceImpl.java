package member.impl;

import member.model.MemberService;
import member.model.MemberVO;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	public void insertUser(MemberVO vo) {
		memberDAO.insertUser(vo);
	}
	
	@Override
	public void updateUser(MemberVO vo) {
		memberDAO.updateUser(vo);
	}
	
	@Override
	public MemberVO getUser(MemberVO vo) {
		return memberDAO.getUser(vo);
	}
	
}
