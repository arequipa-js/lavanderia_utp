<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Solicitudes</h2>
        <a class="btn btn-primary mb-3" href="solicitudes_add.html" role="button">Nueva Solicitud</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">Solicitud #</th>
                    <th scope="col">Cliente</th>
                    <th scope="col">Servicio</th>
                    <th scope="col">Observaciones</th>
                    <th scope="col">Fecha de creación</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${listSolicitudDetalles}">
                    <tr>
                        <td>${s.solicitudId}</td>
                        <td>${s.cliente}</td>
                        <td>${s.servicio}</td>
                        <td>${s.observaciones}</td>
                        <td>${s.fechaCreacion}</td>
                        <td><a href="comprobante?id=${s.id}"><i class="bi bi-receipt"></i></a></td>
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
            location.href = "solicitud_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>