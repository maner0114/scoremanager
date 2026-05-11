<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">
	<c:param name="title">
		得点管理システム 成績管理
	</c:param>

	<c:param name="subtitle">
		成績管理
	</c:param>

	<c:param name="content">
		<section class="me-4">

			<form method="get" id="filter-subject" action="TestListSubject.action">
				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
					<div class="col-md-2">
						科目情報
					</div>
					<div class="col-md-2">
						<label class="form-label fw-bold" for="subject-f1-select">入学年度</label>
						<select class="form-select" id="subject-f1-select" name="f1">
							<option value="0">---------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-2">
						<label class="form-label fw-bold" for="subject-f2-select">クラス</label>
						<select class="form-select" id="subject-f2-select" name="f2">
							<option value="0">---------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-4">
						<label class="form-label fw-bold" for="subject-f3-select">科目</label>
						<select class="form-select" id="subject-f3-select" name="f3">
							<option value="0">---------</option>
							<c:forEach var="subject" items="${subject_num_set}">
								<option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name} (${subject.cd})</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-md-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
				<input type="hidden" name="f" value="sj">
			</form>

			<form method="get" id="filter-student" action="TestListStudent.action">
				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
					<div class="col-md-2">
						学生情報
					</div>
					<div class="col-md-8">
						<label class="form-label fw-bold" for="student-f4-input">学生番号</label>
						<input type="text" id="student-f4-input" name="f4" value="${f4}" class="form-control"
						maxlength="10" placeholder="学生番号を入力してください" required>
					</div>

					<div class="col-md-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
				<input type="hidden" name="f" value="st">
			</form>

			<div class="mt-2 text-warning px-4">${errors.get("f1")}</div>
			<div class="mt-1 text-warning px-4">${errors.get("f2")}</div>
			<div class="mt-1 text-warning px-4">${errors.get("f3")}</div>

			<c:choose>
				<c:when test="${not empty test_list_subject}">
					<div class="text-primary mb-2 px-3">
						<c:if test="${not empty selected_subject_name}">
							<p>科目: ${selected_subject_name} (${f3})</p>
						</c:if>
						<div class="text-muted">検索結果: ${test_list_subject.size()}件</div>
					</div>

					<div class="mx-3">
						<table class="table table-hover table-striped border shadow-sm">
							<thead class="table-light">
								<tr>
									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<c:forEach var="num" items="${num_set}">
										<th class="text-center">${num}回</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="score" items="${test_list_subject}">
									<tr>
										<td>${score.entYear}</td>
										<td>${score.classNum}</td>
										<td>${score.studentNo}</td>
										<td>${score.studentName}</td>
										<c:forEach var="num" items="${num_set}">
											<td class="text-center">
												<c:choose>
													<c:when test="${empty score.points[num]}">-</c:when>
													<c:otherwise>${score.points[num]}</c:otherwise>
												</c:choose>
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${not empty f1 and not empty f2 and not empty f3}">
					<div class="alert alert-info shadow-sm mx-3">成績情報が存在しませんでした。</div>
				</c:when>
				<c:otherwise>
					<div class="text-primary mb-3 px-3">
						<p>科目情報を選択して検索ボタンをクリックしてください。</p>
					</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>
