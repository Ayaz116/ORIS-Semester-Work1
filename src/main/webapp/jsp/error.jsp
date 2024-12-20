<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Ошибка" scope="request"/>
<jsp:include page="header.jsp"/>

<div class="page-container">
    <div class="section" style="text-align: center;">
        <h1>Ooops, что-то пошло не так...</h1>
        <p style="font-size:1.2em; margin-top:20px;"><c:out value="${param.err}" /></p>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</main>
</div>
</body>
</html>
