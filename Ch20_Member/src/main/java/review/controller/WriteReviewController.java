package review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import review.impl.ReviewDAO;
import review.model.ReviewVO;

@WebServlet("/writereview.do")
public class WriteReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String reviewText = request.getParameter("reviewText");
		String bookTitle = request.getParameter("bookTitle");
		String userId = request.getParameter("userid");
		
		ReviewVO vo = new ReviewVO();
		vo.setReviewText(reviewText);
		vo.setBookTitle(bookTitle);
		vo.setUserid(userId);
		
		ReviewDAO reviewDAO = new ReviewDAO();


		Map<String, Object> result = new HashMap<String, Object>();

		try {
			result = reviewDAO.writeReview(vo);
		}catch(Exception e) {
			System.out.println("writer Comment Controller error");
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", result.get("result"));
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObj);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
