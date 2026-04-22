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

public class StudentCreateExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		String entYear = request.getParameter("ent_year");
		String studentNo = request.getParameter("no");
		String name = request.getParameter("name");
		String classNum = request.getParameter("class_num");
		Map<String, String> errors = new HashMap<>();
		StudentDao sDao = new StudentDao();
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		
		ClassNumDao cNumDao = new ClassNumDao();
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		
		//ログインしてなかったらログインページに飛ばす
		if(teacher == null) {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
			}
		
			
			// 学生番号の重複チェック
			if (sDao.get(studentNo) != null) {
			    errors.put("no", "学生番号が重複しています");
			}
	
			if (entYear == null || entYear.isEmpty() || entYear.equals("")) {
			    errors.put("ent_year", "入学年度を選択してください");
			}
	        	
	
	        // エラーがある場合の処理
	        if (!errors.isEmpty()) {
	        	
	    		//DBからデータ取得
	    		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
	    		List<String> list = cNumDao.filter(teacher.getSchool());
	    		
	    		//リストを初期化
	    		List<Integer> entYearSet = new ArrayList<Integer>();
	    		//10年前から1年後までの年をリストに追加
	    		for (int i = year -10; i < year + 1 ; i ++) {
	    			entYearSet.add(i);
	    		}
	        	
	            // JSPで表示するためにリクエストにセット
	            request.setAttribute("errors", errors);
	            // 入力された値を保持させる
	            request.setAttribute("ent_year", entYear);
	            request.setAttribute("no", studentNo);
	            request.setAttribute("name", name);
	            request.setAttribute("class_num", classNum);
	            
				request.setAttribute("classNumList",list);
				request.setAttribute("entYearList", entYearSet);
	            
	            return "student_create.jsp";
	        }
		        Student s = new Student();
		        s.setNo(studentNo);
		        s.setName(name);
		        s.setClassNum(classNum);
		        s.setEntYear(Integer.parseInt(entYear));
		        s.setSchool(teacher.getSchool());
		        s.setIsAttend(true); 
	
		        sDao.save(s);
	
		        return "student_create_done.jsp";
			}
			
		
	}
