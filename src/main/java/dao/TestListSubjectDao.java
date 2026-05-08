package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestListSubjectDao extends DAO {

    public List<Test> filter(
        School school,
        int entYear,
        String classNum,
        String subjectCd
    ) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection con = getConnection();

        String sql =
            "select s.ent_year, s.class_num, s.no, s.name, "
          + "t.no as test_no, t.point, "
          + "sub.cd as subject_cd, sub.name as subject_name "
          + "from student s "
          + "inner join test t "
          + "on s.no = t.student_no "
          + "inner join subject sub "
          + "on t.subject_cd = sub.cd "
          + "where s.school_cd = ? "
          + "and s.ent_year = ? "
          + "and s.class_num = ? "
          + "and t.subject_cd = ? "
          + "order by s.no";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1, school.getCd());
        st.setInt(2, entYear);
        st.setString(3, classNum);
        st.setString(4, subjectCd);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Test test = new Test();

            Student student = new Student();

            student.setEntYear(
                rs.getInt("ent_year")
            );

            student.setClassNum(
                rs.getString("class_num")
            );

            student.setNo(
                rs.getString("no")
            );

            student.setName(
                rs.getString("name")
            );

            Subject subject = new Subject();

            subject.setCd(
                rs.getString("subject_cd")
            );

            subject.setName(
                rs.getString("subject_name")
            );

            test.setStudent(student);

            test.setSubject(subject);

            test.setNo(
                rs.getInt("test_no")
            );

            test.setPoint(
                rs.getInt("point")
            );

            list.add(test);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
}