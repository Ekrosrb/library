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
                            <form method="post" action="${pageContext.request.contextPath}/library/logout">
                                <button class="nav-link active" role="button" aria-current="page" type="submit" style="background-color: transparent; border: none; cursor:pointer;"><fmt:message key="nav.logout"/></button>
                            </form>
                        </li>
                    </c:if>
            </ul>
        </div>
        <div class="nav-item">
            <%@ include file="WEB-INF/jspf/content/navRoleButtons.jspf"%>
        </div>
        <%@ include file="WEB-INF/jspf/content/localization.jspf"%>
    </div>

</nav>
<div style="margin-left: 20%; margin-right: 20%; padding: 5%">
    <div class="mb-3 ">
        <label for="firstName" class="form-label text-dark"><fmt:message key="form.firstName"/></label>
        <input class="form-control border border-dark rounded" id="firstName" name = "firstName" aria-describedby="first name" value="${sessionScope.firstName}" disabled>
    </div>
    <div class="mb-3">
        <label for="lastName" class="form-label text-dark"><fmt:message key="form.lastName"/></label>
        <input class="form-control border border-dark rounded" id="lastName" name = "lastName" aria-describedby="last name" value="${sessionScope.lastName}" disabled>
    </div>

    <div class="mb-3">
        <label for="email" class="form-label text-dark"><fmt:message key="form.email"/></label>
        <input type="email" class="form-control border border-dark rounded" id="email" name = "email" aria-describedby="emailHelp" value="${sessionScope.email}" disabled>
    </div>

    <div class="mb-3">
        <label for="birthday" class="form-label text-dark"><fmt:message key="form.birthday"/></label>
        <input class="form-control border border-dark rounded" type="date" id="birthday" name="birthday" aria-describedby="birthday" value="${sessionScope.birthday}" disabled>
    </div>
    <div class="mb-3">
        <label for="phone" class="form-label text-dark"><fmt:message key="form.phone"/></label>
        <input class="form-control border border-dark rounded" id="phone" name = "phone" aria-describedby="phone number" value="${sessionScope.phone}" disabled>
    </div>
    <div>
        <h3><fmt:message key="library.orders.list.title"/></h3>
        <ul class="list-group border border-dark rounded">
            <c:choose>
                <c:when test="${requestScope.subList.size() > 0}">
                    <c:forEach items="${requestScope.subList}" var="sub">
                        <li class="list-group-item">
                            <p><fmt:message key="profile.order.info.id"/> : ${sub.id}</p>
                            <c:choose>
                                <c:when test="${sub.status == 'ACCEPTED'}">
                                    <p class="alert alert-success" role="alert">
                                        <fmt:message key="profile.order.info.status.accept"/>
                                    </p>
                                </c:when>
                                <c:when test="${sub.status == 'PENDING'}">
                                    <p class="alert alert-info" role="alert">
                                        <fmt:message key="profile.order.info.status.wait"/>
                                    </p>
                                </c:when>
                                <c:when test="${sub.status == 'REJECTED'}">
                                    <p class="alert alert-dark" role="alert">
                                        <fmt:message key="profile.order.info.status.reject"/>
                                    </p>
                                </c:when>
                                <c:when test="${sub.status == 'ON_USE'}">
                                    <p class="alert alert-success" role="alert">
                                        <fmt:message key="profile.order.info.status.on.use"/>
                                    </p>
                                </c:when>
                                <c:when test="${sub.status == 'EXPIRED'}">
                                    <p class="alert alert-danger" role="alert">
                                        <fmt:message key="profile.order.info.status.expired"/>
                                    </p>
                                </c:when>
                                <c:when test="${sub.status == 'CLOSED'}">
                                    <p class="alert alert-light" role="alert">
                                        <fmt:message key="profile.order.info.status.expired"/>
                                    </p>
                                </c:when>
                            </c:choose>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p><fmt:message key="library.orders.list.message"/></p>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
