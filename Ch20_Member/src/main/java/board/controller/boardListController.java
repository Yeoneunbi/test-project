package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.impl.BoardDAO;
import board.model.BoardVO;

@WebServlet("/boardListcon.do")
public class boardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public boardListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		
		// 검색조건 parameter check 해야 함
		String searchCondition = request.getParameter("searchCondition");
		String searchKeyword = request.getParameter("searchkeyword");
		
		vo.setSearchCondition(searchCondition);
		vo.setSearchKeyword(searchKeyword);
		
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		// 3. 검색 결과를 session에 저장하고 getBoardList.jsp로 이동
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		response.sendRedirect(request.getContextPath() + "/viewBoard/boardListView.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
