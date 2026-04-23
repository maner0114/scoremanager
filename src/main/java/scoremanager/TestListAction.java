package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		
		//ログインしてなかったらログインページに飛ばす
		if(teacher != null) {
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao SubDao = new SubjectDao();
		
		
		//リストを初期化
		List<Integer> entYearSet = new ArrayList<Integer>();
		//10年前から1年後までの年をリストに追加
		for (int i = year -10; i < year + 1 ; i ++) {
			entYearSet.add(i);
		}
		

		
		
		//DBからデータ取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> classlist = cNumDao.filter(teacher.getSchool());
		//ログインユーザーの学校コードをもとに科目データを取得
		List<Subject> subjectlist = SubDao.filter(teacher.getSchool());

		
		// レスポンス値をセット
		// リクエストにデータをセット
		request.setAttribute("class_num_set", classlist);
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("subject_num_set", subjectlist);
		
		
			session.setAttribute("teacher",teacher);
			return "test_list.jsp";
		}
		else {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
		}
		
	
	}

}