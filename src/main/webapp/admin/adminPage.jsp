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
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/library/adminUsers"><fmt:message key="nav.admin.books"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/library/admin"><fmt:message key="nav.admin.users"/></a>
                </li>
            </ul>
        </div>

        <%@ include file="../WEB-INF/jspf/content/localization.jspf"%>

    </div>
</nav>
<div style="margin-left: 20%; margin-right: 20%; padding: 5%">
    <button class="btn btn-primary" type="button"><fmt:message key="admin.pane.add"/></button>
    <ul class="list-group">
        <c:forEach items="${requestScope.userList}" var="users">
            <li class="list-group-item">${users.firstName} ${users.lastName} | ${users.role}
                <div class="d-flex justify-content-end">
                <button type="button" class="btn btn-primary" style="margin: 3px"><fmt:message key="admin.pane.edit"/></button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" style="margin: 3px"><fmt:message key="admin.pane.delete"/></button>
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

            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteModalLabel">
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
        </c:forEach>
    </ul>
</div>
<%@ include file="../WEB-INF/jspf/footer.jspf"%>
</body>
</html>
