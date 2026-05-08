package scoremanager;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");

    
        School school = new School();
        school.setCd("S001"); // ←DBにあるschool_cdに合わせる
        request.getSession().setAttribute("school", school);

        SubjectDao dao = new SubjectDao();
        Subject subject = dao.get(cd);

        request.setAttribute("subject", subject);
        request.setAttribute("done", false);

        return "subject_delete.jsp";
    }
}