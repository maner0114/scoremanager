package scoremanager;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if(teacher != null) {

			
			//選択された科目コードを取得
			String cd =request.getParameter("cd");

			request.setAttribute("cd", cd);

			return "student_update.jsp";
		}else {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
		}
		
	}

}