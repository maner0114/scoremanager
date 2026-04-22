package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;


public class TeacherDao extends DAO {
	public Teacher get(String id)
	throws Exception {
	    // Teacherを初期化
	    Teacher teacher = new Teacher();
	    // コネクションを確立
	    Connection con = getConnection();
	    
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from teacher where id = ?");
		st.setString(1,id);
		ResultSet rs=st.executeQuery();

	        rs = st.executeQuery();
	        
	        if (rs.next()) {
		        teacher.setId(rs.getString("id"));
		        teacher.setPassword(rs.getString("password"));
		        teacher.setName(rs.getString("name"));
		        
		        School s = new School();
		        s.setCd(rs.getString("SCHOOL_CD"));
		        teacher.setSchool(s);
		    } else {
		        teacher = null;
		    }
	        
	        
	        
			st.close();
			con.close();

	    return teacher;
	}
	
	public Teacher login(String id ,String password)
	throws Exception {
		Teacher teacher = new Teacher();
		
		boolean result;
	    // コネクションを確立
	    Connection con = getConnection();
	    
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from teacher "
				+ "where id = ? and password = ?");
		st.setString(1,id);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

	        rs = st.executeQuery();
	        
	        if (rs.next()) {
		        teacher.setId(rs.getString("id"));
		        teacher.setPassword(rs.getString("password"));
		        teacher.setName(rs.getString("name"));
		        
		        School s = new School();
		        s.setCd(rs.getString("SCHOOL_CD"));
		        teacher.setSchool(s);
		    } else {
		        teacher = null;
		    }
	        
	        
			st.close();
			con.close();

	    return teacher;
	}
}