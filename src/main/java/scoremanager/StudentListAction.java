package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		
		//ログインしてなかったらログインページに飛ばす
		if(teacher != null) {
		
		String entYearStr = "";
		String classNum = "";
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();
		
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");
		
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		
		//リストを初期化
		List<Integer> entYearSet = new ArrayList<Integer>();
		//10年前から1年後までの年をリストに追加
		for (int i = year -10; i < year + 1 ; i ++) {
			entYearSet.add(i);
		}
		
		//テスト用　後で消す
//		School s = new School();
//		s.setCd("tes");
//		s.setName("テスト校");
//		Teacher teacher = new Teacher();
//		teacher.setSchool(s);
		

		
		// 在学フラグが送信されていた場合
		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
			// リクエストに在学フラグをセット
			request.setAttribute("f3", isAttendStr);
		}
		
		//DBからデータ取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")) {
			// 入学年度とクラス番号を指定
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			// 入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			// 指定なしの場合
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}
		
		// レスポンス値をセット
		// リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);

		// リクエストに学生リストをセット
		request.setAttribute("students", students);
		// リクエストにデータをセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		
		
			session.setAttribute("teacher",teacher);
			return "student-list.jsp";
		}
		else {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
		}
		
	
	}

}