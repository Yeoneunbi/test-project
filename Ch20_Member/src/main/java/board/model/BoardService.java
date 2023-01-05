package board.model;

import java.util.List;

public interface BoardService {

	//게시글 등록 
	void insertBoard(BoardVO vo);
	
	//게시글 수정
	void updateBoard(BoardVO vo);
	
	//게시글 삭제 
	void deleteBoard(BoardVO vo);
	
	//게시글 상세조회
	BoardVO getBoard(BoardVO vo);
	
	//게시글 목록조회
	List<BoardVO> getBoardList(BoardVO vo);
	
	//유저 게시글 목록조회
	List<BoardVO> getBoardList_user(BoardVO vo);
}
