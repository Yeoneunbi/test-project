package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.impl.MemberDAO;
import member.model.MemberVO;

@WebServlet("/signup.do")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String id = request.getParameter("userid");
		String pw = request.getParameter("userpw");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String role = request.getParameter("role");
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setId(id);
		vo.setPassword(pw);
		vo.setEmail(email);
		vo.setTel(tel);
		vo.setRole(role);
		
		MemberDAO memberDAO = new MemberDAO();
		
		int result = memberDAO.insertUser(vo);
		
		
		if(result == -1) {
			request.setAttribute("memberSign", vo);
			RequestDispatcher rd =request.getRequestDispatcher("signUpView.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect("loginView.jsp");
		}

			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
