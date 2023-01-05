package member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCUtil;
import member.model.MemberVO;

public class MemberDAO {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	private final String USER_INSERT = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?)";
	private final String USER_UPDATE = "UPDATE MEMBERS SET " + "NAME = ?, PASSWORD=?, EMAIL=?, TEL =?,ROLE=? "
			+ "WHERE ID=?";
	private final String USER_GET = "SELECT * FROM MEMBERS WHERE ID = ? AND PASSWORD =?";
	private final String USER_GETID = "SELECT * FROM MEMBERS WHERE ID = ?";
	
	public int insertUser(MemberVO vo) {
		System.out.println("---> start insertUser()");
		con = JDBCUtil.getConnection();
		try {

			stmt = con.prepareStatement(USER_INSERT);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getId());
			stmt.setString(3, vo.getPassword());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getTel());
			stmt.setString(6, vo.getRole());
			
			return stmt.executeUpdate();
			
			

		} catch (SQLException e) {
			System.out.println("---> insert error");
		} finally {
			JDBCUtil.close(stmt, con);
		}
		
		return -1;
	}

	public void updateUser(MemberVO vo) {
		System.out.println("---> start updateUser()");
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(USER_UPDATE);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getEmail());
			stmt.setString(4, vo.getTel());
			stmt.setString(5, vo.getRole());
			stmt.setString(6, vo.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("update error");
		} finally {
			JDBCUtil.close(stmt, con);
		}

	}

	public MemberVO getUser(MemberVO vo) {
		System.out.println("---> start getUser()");
		MemberVO member = null;
		con = JDBCUtil.getConnection();
		try {
			stmt = con.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());

			rs = stmt.executeQuery();
			
			if (rs.next()) {
				member = new MemberVO();

				member.setName(rs.getString("NAME"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setEmail(rs.getString("EMAIL"));
				member.setTel(rs.getString("TEL"));
				member.setRole(rs.getString("ROLE"));
				
			}
		} catch (SQLException e) {
			System.out.println("get error");
		} finally {
			JDBCUtil.close(stmt, con, rs);
		}
		return member;
	}

	public String existUserId(MemberVO vo) {
		try {
			con = JDBCUtil.getConnection();
			stmt = con.prepareStatement(USER_GETID);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getString("ID");
			}
			
		} catch (SQLException e) {
			System.out.println("getUser error");
		} finally {
			JDBCUtil.close(stmt, con, rs);
		}
			
		return null;
	}
}
