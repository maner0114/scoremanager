package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends DAO {
	
	private String baseSql = "select * from subject where school_cd = ?";
	
//科目コードを入力しその科目情報を返す
	public Subject get(String cd) 
	throws Exception {
		
		Subject s = null;
		
		//データベースと接続
		Connection con = getConnection();
		
		//SQLを作成して実行
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from subject where cd=?");
		st.setString(1, cd);
		ResultSet rs=st.executeQuery();
		
		//SQLをデータに格納
		if(rs.next()) {
			s = new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			
		}
		
		//データベースから切断
		st.close();
		con.close();
		rs.close();
		return s;
	}
	public List<Subject> filter(School school) throws Exception {

	    List<Subject> list = new ArrayList<>();

	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement(baseSql)) {

	        st.setString(1, school.getCd());

	        try (ResultSet rs = st.executeQuery()) {

	            while (rs.next()) {
	                Subject s = new Subject();
	                s.setCd(rs.getString("cd"));
	                s.setName(rs.getString("name"));

	                s.setSchool(school); // ★重要

	                list.add(s);
	            }
	        }
	    }

	    return list;
	}
	// 科目コードを指定して削除
		public boolean delete(String cd, String schoolCd) throws Exception {

		    boolean result = false;

		    try (Connection con = getConnection();
		         PreparedStatement st = con.prepareStatement(
		             "DELETE FROM subject WHERE cd = ? AND school_cd = ?")) {

		        
		        if (cd == null || schoolCd == null) {
		            throw new Exception("削除条件が不正");
		        }

		        System.out.println("cd=" + cd);
		        System.out.println("schoolCd=" + schoolCd);

		        st.setString(1, cd);
		        st.setString(2, schoolCd);

		        int count = st.executeUpdate();

		        System.out.println("削除件数=" + count);

		        if (count > 0) {
		            result = true;
		        }
		    }

		    return result;
		}
}