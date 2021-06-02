<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<%@ include file="WEB-INF/jspf/head.jspf"%>
<body style="background-color: rgb(225,225,225)">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
    <div class="d-flex justify-content-center">
        <ul class="pagination">
            <c:forEach begin="1" end="${requestScope.pages}" varStatus="loop">
                <li class="page-item">
                    <form method="post" action="${pageContext.request.contextPath}/library/profile">
                        <input type="hidden" name="from" value="${(loop.index-1)*20}"/>
                        <button class="page-link text-light bg-dark" type="submit">${loop.index}</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div>
        <h3><fmt:message key="library.orders.list.title"/></h3>
        <ul class="list-group border rounded">
            <c:choose>
                <c:when test="${requestScope.subList.size() > 0}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Term</th>
                        <th scope="col">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.subList}" var="order">
                    <tr>
                        <c:choose>
                            <c:when test="${order.status == 'ACCEPTED'}">
                                <tr class="table-success">
                                    <th scope="row">${order.id}</th>
                                    <td>${order.term}</td>
                                    <td>
                                        <fmt:message key="profile.order.info.status.accept"/>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${order.status == 'PENDING'}">
                                <tr class="table-info">
                                    <th scope="row">${order.id}</th>
                                    <td>${order.term}</td>
                                    <td>
                                        <fmt:message key="profile.order.info.status.wait"/>
                                    </td>
                                </tr>
                            </c:when>
                            <c:when test="${order.status == 'REJECTED'}">
                                <tr class="table-secondary">
                                <th scope="row">${order.id}</th>
                                <td>${order.term}</td>
                                <td>
                                    <fmt:message key="profile.order.info.status.reject"/>
                                </td>
                                </tr>
                            </c:when>
                            <c:when test="${order.status == 'ON_USE'}">
                                <tr class="table-primary">
                                <th scope="row">${order.id}</th>
                                <td>${order.term}</td>
                                <td>
                                    <fmt:message key="profile.order.info.status.on.use"/>
                                </td>
                                </tr>
                            </c:when>
                            <c:when test="${order.status == 'EXPIRED'}">
                                <tr class="table-danger">
                                <th scope="row">${order.id}</th>
                                <td>${order.term}</td>
                                <td>
                                    <fmt:message key="profile.order.info.status.expired"/>
                                </td>
                                </tr>
                            </c:when>
                            <c:when test="${order.status == 'CLOSED'}">
                                <tr class="table-active">
                                <th scope="row">${order.id}</th>
                                <td>${order.term}</td>
                                <td>
                                    <fmt:message key="profile.order.info.status.closed"/>
                                </td>
                                </tr>

                            </c:when>
                        </c:choose>

                    </c:forEach>
                    </tbody>
                </table>
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
