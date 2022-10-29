<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Usuarios administradores</h2>
        <a class="btn btn-primary mb-3" href="usuarios_add.html" role="button">Agregar Usuario</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Email</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="u" items="${listUsuarios}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.email}</td>
                        <td>${u.nombres}</td>
                        <td>${u.apellidos}</td>
                        <td><a href="usuario_edit?id=${u.id}"><i class="bi bi-pencil-fill"></i></a></td>
                        <td><a href="javascript:confirmDelete(${u.id});"><i class="bi bi-trash-fill"></i></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp" />
<script type="text/javascript">
    function confirmDelete(id) {
        if (confirm('¿Estás seguro que deseas eliminar al usuario?')) {
            location.href = "usuario_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>
