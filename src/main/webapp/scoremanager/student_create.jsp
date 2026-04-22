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
	
		<c:forEach var="error" items="${errors}">
		    <p style="color:red;">${error.value}</p>
		</c:forEach>
		
		<form action="StudentUpdateExecute.action" method="post">
		    <div style="margin-bottom: 15px;">
		        <label for="ent_year">入学年度</label>
		        <select name="ent_year" id="ent_year">
		            <option value="">---------</option>
		            <c:forEach var="year" items="${entYearList}">
		                <option value="${year}">${year}</option>
		            </c:forEach>
		        </select>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="no">学生番号</label>
		        <input type="text" name="no" id="no" value="${no}" maxlength="10" placeholder="学生番号を入力してください" required>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label>氏名</label>
		        <input type="text" name="name" id="name" value="${name}" maxlength="30" placeholder="氏名を入力してください" required>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="class_num">クラス</label>
		        <select name="class_num" id="class_num">
		            <c:forEach var="classNum" items="${classNumList}">
		                <option value="${classNum}">${classNum}</option>
		            </c:forEach>
		        </select>
		    </div>

		    <div style="margin-top: 20px;">
		        <button type="submit" name="end">登録して終了</button>
		    </div>
		
		    <div style="margin-top: 10px;">
		        <a href="StudentList.action">戻る</a>
		    </div>
		</form>
	</c:param>
</c:import>