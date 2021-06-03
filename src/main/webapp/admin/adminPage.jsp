<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/jspf/taglib.jspf" %>

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

    <div class="d-flex justify-content-center" style="margin-top: 15px">
        <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/admin">
            <input type="hidden" name="type" value="info">
            <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="id"/>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
        </form>
    </div>


    <%@ include file="../WEB-INF/jspf/content/addUserModal.jspf"%>
    <%@ include file="../WEB-INF/jspf/content/adminPagination.jspf"%>
    <ul class="list-group">
        <c:forEach items="${requestScope.userList}" var="users">

            <li class="list-group-item">
                <span>${users.firstName} ${users.lastName} | ${users.role}</span>
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${users.id}" style="margin: 3px">
                        <fmt:message key="admin.pane.edit"/>
                    </button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#${users.id}deleteModal" style="margin: 3px"><fmt:message key="admin.pane.delete"/></button>
                    <form method="post" action="${pageContext.request.contextPath}/library/updateUser">
                        <input name="id" type="hidden" value="${users.id}">
                        <input name="block" type="hidden" value="${not users.block}">
                        <input name="href" type="hidden" value="/library/admin">
                        <button type="submit" class="btn btn-warning" style="margin: 3px">
                            <c:choose>
                                <c:when test="${users.block}">
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

            <div class="modal fade" id="${users.id}deleteModal" tabindex="-1" aria-labelledby="${users.id}deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="${users.id}deleteModalLabel">
                                <fmt:message key="message.alert.delete.title"/> ${users.email}
                            </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <fmt:message key="message.alert.delete"/>
                        </div>
                        <form method="post" action="${pageContext.request.contextPath}/library/deleteUser">
                            <input type="hidden" name="id" value="${users.id}"/>
                            <input type="hidden" name="href" value="/library/admin">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="message.alert.delete.discard"/>
                            </button>
                            <button type="submit" class="btn btn-danger">
                                <fmt:message key="message.alert.delete.confirm"/>
                            </button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <%@ include file="../WEB-INF/jspf/content/editUserModal.jspf"%>

        </c:forEach>
    </ul>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
