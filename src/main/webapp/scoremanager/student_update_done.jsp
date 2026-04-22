<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム　学生情報変更
	</c:param>
	
	<c:param name="subtitle">
		学生情報変更
	</c:param>
	
	<c:param name="content">
		<div>
			<p>変更が完了しました。</p>
		</div>

		<div style="margin-top: 10px;">
			<a href="StudentList.action">学生一覧へ戻る</a>
		</div>
	</c:param>
</c:import>