package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.nodes.Document;

import spider.*;

/**
 * Servlet implementation class LoadInfoServlet
 */
@WebServlet("/LoadInfoServlet")
public class LoadInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=utf-8");//ָ�����صĸ�ʽΪJSON��ʽ  
		response.setCharacterEncoding("UTF-8");//setContentType��setCharacterEncoding��˳���ܵ������������޷�����������������
		Document wbpage = HotSpot.get_page("http://s.weibo.com/top/summary?cate=realtimehot");
		String wbStr = HotSpot.Analysis_page(wbpage,"script");
		Document dbpage = HotSpot.get_page("https://www.douban.com/gallery/");
		String dbStr = HotSpot.Analysis_page(dbpage,"div");
		Document ttpage = HotSpot.get_page("https://www.toutiao.com/ch/news_hot/");
		String ttStr = HotSpot.Analysis_page(ttpage, "script");
		String []wbresult = HotSpot.patter_wb(wbStr);
		String []dbresult = HotSpot.patter_db(dbStr);
		String []ttresult = HotSpot.patter_tt(ttStr);
		String wbkwdStr = getStringFromArray(wbresult);
		String dbkwdStr = getStringFromArray(dbresult);
		String ttkwdStr = getStringFromArray(ttresult);
		
		wbkwdStr=wbkwdStr.replace("\n", "");
		dbkwdStr=dbkwdStr.replace("\n", "");
		ttkwdStr=ttkwdStr.replace("\n", "");
		
		PrintWriter out = response.getWriter();
		out.write("{"
				+"\"wbkwdStr\": \""+wbkwdStr + "\","
				+"\"dbkwdStr\": \""+dbkwdStr + "\","
				+"\"ttkwdStr\": \""+ttkwdStr + "\""
				+ "}");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getStringFromArray(String []array){
		String str = "";
		for(String s:array){
			str += (s+",");
		}
		str = str.substring(0,str.lastIndexOf(','));
		return str;
	}

}
