<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="../common/base.jsp">

    <c:param name="title">
        成績登録
    </c:param>

    <c:param name="subtitle">
        学生情報登録
    </c:param>

    <c:param name="content">
        <section class="mt-4">
            <!-- 完了メッセージ -->
            <div class="alert alert-success text-center py-3 mb-4" style="background-color:#d1e7dd;">
                登録が完了しました
            </div>

            <!-- 戻る・一覧リンク -->
            <div class="text-start ms-3">
                <a href="TestRegist.action" class="me-3 text-decoration-none">戻る</a>
                <a href="StudentList.action" class="text-decoration-none">学生一覧</a>
            </div>
        </section>
    </c:param>

</c:import>
