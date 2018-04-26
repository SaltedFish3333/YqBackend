package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import dao.*;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String openid = request.getParameter("openId").toString();
		response.setContentType("application/json;charset=utf-8");//ָ�����صĸ�ʽΪJSON��ʽ  
		response.setCharacterEncoding("UTF-8");//setContentType��setCharacterEncoding��˳���ܵ������������޷�����������������  
		PrintWriter out = response.getWriter() ; 
		String responseStr = "{"+
				"\"keywords\":\"\""+","+
				"\"openid\":"+"\""+openid+"\""
				+ "}";
		out.write(responseStr);
		UserDao userDao = new UserDao();	
		try {
			if(!userDao.userExists(openid)){
				User user = new User(openid);
				userDao.addUser(user);
			}
			
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
