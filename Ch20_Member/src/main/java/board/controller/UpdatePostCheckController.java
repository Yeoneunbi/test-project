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

@WebServlet("/checkUpdate.do")
public class UpdatePostCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePostCheckController() {
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
			response.sendRedirect(request.getContextPath()+"/viewBoard/boardUpdateView.jsp");
		}else {
			session.setAttribute("msg2","본인아이디의 게시글만 수정할수있습니다.");
			response.sendRedirect(request.getContextPath()+"/viewBoard/boardView.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
