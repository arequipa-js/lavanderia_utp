<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Solicitudes</h2>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <a class="btn btn-primary mb-3" href="solicitudes_add.html" role="button">Nueva Solicitud</a>
                </div>
                <div class="col-9">
                    <a href="solicitudes?estado=A"><span class="text-light h4">Aceptadas</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="solicitudes?estado=C"><span class="text-light h4 ml-5">Canceladas</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="solicitudes"><span class="text-light h4 mr-5">Ver Todas</span></a>
                </div>
            </div>
        </div>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">Solicitud #</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Cliente</th>
                    <th scope="col">Servicio</th>
                    <th scope="col">Observaciones</th>
                    <th scope="col">Fecha de creación</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="s" items="${listSolicitudDetalles}">
                    <tr>
                        <td>${s.solicitudId}</td>
                        <td><c:out value="${fn:substring(s.estado, 0, 1) == 'A' ? 'Aceptada' : 'Cancelada'}" /></td>
                        <td>${s.cliente}</td>
                        <td>${s.servicio}</td>
                        <td>${s.observaciones}</td>
                        <td>${s.fechaCreacion}</td>
                        <td><a href="comprobante?id=${s.solicitudId}"><i class="bi bi-receipt"></i></a></td>
                        <td><a href="solicitud_servicios_add?id=${s.solicitudId}"><i class="bi bi-plus-square-fill"></i></a></td>
                        <td><a href="solicitud_edit?id=${s.solicitudId}"><i class="bi bi-pencil-fill"></i></a></td>
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