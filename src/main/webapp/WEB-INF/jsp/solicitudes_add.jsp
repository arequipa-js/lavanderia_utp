<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Solicitud</h2>
                <form:form action="solicitud_add" method="post" modelAttribute="solicitud">
                    <div class="mb-3">
                        <label for="1" class="form-label">Cliente</label>
                        <form:select path="personaId" cssClass="form-select w-50">
                            <c:forEach var="c" items="${listClientes}">
                                <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="fechaSolicitud" class="form-label">Fecha de Solicitud</label>
                        <input type="text" class="form-control w-25" id="2">
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
                            
            </div>
        </div>
        <jsp:include page="footer.jsp" />