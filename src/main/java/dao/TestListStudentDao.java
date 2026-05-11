package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends DAO {
	
	private String baseSql = "select * from test";
	
	private List<TestListStudent> postFilter(ResultSet rSet) {
        // リストを初期化
        List<TestListStudent> list = new ArrayList<>();
        
        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                // インスタンス化
                TestListStudent testListStudent = new TestListStudent();

                testListStudent.setSubjectName(rSet.getString("subject_cd"));
                testListStudent.setSubjectCd(rSet.getString("SCHOOL_CD"));
                testListStudent.setNum(rSet.getInt("no"));
                testListStudent.setPoint(rSet.getInt("point"));
                
                // リストに追加
                list.add(testListStudent);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }
	
	public List<TestListStudent> filter(String no)throws Exception {
	    // リストを初期化
	    List<TestListStudent> list = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // リザルトセット
	    ResultSet rSet = null;
	    // SQL文の条件
	    String condition = " where student_no = ?";


	    try {
	        // SQL文をセット
	        statement = connection.prepareStatement(baseSql + condition);
	        statement.setString(1, no);
	        
	        // プライベートステートメントを実行
	        rSet = statement.executeQuery();
	        
	        // リストへの格納処理を実行
	        list = postFilter(rSet);
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // プリペアードステートメントを閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
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