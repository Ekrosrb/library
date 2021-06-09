<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ attribute name="book" required="true" type="com.ekros.library.model.entity.Book" description="book" %>
<button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#${book.id}editBookModal" style="margin: 3px">
    <fmt:message key="book.card.admin.edit"/>
</button>
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