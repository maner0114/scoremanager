<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム　学生情報登録
	</c:param>
	
	<c:param name="subtitle">
		学生情報登録
	</c:param>
	
	<c:param name="content">
		<div>
			<p>登録が完了しました。</p>
		</div>

		<div style="margin-top: 20px;">
			<a href="StudentCreate.action">続けて登録する</a>
		</div>

		<div style="margin-top: 10px;">
			<a href="StudentList.action">学生一覧へ戻る</a>
		</div>
	</c:param>
</c:import>