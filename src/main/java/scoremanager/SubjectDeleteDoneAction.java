package scoremanager;

import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
public class SubjectDeleteDoneAction extends Action {

	
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

    	HttpSession session =request.getSession();
    	
    	Teacher teacher = (Teacher) session.getAttribute("teacher");
    	
        String cd = request.getParameter("cd");

        SubjectDao dao = new SubjectDao();

        
        dao.delete(cd, teacher.getSchool().getCd());  // 

        request.setAttribute("done", true);

        return "subject_delete.jsp";
    }
}