<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Chofer</h2>
                <form:form action="chofer_add" method="post" modelAttribute="chofer">
                    <div class="mb-3">
                        <label for="nombres" class="form-label">Nombres</label>
                        <form:input path="nombres" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <form:input path="apellidos" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="nroLicencia" class="form-label">Nro. de licencia</label>
                        <form:input path="nroLicencia" cssClass="form-control w-25" required="true" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />