package commnet.controller;

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

@WebServlet("/deletecomment.do")
public class DeleteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentid = request.getParameter("commentid");
		String bbsid = request.getParameter("bbsid");
		
		CommentVO vo = new CommentVO();
		vo.setCommentID(Integer.parseInt(commentid));
		vo.setBbsID(Integer.parseInt(bbsid));
		
		CommentDAO commentDAO = new CommentDAO();
		HashMap<String,Object> result = commentDAO.DeleteComment(vo);
		
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
