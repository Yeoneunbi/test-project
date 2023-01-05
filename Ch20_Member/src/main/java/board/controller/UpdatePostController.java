package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.impl.BoardDAO;
import board.model.BoardVO;

//@WebServlet("/updatePost.do")
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		String seq = request.getParameter("seq");
		
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setId(id);
		vo.setSeq(Integer.parseInt(seq));
		
		boardDAO.updateBoard(vo);
		
		response.sendRedirect("getBoard.do?seq="+seq);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
