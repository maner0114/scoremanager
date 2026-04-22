package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		TeacherDao t = new TeacherDao();
		
		if(t.login(id, password) != null) {
			Teacher teacher = t.login(id, password);
			session.setAttribute("teacher",teacher);
			return "menu.jsp";
		}
		else {
			session.setAttribute("error", "ログインに失敗しました、IDまたはパスワードが正しくありません");
			return "login.jsp";
		}
		
	}

}