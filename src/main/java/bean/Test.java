package bean;

import java.io.Serializable;

public class Test implements Serializable {

    private Student student;   // 学生情報
    private String classNum;   // クラス番号
    private Subject subject;   // 科目情報
    private School school;     // 学校情報
    private int no;            // 回数
    private Integer point;     // 得点（NULL許容）

    // ===== Getter =====
    public Student getStudent() {
        return student;
    }

    public String getClassNum() {
        return classNum;
    }

    public Subject getSubject() {
        return subject;
    }

    public School getSchool() {
        return school;
    }

    public int getNo() {
        return no;
    }

    public Integer getPoint() {
        return point;
    }

    // ===== Setter =====
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

}

