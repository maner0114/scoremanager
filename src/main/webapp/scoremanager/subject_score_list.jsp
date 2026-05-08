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

			<!-- 科目検索 -->
			<form method="get" action="TestScores.action">

				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">

					<div class="col-md-2">
						科目情報
					</div>

					<div class="col-md-2">

						<label class="form-label fw-bold">
							入学年度
						</label>

						<select class="form-select" name="f1">

							<option value="0">
								---------
							</option>

							<c:forEach var="year" items="${ent_year_set}">

								<option value="${year}"
									<c:if test="${year == f1}">
										selected
									</c:if>>

									${year}

								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-md-2">

						<label class="form-label fw-bold">
							クラス
						</label>

						<select class="form-select" name="f2">

							<option value="0">
								---------
							</option>

							<c:forEach var="num" items="${class_num_set}">

								<option value="${num}"
									<c:if test="${num == f2}">
										selected
									</c:if>>

									${num}

								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-md-4">

						<label class="form-label fw-bold">
							科目
						</label>

						<select class="form-select" name="f3">

							<option value="0">
								---------
							</option>

							<c:forEach var="subject" items="${subject_num_set}">

								<option value="${subject.cd}"
									<c:if test="${subject.cd == f3}">
										selected
									</c:if>>

									${subject.name}

								</option>

							</c:forEach>

						</select>

					</div>

					<div class="col-md-2 text-center pt-4">

						<button class="btn btn-secondary w-100">
							検索
						</button>

					</div>

				</div>

			</form>

			<!-- エラー表示 -->
			<c:if test="${!empty error}">

				<div class="alert alert-danger mx-3">

					${error}

				</div>

			</c:if>

			<!-- 学生番号検索 -->
			<form method="get" action="TestListStudent.action">

				<div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">

					<div class="col-md-2">
						学生情報
					</div>

					<div class="col-md-8">

						<label class="form-label fw-bold">
							学生番号
						</label>

						<input
							type="text"
							name="f4"
							value="${f4}"
							class="form-control"
							maxlength="10"
							placeholder="学生番号を入力してください"
							required>

					</div>

					<div class="col-md-2 text-center pt-4">

						<button class="btn btn-secondary w-100">
							検索
						</button>

					</div>

				</div>

			</form>

			<!-- 検索結果 -->
			<div class="mx-3">

				<c:choose>

					<c:when test="${list.size() > 0}">

						<div class="mb-2 text-muted">

							検索結果：${list.size()}件

						</div>

						<table class="table table-hover table-striped border shadow-sm">

							<thead class="table-light">

								<tr>

									<th>入学年度</th>
									<th>クラス</th>
									<th>学生番号</th>
									<th>氏名</th>
									<th class="text-center">1回</th>
									<th class="text-center">2回</th>

								</tr>

							</thead>

							<tbody>

								<c:forEach var="test" items="${list}">

									<tr>

										<td>${test.student.entYear}</td>

										<td>${test.student.classNum}</td>

										<td>${test.student.no}</td>

										<td>${test.student.name}</td>

										<td class="text-center">

											<c:choose>

												<c:when test="${test.no == 1}">

													${test.point}

												</c:when>

												<c:otherwise>

													-

												</c:otherwise>

											</c:choose>

										</td>

										<td class="text-center">

											<c:choose>

												<c:when test="${test.no == 2}">

													${test.point}

												</c:when>

												<c:otherwise>

													-

												</c:otherwise>

											</c:choose>

										</td>

									</tr>

								</c:forEach>

							</tbody>

						</table>

					</c:when>

					<c:otherwise>

						<div class="alert alert-info shadow-sm">

							成績情報が存在しませんでした。

						</div>

					</c:otherwise>

				</c:choose>

			</div>

		</section>

	</c:param>

</c:import>