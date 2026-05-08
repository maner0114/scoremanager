package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestScoresAction extends Action {

	@Override
	public String execute(
		HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {

		HttpSession session =
			request.getSession();

		Teacher teacher =
			(Teacher) session.getAttribute("teacher");

		// ログイン確認
		if (teacher == null) {

			session.setAttribute(
				"error",
				"接続が切れました"
			);

			return "login.jsp";
		}

		// パラメータ取得
		String entYearStr =
			request.getParameter("f1");

		String classNum =
			request.getParameter("f2");

		String subjectCd =
			request.getParameter("f3");

		int entYear = 0;

		// 入学年度変換
		if (entYearStr != null &&
			!entYearStr.equals("0")) {

			entYear =
				Integer.parseInt(entYearStr);
		}

		// エラーメッセージ
		String error = null;

		// 検索ボタン押下時のみチェック
		if (request.getParameter("f1") != null) {

			if (entYear == 0 ||
				classNum == null ||
				classNum.equals("0") ||
				subjectCd == null ||
				subjectCd.equals("0")) {

				error =
					"入学年度・クラス・科目を選択してください";
			}
		}

		// DAO
		TestListSubjectDao dao =
			new TestListSubjectDao();

		ClassNumDao cNumDao =
			new ClassNumDao();

		SubjectDao subDao =
			new SubjectDao();

		// 検索結果
		List<Test> list =
			new ArrayList<Test>();

		// 条件が全部揃っている時のみ検索
		if (error == null &&
			entYear != 0 &&
			classNum != null &&
			!classNum.equals("0") &&
			subjectCd != null &&
			!subjectCd.equals("0")) {

			list =
				dao.filter(
					teacher.getSchool(),
					entYear,
					classNum,
					subjectCd
				);
		}

		// 入学年度リスト
		LocalDate todaysDate =
			LocalDate.now();

		int year =
			todaysDate.getYear();

		List<Integer> entYearSet =
			new ArrayList<Integer>();

		for (int i = year - 10;
			 i < year + 1;
			 i++) {

			entYearSet.add(i);
		}

		// クラス一覧
		List<String> classlist =
			cNumDao.filter(
				teacher.getSchool()
			);

		// 科目一覧
		List<Subject> subjectlist =
			subDao.filter(
				teacher.getSchool()
			);

		// JSPへ値を渡す
		request.setAttribute(
			"f1",
			entYear
		);

		request.setAttribute(
			"f2",
			classNum
		);

		request.setAttribute(
			"f3",
			subjectCd
		);

		request.setAttribute(
			"list",
			list
		);

		request.setAttribute(
			"error",
			error
		);

		request.setAttribute(
			"ent_year_set",
			entYearSet
		);

		request.setAttribute(
			"class_num_set",
			classlist
		);

		request.setAttribute(
			"subject_num_set",
			subjectlist
		);

		return "subject_score_list.jsp";
	}
}