package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

/**
 * Servlet implementation class CheckKeywordServlet
 */
@WebServlet("/CheckKeywordServlet")
public class CheckKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckKeywordServlet() {
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
		
		//keyword=new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
		
		response.setContentType("application/json;charset=utf-8");//ָ�����صĸ�ʽΪJSON��ʽ  
		response.setCharacterEncoding("UTF-8");//setContentType��setCharacterEncoding��˳���ܵ������������޷�����������������
		UserDao userDao = new UserDao();
		String keywords = "";
		try {
			keywords = userDao.getKeywords(openid);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isKeySubscribed = keywords.contains(keyword);
		PrintWriter out = response.getWriter() ;
		String responseStr = "{"
				+ "\"isKeySubscribed\":"+ isKeySubscribed
				+ "}";
		out.write(responseStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
