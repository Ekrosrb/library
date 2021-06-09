<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ attribute name="user" required="true" type="com.ekros.library.model.entity.User" description="user" %>
<div class="modal fade" id="${user.id}deleteModal" tabindex="-1" aria-labelledby="${user.id}deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="${user.id}deleteModalLabel">
                    <fmt:message key="message.alert.delete.title"/> ${user.email}
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <fmt:message key="message.alert.delete"/>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/library/deleteUser">
                <input type="hidden" name="id" value="${user.id}"/>
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