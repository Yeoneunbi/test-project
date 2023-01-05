package board.impl;

import java.util.List;

import board.model.BoardService;
import board.model.BoardVO;

public class BoardServiceImpl implements BoardService{
	private BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}
	
	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
	
	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	@Override
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
	
	@Override
	public List<BoardVO> getBoardList_user(BoardVO vo) {
		return boardDAO.getBoardList_user(vo);
	}
}
