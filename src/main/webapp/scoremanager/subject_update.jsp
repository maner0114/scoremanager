<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム　科目情報変更
	</c:param>
	
	<c:param name="subtitle">
		科目情報変更
	</c:param>
	
	<c:param name="content">
	

		
		<form action="StudentUpdateExecute.action" method="post">
		    <div style="margin-bottom: 15px;">
		        <label for="ent_year">科目コード</label>
		        <input type="text" name="ent_year" id="ent_year" value="${cd}" readonly>
		    </div>

			    <p style="color:red;">${error}</p>


		    <div style="margin-bottom: 15px;">
		        <label for="name">科目名</label>
		        <input type="text" name="name" id="name" value="${name}"
		         maxlength="30" placeholder="科目名を入力してください" required>
		    </div>


		    <div style="margin-top: 20px;">
		        <button type="submit" name="">変更</button>
		    </div>
		
		    <div style="margin-top: 10px;">
		        <a href="StudentList.action">戻る</a>
		    </div>
		</form>
	</c:param>
</c:import>