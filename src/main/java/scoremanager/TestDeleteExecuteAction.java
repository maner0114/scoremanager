package scoremanager;

import bean.Teacher;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            return "login.jsp";
        }

        String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");
        int count = Integer.parseInt(request.getParameter("count"));

        TestDao dao = new TestDao();
        dao.delete(studentNo, subjectCd, teacher.getSchool().getCd(), count);

        return "test_delete_done.jsp";
    }
}
