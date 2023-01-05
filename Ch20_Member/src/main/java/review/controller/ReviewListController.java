package review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import review.impl.ReviewDAO;
import review.model.ReviewVO;

@WebServlet("/reviewListCon.do")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewVO vo = new ReviewVO();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		List<ReviewVO> reviewList = reviewDAO.ReviewList(vo);
		
		HttpSession session = request.getSession();
		session.setAttribute("reviewList", reviewList);
		response.sendRedirect(request.getContextPath() + "/reviewBoard/ReviewBoardView.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
