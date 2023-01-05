package commnet.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import org.apache.coyote.http11.filters.VoidInputFilter;

import commnet.model.CommentVO;
import common.JDBCUtil;

public class CommentDAO {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String GET_COMMENT_LIST = "SELECT * FROM COMMENTS WHERE BBSID = ? ORDER BY COMMENTID DESC";
	private final String WRITE_COMMENT = "INSERT INTO COMMENTS(COMMENTID, BBSID, USERID, COMMENTTEXT) VALUES((SELECT NVL(MAX(COMMENTID),0)+1 FROM COMMENTS),?,?,?)";
	private final String UPDATE_COMMENT = "UPDATE COMMENTS SET COMMENTTEXT = ? WHERE COMMENTID=? AND BBSID=?";
	private final String DELETE_COMMENT = "DELETE FROM COMMENTS WHERE COMMENTID=? AND BBSID=?";
	private final String SELECT_COMMENT = "SELECT * FROM COMMENTS WHERE COMMENTID=? AND BBSID=?";
	

	public ArrayList<CommentVO> getCommentList(CommentVO vo) {
		ArrayList<CommentVO> commentList = null;
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(GET_COMMENT_LIST);
			stmt.setInt(1, vo.getBbsID());
			
			rs = stmt.executeQuery();
			
			commentList = new ArrayList<CommentVO>();
			
			while(rs.next()) {
				CommentVO comment = new CommentVO();
				comment.setCommentID(rs.getInt("COMMENTID"));
				comment.setBbsID(rs.getInt("BBSID"));
				comment.setUserID(rs.getString("USERID"));
				comment.setCommentDate(rs.getDate("COMMENTDATE"));
				comment.setCommentText(rs.getString("COMMENTTEXT"));
				commentList.add(comment);
			}
			
		} catch (SQLException e) {
			System.out.println("---> get comment list error");
		}finally {
			JDBCUtil.close(stmt, con, rs);
		}
		
		return commentList;
	}
	
	public HashMap<String, Object> writeComment(CommentVO vo) {
		
		con=JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(WRITE_COMMENT);
			stmt.setInt(1, vo.getBbsID());
			stmt.setString(2, vo.getUserID());
			stmt.setString(3, vo.getCommentText());
			
			int result = stmt.executeUpdate();
			ArrayList<CommentVO> comments = getCommentList(vo);
			//return값을 JSONObject의 생성장 파라미터로 넣어주기 위해 타입을 HashMap으로 선언하였다.
			HashMap<String, Object > hm = new HashMap<String, Object>();
			hm.put("result", result);
			hm.put("comments", comments);
			return hm;
		} catch (SQLException e) {
			System.out.println("---> write comment error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
	}
	
	public HashMap<String, Object> UpdateComment(CommentVO vo) {
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(UPDATE_COMMENT);
			stmt.setString(1, vo.getCommentText());
			stmt.setInt(2, vo.getCommentID());
			stmt.setInt(3, vo.getBbsID());
			
			int result = stmt.executeUpdate();
			HashMap<String, Object > hm = new HashMap<String, Object>();
			hm.put("result", result);
			return hm;
			
		}catch (SQLException e) {
			System.out.println("---> write comment error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
	}

	public HashMap<String, Object> DeleteComment(CommentVO vo) {
		con = JDBCUtil.getConnection();
		try {
			stmt= con.prepareStatement(DELETE_COMMENT);
			stmt.setInt(1,vo.getCommentID());
			stmt.setInt(2, vo.getBbsID());
			
			HashMap<String, Object > hm = new HashMap<String, Object>();
			int result = stmt.executeUpdate();
			hm.put("result", result);
			return hm;
		} catch (SQLException e) {
			System.out.println("---> delete comment error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
		
	}
	
	public CommentVO selectComment(CommentVO vo) {
		CommentVO comment = null;
		con = JDBCUtil.getConnection();
		try {
			stmt= con.prepareStatement(SELECT_COMMENT);
			stmt.setInt(1,vo.getCommentID());
			stmt.setInt(2, vo.getBbsID());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				comment = new CommentVO();
				comment.setCommentID(rs.getInt("COMMENTID"));
				comment.setBbsID(rs.getInt("BBSID"));
				comment.setUserID(rs.getString("USERID"));
				comment.setCommentDate(rs.getDate("COMMENTDATE"));
				comment.setCommentText(rs.getString("COMMENTTEXT"));
			}
			return comment;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
