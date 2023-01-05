package review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import commnet.impl.CommentDAO;
import commnet.model.CommentVO;
import review.impl.ReviewDAO;
import review.model.ReviewVO;

@WebServlet("/deleteReview.do")
public class DeleteReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reviewid = request.getParameter("reviewid");
		
		ReviewVO vo = new ReviewVO();
		vo.setReviewid(Integer.parseInt(reviewid));
		
		ReviewDAO reviewDAO = new ReviewDAO();
		
		HashMap<String,Object> result = reviewDAO.DeleteReview(vo);
		
		JSONObject jsonObj = new JSONObject(result);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObj);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
