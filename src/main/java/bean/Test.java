package bean;


public class Test implements java.io.Serializable{
	private Student student;
	private String classNum;
	private Subject subejct;
	private School school;
	private int no;
	private int point;
	
	//ゲッター
	public Student getStudent() {
		return student;
	}
	public String getClassNum() {
		return classNum;
	}
	public Subject getSubject(){
		return subejct;
	}
	public School getSchool() {
		return school;
	}
	public int getNo() {
		return no;
	}
	public int getPoint() {
		return point;
	}
	
	//セッター
	public void setStudent(Student student) {
		this.student = student;
	}
	public void setClasaNum(String classNum) {
		this.classNum = classNum;
	}
	public void setSubject(Subject subject) {
		this.subejct = subject;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
