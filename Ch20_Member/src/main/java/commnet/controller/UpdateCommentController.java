package commnet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import commnet.impl.CommentDAO;
import commnet.model.CommentVO;


@WebServlet("/updatecomment.do")
public class UpdateCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentText = request.getParameter("commentText");
		String commentID = request.getParameter("commentid");
		String bbsid = request.getParameter("bbsid");
		
		System.out.println(commentID);
		
		CommentVO vo = new CommentVO();
		vo.setCommentText(commentText);
		vo.setCommentID(Integer.parseInt(commentID));
		vo.setBbsID(Integer.parseInt(bbsid));
		
		CommentDAO commentDAO = new CommentDAO();
		
		HashMap<String,Object>map = commentDAO.UpdateComment(vo);
		
		JSONObject jsonObj = new JSONObject(map);
		jsonObj.put("result", map.get("result"));
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObj);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
