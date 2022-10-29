<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Servicios</h2>
        <a class="btn btn-primary mb-3" href="servicios_add.html" role="button">Agregar Servicio</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">Servicio</th>
                    <th scope="col">Categoria</th>
                    <th scope="col">Tarifa</th>
                    <th scope="col">Activo</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${listServicios}">
                    <tr>
                        <td>${s.nombre}</td>
                        <td>${s.categoria}</td>
                        <td>${s.tarifa}</td>
                        <td><c:out value="${s.activo ? 'Si' : 'No'}" /></td>
                        <td><a href="servicio_edit?id=${s.id}"><i class="bi bi-pencil-fill"></i></a></td>
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
            location.href = "servicio_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>