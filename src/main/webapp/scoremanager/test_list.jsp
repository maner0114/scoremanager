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
			
			<form method="get" id="filter" action="TestListSubjet.action">
				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
					<div class="col-md-2">
						科目情報
					</div>
					<div class="col-md-2">
						<label class="form-label fw-bold" for="student-f1-select">入学年度</label>
						<select class="form-select">
							<option value="0">---------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" >${year}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-2">
						<label class="form-label fw-bold" for="student-f2-select">クラス</label>
						<select class="form-select">
							<option value="0">---------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}">${num}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-4 ">					
						<label class="form-label fw-bold">科目</label>
						<select class="form-select">
							<option value="0">---------</option>
							<c:forEach var="num" items="${subject_num_set}">
								<option value="${num}">${num}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-2 text-center pt-4">
						<button class="btn btn-secondary w-100" id="filter-button">検索</button>
					</div>
				</div>
			</form>
			
			
			</div>
		</section>
	</c:param>
</c:import>