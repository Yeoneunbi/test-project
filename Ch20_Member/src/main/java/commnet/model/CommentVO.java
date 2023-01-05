package commnet.model;

import java.util.Date;

public class CommentVO {
	private int commentID; //코멘트 seq
	private int bbsID; //게시물번호 seq
	private String userID; //댓글작성자
	private Date commentDate; //댓글등록날짜
	private String commentText; //댓글내용
	
	public CommentVO() {
	}

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public int getBbsID() {
		return bbsID;
	}

	public void setBbsID(int bbsID) {
		this.bbsID = bbsID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}


	
	
}
