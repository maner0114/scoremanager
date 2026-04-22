<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	
	<c:param name="title">
		得点管理システム ログイン
	</c:param>
	
	<c:param name="subtitle">
		ログイン
	</c:param>
		
	<c:param name="content">
		
		<c:if test="${not empty error}">
		    <p style="color: red;">${error}</p>
		</c:if>
	
		<form action="LoginExecute.action" method="post">
			<input type="text" name="id" value="${id}" maxlength="10"
			placeholder="半角でご入力ください" required>
			
			<input type="password" id="password" name="password" maxlength="30"
			       placeholder="30文字以内の半角英数字でご入力ください" required>
			
			<label>
			    <input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()">
			    パスワードを表示
			</label>
		
			<input type="submit" name="login" value="ログイン"> 
			
		<script><%--参考 https://saycon.co.jp/portal-for-newcomer/javaweb/webtips/show-pass--%>
		function togglePasswordVisibility() {
			let passwordInput = document.getElementById("password");
			let showPasswordCheckbox = document.getElementById("showPassword");

			if (showPasswordCheckbox.checked) {
				passwordInput.type = "text";
			} else {
				passwordInput.type = "password";
			}
		}
	</script>
		</form>
	</c:param>
</c:import>