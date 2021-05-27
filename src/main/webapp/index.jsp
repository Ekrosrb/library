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
                <c:choose>
                    <c:when test="${not empty sessionScope.auth}">
                        <li class="nav-item">
                            <form method="post" action="${pageContext.request.contextPath}/library/logout">
                                <button class="nav-link active" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.logout"/></button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                <button class="nav-link active" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.profile"/></button>
                            </form>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <%@include file="WEB-INF/jspf/content/loginForm.jspf"%>
                        </li>
                        <li class="nav-item">
                           <%@include file="WEB-INF/jspf/content/siginForm.jspf"%>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
            <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/books">
                <input name="from" type="hidden" value="0">
                <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="bookName">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
            </form>
            <div class="nav-item">
                <c:if test="${sessionScope.auth.role == 'ADMIN'}">
                    <form method="post" action="${pageContext.request.contextPath}/library/admin">
                        <button class="nav-link active text-light" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.admin"/></button>
                    </form></c:if>
                <c:if test="${sessionScope.auth.role == 'LIBRARIAN'}">
                    <form method="post" action="${pageContext.request.contextPath}/library/librarian">
                        <button class="nav-link active text-light" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.lib"/></button>
                    </form>
                </c:if>
            </div>
        </div>
        <%@ include file="WEB-INF/jspf/content/localization.jspf"%>
    </div>
</nav>
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-danger" role="alert">
        ${requestScope.message}
    </div>
</c:if>
<div>
    <p>${requestScope.count}</p>
</div>

<%@ include file="/WEB-INF/jspf/content/booksPagination.jspf" %>

<div class="card-columns" style="margin-left: 10%; margin-right: 10%">
    <c:forEach items="${requestScope.books}" var="book">
    <div class="card border-dark mb-3">
        <img src="${book.img}" class="card-img-top" alt="...">
        <div class="card-body">
            <h5 class="card-title">${book.name}</h5>
            <p class="card-text">
                <c:if test="${empty sessionScope.locale || sessionScope.locale eq 'en'}">
                    ${book.description}
                </c:if>
                <c:if test="${sessionScope.locale eq 'ru'}">
                    ${book.descriptionRu}
                </c:if>
            </p>
            <div class="card-footer text-muted">
                ${book.edition}
            </div>

            <button></button>
        </div>
    </div>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/jspf/content/booksPagination.jspf" %>

<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>