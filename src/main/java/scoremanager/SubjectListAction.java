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
<<<<<<< HEAD

    	if (teacher == null) {

    	    return "login.jsp";

=======

    	if (teacher == null) {
    	    return "login.jsp"; // ログインしてない時
>>>>>>> 8382051be1a7a7a6fec17c5acd827fcd100dfcaf
    	}
    	
    	
    	

    	SubjectDao sDAO = new SubjectDao();
    	List<Subject> sList = sDAO.filter(teacher.getSchool());
<<<<<<< HEAD
=======

        request.setAttribute("sList", sList);
>>>>>>> 8382051be1a7a7a6fec17c5acd827fcd100dfcaf

    	request.setAttribute("sList", sList);
        return "subject-list.jsp";
    }
}