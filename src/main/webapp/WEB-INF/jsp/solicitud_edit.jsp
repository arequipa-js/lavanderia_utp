<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Actualizar Solicitud</h2>
                <form:form action="solicitud_update" method="post" modelAttribute="solicitud">
                    <form:hidden path="id" />
                    <div class="mb-3">
                        <label for="1" class="form-label">Cliente</label>
                        <form:select path="personaId" cssClass="form-select w-50">
                            <c:forEach var="c" items="${listClientes}">
                                <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                </form:form>            
            </div>
        </div>
        <jsp:include page="footer.jsp" />