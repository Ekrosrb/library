<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ attribute name="book" required="true" type="com.ekros.library.model.entity.Book" description="book" %>
<button class="btn btn-outline-primary" type="button" data-toggle="modal" data-target="#${book.id}orderModal"><fmt:message key="book.card.button"/></button>
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
                        <input class="form-control" type="date" min="2021-01-01" value="2021-01-01" id="term" name="term" aria-describedby="term">
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