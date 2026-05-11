package scoremanager;

import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

    public String execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

    	HttpSession session = request.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("teacher");

    	if (teacher == null) {

    	    return "login.jsp";

    	}
    	
    	
    	

    	SubjectDao sDAO = new SubjectDao();
    	List<Subject> sList = sDAO.filter(teacher.getSchool());

    	request.setAttribute("sList", sList);
        return "subject-list.jsp";
    }
}