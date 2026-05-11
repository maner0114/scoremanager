<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="../common/base.jsp">
	 <c:param name="title">
		学生管理システム　科目登録
	 </c:param>

	 <c:param name="subtitle">
		科目情報登録
	 </c:param>
	
	 <c:param name="content">

	 <!-- テキストボックスの指定 CSS-->
	 <style>
	 /*テキストの縦横の指定*/
	 	.textbox-1{
		 width:  600px;
		 height: 30px
		}
		
		/*ラベルの改行*/
		.form-gruop{
			display: flex;
			flex-direction: column; /*縦並び*/
			margin-bottom: 12px;
		}
	 </style>
	 	
	 <!-- 一つでもテキストがnullの場合 -->
	 	<c:forEach var="error" items="${errors }">
	 		<p style="color:red;">${error.value}</p>
	 	</c:forEach>
	
	 <form action="SubjectCreateExecute.action" method="post">
	 	<div class="form-gruop">
			<label for="textbox-1-id" >科目コード</label>
				<input type="text" id="textbox-1-id" name="cd" class="textbox-1" placeholder="科目コードを入力してください" required>
		</div>
		
		<div class="form-gruop">
			<label for="textbox-1-id">科目名　</label>
				<input type="text" id="textbox-1-id" name="name" class="textbox-1" placeholder="科目名を入力してください" required>
		</div>
		
		<div>
			<button type="submit" name="end">登録</button>
		</div>
		
		<div>
			<a href="subject-list.jsp">戻る</a> <!-- 科目一覧に戻る　後で追加 -->
		</div>
	 </form>
  </c:param>	
</c:import>