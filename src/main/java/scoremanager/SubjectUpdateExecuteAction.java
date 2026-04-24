package scoremanager;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		//ログインしてなかったらログインページに飛ばす
		if(teacher == null) {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
			}
		
		Subject s = new Subject();
		SubjectDao subDao = new SubjectDao();
		
		s.setCd(cd);
		s.setName(name);
		s.setSchool(teacher.getSchool());
		
		//選択していた科目が既に削除されていたらエラー
        if(subDao.update(s)) { return "subject_update_done.jsp";	}
        else {
        	request.setAttribute("error", "科目が存在していません");
        	return "subject_update_error.jsp";
        	}
        
		
	}
}
