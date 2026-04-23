package dao;

import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO {
	
	//あとでいれる
	private String baseSql = "select * from ";
	
	private List<TestListSubject> postFilter(ResultSet rSet){
		
		
	}
	
	public List<TestListSubject> filter(int entYear, String subject,School school){
		
	}
}