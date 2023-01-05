package review.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.JDBCUtil;
import review.model.ReviewVO;

public class ReviewDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private final String WRITE_REVIEW = "INSERT INTO REVIEW VALUES(REVIEW_SEQ.NEXTVAL,?,?,sysdate,?)";
	private final String UPDATE_REVIEW = "UPDATE REVIEW SET REVIEWTEXT = ? WHERE REVIEWID = ?";
	private final String DELETE_REVIEW = "DELETE FROM REVIEW WHERE REVIEWID=?";
	private final String LIST_REVIEW = "SELECT* FROM REVIEW ORDER BY REVIEWID DESC";	
	
	
	public HashMap<String, Object> writeReview(ReviewVO vo) {
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(WRITE_REVIEW);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getBookTitle());
			stmt.setString(3, vo.getReviewText());
			
			int result = stmt.executeUpdate();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			
			hm.put("result", result);
			
			return hm;
		} catch (SQLException e) {
			System.out.println("write review method error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		return null;
	}

	public HashMap<String, Object> updateReview(ReviewVO vo) {
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(UPDATE_REVIEW);
			stmt.setString(1, vo.getReviewText());
			stmt.setInt(2, vo.getReviewid());
			
			int result = stmt.executeUpdate();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			
			hm.put("result", result);
			
			return hm;
			
		} catch (SQLException e) {
			System.out.println("update review method error");
		}
		
		return null;
	}

	public HashMap<String, Object> DeleteReview(ReviewVO vo) {
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(DELETE_REVIEW);
			stmt.setInt(1, vo.getReviewid());
			
			int result = stmt.executeUpdate();
			HashMap<String, Object> hm = new HashMap<String, Object>();
			
			hm.put("result", result);
			
			return hm;
			
		} catch (SQLException e) {
			System.out.println("delete review method error");
		}
		return null;
	}

	public List<ReviewVO> ReviewList(ReviewVO vo) {
		ReviewVO review = null;
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(LIST_REVIEW);
			rs = stmt.executeQuery();
			
			List<ReviewVO> list = new ArrayList<ReviewVO>();
			
			while(rs.next()) {
				review = new ReviewVO();
				review.setReviewid(rs.getInt("REVIEWID"));
				review.setUserid(rs.getString("USERID"));
				review.setBookTitle(rs.getString("BOOKTITLE")); 
				review.setReviewDate(rs.getDate("REVIEWDATE"));
				review.setReviewText(rs.getString("REVIEWTEXT"));
				list.add(review);
			}
			return list;
			
		}catch (SQLException e) {
			System.out.println("review list method error");
		}finally {
			JDBCUtil.close(stmt, con, rs);
		}
		return null;
	}

}
