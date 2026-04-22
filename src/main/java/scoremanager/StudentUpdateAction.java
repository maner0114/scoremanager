package scoremanager;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if(teacher != null) {
		
		ClassNumDao cNumDao = new ClassNumDao();
		StudentDao sDao = new StudentDao();
		Student student = new Student();
		
		String no =request.getParameter("no");
		
		//DBからデータ取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		
		//選択された学生の詳細データを取得
		student = sDao.get(no);
		
		
			request.setAttribute("classNumList",list);
			request.setAttribute("no",student.getNo());
			request.setAttribute("name", student.getName());
			request.setAttribute("ent_year", student.getEntYear());
			request.setAttribute("selected_class_num", student.getClassNum());
			request.setAttribute("is_attend", student.getIsAttend());
			return "student_update.jsp";
		}else {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
		}
		
	}

}