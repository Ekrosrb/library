<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ attribute name="user" required="true" type="com.ekros.library.model.entity.User" description="user" %>
<div class="modal fade" id="${user.id}" tabindex="-1" aria-labelledby="${user.id}Label" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="${user.id}Label"><fmt:message key="modal.edit.user.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/library/updateUser">
                <div class="modal-body">
                    <input name="id" type="hidden" value="${user.id}">
                    <div class="mb-3">
                        <label for="firstName" class="form-label text-dark"><fmt:message key="form.firstName"/></label>
                        <input class="form-control" id="firstName" name = "firstName" aria-describedby="first name" value="${user.firstName}">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label text-dark"><fmt:message key="form.lastName"/></label>
                        <input class="form-control" id="lastName" name = "lastName" aria-describedby="last name" value="${user.lastName}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label text-dark"><fmt:message key="form.email"/></label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp" value="${user.email}" disabled>
                    </div>
                    <div class="mb-3">
                        <label for="birthday" class="form-label text-dark"><fmt:message key="form.birthday"/></label>
                        <input class="form-control" type="date" id="birthday" name="birthday" aria-describedby="birthday" value="${user.birthday}">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label text-dark"><fmt:message key="form.phone"/></label>
                        <input class="form-control" id="phone" name = "phone" aria-describedby="phone number" value="${user.phone}">
                    </div>
                    <div class="form-group">
                        <label for="role"><fmt:message key="form.admin.role"/></label>
                        <select class="form-control" id="role" name="role">
                            <option><fmt:message key="form.role.name.admin"/></option>
                            <option><fmt:message key="form.role.name.librarian"/></option>
                            <option><fmt:message key="form.role.name.user"/></option>
                        </select>
                    </div>
                    <input name="href" type="hidden" value="/library/admin">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="modal.edit.user.discard"/></button>
                    <button type="submit" class="btn btn-primary"><fmt:message key="modal.edit.user.confirm"/></button>
                </div>
            </form>
        </div>
    </div>
</div>