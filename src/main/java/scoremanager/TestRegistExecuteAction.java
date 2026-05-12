package scoremanager;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ログインチェック
        if (teacher == null) {
            return "login.jsp";
        }

        School school = teacher.getSchool();

        String[] registNos = request.getParameterValues("regist"); // 学生番号一覧
        String subjectCd = request.getParameter("subject");        // 科目コード
        int count = Integer.parseInt(request.getParameter("count")); // 回数
        String classNum = request.getParameter("classNum");        // クラス番号

        TestDao dao = new TestDao();

        for (String studentNo : registNos) {

            // 入力欄 name="point_学生番号"
            String pointStr = request.getParameter("point_" + studentNo);

            // ブランク → 保存しない
            if (pointStr == null || pointStr.isEmpty()) {
                continue;
            }

            int point = Integer.parseInt(pointStr);

            // 0〜100 の範囲チェック（範囲外は保存しない）
            if (point < 0 || point > 100) {
                continue;
            }

            Student stu = new Student();
            stu.setNo(studentNo);
            stu.setSchool(school);
            stu.setClassNum(classNum);

            Subject sub = new Subject();
            sub.setCd(subjectCd);
            sub.setSchool(school);

            Test test = new Test();
            test.setStudent(stu);
            test.setSubject(sub);
            test.setSchool(school);
            test.setClassNum(classNum);
            test.setNo(count);
            test.setPoint(point);

            // 保存
            dao.save(test);
        }

        // 完了画面へ
        return "test_regist_done.jsp";
    }
}
