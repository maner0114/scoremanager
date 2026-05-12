package scoremanager;

import java.util.List;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            return "login.jsp";
        }

        TestDao dao = new TestDao();
        List<Test> list = dao.findAll(teacher.getSchool());

        request.setAttribute("test_list", list);

        return "test_delete.jsp";
    }
}
