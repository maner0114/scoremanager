package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // ログインチェック
        if (teacher == null) {
            session.setAttribute("error", "接続が切れました");
            return "login.jsp";
        }

        School school = teacher.getSchool();
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        // DAO初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();


        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        List<String> classNumSet = cNumDao.filter(school);
        List<bean.Subject> subjectNumSet = subjectDao.filter(school);

        // リクエストパラメータを取得（学生番号 f4）
        String f4 = request.getParameter("f4");

        // 変数初期化
        List<TestListStudent> testListStudent = new ArrayList<>();
        Student student = null;
        Map<String, String> errors = new HashMap<>();

        // 検索実行：学生番号が入力されている場合
        if (f4 != null && !f4.isEmpty()) {
            // 学生情報の取得（氏名表示用）
            student = studentDao.get(f4);

            if (student != null) {
                // 学生が存在する場合、その学生の成績一覧を取得
                testListStudent = testListStudentDao.filter(f4);
            } else {
                // 学生が存在しない場合のエラー処理
                errors.put("f4", "学生情報が存在しませんでした");
            }
        }

        // JSPへ属性をセット
        request.setAttribute("f4", f4); // 検索窓の値を保持
        request.setAttribute("student", student); // 学生情報（氏名・番号用）
        request.setAttribute("test_list_student", testListStudent); // 成績リスト
        
        // フォーム表示継続用のデータ
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("subject_num_set", subjectNumSet);
        request.setAttribute("errors", errors);

        return "test_list_student.jsp";
    }
}