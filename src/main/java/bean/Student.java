package bean;



public class Student implements java.io.Serializable{
	
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;
	
	//ゲッター
	public String getNo() {
		return this.no;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getEntYear() {
		return this.entYear;
	}
	
	public String getClassNum() {
		return this.classNum;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	public boolean getIsAttend() {
		return this.isAttend;
	}
	
	//セッター
	public void setNo(String no) {
		this.no = no;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	public void setIsAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
}