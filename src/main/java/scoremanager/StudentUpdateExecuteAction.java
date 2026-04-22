package scoremanager;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		
		String entYear = request.getParameter("ent_year");
		String studentNo = request.getParameter("no");
		String name = request.getParameter("name");
		String classNum = request.getParameter("class_num");
		String is_attendStr = request.getParameter("is_attend"); 
		boolean is_attend = false;
		StudentDao sDao = new StudentDao();
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		
		//在学中ならis_attendをtrueにする
		if(is_attendStr != null && !is_attendStr.isEmpty()) {
			is_attend = true;
		}
		
		
		//ログインしてなかったらログインページに飛ばす
		if(teacher == null) {
			session.setAttribute("error", "接続が切れました");
			return "login.jsp";
			}
		
		        Student s = new Student();
		        s.setNo(studentNo);
		        s.setName(name);
		        s.setClassNum(classNum);
		        s.setEntYear(Integer.parseInt(entYear));
		        s.setSchool(teacher.getSchool());
		        s.setIsAttend(is_attend); 
	
		        sDao.update(s);
	
		        return "student_update_done.jsp";
			}
			
		
	}
