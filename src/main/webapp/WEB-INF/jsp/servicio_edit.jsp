<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Actualizar Servicio</h2>
                <form:form action="servicio_update" method="post" modelAttribute="servicio">
                    <form:hidden path="id" />
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
                        <label for="2" class="form-label">Tarifa</label>
                        <form:input type="number" path="tarifa" cssClass="form-control w-10" />
                    </div>
                    <div class="mb-1">
                      <label class="form-label">Activo</label>
                     </div>
                    <div class="mb-3">
                        Si <form:radiobutton path="activo" value="1" cssClass="form-check-input" />  
                        No <form:radiobutton path="activo" value="0" cssClass="form-check-input" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                </form:form>
                            
            </div>
        </div>
        <jsp:include page="footer.jsp" />