package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            return "login.jsp";
        }

        int year = LocalDate.now().getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        List<Integer> countNumSet = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            countNumSet.add(i);
        }

        School school = teacher.getSchool();

        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();

        request.setAttribute("class_num_set", cNumDao.filter(school));
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("subject_num_set", subDao.filter(school));
        request.setAttribute("count_num_set", countNumSet);

        String f = request.getParameter("f");

        if ("sj".equals(f)) {

            try {
                int entYear = Integer.parseInt(request.getParameter("f1"));
                String classNum = request.getParameter("f2");
                String subjectCd = request.getParameter("f3");
                int count = Integer.parseInt(request.getParameter("f4"));

                if (entYear != 0 && classNum != null && subjectCd != null && count != 0) {

                    Subject subject = new Subject();
                    subject.setCd(subjectCd);

                    TestDao dao = new TestDao();

                    List<Test> resultList =
                            dao.filter(entYear, classNum, subject, count, school);

                    request.setAttribute("result_list", resultList);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "検索処理でエラーが発生しました");
            }
        }

        return "test_regist.jsp";
    }
}
