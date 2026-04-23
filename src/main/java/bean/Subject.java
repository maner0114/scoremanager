package bean;

public class Subject implements java.io.Serializable{
	private String cd;
	private String name;
	private School school;
	
	//ゲッター
	public String getCd() {
		return this.cd;
	}
	public String getName() {
		return this.name;
	}
	public School getSchool() {
		return this.school;
	}
	
	//セッター
	public void setCd(String cd) {
		this.cd=cd;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setSchool(School school) {
		this.school=school;
	}
}