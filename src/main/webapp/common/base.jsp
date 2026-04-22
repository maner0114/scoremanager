<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<html>
  <head>
    <meta charset="UTF-8">
    <title>${param.title}</title>

    <jsp:include page="header.jsp" />

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
  </head>

  <body>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>



    <div class="row">
      <div class="col-2 ms-3">
      	<c:if test="${not empty sessionScope.teacher}">
	        <jsp:include page="sidebar.jsp" />
	    </c:if>
      </div>

      <div class="col-9">
	    <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
	      ${param.subtitle}
	    </h2>
        ${param.content}
      </div>
    </div>
  </body>

  <jsp:include page="footer.jsp" />
</html>

