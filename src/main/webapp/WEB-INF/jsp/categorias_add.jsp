<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Categoría</h2>
                <form:form action="categoria_add" method="post" modelAttribute="categoria">
                    <div class="mb-3">
                        <label for="1" class="form-label">Nombre</label>
                        <form:input path="nombre" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="2" class="form-label">Descripción</label>
                        <form:input path="descripcion" cssClass="form-control w-75" required="true" maxlength="100" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />