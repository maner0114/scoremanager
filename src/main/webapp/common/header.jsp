<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<header class="navbar navbar-expand-lg bg-info bg-opacity-10 border-bottom py-3">
    <div class="container-fluid mx-4">
        <h1 class="h3 mb-0 fw-bold">得点管理システム</h1>
		
		<c:if test="${not empty sessionScope.teacher}">
	        <div class="d-flex align-items-center">
	            <span class="me-3">${teacher.id }様</span>
	            <a href="Logout.action" class="btn btn-outline-primary btn-sm">ログアウト</a>
	        </div>
	    </c:if>
    </div>
</header>
