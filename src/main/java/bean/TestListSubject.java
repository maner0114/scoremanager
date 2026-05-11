package bean;

import java.util.Map;

public class TestListSubject implements java.io.Serializable{
	private int entYear;
	private String studentNo;
	private String studetnName;
	private String classNum;
	private Map<Integer,Integer> points;
	
	//ゲッター
	public int getEntYear() {
		return this.entYear;
	}
	public String getStudentNo() {
		return this.studentNo;
	}
	public String getStudentName() {
		return this.studetnName;
	}
	public String getClassNum() {
		return this.classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return this.points;
	}
	
	//指定したキーに対応する点数を、文字列として取り出す
	public String getPoint(int key) {
        Integer value = points.get(key);
        return value != null ? value.toString() : null;
    }

	
	//セッター
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public void setStudentName(String studentName) {
		this.studetnName = studentName;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public void putPoint(int key, int value) {
    points.put(key, value);
    }

	
}
