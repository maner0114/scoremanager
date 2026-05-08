package scoremanager;
 
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class LoginAction extends Action {
 
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        HttpSession session = request.getSession();
 
        String id = request.getParameter("id");
        String password = request.getParameter("password");
 
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password); // ← DBから取得
 
        if (teacher != null) {
            session.setAttribute("teacher", teacher); // ★これが超重要
            session.setAttribute("school", teacher.getSchool());
            return "subject-list.action"; // ログイン後画面
        } else {
            request.setAttribute("error", "ログインに失敗しました");
            return "login.jsp";
        }
    }
}
 