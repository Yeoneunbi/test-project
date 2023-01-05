package member.model;

public interface MemberService {
	//회원등록
	void insertUser(MemberVO vo);
	
	//회원수정
	void updateUser(MemberVO vo);
	
	//회원조회
	MemberVO getUser(MemberVO vo);
}
