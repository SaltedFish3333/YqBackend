package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import dao.*;
/**
 * Servlet implementation class ChangeKeywordsServlet
 */
@WebServlet("/ChangeKeywordsServlet")
public class ChangeKeywordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeKeywordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String openid = request.getParameter("openId").toString();
		String keyword = request.getParameter("keyword").toString();
		Boolean isSubscribed = Boolean.parseBoolean(request.getParameter("isSubscribed").toString());
		System.out.println(openid+keyword+isSubscribed);
		response.setContentType("application/json;charset=utf-8");//指定返回的格式为JSON格式  
		response.setCharacterEncoding("UTF-8");//setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题
		UserDao userDao = new UserDao();
		try {
			String keywords = userDao.getKeywords(openid);
			System.out.println(keywords);
			if(!isSubscribed){
				if(keywords.isEmpty()){
					keywords = keyword;
				}else{
					keywords = keywords + ","+keyword;
				}				
				
			}else{
				if(keywords.contains(","+keyword)){
					keywords = keywords.replace(","+keyword, "");
				}else{
					keywords = keywords.replace(keyword,"");
				}
			}
			userDao.changeKeywords(openid, keywords);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
