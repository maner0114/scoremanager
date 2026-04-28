package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO{
	private String baseSql = "select * from student where school_cd = ?";
	
	public String get(Student student, Subject subject, School school,int no) {
		
	}
	
	public List<Test> postFilter(ResultSet set, School school){
		
	}
	
	public  List<Test> filter(int entYear, String classNum, Subject subject, int num, School school){
		
	}
	
	public boolean save(List<Test> list) {
		
	}
	
	//変更
	public boolean  save(Test test, Connection connection) throws Exception{
		
		PreparedStatement st = null;
		int count = 0;
		
		try {
			st = connection.prepareStatement("update test set point=? where student_cd=? and subject_cd=? and school_cd=? and no=? and class_num=?");
			
			
			st.setInt(1,test.getPoint());
			st.setString(2, test.getStudent().getNo());
			st.setString(3, test.getSubject().getCd());
			st.setString(4, test.getSchool().getCd());
			st.setInt(5, test.getNo());
			st.setString(6, test.getClassNum());
			
			
			count = st.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally {
			if(st !=null) {
				st.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return count > 0;
	}
}
