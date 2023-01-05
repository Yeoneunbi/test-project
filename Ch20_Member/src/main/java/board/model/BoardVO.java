package board.model;

import java.util.Date;

public class BoardVO {
	private int seq;
	private String title;
	private String id;
	private String content;
	private Date regDate;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;
	
	public BoardVO() {
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int sea) {
		this.seq = sea;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", id=" + id + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ "]";
	}

	
	
	
	
	
	

}
