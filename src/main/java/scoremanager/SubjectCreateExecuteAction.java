package scoremanager;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		//セッション
		HttpSession session = request.getSession();
		//ユーザーデータ
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		
		//daoの取得
		SubjectDao dao = new SubjectDao();
		
		//JSPのnameの値の取得
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		Map<String, String> errors = new HashMap();
		
		
		//重複しているか調べる
		Subject ex = dao.get(cd);
		
		//エラーがある場合
		if(ex !=null) {
			errors.put("cd", "科目コードが重複しています");
		}
		//文字数が3文字でない場合
		if(cd.length() != 3) {
			errors.put("cd", "三文字で入力してください");
		}
		//エラ一つでもある場合新規登録に戻る
		if(!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			return "subject_create.jsp";
		}
		
		//登録処理
		Subject su = new Subject();
		su.setCd(cd);
		su.setName(name);
		su.setSchool(teacher.getSchool());
		dao.save(su);
		
		//登録完了画面に遷移
		return "subject_create_done.jsp";
	}
}
