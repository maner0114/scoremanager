<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>科目情報削除</h2>

<c:choose>

    <c:when test="${!done}">
        <p>
            「${subject.name}(${subject.cd})」を削除してもよろしいですか？
        </p>

        <form action="SubjectDeleteDone.action" method="post">
            <input type="hidden" name="cd" value="${subject.cd}">
            <input type="submit" value="削除">
        </form>

        <a href="SubjectList.action">戻る</a>
    </c:when>

    <c:otherwise>
        <p style="color:green;">削除が完了しました</p>
        <a href="SubjectList.action">科目一覧</a>
    </c:otherwise>

</c:choose>