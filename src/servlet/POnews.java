package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class POnews
 */
@WebServlet("/POnews")
public class POnews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean firstFlag=true;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public POnews() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String getResult(String strURL, String params,boolean firstFlag) throws Exception {
		Gson gson = new Gson();
		String json = null;

		URL url = new URL(strURL);// 创建连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestMethod("POST"); // 设置请求方式
		connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
		connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
		out.append(params);
		out.flush();
		out.close();
		// 读取响应
		int length = (int) connection.getContentLength();// 获取长度
		InputStream is = connection.getInputStream();
		if (length != -1) {
			byte[] data = new byte[length];
			byte[] temp = new byte[512];
			int readLen = 0;
			int destPos = 0;
			while ((readLen = is.read(temp)) > 0) {
				System.arraycopy(temp, 0, data, destPos, readLen);
				destPos += readLen;
			}
			String result = new String(data, "UTF-8"); // utf-8编码
			json = gson.toJson(result);
		}
		//if(json.contains("\\\"error\\\": false")&&firstFlag){
		//	return "error";
		//}
		//else{
			return json;
		//}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywords = request.getParameter("keywords");
		String scope = request.getParameter("scope");

		System.out.print(keywords);
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");  
        response.setHeader("Pragme", "no-cache");
        
		PrintWriter outPrint = response.getWriter();
		Gson gson = new Gson();

		//String jsonResult;
		String strURL = "https://api.niucodata.com/api/vip/items/?access_token=df6cfe180cc1aae3faabeccccf3a716c705c410e";
		String params = "{\"keywords\":\"" + URLEncoder.encode(keywords, "UTF-8") + "\",\"scope\":\""
				+ URLEncoder.encode(scope, "UTF-8") + "\"}";
		System.out.println(params);
		try {
			//jsonResult = getResult(strURL, params,firstFlag);
			String json = null;

			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // utf-8编码
				json = gson.toJson(result);
			}
			//if(jsonResult=="error"){
			//	firstFlag=false;
			//	Thread.sleep(5000);
			//	jsonResult=getResult(strURL, params,firstFlag);
			//}
			outPrint.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
