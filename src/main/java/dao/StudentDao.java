package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {
	
	private String baseSql = "select * from student where school_cd = ?";
	
//学生番号を入力しその生徒の情報を返す
	public Student get(String no) 
	throws Exception {
		
		Student s = null;
		
		//データベースと接続
		Connection con = getConnection();
		
		//SQLを作成して実行
		PreparedStatement st;
		st=con.prepareStatement(
				"select * from student where no=?");
		st.setString(1, no);
		ResultSet rs=st.executeQuery();
		
		//SQLをデータに格納
		if(rs.next()) {
			s = new Student();
			s.setNo(rs.getString("no"));
			s.setName(rs.getString("name"));
			s.setEntYear(rs.getInt("ent_year"));
			s.setClassNum(rs.getString("class_num"));
			s.setIsAttend(rs.getBoolean("is_attend"));
			
		}
		
		//データベースから切断
		st.close();
		con.close();
		return s;
	}
	
	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Student student = new Student();
				// 学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setIsAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);
				// リストに追加
				list.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
	    // リストを初期化
	    List<Student> list = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // リザルトセット
	    ResultSet rSet = null;
	    // SQL文の条件
	    String condition = "and ent_year=? and class_num=?";
	    // SQL文のソート
	    String order = " order by no asc";

	    // SQL文の在学フラグ条件
	    String conditionIsAttend = "";
	    // 在学フラグがtrueの場合
	    if (isAttend) {
	        conditionIsAttend = "and is_attend=true";
	    }

	    try {
	        // SQL文をセット
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

	        statement.setString(1, school.getCd());
	        statement.setInt(2, entYear);
	        statement.setString(3, classNum);
	        
	        // プライベートステートメントを実行
	        rSet = statement.executeQuery();
	        
	        // リストへの格納処理を実行
	        list = postFilter(rSet, school);
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
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
	    // リストを初期化
	    List<Student> list = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // リザルトセット
	    ResultSet rSet = null;
	    // SQL文の条件
	    String condition = "and ent_year=?";
	    // SQL文のソート
	    String order = " order by no asc";

	    // SQL文の在学フラグ条件
	    String conditionIsAttend = "";
	    // 在学フラグがtrueの場合
	    if (isAttend) {
	        conditionIsAttend = "and is_attend=true";
	    }

	    try {
	        // SQL文をセット
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

	        statement.setString(1, school.getCd());
	        statement.setInt(2, entYear);
	        
	        // プライベートステートメントを実行
	        rSet = statement.executeQuery();
	        
	        // リストへの格納処理を実行
	        list = postFilter(rSet, school);
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

	public List<Student> filter(School school, boolean isAttend) throws Exception {
	    // リストを初期化
	    List<Student> list = new ArrayList<>();
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアードステートメント
	    PreparedStatement statement = null;
	    // リザルトセット
	    ResultSet rSet = null;
	    // SQL文の条件
	    String condition = "";
	    // SQL文のソート
	    String order = " order by no asc";

	    // SQL文の在学フラグ条件
	    String conditionIsAttend = "";
	    // 在学フラグがtrueの場合
	    if (isAttend) {
	        conditionIsAttend = "and is_attend=true";
	    }

	    try {
	        // SQL文をセット
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);

	        statement.setString(1, school.getCd());
	        
	        // プライベートステートメントを実行
	        rSet = statement.executeQuery();
	        
	        // リストへの格納処理を実行
	        list = postFilter(rSet, school);
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

	public boolean save(Student student) throws Exception {
		// 接続
	    Connection connection = getConnection(); 
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // SQL文の準備
	        statement = connection.prepareStatement(
	            "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)"
	        );

	        // プレースホルダ（?）に値をセット
	        statement.setString(1, student.getNo());
	        statement.setString(2, student.getName());
	        statement.setInt(3, student.getEntYear());
	        statement.setString(4, student.getClassNum());
	        statement.setBoolean(5, student.getIsAttend());
	        // Schoolオブジェクトからコード（CD）を取得してセット
	        statement.setString(6, student.getSchool().getCd());

	        // 実行
	        count = statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースの解放
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    
		}
	    // 1行以上更新されていれば成功
	    return count > 0;

	}
	
	public boolean update(Student student) throws Exception {
	    // 接続
	    Connection connection = getConnection(); 
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // SQL文の準備：noをキーにして他の項目を更新する
	        statement = connection.prepareStatement(
	            "UPDATE student SET name = ?, class_num = ?, is_attend = ? WHERE no = ?"
	        );

	        // プレースホルダ（?）に値をセット
	        statement.setString(1, student.getName());
	        statement.setString(2, student.getClassNum());
	        statement.setBoolean(3, student.getIsAttend());
	        // WHERE句の条件として学生番号を指定
	        statement.setString(4, student.getNo());

	        // 実行
	        count = statement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        // リソースの解放
	        if (statement != null) {
	            statement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	    // 1行以上更新されていれば成功
	    return count > 0;
	}

}

