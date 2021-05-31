<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/jspf/taglib.jspf" %>
<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<%@ include file="../WEB-INF/jspf/head.jspf"%>
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
            </ul>
        </div>
        <div class="nav-item">
            <%@ include file="/WEB-INF/jspf/content/navRoleButtons.jspf"%>
        </div>
        <%@ include file="../WEB-INF/jspf/content/localization.jspf"%>
    </div>
</nav>

<div style="margin-left: 20%; margin-right: 20%; padding: 5%">
    <form action="${pageContext.request.contextPath}/library/librarian">
        <input type="hidden" name="type" value="pending">
        <button class="btn btn-primary" type="submit" style="margin: 2px"><fmt:message key="library.orders.list.title"/></button>
    </form>
    <form action="${pageContext.request.contextPath}/library/librarian">
        <input type="hidden" name="type" value="info">
        <button class="btn btn-primary" type="submit" style="margin: 2px"><fmt:message key="library.orders.info.title"/></button>
    </form>
    <c:choose>
        <c:when test="${requestScope.type eq 'pending'}">
            <ul class="list-group">
                <h3><fmt:message key="library.orders.list.title"/></h3>
                <c:choose>
                    <c:when test="${requestScope.subList.size() > 0}">
                        <c:forEach items="${requestScope.subList}" var="sub">
                            <li class="list-group-item">
                                <span>${sub.id} | ${sub.term}</span>
                                <div class="d-flex justify-content-end">
                                    <form method="post" action="${pageContext.request.contextPath}/library/acceptSub">
                                        <input type="hidden" name="id" value="${sub.id}"/>
                                        <button type="submit" class="btn btn-outline-success" style="margin: 1px"><fmt:message key="lib.accept"/></button>
                                    </form>
                                    <form method="post" action="${pageContext.request.contextPath}/library/rejectSub">
                                        <input type="hidden" name="id" value="${sub.id}"/>
                                        <button type="Submit" class="btn btn-outline-danger" style="margin: 1px"><fmt:message key="lib.discard"/></button>
                                    </form>
                                </div>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p><fmt:message key="library.orders.list.message"/></p>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:when>
        <c:when test="${requestScope.type eq 'info'}">
            <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/librarian">
                <input type="hidden" name="type" value="info">
                <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="id"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
            </form>
            <c:if test="${not empty requestScope.orderInfo}">
                <div class="mb-3 ">
                    <label for="userName" class="form-label text-dark"><fmt:message key="library.orders.info.user.name"/></label>
                    <input class="form-control border border-dark rounded" id="userName" name = "userName" value="${requestScope.orderInfo.userName}" disabled>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label text-dark"><fmt:message key="library.orders.info.user.email"/></label>
                    <input class="form-control border border-dark rounded" type="email" id="email" name = "email" value="${requestScope.orderInfo.email}" disabled>
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label text-dark"><fmt:message key="form.phone"/></label>
                    <input type="number" class="form-control border border-dark rounded" id="phone" name = "phone" value="${requestScope.orderInfo.phone}" disabled>
                </div>

                <div class="mb-3">
                    <label for="bookName" class="form-label text-dark"><fmt:message key="library.orders.info.book.name"/></label>
                    <input class="form-control border border-dark rounded" id="bookName" name="bookName" value="${requestScope.orderInfo.bookName}" disabled>
                </div>
                <div class="mb-3">
                    <label for="term" class="form-label text-dark"><fmt:message key="form.order.term"/></label>
                    <input class="form-control border border-dark rounded" type="date" id="term" name = "term" value="${requestScope.orderInfo.term}" disabled>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label text-dark"><fmt:message key="profile.order.info.status"/></label>
                    <input class="form-control border border-dark rounded" id="status" name = "status" value="${requestScope.orderInfo.status}" disabled>
                </div>
                <div class="mb-3">
                    <label for="fine" class="form-label text-dark"><fmt:message key="library.orders.info.book.fine"/></label>
                    <input class="form-control border border-dark rounded" id="fine" name = "fine" value="${requestScope.orderInfo.fine}" disabled>
                </div>
            </c:if>
        </c:when>
    </c:choose>
</div>
</body>
</html>
