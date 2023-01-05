package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.impl.MemberDAO;
import member.model.MemberVO;

@WebServlet("/update.do")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("userpw");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String role = request.getParameter("role");
		String id = request.getParameter("userid");
		
		MemberVO vo = new MemberVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setEmail(email);
		vo.setTel(tel);
		vo.setRole(role);
		vo.setId(id);
		
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.updateUser(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("member", vo);
		response.sendRedirect("memberView.jsp");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
