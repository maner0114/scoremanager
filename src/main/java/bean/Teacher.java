package bean;

import java.io.Serializable;

public class Teacher extends User implements Serializable {

	//教員ID:String
	private String id;

	 //パスワード:String
	private String password;


	 //教員名:String
	private String name;


	//所属校:School
	private School school; 
	//ゲッター
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public School getSchool() {
		return school;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	
}