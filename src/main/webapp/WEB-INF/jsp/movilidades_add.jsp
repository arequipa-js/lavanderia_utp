<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Movilidad</h2>
                <form:form action="movilidad_add" method="post" modelAttribute="movilidad">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <form:input path="nombre" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="marca" class="form-label">Marca</label>
                        <form:input path="marca" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="modelo" class="form-label">Modelo</label>
                        <form:input path="modelo" cssClass="form-control w-25" required="true" />
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Chofer</label>
                        <form:select path="choferId" cssClass="form-select w-50">
                            <c:forEach var="c" items="${listChoferes}">
                                <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />