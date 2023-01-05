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


@WebServlet("/checkID.do")
public class CheckIDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckIDController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("userid");
		
		
		// 2. DB 연동 처리
		MemberVO vo = new MemberVO();
		vo.setId(id);
		
		MemberDAO MemberDAO = new MemberDAO();
		String newId = MemberDAO.existUserId(vo);
		
		JSONObject idJson = new JSONObject();
		idJson.put("id", newId);
		response.setContentType("application/json");
		
		
		PrintWriter out = response.getWriter();
		out.print(idJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
