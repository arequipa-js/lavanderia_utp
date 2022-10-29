<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Servicio</h2>
                <form:form action="servicio_add" method="post" modelAttribute="servicio">
                    <div class="mb-3">
                        <label for="1" class="form-label">Categoria</label>
                        <form:select path="categoriaId" cssClass="form-select w-50">
                            <c:forEach var="c" items="${listCategorias}">
                                <form:option value="${c.id}" label="${c.nombre}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="2" class="form-label">Servicio</label>
                        <form:input path="nombre" cssClass="form-control w-50" required="true" maxlength="100" />
                    </div>
                    <div class="mb-3">
                        <label for="tarifa" class="form-label">Tarifa</label>
                        <form:input type="number" path="tarifa" cssClass="form-control w-25" required="true" min="1" max="99" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
                            
            </div>
        </div>
        <jsp:include page="footer.jsp" />