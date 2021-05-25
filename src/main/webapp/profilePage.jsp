<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<%@ include file="WEB-INF/jspf/head.jspf"%>
<body style="background-color: rgb(225,225,225)">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/"><fmt:message key="nav.home"/></a>
                </li>
                    <c:if test="${not empty sessionScope.auth}">
                        <li class="nav-item">
                            <form method="post" action="${pageContext.request.contextPath}/auth/logout">
                                <button class="nav-link active" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.logout"/></button>
                            </form>
                        </li>
                    </c:if>
            </ul>
        </div>
        <%@ include file="WEB-INF/jspf/content/localization.jspf"%>
    </div>
</nav>
<div style="margin-left: 20%; margin-right: 20%; padding: 5%">
    <div class="mb-3">
        <label for="firstName" class="form-label text-dark"><fmt:message key="form.firstName"/></label>
        <input class="form-control" id="firstName" name = "firstName" aria-describedby="first name" value="${sessionScope.firstName}" disabled>
    </div>
    <div class="mb-3">
        <label for="lastName" class="form-label text-dark"><fmt:message key="form.lastName"/></label>
        <input class="form-control" id="lastName" name = "lastName" aria-describedby="last name" value="${sessionScope.lastName}" disabled>
    </div>

    <div class="mb-3">
        <label for="email" class="form-label text-dark"><fmt:message key="form.email"/></label>
        <input type="email" class="form-control" id="email" name = "email" aria-describedby="emailHelp" value="${sessionScope.email}" disabled>
    </div>

    <div class="mb-3">
        <label for="birthday" class="form-label text-dark"><fmt:message key="form.birthday"/></label>
        <input class="form-control" type="date" id="birthday" name="birthday" aria-describedby="birthday" value="${sessionScope.birthday}" disabled>
    </div>
    <div class="mb-3">
        <label for="phone" class="form-label text-dark"><fmt:message key="form.phone"/></label>
        <input class="form-control" id="phone" name = "phone" aria-describedby="phone number" value="${sessionScope.phone}" disabled>
    </div>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
