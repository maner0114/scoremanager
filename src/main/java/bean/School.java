package bean;

public class School implements java.io.Serializable{
	
	private String cd;
	private String name;
	
	//ゲッター
	public String getCd() {
		return this.cd;
	}
	
	public String getName() {
		return this.name;
	}
	
	//セッター
	public void setCd(String cd) {
		this.cd = cd;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}