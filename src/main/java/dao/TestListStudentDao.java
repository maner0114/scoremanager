package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;



public class TestListStudentDao extends DAO{
	
	private String baseSql = "select * from test where school_cd = ?";
	
	public List<TestListStudent> postFilter(ResultSet rSet)throws Exception{
		//リストのインスタンス化
		List<TestListStudent> list = new ArrayList<>();
		
		try {
			while(rSet.next()) {
				TestListStudent testliststudent = new TestListStudent();
				
				testliststudent.setSubjectName(rSet.getString("student_no"));
				testliststudent.setSubjectCd(rSet.getString("subject_cd"));
				testliststudent.setNum(rSet.getInt("no"));
				testliststudent.setPoint(rSet.getInt("point"));
				
				list.add(testliststudent);
			}
			
			}catch(SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			return list;
			
		}
	
	public List<TestListStudent> filter(Student student)throws Exception{
		//リストの初期化
		List<TestListStudent> list = new ArrayList<>();
		
		//データベースの接続
		Connection connection = getConnection();
		
		PreparedStatement st= null;
		ResultSet rs = null;
		
		String sql = "select student_no,subject_cd,no,point,class_num from test where student_no = ?";
		String order = "order by subject_cd, no";
		
		try {
			st = connection.prepareStatement(sql + order);
			st.setString(1, student.getNo());
			
			rs = st.executeQuery();
			
			list = postFilter(rs);
			
		}catch(Exception e) {
			throw e;
		}finally {
			if(st != null) {
				try {
					st.close();
				}catch(SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return list;
		}
}
