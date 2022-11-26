<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Prenda</h2>
                <form:form action="prenda_add" method="post" modelAttribute="prenda">
                    <div class="mb-3">
                        <label for="1" class="form-label">Cliente</label>
                        <form:select path="personaId" cssClass="form-select w-50" autocomplete="off" required="true">
                            <c:forEach var="c" items="${listClientes}">
                                <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Tipo</label>
                        <form:select path="tipoId" cssClass="form-select w-50">
                            <c:forEach var="t" items="${listTipos}">
                                <form:option value="${t.id}" label="${t.tipo}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Color</label>
                        <form:select path="colorId" cssClass="form-select w-50">
                            <c:forEach var="c" items="${listColores}">
                                <form:option value="${c.id}" label="${c.color}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Material</label>
                        <form:select path="materialId" cssClass="form-select w-50">
                            <c:forEach var="m" items="${listMateriales}">
                                <form:option value="${m.id}" label="${m.material}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Estado</label>
                        <form:select path="estadoId" cssClass="form-select w-50">
                            <c:forEach var="e" items="${listEstados}">
                                <form:option value="${e.id}" label="${e.estado}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="marca" class="form-label">Marca</label>
                        <form:input path="marca" cssClass="form-control w-50" required="true" maxlength="100" />
                    </div>
                    <div class="mb-3">
                        <label for="peso" class="form-label">Peso</label>
                        <form:input path="peso" cssClass="form-control w-25" required="true" maxlength="5" />
                    </div>
                    <div class="mb-3">
                        <label for="observaciones" class="form-label">Observaciones</label>
                        <form:input path="observaciones" cssClass="form-control w-75" required="true" maxlength="100" />
                    </div>
                    <div class="mb-3">
                        <label for="cantidad" class="form-label">Cantidad</label>
                        <form:input type="number" max="99" path="cantidad" cssClass="form-control w-25" required="true"  />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
                            
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
</script>