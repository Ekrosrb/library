<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/jspf/taglib.jspf" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<%@ include file="../WEB-INF/jspf/head.jspf"%>
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
<c:if test="${not empty requestScope.message}">
    <div class="alert alert-danger" role="alert">
            ${requestScope.message}
    </div>
</c:if>
<div style="margin-left: 20%; margin-right: 20%; padding: 5%">

    <div class="d-flex justify-content-start" style="margin-top: 15px">
        <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/admin">
            <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search.admin.hide.text"/>" aria-label="Search" name="id"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
        </form>
    </div>

    <%@ include file="../WEB-INF/jspf/content/addUserModal.jspf"%>
    <%@ include file="../WEB-INF/jspf/content/adminPagination.jspf"%>

    <ul class="list-group">
        <c:forEach items="${requestScope.userList}" var="user">

            <li class="list-group-item">
                <span>${user.firstName} ${user.lastName} | ${user.role}</span>
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${user.id}" style="margin: 3px">
                        <fmt:message key="admin.pane.edit"/>
                    </button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${user.id}deleteModal" style="margin: 3px"><fmt:message key="admin.pane.delete"/></button>
                    <form method="post" action="${pageContext.request.contextPath}/library/updateUser">
                        <input name="id" type="hidden" value="${user.id}">
                        <input name="block" type="hidden" value="${not user.block}">
                        <input name="href" type="hidden" value="/library/admin">
                        <button type="submit" class="btn btn-warning" style="margin: 3px">
                            <c:choose>
                                <c:when test="${user.block}">
                                    <fmt:message key="admin.pane.unblock"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="admin.pane.block"/>
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </form>
                </div>
            </li>
            <%-- modal delete user --%>
            <m:modalDeleteUser user="${user}"/>
            <%-- modal edit user --%>
            <m:modalEditUser user="${user}"/>

        </c:forEach>
    </ul>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
