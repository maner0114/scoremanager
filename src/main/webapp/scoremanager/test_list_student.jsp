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

			<form method="get" id="filter-subject" action="TestListSubjet.action">
				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
					<div class="col-md-2">
						科目情報
					</div>
					<div class="col-md-2">
						<label class="form-label fw-bold">入学年度</label>
						<select class="form-select" name="f1">
							<option value="0">---------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-2">
						<label class="form-label fw-bold">クラス</label>
						<select class="form-select" name="f2">
							<option value="0">---------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}">${num}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-4">					
						<label class="form-label fw-bold">科目</label>
						<select class="form-select" name="f3">
							<option value="0">---------</option>
							<c:forEach var="num" items="${subject_num_set}">
								<option value="${num}">${num}</option>
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
						<label class="form-label fw-bold">学生番号</label>
						<input type="text" name="f4" value="${f4}" class="form-control"
						maxlength="10" placeholder="学生番号を入力してください" required>	
					</div>
					
					<div class="col-md-2 text-center pt-4">
						<button class="btn btn-secondary w-100">検索</button>
					</div>
				</div>
				<input type="hidden" name="f" value="st">
			</form>
			
			<div class="text-primary mb-3">
				<p>氏名:${student.name}(${student.no})</p>
			</div>
			<div class="mx-3">
				<c:choose>
					<c:when test="${test_list_student.size() > 0}">
						<table class="table table-hover table-striped border shadow-sm">
							<thead class="table-light">
								<tr>
									<th>科目名</th>
									<th>科目コード</th>
									<th>回数</th>
									<th>点数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="test" items="${test_list_student}">
									<tr>
										<td>${test.subjectName}</td>
										<td>${test.subjectCd}</td>
										<td>${test.num}</td>
										<td>${test.point}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info shadow-sm">成績情報が存在しませんでした。</div>
					</c:otherwise>
				</c:choose>
			</div>
		</section>
	</c:param>
</c:import>