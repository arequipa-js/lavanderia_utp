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
                    <h2 class="text-left mb-3">Reportes</h2>
                    <div class="">
                        <form:form cssClass="form-inline" action="reportes" method="post" modelAttribute="solicitudDetalle">
                            <div class="form-group row">
                                <div class="col-3">
                                    <form:select path="personaId" cssClass="mb-3 form-select" autocomplete="off" required="true">
                                        <form:option value="0" label="Seleccionar cliente" />
                                        <c:forEach var="c" items="${listClientes}">
                                            <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="col-3">
                                    <form:select path="categoriaId" cssClass="mb-3 form-select">
                                        <form:option value="0" label="Seleccionar categoria" />
                                        <c:forEach var="c" items="${listCategorias}">
                                            <form:option value="${c.id}" label="${c.nombre}" />
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="col-3">
                                    <form:select path="servicioId" cssClass="mb-3 form-select">
                                        <form:option value="0" label="Seleccionar servicio" />
                                        <c:forEach var="s" items="${listServicios}">
                                            <form:option value="${s.id}" label="${s.nombre}" />
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="col-3">
                                    <form:input type="date" path="fechaSolicitud" cssClass="form-control w-50" min="2022-01-01" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-3">
                                    <button type="submit" class="btn btn-primary mt-1 mr-2">Buscar</button>
                                    <a href="reportes"><button type="button" class="btn btn-primary mt-1">Ver todos</button></a>
                                </div>
                            </div>
                        </form:form>
                    </div>
                    <table class="table bg-white p-5 w-100">
                        <thead>
                            <tr>
                                <th scope="col">Cliente</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Servicio</th>
                                <th scope="col">Monto</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="r" items="${listReportes}">
                                <tr id="${r.id}">
                                    <td>${r.cliente}</td>
                                    <td>${r.categoria}</td>
                                    <td>${r.servicio}</td>
                                    <td>${r.tarifa}</td>
                                    <td>${r.fechaSolicitud}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <h5 class="text-right mb-1">${nroResultados} ${nroResultados == 1 ? 'resultado' : 'resultados'}</h5>
                    <c:if test="${nroResultados  > 0}">
                        <button type="button" id="exportar_pdf" class="btn btn-primary mt-3">Exportar a PDF</button>
                    </c:if>
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

                $("#exportar_pdf").on("click", function () {
                    var personaId = $("#personaId").val();
                    var categoriaId = $("#categoriaId").val();
                    var servicioId = $("#servicioId").val();
                    var fechaSolicitud = $("#fechaSolicitud").val();
                    console.log(fechaSolicitud);
                    $.ajax({
                        type: 'POST',
                        dataType: 'text',
                        url: 'export_pdf',
                        data: {
                            personaId: parseInt(personaId),
                            categoriaId: parseInt(categoriaId),
                            servicioId: parseInt(servicioId),
                            fechaSolicitud: fechaSolicitud
                        },
                        success: function () {
                            console.log("success");
                            setTimeout(() => {
                                const url = window.location.href;
                                const pdfUrl = url.slice(0, url.lastIndexOf('/')) + '/resources/reporte_servicios.pdf';
                                console.log(pdfUrl);
                                window.open(pdfUrl, '_blank');
                            }, "2000");
                        },
                        error: function (error) {
                            console.log("Error : ", error);
                        }
                    });
                });
            </script>