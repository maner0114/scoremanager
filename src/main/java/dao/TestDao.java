package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {

    // 成績検索
    public List<Test> filter(int entYear, String classNum, Subject subject, int count, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql =
                "SELECT s.no AS student_no, s.name AS student_name, s.ent_year, " +
                "t.point, t.class_num, sub.name AS subject_name " +
                "FROM STUDENT s " +
                "LEFT JOIN TEST t ON s.no = t.student_no AND t.subject_cd=? AND t.no=? AND t.school_cd=? " +
                "JOIN SUBJECT sub ON sub.cd=? AND sub.school_cd=? " +
                "WHERE s.school_cd=? AND s.ent_year=? AND s.class_num=? " +
                "ORDER BY s.no";

            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, count);
            statement.setString(3, school.getCd());
            statement.setString(4, subject.getCd());
            statement.setString(5, school.getCd());
            statement.setString(6, school.getCd());
            statement.setInt(7, entYear);
            statement.setString(8, classNum);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Student stu = new Student();
                stu.setNo(rs.getString("student_no"));
                stu.setName(rs.getString("student_name"));
                stu.setEntYear(rs.getInt("ent_year"));
                stu.setClassNum(classNum);
                stu.setSchool(school);

                Subject sub = new Subject();
                sub.setCd(subject.getCd());
                sub.setName(rs.getString("subject_name"));
                sub.setSchool(school);

                Test t = new Test();
                t.setStudent(stu);
                t.setSubject(sub);
                t.setSchool(school);
                t.setClassNum(classNum);
                t.setNo(count);
                t.setPoint((Integer) rs.getObject("point"));

                list.add(t);
            }

        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { throw e; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { throw e; }
            }
        }

        return list;
    }

    // 成績登録
    public void save(Test t) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql =
                "MERGE INTO TEST KEY(student_no, subject_cd, school_cd, no) " +
                "VALUES(?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, t.getStudent().getNo());
            statement.setString(2, t.getSubject().getCd());
            statement.setString(3, t.getSchool().getCd());
            statement.setInt(4, t.getNo());
            statement.setObject(5, t.getPoint());
            statement.setString(6, t.getClassNum());

            statement.executeUpdate();

        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { throw e; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { throw e; }
            }
        }
    }

    // 削除画面用成績一覧
    public List<Test> findAll(School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql =
                "SELECT * FROM TEST WHERE SCHOOL_CD=? ORDER BY SUBJECT_CD, NO, STUDENT_NO";

            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Student stu = new Student();
                stu.setNo(rs.getString("student_no"));
                stu.setSchool(school);

                Subject sub = new Subject();
                sub.setCd(rs.getString("subject_cd"));
                sub.setSchool(school);

                Test t = new Test();
                t.setStudent(stu);
                t.setSubject(sub);
                t.setSchool(school);
                t.setNo(rs.getInt("no"));
                t.setPoint((Integer) rs.getObject("point"));
                t.setClassNum(rs.getString("class_num"));

                list.add(t);
            }

        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { throw e; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { throw e; }
            }
        }

        return list;
    }

    // 成績削除
    public void delete(String studentNo, String subjectCd, String schoolCd, int count) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql =
                "DELETE FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, studentNo);
            statement.setString(2, subjectCd);
            statement.setString(3, schoolCd);
            statement.setInt(4, count);

            statement.executeUpdate();

        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { throw e; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { throw e; }
            }
        }
    }
}
