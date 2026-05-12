<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">成績削除完了</c:param>
    <c:param name="subtitle">成績削除完了</c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="alert alert-success mt-4 mx-3">
                成績を削除しました。
            </div>

            <div class="mx-3 mt-3 d-flex gap-3">
                <a href="TestDelete.action" class="btn btn-secondary">成績削除へ戻る</a>
                <a href="../scoremanager/menu.jsp" class="btn btn-outline-secondary">メニューへ戻る</a>
            </div>

        </section>
    </c:param>

</c:import>
