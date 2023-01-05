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

//@WebServlet("/checkDelete.do")
public class DeletePostCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeletePostCheckController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String seq = request.getParameter("seq");
		
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		
		vo.setId(id);
		vo.setSeq(Integer.parseInt(seq));
		
		
		BoardVO vo2 = boardDAO.getBoard(vo);
		
		HttpSession session =request.getSession();
		
		if(id.equals(vo2.getId())) {
			response.sendRedirect("deletePost.do?seq="+seq+"&id="+id);
		}else {
			session.setAttribute("msg3","본인아이디의 게시글만 삭제할수있습니다.");
			response.sendRedirect(request.getContextPath()+"/viewBoard/boardView.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
