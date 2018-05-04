package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.*;

public class UserDao {
	public static final String URL = "jdbc:mysql://localhost:3306/yqanalysis?serverTimezone=GMT&useSSL=false";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String UNAME = "root";
	public static final String PASSWORD = "123456";
	
	/**
     * �õ����ݿ�����
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return ���ݿ�����
     */
    public Connection getConn() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);                                                    //ע������
        Connection conn = DriverManager.getConnection(URL,UNAME,PASSWORD);        //������ݿ�����
        return conn ;                                                            //��������
    }
    
    public boolean userExists(String openid) throws ClassNotFoundException, SQLException{
    	String sql = "select * from user where openid=?";
    	Connection conn = getConn();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, openid);
    	ResultSet rs = ps.executeQuery();
    	boolean isExist = rs.next();
    	rs.close();
    	ps.close();
    	conn.close();
    	return isExist;
    	
    }
    
    /**
     * �����û�����
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public void addUser(User user) throws ClassNotFoundException, SQLException{
    	String sql = "insert into user values(?,?)";
    	Connection conn = getConn();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, user.getOpenid());
    	ps.setString(2, user.getKeywords());
    	ps.execute();
    	ps.close();
    	conn.close();
    }
    
    public void changeKeywords(String openid, String keywords) throws ClassNotFoundException, SQLException{
    	String sql = "update user set keywords= ? where openid= ?";
    	Connection conn = getConn();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, keywords);
    	ps.setString(2, openid);
    	ps.execute();
    	ps.close();
    	conn.close();
    }
    
    public String getKeywords(String openid) throws ClassNotFoundException, SQLException{
    	String sql = "select keywords from user where openid= ?";
    	Connection conn = getConn();
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.setString(1, openid);
    	ResultSet rs = ps.executeQuery();
    	String keywords = "";
    	while(rs.next()){
    		keywords = rs.getString("keywords");
    	}
    	rs.close();
    	ps.close();
    	conn.close();
    	System.out.println(keywords);
    	return keywords;
    	
    }
    
    
    
    
}
