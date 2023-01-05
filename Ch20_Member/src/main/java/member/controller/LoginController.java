package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import member.impl.MemberDAO;
import member.model.MemberVO;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid");
		String pw = request.getParameter("userpw");
		
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPassword(pw);
		
		MemberVO member = null;
		MemberDAO memberDAO = new MemberDAO();
		member = memberDAO.getUser(vo);
		
		HttpSession session =request.getSession();
		
		
		if(member!=null) {
			if(vo.getPassword().equals(member.getPassword())) {
				session.setAttribute("member", member );
				response.sendRedirect("mainView.jsp");
			}
		}else {
			session.setAttribute("msg","잘못된 아이디와 비밀번호 입니다.");
			response.sendRedirect("loginView.jsp");
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
