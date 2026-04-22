<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム 学生管理
	</c:param>
	
	<c:param name="subtitle">
		学生管理
	</c:param>
	
	<c:param name="content">
		<section class="me-4">
			
			<div class="my-2 text-end px-4">
				<a href="StudentCreate.action" class="btn btn-outline-primary btn-sm">新規登録</a>
			</div>
			
			<form method="get" id="filter">
				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
					<div class="col-md-4">
						<label class="form-label fw-bold" for="student-f1-select">入学年度</label>
						<select class="form-select" id="student-f1-select" name="f1">
							<option value="0">---------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-4">
						<label class="form-label fw-bold" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">---------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-2 form-check text-center pt-4">
						<input class="form-check-input mt-2" type="checkbox" id="student-f3-check" name="f3" value="t"
							<c:if test="${!empty f3}">checked</c:if> />
						<label class="form-check-label pt-1" for="student-f3-check">在学中</label>
					</div>
					
					<div class="col-md-2 text-center pt-4">
						<button class="btn btn-secondary w-100" id="filter-button">絞込み</button>
					</div>
				</div>
				<div class="mt-2 text-warning px-4">${errors.get("f1")}</div>
			</form>
			
			<div class="mx-3">
				<c:choose>
					<c:when test="${students.size() > 0}">
						<div class="mb-2 text-muted">検索結果：${students.size()}件</div>
						<table class="table table-hover table-striped border shadow-sm">
							<thead class="table-light">
								<tr>
									<th>入学年度</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th>クラス</th>
									<th class="text-center">在学中</th>
									<th class="text-center">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="student" items="${students}">
									<tr>
										<td>${student.entYear}</td>
										<td>${student.no}</td>
										<td>${student.name}</td>
										<td>${student.classNum}</td>
										<td class="text-center">
											<c:choose>
												<c:when test="${student.isAttend}">〇</c:when>
												<c:otherwise>×</c:otherwise>
											</c:choose>
										</td>
										<td class="text-center">
											<a href="StudentUpdate.action?no=${student.no}" class="btn btn-sm btn-link py-0">変更</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<div class="alert alert-info shadow-sm">学生情報が存在しませんでした。</div>
					</c:otherwise>
				</c:choose>
			</div>
		</section>
	</c:param>
</c:import>