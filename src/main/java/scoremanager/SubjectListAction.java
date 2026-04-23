package scoremanager;

import java.util.List;

import bean.School;
import bean.Subject;
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
    	School school = (School) session.getAttribute("school");

    	// 仮対応（テスト用）
    	if (school == null) {
    	    school = new School();
    	    school.setCd("tes");
    	}

    	SubjectDao sDAO = new SubjectDao();
    	List<Subject> sList = sDAO.filter(school);

        request.setAttribute("sList", sList);

        return "subject-list.jsp";
    }
}