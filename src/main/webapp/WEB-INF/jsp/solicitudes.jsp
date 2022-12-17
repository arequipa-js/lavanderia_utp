<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="content text-white">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <div class="p-3 mb-5 bg-dark text-white">
                <div class="container w-100 mt-5 mb-5">
                    <h2 class="text-left mb-3">Solicitudes</h2>

                    <div class="container">
                        <div class="row">
                            <div class="col-2">
                                <a class="btn btn-primary mb-3" href="solicitudes_add.html" role="button">Nueva Solicitud</a>
                            </div>
                            <c:if test="${sessionScope.clienteId == 0}">
                              <div class="col-3">
                                <form:form action="solicitud_search" method="post" modelAttribute="solicitud">
                                    <form:select path="personaId" cssClass="form-select w-100" autocomplete="off" required="true">
                                    <c:forEach var="c" items="${listClientes}">
                                        <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                                    </c:forEach>
                                </form:select>
                                    <button type="submit" class="btn btn-primary mt-3">Buscar</button>
                                </form:form>
                            </div>
                            </c:if>
                            <div class="col-7">
                                <a href="solicitudes?estado=A"><span class="text-light h4">Aceptadas</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
-                               <a href="solicitudes?estado=C"><span class="text-light h4 ml-5">Canceladas</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
-                               <a href="solicitudes"><span class="text-light h4 mr-5">Ver Todas</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table bg-white p-5 w-100">
                        <thead>
                            <tr>
                                <th scope="col">Solicitud #</th>
                                <th scope="col">Estado</th>
                                <th scope="col">Cliente</th>
                                <th scope="col">Fecha de solicitud</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="s" items="${listSolicitudes}">
                                <tr id="${s.id}">
                                    <td>${s.id}</td>
                                    <td><c:out value="${fn:substring(s.estado, 0, 1) == 'A' ? 'Aceptada' : 'Cancelada'}" /></td>
                                    <td>${s.cliente}</td>
                                    <td>${s.fechaSolicitud}</td>
                                    <td><a href="comprobante?id=${s.id}"><i class="bi bi-receipt"></i></a></td>
                                    <td><a href="solicitud_servicios_add?id=${s.id}"><i class="bi bi-plus-square-fill"></i></a></td>
                                    <td><a href="solicitud_edit?id=${s.id}" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-pencil-fill"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
<jsp:include page="footer.jsp" />
<script type="text/javascript">
     new TomSelect("#personaId", {
         sortField: {
             field: "text",
             direction: "asc"
         }
     });

    function confirmDelete(id) {
        if (confirm('¿Estás seguro que deseas eliminar el registro?')) {
            location.href = "solicitud_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>