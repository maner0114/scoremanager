package bean;

public class ClassNum implements java.io.Serializable{
	
	private String class_num;
	private School school;
	
	//ゲッター
	public String getClass_num() {
		return this.class_num;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	//セッター
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
}