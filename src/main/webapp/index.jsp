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
                <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="bookName" value="${requestScope.bookName}"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
            </form>
            <div class="nav-item">
                <%@ include file="WEB-INF/jspf/content/navRoleButtons.jspf"%>
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
            <nav>
                <c:if test="${not empty sessionScope.auth}">
                    <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#${book.id}orderModal"><fmt:message key="book.card.button"/></button>
                    <div class="modal fade" id="${book.id}orderModal" tabindex="-1" aria-labelledby="${book.id}orderModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="${book.id}orderModalLabel"><fmt:message key="form.order.title"/></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form method="post" action="${pageContext.request.contextPath}/library/orderBook" style="margin: 3px">
                                    <div class="modal-body">
                                        <input type="hidden" name="userId" value="${sessionScope.auth.userId}">
                                        <input type="hidden" name="bookId" value="${book.id}">
                                        <div class="mb-3">
                                            <label for="term" class="form-label text-light"><fmt:message key="form.order.term"/></label>
                                            <input class="form-control" type="date" value="2000-01-01" id="term" name="term" aria-describedby="term">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="modal.add.user.discard"/></button>
                                        <button type="submit" class="btn btn-primary"><fmt:message key="modal.add.user.confirm"/></button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.auth.role == 'ADMIN'}">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#${book.id}editBookModal" style="margin: 3px">
                        <fmt:message key="book.card.admin.edit"/>
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="${book.id}editBookModal" tabindex="-1" aria-labelledby="${book.id}editBookModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="${book.id}editBookModalLabel"><fmt:message key="modal.book.title"/></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form method="post" action="${pageContext.request.contextPath}/library/adminUpdateBook">
                                    <div class="modal-body">
                                        <input type="hidden" name="id" value="${book.id}">
                                        <input type="hidden" name="bookName" value="${requestScope.bookName}"/>
                                        <div class="mb-3">
                                            <label for="name" class="form-label text-dark"><fmt:message key="modal.book.name"/></label>
                                            <input class="form-control" id="name" name="name" aria-describedby="name" value="${book.name}">
                                        </div>
                                        <div class="mb-3">
                                            <label for="author" class="form-label text-dark"><fmt:message key="modal.book.author"/></label>
                                            <input class="form-control" id="author" name="author" aria-describedby="author" value="${book.author}">
                                        </div>
                                        <div class="mb-3">
                                            <label for="edition" class="form-label text-dark"><fmt:message key="modal.book.edition"/></label>
                                            <input class="form-control" id="edition" name="edition" aria-describedby="edition" value="${book.edition}">
                                        </div>
                                        <div class="mb-3">
                                            <label for="count" class="form-label text-dark"><fmt:message key="modal.book.count"/></label>
                                            <input class="form-control" type="number" id="count" name="count" aria-describedby="edition" value="${book.count}">
                                        </div>
                                        <div class="mb-3">
                                            <label for="description"><fmt:message key="modal.book.description.en"/></label>
                                            <textarea class="form-control" id="description" name="description" rows="5" >${book.description}</textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="descriptionRu"><fmt:message key="modal.book.description.ru"/></label>
                                            <textarea class="form-control" id="descriptionRu" name="descriptionRu" rows="5" >${book.descriptionRu}</textarea>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="modal.edit.user.discard"/></button>
                                        <button type="submit" class="btn btn-primary"><fmt:message key="modal.add.user.confirm"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" style="margin: 3px"><fmt:message key="admin.pane.delete"/></button>
                    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">
                                        <fmt:message key="message.alert.delete.title"/> ${book.name}
                                    </h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <fmt:message key="message.alert.delete"/>
                                </div>
                                <form method="post" action="${pageContext.request.contextPath}/library/deleteBook">
                                    <input type="hidden" name="id" value="${book.id}"/>
                                    <input type="hidden" name="href" value="/">
                                    <input type="hidden" name="bookName" value="${requestScope.bookName}"/>
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
                </c:if>
            </nav>
        </div>
    </div>
    </c:forEach>
</div>

<%@ include file="/WEB-INF/jspf/content/booksPagination.jspf" %>

<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>