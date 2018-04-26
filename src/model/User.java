package model;

public class User {
	String openid;
	String keywords;
	
	public User(String openid,String keywords){
		this.openid = openid;
		this.keywords = keywords;
	}
	public User(String openid){
		this.openid = openid;
		this.keywords = "";
	}
	public String getOpenid(){
		return this.openid;
	}
	public String getKeywords(){
		return this.keywords;
	}
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public void serKeywors(String keywords){
		this.keywords = keywords;
	}
}
