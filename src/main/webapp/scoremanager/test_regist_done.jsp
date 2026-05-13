<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="../common/base.jsp">
    
    <c:param name="title">
        得点管理システム 成績登録完了
    </c:param>

    <c:param name="subtitle">
        成績管理
    </c:param>

    <c:param name="content">
        <section class="me-4">

            <div class="alert alert-success mt-4 mx-3" role="alert">
                登録が完了しました。
            </div>

            <div class="mx-3 mt-3 d-flex gap-3">
            
				 <!--メニューへ戻る-->
                <a href="../scoremanager/menu.jsp" class="btn btn-secondary">
                    戻る
                </a>
                
                <!--成績参照へ遷移-->
                <a href="TestList.action" class="btn btn-secondary">
                    成績参照
                </a>

            </div>

        </section>
    </c:param>

</c:import>
