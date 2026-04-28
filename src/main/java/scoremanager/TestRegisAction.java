package scoremanager;

import bean.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegisAction extends Action{
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//セッション
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		
		//teacherがnullでないときは新規登録
		if (teacher != null) {
		      return "test_regist.jsp";
		    }else{
		    	//nullのときはログイン画面にとぶ
		        session.setAttribute("error", "接続が切れました");
		        return "login.jsp";
		    }
	}
	
}
