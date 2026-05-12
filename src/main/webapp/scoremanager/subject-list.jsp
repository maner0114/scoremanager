<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">	

    <c:param name="title">
        得点管理システム 科目管理
    </c:param>
    
    <c:param name="subtitle">
        科目一覧
    </c:param>
    
    <c:param name="content">
        <section class="me-4">

            <div class="my-2 text-end px-4">
                <a href="SubjectCreate.action" class="btn btn-outline-primary btn-sm">新規登録</a>
            </div>

            <div class="mx-3">

                <c:choose>

                    <c:when test="${not empty sList}">
                        <div>検索結果：${sList.size()}件</div>

                        <table class="table table-hover table-striped border shadow-sm">
                            <thead class="table-light">
                                <tr>
                                    <th>科目コード</th>
                                    <th>科目名</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="subject" items="${sList}">
                                    <tr>
                                        <td>${subject.cd}</td>
                                        <td>${subject.name}</td>
                                        <td><a href="SubjectUpdate.action?cd=${subject.cd}" class="btn btn-sm btn-link">変更</a></td>
                                        <td><a href="SubjectDelete.action?cd=${subject.cd}" class="btn btn-sm btn-link text-danger">削除</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>

                        </table>
                    </c:when>

                    <c:otherwise>
                        <div class="alert alert-info shadow-sm">科目情報が存在しませんでした。</div>
                    </c:otherwise>

                </c:choose>

            </div>
        </section>
    </c:param>

</c:import>