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
	public static final String PASSWORD = "czc948200";
	
	/**
     * 得到数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return 数据库连接
     */
    public Connection getConn() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);                                                    //注册驱动
        Connection conn = DriverManager.getConnection(URL,UNAME,PASSWORD);        //获得数据库连接
        return conn ;                                                            //返回连接
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
     * 插入用户数据
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
