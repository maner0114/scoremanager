package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		if(teacher != null) {
		
		ClassNumDao cNumDao = new ClassNumDao();
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		
		//DBからデータ取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		
		//リストを初期化
		List<Integer> entYearSet = new ArrayList<Integer>();
		//10年前から1年後までの年をリストに追加
		for (int i = year -10; i < year + 1 ; i ++) {
			entYearSet.add(i);
		}
		
		
			request.setAttribute("classNumList",list);
			request.setAttribute("entYearList", entYearSet);
			return "student_create.jsp";
		}else {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
		}
		
	}

}