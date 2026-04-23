<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム メニュー
	</c:param>
	
	<c:param name="subtitle">
		メニュー
	</c:param>
	
	<c:param name="content">
		<section class="mt-4">
			<div class="row g-4 justify-content-start">
				
				<div class="col-md-4">
					<div class="card h-100 shadow-sm border-0" style="background-color: #f8d7da;">
						<div class="card-body d-flex align-items-center justify-content-center py-5">
							<a href="StudentList.action" class="btn btn-link text-decoration-none fs-4 fw-bold text-primary">学生管理</a>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card h-100 shadow-sm border-0" style="background-color: #d1e7dd;">
						<div class="card-body py-4 text-center">
							<h5 class="card-title fw-bold mb-3">成績管理</h5>
							<div class="d-grid gap-2">
								<a href="#" class="btn btn-outline-primary bg-white border-0 py-2">成績登録</a>
								<a href="TestList.action" class="btn btn-outline-primary bg-white border-0 py-2">成績参照</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-4">
					<div class="card h-100 shadow-sm border-0" style="background-color: #cfe2ff;">
						<div class="card-body d-flex align-items-center justify-content-center py-5">
							<a href="SubjectList.action" class="btn btn-link text-decoration-none fs-4 fw-bold text-primary">科目管理</a>
						</div>
					</div>
				</div>

			</div>
		</section>
	</c:param>
</c:import>