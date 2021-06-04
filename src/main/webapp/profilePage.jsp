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
    <c:if test="${not empty requestScope.order}">

<div class="modal fade" id="orderInfo" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="orderInfoLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="orderInfoLabel"><fmt:message key="library.orders.list.view"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="1bookName" class="form-label text-dark"><fmt:message key="library.orders.info.book.name"/></label>
                    <input class="form-control border border-dark rounded" id="1bookName" name="bookName" value="${requestScope.order.bookName}" disabled>
                </div>
                <div class="mb-3">
                    <label for="1term" class="form-label text-dark"><fmt:message key="form.order.term"/></label>
                    <input class="form-control border border-dark rounded" type="date" id="1term" name = "term" value="${requestScope.order.term}" disabled>
                </div>
                <div class="mb-3">
                    <label for="1term" class="form-label text-dark"><fmt:message key="form.order.date"/></label>
                    <input class="form-control border border-dark rounded" type="date" id="1date" name = "date" value="${requestScope.order.orderDate}" disabled>
                </div>
                <div class="mb-3">
                    <label for="1status" class="form-label text-dark"><fmt:message key="profile.order.info.status"/></label>
                    <input class="form-control border border-dark rounded" id="1status" name = "status" value="${requestScope.order.status}" disabled>
                </div>
                <div class="mb-3">
                    <label for="1fine" class="form-label text-dark"><fmt:message key="library.orders.info.book.fine"/></label>
                    <input class="form-control border border-dark rounded" id="1fine" name = "fine" value="${requestScope.order.fine}" disabled>
                </div>
                <div class="d-flex justify-content-start">
                    <c:if test="${requestScope.order.status == 'EXPIRED'}">
                        <form method="post" action="${pageContext.request.contextPath}/library/payFine">
                            <input type="hidden" name="id" value="${requestScope.order.id}"/>
                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="pay.fine"/></button>
                        </form>
                    </c:if>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
    </c:if>

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

    <%@ include file="WEB-INF/jspf/content/profilePagination.jspf"%>
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
                        <th scope="col">Action</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.subList}" var="order">
                        <c:choose>
                            <c:when test="${order.status == 'ACCEPTED'}">
                                <tr class="table-success">
                                    <th scope="row">${order.id}</th>
                                    <td>${order.term}</td>
                                    <td>
                                        <fmt:message key="profile.order.info.status.accept"/>
                                    </td>
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
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
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
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
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
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
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
                                    </td>
                                </tr>

                            </c:when>
                            <c:when test="${order.status == 'EXPIRED'}">
                                <tr class="table-danger">
                                <th scope="row">${order.id}</th>
                                <td>${order.term}</td>
                                <td>
                                    <fmt:message key="profile.order.info.status.expired"/>   ${order.fine}$
                                </td>
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/payFine">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="pay.fine"/></button>
                                        </form>
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
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
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
                                    </td>
                                </tr>

                            </c:when>
                            <c:when test="${order.status == 'PAID'}">
                                <tr class="table-primary">
                                    <th scope="row">${order.id}</th>
                                    <td>${order.term}</td>
                                    <td>
                                        <fmt:message key="pay.paid"/>
                                    </td>
                                    <td class="d-flex justify-content-end">
                                        <form method="post" action="${pageContext.request.contextPath}/library/profile">
                                            <input type="hidden" name="id" value="${order.id}"/>
                                            <button type="submit" class="btn btn-outline-primary" style="margin: 2px"><fmt:message key="library.orders.list.view"/></button>
                                        </form>
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
<script>
    $(document).ready(function(){
        $("#orderInfo").modal('show');
    });
</script>
</body>
</html>
