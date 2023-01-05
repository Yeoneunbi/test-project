package board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.BoardVO;
import common.JDBCUtil;

public class BoardDAO {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private String INSERT_BOARD = "INSERT INTO BOARD (SEQ,TITLE,ID,CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)+1 FROM BOARD),?,?,?)";
	private String UPDATE_BOARD = "UPDATE BOARD SET TITLE=?,CONTENT=? WHERE SEQ = ? AND ID = ?";
	private String DELETE_BOARD = "DELETE FROM BOARD WHERE SEQ=? AND ID=?";
	private String GET_BOARD = "SELECT * FROM BOARD WHERE SEQ=?";
	private String USER_GET_BOARD = "SELECT * FORM BOARD WHERE ID =? ORDER BY SEQ DESC";
	private String LIST_BOARD = "SELECT * FROM BOARD ORDER BY SEQ DESC";
	private String BOARD_LIST_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%' || ? || '%' ORDER BY SEQ DESC";
	private String BOARD_LIST_CONTENT = "SELECT * FROM BOARD WHERE CONTENT LIKE '%' || ? || '%' ORDER BY SEQ DESC";
	private String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT=? WHERE SEQ=?";
	
	public void insertBoard(BoardVO vo) {
		System.out.println("--->board insert method start");
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(INSERT_BOARD);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getId());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("-->insert error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("--->board update method start");
		con = JDBCUtil.getConnection();
		try {
			stmt=con.prepareStatement(UPDATE_BOARD);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.setString(4, vo.getId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("--->update error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("--->board delete method start");
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(DELETE_BOARD);
			stmt.setInt(1, vo.getSeq());
			stmt.setString(2, vo.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("--->delete error");
		}finally {
			JDBCUtil.close(stmt, con);
		}
		
	}
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board = null;
		
		System.out.println("---> JDBC getBoard() start");
		
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(GET_BOARD);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if (rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				stmt = con.prepareStatement(BOARD_UPDATE_CNT);
				stmt.setInt(1, rs.getInt("CNT")+1);
				stmt.setInt(2, vo.getSeq());
				stmt.executeUpdate();			
			}
		} catch (SQLException e) {
			System.out.println("getBoard error");
		} finally {
			JDBCUtil.close(stmt, con, rs);
		}
		
		return board;
	}
	
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		System.out.println("---> JDBC getBoardList() start");
		
		try {
			con = JDBCUtil.getConnection();
			
			if (vo.getSearchCondition() == null) {
				stmt = con.prepareStatement(LIST_BOARD);
			} else {
				if (vo.getSearchCondition().equals("TITLE")) {
					stmt = con.prepareStatement(BOARD_LIST_TITLE);
				} else if (vo.getSearchCondition().equals("CONTENT")) {
					stmt = con.prepareStatement(BOARD_LIST_CONTENT);
				}
				stmt.setString(1, vo.getSearchKeyword());
			}
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				BoardVO board = new BoardVO();
				
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("getBoardList error");
		} finally {
			JDBCUtil.close(stmt, con, rs);
		}
		
		return boardList;
	}

	
	public List<BoardVO> getBoardList_user(BoardVO vo) {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		System.out.println("---> get board list user method start");
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(USER_GET_BOARD);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setId(rs.getString("ID"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			System.out.println("get board list error");
		}finally {
			JDBCUtil.close(stmt, con, rs);
		}
		return boardList;
		
	}
}
