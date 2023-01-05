package commnet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import commnet.impl.CommentDAO;
import commnet.model.CommentVO;

@WebServlet("/getlistcomment.do")
public class GetListCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public String GetListCommentController() {
        
    	return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bbsid = Integer.parseInt(request.getParameter("bbsID"));
		CommentVO vo = new CommentVO();
		vo.setBbsID(bbsid);
		
		ArrayList<CommentVO> comments = null;
		CommentDAO commentDAO = new CommentDAO();
		
		try {
			comments = commentDAO.getCommentList(vo);
			
			JSONObject obj = new JSONObject();
			JSONArray JsonArr = new JSONArray();
			
			for(int i = 0; i<comments.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("commentid",comments.get(i).getCommentID());
				sObject.put("bbsid", comments.get(i).getBbsID());
				sObject.put("userid", comments.get(i).getUserID());
				sObject.put("commentdate",comments.get(i).getCommentDate().toString());
				sObject.put("commentText", comments.get(i).getCommentText());
				JsonArr.add(sObject);
			}
			
			
			
			response.setContentType("apllication/json");
			response.setCharacterEncoding("utf-8");
			
			PrintWriter out = response.getWriter();
			out.print(JsonArr);
			
			
		}catch(Exception e) {
			
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
