package commnet.controller;

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

import commnet.impl.CommentDAO;
import commnet.model.CommentVO;

@WebServlet("/writerComment.do")
public class WriterCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriterCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String bbsID = request.getParameter("bbsID");
		String userID = request.getParameter("userID");
		String contentText = request.getParameter("commentText");
		
		CommentVO vo = new CommentVO();
		vo.setBbsID(Integer.parseInt(bbsID));
		vo.setUserID(userID);
		vo.setCommentText(contentText);
		
		CommentDAO commentDAO = new CommentDAO();


		Map<String, Object> result = new HashMap<String, Object>();

		try {
			result = commentDAO.writeComment(vo);
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
