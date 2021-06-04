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

<div style="margin-left: 20%; margin-right: 20%; padding: 5%">
    <div class="d-flex justify-content-start dropdown">

            <button type="button" class="btn btn-dark dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${requestScope.type}
            </button>
            <form action="${pageContext.request.contextPath}/library/librarian" method="post">
                <div class="dropdown-menu">
                    <input class="dropdown-item" type="submit" name="type" value="PENDING"/>
                    <input class="dropdown-item" type="submit" name="type" value="ACCEPTED"/>
                    <input class="dropdown-item" type="submit" name="type" value="EXPIRED"/>
                    <input class="dropdown-item" type="submit" name="type" value="REJECTED"/>
                    <input class="dropdown-item" type="submit" name="type" value="ON_USE"/>
                    <input class="dropdown-item" type="submit" name="type" value="CLOSED"/>
                    <input class="dropdown-item" type="submit" name="type" value="PAID"/>
                    <div class="dropdown-divider"></div>
                    <input class="dropdown-item" type="submit" name="type" value="info"/>
                    <input class="dropdown-item" type="submit" name="type" value="user"/>
                </div>
            </form>
    </div>
    <c:choose>
        <c:when test="${requestScope.type eq 'info'}">
            <div class="d-flex justify-content-center" style="margin-top: 15px">
                <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/librarian">
                    <input type="hidden" name="type" value="${requestScope.type}">
                    <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="id"/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
                </form>
            </div>
            <c:choose>
            <c:when test="${not empty requestScope.order}">
                <div class="mb-3 ">
                    <label for="userName" class="form-label text-dark"><fmt:message key="library.orders.info.user.name"/></label>
                    <div class="d-flex justify-content-center">
                    <input class="form-control border border-dark rounded" id="userName" name = "userName" value="${requestScope.order.userName}" disabled>
                    <div class="d-flex justify-content-center">
                        <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/librarian">
                            <input type="hidden" name="type" value="user"/>
                            <input type="hidden" name="id" value="${requestScope.order.userId}"/>
                            <button class="btn btn-outline-primary" type="submit" id="userId" name = "id" style="margin-left: 20px"><fmt:message key="library.orders.list.view"/></button>
                        </form>
                    </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label text-dark"><fmt:message key="library.orders.info.user.email"/></label>
                    <input class="form-control border border-dark rounded" type="email" id="email" name = "email" value="${requestScope.order.email}" disabled>
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label text-dark"><fmt:message key="form.phone"/></label>
                    <input type="number" class="form-control border border-dark rounded" id="phone" name = "phone" value="${requestScope.order.phone}" disabled>
                </div>

                <div class="mb-3">
                    <label for="bookName" class="form-label text-dark"><fmt:message key="library.orders.info.book.name"/></label>
                    <input class="form-control border border-dark rounded" id="bookName" name="bookName" value="${requestScope.order.bookName}" disabled>
                </div>
                <div class="mb-3">
                    <label for="term" class="form-label text-dark"><fmt:message key="form.order.term"/></label>
                    <input class="form-control border border-dark rounded" type="date" id="term" name = "term" value="${requestScope.order.term}" disabled>
                </div>
                <div class="mb-3">
                    <label for="term" class="form-label text-dark"><fmt:message key="form.order.date"/></label>
                    <input class="form-control border border-dark rounded" type="date" id="date" name = "date" value="${requestScope.order.orderDate}" disabled>
                </div>
                <div class="mb-3">
                    <label for="status" class="form-label text-dark"><fmt:message key="profile.order.info.status"/></label>
                    <input class="form-control border border-dark rounded" id="status" name = "status" value="${requestScope.order.status}" disabled>
                </div>
                <div class="mb-3">
                    <label for="fine" class="form-label text-dark"><fmt:message key="library.orders.info.book.fine"/></label>
                    <input class="form-control border border-dark rounded" id="fine" name = "fine" value="${requestScope.order.fine}" disabled>
                </div>
                <div class="d-flex justify-content-start">
                    <c:if test="${requestScope.order.status == 'ACCEPTED'}">
                    <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                        <input type="hidden" name="id" value="${requestScope.order.id}"/>
                        <input type="hidden" name="status" value="ON_USE"/>
                        <input type="hidden" name="type" value="info"/>
                        <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="profile.order.info.status.on.use"/></button>
                    </form>
                    </c:if>
                    <c:if test="${requestScope.order.status == 'PENDING'}">
                        <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                            <input type="hidden" name="id" value="${requestScope.order.id}"/>
                            <input type="hidden" name="status" value="ACCEPTED"/>
                            <button type="submit" class="btn btn-outline-success" style="margin: 2px"><fmt:message key="lib.accept"/></button>
                        </form>
                        <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                            <input type="hidden" name="id" value="${requestScope.order.id}"/>
                            <input type="hidden" name="status" value="REJECTED"/>
                            <button type="submit" class="btn btn-outline-danger" style="margin: 2px"><fmt:message key="lib.discard"/></button>
                        </form>
                    </c:if>

                    <c:if test="${requestScope.order.status == 'ON_USE' || requestScope.order.status == 'PAID'}">
                        <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                            <input type="hidden" name="id" value="${requestScope.order.id}"/>
                            <input type="hidden" name="status" value="CLOSED"/>
                            <button type="submit" class="btn btn-outline-secondary" style="margin: 2px"><fmt:message key="profile.order.info.status.closed"/></button>
                        </form>
                        <div>
                            <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#addFineModal" style="margin: 2px"><fmt:message key="pay.add.fine"/></button>
                        </div>
                        <div class="modal fade" id="addFineModal" data-backdrop="show" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" >
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel"><fmt:message key="library.orders.list.view"/></h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form method="post" action="${pageContext.request.contextPath}/library/addFine">
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="fineModal" class="form-label text-dark"><fmt:message key="library.orders.info.book.fine"/></label>
                                            <input class="form-control" id="fineModal" name = "fine" aria-describedby="fine" value="${requestScope.order.fine}">
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <input type="hidden" name="id" value="${requestScope.order.id}"/>
                                        <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="modal.add.user.confirm"/></button>
                                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal"><fmt:message key="modal.edit.user.discard"/></button>
                                    </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>

                </div>
            </c:when>
                <c:otherwise>
                    <div class="d-flex justify-content-center" style="margin-top: 20px">
                        ${requestScope.message}
                    </div>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:if test="${requestScope.type eq 'user'}">
                <div class="d-flex justify-content-center" style="margin-top: 15px">
                    <form class="form-inline mx-auto" method="post" action="${pageContext.request.contextPath}/library/librarian">
                        <input type="hidden" name="type" value="${requestScope.type}">
                        <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="nav.search"/>" aria-label="Search" name="id"/>
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="nav.search"/></button>
                    </form>
                </div>
            </c:if>
            <%@ include file="/WEB-INF/jspf/content/librarianPagination.jspf"%>
            <div>
                <h3><fmt:message key="library.orders.list.title"/></h3>
                <c:choose>
                    <c:when test="${requestScope.orders.size() > 0}">
                        <table class="table table-striped">
                            <caption>Order list</caption>
                            <thead>
                            <tr>
                                <th scope="col"><fmt:message key="profile.order.info.id"/></th>
                                <th scope="col"><fmt:message key="form.order.term"/></th>
                                <th scope="col"><fmt:message key="library.orders.list.action"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.orders}" var="order">
                                <tr>
                                    <th scope="row">${order.id}</th>
                                    <td>${order.term}</td>
                                    <td>
                                        <div class="d-flex justify-content-end">
                                        <c:choose>
                                            <c:when test="${order.status == 'PENDING'}">

                                                <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                                                    <input type="hidden" name="id" value="${order.id}"/>
                                                    <input type="hidden" name="status" value="ACCEPTED"/>
                                                    <button type="submit" class="btn btn-outline-success" style="margin: 2px"><fmt:message key="lib.accept"/></button>
                                                </form>
                                                <form method="post" action="${pageContext.request.contextPath}/library/changeStatus">
                                                    <input type="hidden" name="id" value="${order.id}"/>
                                                    <input type="hidden" name="status" value="REJECTED"/>
                                                    <button type="submit" class="btn btn-outline-danger" style="margin: 2px"><fmt:message key="lib.discard"/></button>
                                                </form>
                                            </c:when>
                                        </c:choose>
                                        <form method="post" action="${pageContext.request.contextPath}/library/librarian">
                                            <input type="hidden" name="type" value="info"/>
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p><fmt:message key="library.orders.list.message"/></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
