<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<jsp:include page="../common/base.jsp">
    <jsp:param name="title" value="得点管理システム 科目管理"/>
    <jsp:param name="subtitle" value="科目一覧"/>
</jsp:include>

<section class="me-4">

    <div class="my-2 text-end px-4">
        <a href="SubjectCreate.action" class="btn btn-outline-primary btn-sm">新規登録</a>
    </div>

    <div class="mx-3">

        <c:choose>

            <c:when test="${sList != null && sList.size() > 0}">
                <div>検索結果：${sList.size()}件</div>

                <table class="table">
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                    </tr>

                    <c:forEach var="subject" items="${sList}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:otherwise>
                <div>科目情報がありません</div>
            </c:otherwise>

        </c:choose>

    </div>
</section>