<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">

    <c:param name="title">成績削除</c:param>
    <c:param name="subtitle">成績削除</c:param>

    <c:param name="content">
        <section class="me-4">

            <table class="table table-bordered text-center mx-3 mt-3">
                <thead>
                    <tr>
                        <th>学生番号</th>
                        <th>科目</th>
                        <th>回数</th>
                        <th>点数</th>
                        <th>クラス</th>
                        <th>削除</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="t" items="${test_list}">
                        <tr>
                            <td>${t.student.no}</td>
                            <td>${t.subject.cd}</td>
                            <td>${t.no}</td>
                            <td>${t.point}</td>
                            <td>${t.classNum}</td>
                            <td>
                                <form method="post" action="TestDeleteExecute.action">
                                    <input type="hidden" name="studentNo" value="${t.student.no}">
                                    <input type="hidden" name="subjectCd" value="${t.subject.cd}">
                                    <input type="hidden" name="count" value="${t.no}">
                                    <button class="btn btn-danger btn-sm">削除</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </section>
    </c:param>

</c:import>
