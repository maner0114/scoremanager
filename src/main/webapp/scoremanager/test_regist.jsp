<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">    
    <c:param name="title">
        得点管理システム 成績管理
    </c:param>
    
    <c:param name="subtitle">
        成績管理
    </c:param>
    
    <c:param name="content">
        <section class="me-4">

            <!-- 検索 -->
            <form method="get" action="TestRegist.action">
                <div class="row border mx-3 mb-3 py-3 align-items-center rounded shadow-sm bg-light">
                
                    <div class="col-md-2">
                        <label class="form-label fw-bold">入学年度</label>
                        <select class="form-select" name="f1">
                            <option value="0">---------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-md-2">
                        <label class="form-label fw-bold">クラス</label>
                        <select class="form-select" name="f2">
                            <option value="0">---------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-md-4">                    
                        <label class="form-label fw-bold">科目</label>
                        <select class="form-select" name="f3">
                            <option value="0">---------</option>
                            <c:forEach var="s" items="${subject_num_set}">
                                <option value="${s.cd}">${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-md-2">
                        <label class="form-label fw-bold">回数</label>
                        <select class="form-select" name="f4">
                            <option value="0">---------</option>
                            <c:forEach var="n" items="${count_num_set}">
                                <option value="${n}">${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="col-md-2 text-center pt-4">
                        <button class="btn btn-secondary w-100">検索</button>
                    </div>
                </div>

                <input type="hidden" name="f" value="sj">
            </form>

         
            <c:if test="${param.f == 'sj' and not empty result_list}">

                <div class="mx-3 mb-2">
                    科目：${result_list[0].subject.name}（${param.f4}回）
                </div>

                <form method="post" action="TestRegistExecute.action">
                    <div class="mx-3">
                        <table class="table table-bordered text-center">
                            <thead>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>点数</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="t" items="${result_list}">
                                    <tr>
                                        <td>${t.student.entYear}</td>
                                        <td>${t.classNum}</td>
                                        <td>${t.student.no}</td>
                                        <td>${t.student.name}</td>
                                        <td>
                                            <input type="number"
                                                   name="point_${t.student.no}"
                                                   value="${t.point}"
                                                   class="form-control">
                                        </td>
                                    </tr>

                                    <input type="hidden" name="regist" value="${t.student.no}">
                                    <input type="hidden" name="subject" value="${param.f3}">
                                    <input type="hidden" name="count" value="${param.f4}">
                                    <input type="hidden" name="classNum" value="${t.classNum}">
                                </c:forEach>
                            </tbody>
                        </table>

                        <div class="text-start">
                            <button class="btn btn-secondary">登録して終了</button>
                        </div>
                    </div>
                </form>

            </c:if>

        </section>
    </c:param>
</c:import>
