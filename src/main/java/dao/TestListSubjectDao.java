package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.TestListSubject;
import dao.StudentDao;

public class TestListSubjectDao extends DAO {

    private String baseSql = "select t.* from test t "
            + "join student s on s.no = t.student_no and s.school_cd = t.school_cd "
            + "where t.school_cd = ? and s.ent_year = ? and t.class_num = ? and t.subject_cd = ? "
            + "order by t.student_no, t.no";

    /**
     * 科目別成績を学生ごとに集約してリストにして返す
     */
    private List<TestListSubject> postFilter(ResultSet rSet, int entYear, String classNum, String schoolCd)
            throws Exception {
        // マップで学生別にまとめる
        Map<String, TestListSubject> map = new HashMap<>();
        StudentDao sDao = new StudentDao();

        try {
            // リザルトセットを全件走査
            while (rSet.next()) {
                String studentNo = rSet.getString("student_no");

                // 学生マップに未登録なら新規作成
                if (!map.containsKey(studentNo)) {
                    // 学生を学校・学生番号で特定
                    Student student = sDao.get(studentNo);
                    TestListSubject testListSubject = new TestListSubject();
                    testListSubject.setEntYear(entYear);
                    testListSubject.setStudentNo(studentNo);
                    if (student != null) {
                        testListSubject.setStudentName(student.getName());
                    }
                    testListSubject.setClassNum(classNum);
                    testListSubject.setPoints(new HashMap<>());
                    map.put(studentNo, testListSubject);
                }

                // 該当学生の points マップに試験回数→点数を追加
                int testNo = rSet.getInt("no");
                int point = rSet.getInt("point");
                map.get(studentNo).getPoints().put(testNo, point);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            throw new Exception("科目別成績データの処理に失敗しました", e);
        }

        // Map を List に変換
        return new ArrayList<>(map.values());
    }

    /**
     * 科目別成績一覧を取得
     * 
     * @param school    学校
     * @param entYear   入学年度
     * @param classNum  クラス番号
     * @param subjectCd 科目コード
     * @return 成績リスト
     */
    public List<TestListSubject> filter(School school, int entYear, String classNum, String subjectCd)
            throws Exception {
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;

        try {
            // SQL文をセット
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subjectCd);

            // プリペアードステートメントを実行
            rSet = statement.executeQuery();

            // リストへの格納処理を実行
            return postFilter(rSet, entYear, classNum, school.getCd());
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
    }

    /**
     * 試験回数リストを取得
     * 
     * @param school    学校
     * @param entYear   入学年度
     * @param classNum  クラス番号
     * @param subjectCd 科目コード
     * @return 試験回数リスト
     */
    public List<Integer> filterTestNoSet(School school, int entYear, String classNum, String subjectCd)
            throws Exception {
        // リストを初期化
        List<Integer> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;

        // SQL文：distinct で試験回数のみ取得
        String sql = "select distinct t.no from test t "
                + "join student s on s.no = t.student_no and s.school_cd = t.school_cd "
                + "where t.school_cd = ? and s.ent_year = ? and t.class_num = ? and t.subject_cd = ? "
                + "order by t.no";

        try {
            // SQL文をセット
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subjectCd);

            // プリペアードステートメントを実行
            rSet = statement.executeQuery();

            // 試験回数を取得
            while (rSet.next()) {
                list.add(rSet.getInt("no"));
            }
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
