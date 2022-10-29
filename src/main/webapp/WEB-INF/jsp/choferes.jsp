<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Choferes</h2>
        <a class="btn btn-primary mb-3" href="choferes_add.html" role="button">Agregar Chofer</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombres</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Nro. de licencia</th>
                    <th scope="col">Disponible</th>
                    <th scope="col">Fecha de creacion</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${listChoferes}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.nombres}</td>
                        <td>${c.apellidos}</td>
                        <td>${c.nroLicencia}</td>
                        <td><c:out value="${c.disponible ? 'Si' : 'No'}" /></td>
                        <td>${c.fechaCreacion}</td>
                        <td><a href="chofer_edit?id=${c.id}"><i class="bi bi-pencil-fill"></i></a></td>
                        <td><a href="javascript:confirmDelete(${c.id});"><i class="bi bi-trash-fill"></i></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp" />
<script type="text/javascript">
    function confirmDelete(id) {
        if (confirm('¿Estás seguro que deseas eliminar el registro?')) {
            location.href = "chofer_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>