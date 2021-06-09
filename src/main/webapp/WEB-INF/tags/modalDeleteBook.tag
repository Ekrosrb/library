<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ attribute name="book" required="true" type="com.ekros.library.model.entity.Book" description="book" %>
<button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#${book.id}deleteModal" style="margin: 3px"><fmt:message key="admin.pane.delete"/></button>
<div class="modal fade" id="${book.id}deleteModal" tabindex="-1" aria-labelledby="${book.id}deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="${book.id}deleteModalLabel">
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