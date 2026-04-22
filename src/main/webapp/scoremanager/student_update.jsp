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
	
		<c:forEach var="error" items="${errors}">
		    <p style="color:red;">${error.value}</p>
		</c:forEach>
		
		<form action="StudentUpdateExecute.action" method="post">
		    <div style="margin-bottom: 15px;">
		        <label for="ent_year">入学年度</label>
		        <input type="text" name="ent_year" id="ent_year" value="${ent_year}" readonly>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="no">学生番号</label>
		        <input type="text" name="no" id="no" value="${no}" readonly>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="name">氏名</label>
		        <input type="text" name="name" id="name" value="${name}" maxlength="30" placeholder="氏名を入力してください" required>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="class_num">クラス</label>
		        <select name="class_num" id="class_num">
		            <c:forEach var="classNum" items="${classNumList}">
		                <option value="${classNum}" <c:if test="${classNum == selected_class_num}">selected</c:if>>${classNum}</option>
		            </c:forEach>
		        </select>
		    </div>

		    <div style="margin-bottom: 15px;">
		        <label for="is_attend">在学中</label>
		        <input type="checkbox" name="is_attend" id="is_attend" <c:if test="${is_attend}">checked</c:if>>
		    </div>

		    <div style="margin-top: 20px;">
		        <button type="submit" name="login">変更</button>
		    </div>
		
		    <div style="margin-top: 10px;">
		        <a href="StudentList.action">戻る</a>
		    </div>
		</form>
	</c:param>
</c:import>