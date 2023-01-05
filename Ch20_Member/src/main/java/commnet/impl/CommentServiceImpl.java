package commnet.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commnet.model.CommentService;
import commnet.model.CommentVO;

public class CommentServiceImpl implements CommentService{
	private CommentDAO commentDAO = new CommentDAO();
	
	@Override
	public ArrayList<CommentVO> getCommentList(CommentVO vo) {
		return commentDAO.getCommentList(vo);
	}
	
	@Override
	public HashMap<String, Object> writeComment(CommentVO vo) {
		return commentDAO.writeComment(vo);
	}
	
	@Override
	public void UpdateComment(CommentVO vo) {
		commentDAO.UpdateComment(vo);
	}
	
	@Override
	public void DeleteComment(CommentVO vo) {
		commentDAO.DeleteComment(vo);
	}
	
	@Override
	public CommentVO selectComment(CommentVO vo) {
		return commentDAO.selectComment(vo);
	}
}
