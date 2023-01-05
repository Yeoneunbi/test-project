package commnet.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface CommentService {
	
	public ArrayList<CommentVO> getCommentList(CommentVO vo);
	
	public HashMap<String, Object> writeComment(CommentVO vo);
	
	public void UpdateComment(CommentVO vo);
	
	public void DeleteComment(CommentVO vo);
	
	public CommentVO selectComment(CommentVO vo); 
	
}
