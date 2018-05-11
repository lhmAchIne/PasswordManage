package top.lhmachine.login;

import java.io.Serializable;

public class PassWord implements Serializable{
	private String webname;
	private String username;
	private String password;
	
	public PassWord(String web, String user, String pass) {
		webname = web;
		username = user;
		password = pass;
	}
	
	public void setWebName(String web) {
		webname = web;
	}
	
	public String getWebName() {
		return webname;
	}
	
	public void setUserName(String user) {
		username = user;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setPassWord(String pass) {
		password = pass;
	}
	
	public String getPassWord() {
		return password;
	}
	
	public String toString() {
		return webname+" "+username+" "+password;
	}
}