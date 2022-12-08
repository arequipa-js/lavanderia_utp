<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-2">Agregar servicio a solicitud 00${solicitud}</h2>
                <h3 class="mt-3 mb-5">Cliente: ${cliente.nombres} ${cliente.apellidos}</h3>
                
                <form:form action="solicitud_servicio_add" method="post" modelAttribute="solicitudDetalle">
                    <form:hidden path="solicitudId" value="${solicitud}" />
                    <div class="mb-3">
                        <label for="1" class="form-label">Servicio</label>
                        <form:select path="servicioId" cssClass="form-select w-50">
                            <c:forEach var="se" items="${listServicios}">
                                <form:option value="${se.id}" label="${se.nombre}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Prenda</label>
                        <form:select path="prendaId" cssClass="form-select w-50">
                            <c:forEach var="p" items="${listPrendas}">
                                <form:option value="${p.id}" label="${p.id} - ${p.cliente} - ${p.tipo} ${p.color} ${p.material} ${p.marca}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Observaciones</label>
                        <form:textarea path="observaciones" rows="5" cols="20" cssClass="form-control w-50" required="true" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Registrar</button>
                </form:form>
                            
            </div>
        </div>
        <jsp:include page="footer.jsp" />