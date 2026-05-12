package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

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
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        // 初期検索条件リストを生成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }
        List<String> classNumSet = cNumDao.filter(school);
        List<Subject> subjectNumSet = subjectDao.filter(school);

        // リクエストパラメータを取得
        String f1Str = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        // エラーマップと検索結果初期化
        Map<String, String> errors = new HashMap<>();
        List<TestListSubject> testListSubject = new ArrayList<>();
        List<Integer> numSet = new ArrayList<>();
        String selectedSubjectName = "";
        int f1 = 0;

        // バリデーション：f1（入学年度）
        if (f1Str != null && !f1Str.isEmpty() && !"0".equals(f1Str)) {
            try {
                f1 = Integer.parseInt(f1Str);
            } catch (NumberFormatException e) {
                errors.put("f1", "入学年度の指定が不正です");
            }
        }

        // バリデーション：f2（クラス）が選択されているが f1 が未選択
        if ((f2 != null && !"0".equals(f2) && !f2.isEmpty()) && f1 == 0) {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
        }

        // バリデーション：f3（科目）が選択されているが f1 が未選択
        if ((f3 != null && !"0".equals(f3) && !f3.isEmpty()) && f1 == 0) {
            errors.put("f1", "科目を指定する場合は入学年度も指定してください");
        }

        // 検索実行：f1, f2, f3 が全て妥当な値の場合
        if (errors.isEmpty() && f1 != 0 && f2 != null && !"0".equals(f2) && f3 != null && !"0".equals(f3)) {
            // 科目別一覧を取得
            testListSubject = testListSubjectDao.filter(school, f1, f2, f3);

            // 試験回数リストを取得
            numSet = testListSubjectDao.filterTestNoSet(school, f1, f2, f3);

            // 科目名を取得
            for (Subject subject : subjectNumSet) {
                if (subject.getCd().equals(f3)) {
                    selectedSubjectName = subject.getName();
                    break;
                }
            }
        }

        // 検索フォーム用に検索条件を保持
        if (f1 != 0) {
            request.setAttribute("f1", f1);
        }
        if (f2 != null && !"0".equals(f2)) {
            request.setAttribute("f2", f2);
        }
        if (f3 != null && !"0".equals(f3)) {
            request.setAttribute("f3", f3);
        }

        // JSPへ属性をセット
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("subject_num_set", subjectNumSet);
        request.setAttribute("test_list_subject", testListSubject);
        request.setAttribute("num_set", numSet);
        request.setAttribute("selected_subject_name", selectedSubjectName);
        request.setAttribute("errors", errors);

        session.setAttribute("teacher", teacher);
        return "test_list_subject.jsp";
    }

}
