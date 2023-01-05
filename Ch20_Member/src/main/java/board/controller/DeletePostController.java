package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.impl.BoardDAO;
import board.model.BoardVO;

//@WebServlet("/deletePost.do")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletePostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 정보 추출
		String seq = request.getParameter("seq");
		String id = request.getParameter("id");

		// 2. DB 연동
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		vo.setId(id);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		// 화면 navigation
		response.sendRedirect("boardListcon.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
