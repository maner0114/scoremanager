package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//追加のメソッド
	public Boolean save(Subject subject)throws Exception {
		
		Connection con = getConnection();
		PreparedStatement st = null;
		
		int count = 0;
		try {
			
			//SQL
			st = con.prepareStatement("insert into subject(school_cd, cd, name) values(?,?,?)");
			st.setString(1, subject.getSchool().getCd());
			st.setString(2, subject.getCd());
			st.setString(3, subject.getName());
			
			count = st.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}finally {
				if(st != null) {
					st.close();
				}if(con !=null) {
					con.close();
				}
		}
		return count >0;
	}
	
	//科目の変更
	public boolean update(Subject subject) throws Exception {
	    // 接続
	    Connection con = null;
	    PreparedStatement st = null;
	    int count = 0;

	    try {
	        con = getConnection();
	        // SQLをデータに格納
	        st = con.prepareStatement(
	            "UPDATE subject SET name = ? WHERE cd = ? and school_cd = ?"
	        );

	        // プレースホルダ（?）に値をセット
	        st.setString(1, subject.getName());
	        st.setString(2, subject.getCd());
	        st.setString(3, subject.getSchool().getCd());

	        // 実行
	        count = st.executeUpdate();

	    }  catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        // 接続を切る
	        if (st != null) {
	            st.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	    
	    // 1行以上更新されていれば成功(true)、そうでなければ失敗(false)
	    return count > 0;
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