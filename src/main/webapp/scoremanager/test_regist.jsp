<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="../common/base.jsp">    
    <c:param name="title">
        得点管理システム 成績管理
    </c:param>
    
    <c:param name="subtitle">
        成績登録
    </c:param>
    
    <c:param name="content">
        <section class="me-4">

            <!-- 検索フォーム -->
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

            <!-- 成績入力 -->
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
                                                   class="form-control point-input"
                                                   data-student="${t.student.no}">

                                            <!-- JS がここにエラーを表示 -->
                                            <small id="err_${t.student.no}" class="text-danger"></small>

                                            <!-- サーバー側エラー（残す） -->
                                            <c:if test="${not empty errorMap[t.student.no]}">
                                                <small class="text-danger">${errorMap[t.student.no]}</small>
                                            </c:if>
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
                            <button id="submitBtn" class="btn btn-secondary">登録して終了</button>
                        </div>
                    </div>
                </form>
            </c:if>

        </section>

        <!-- リアルタイム点数チェック（未入力 & 0〜100 & ボタン制御） -->
        <script>
        document.addEventListener("DOMContentLoaded", () => {

            const inputs = document.querySelectorAll(".point-input");
            const submitBtn = document.getElementById("submitBtn");

            function validateAll() {
                let hasError = false;

                inputs.forEach(input => {
                    const value = input.value;
                    const studentNo = input.dataset.student;
                    const err = document.getElementById("err_" + studentNo);

                    if (value === "") {
                        err.textContent = "入力してください";
                        hasError = true;
                    } else if (value < 0 || value > 100) {
                        err.textContent = "0～100の範囲で入力してください";
                        hasError = true;
                    } else {
                        err.textContent = "";
                    }
                });

                submitBtn.disabled = hasError;
            }

            inputs.forEach(input => {
                input.addEventListener("input", validateAll);
            });

            validateAll(); // 初期チェック
        });
        </script>

    </c:param>
</c:import>
