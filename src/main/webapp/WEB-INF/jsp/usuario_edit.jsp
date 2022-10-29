<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>

        <div class="content text-white">
            <div class="p-3 mb-5 bg-dark text-white">
                <div class="container w-50 mt-5 mb-5">
                    <h2 class="text-left mb-4">Actualizar Usuario</h2>
                    <form:form action="update" method="post" modelAttribute="usuario">
                        <form:hidden path="id" /> 
                        <div class="mb-3">
                            <label for="2" class="form-label">Email</label>
                            <form:input type="email" path="email" cssClass="form-control w-50" required="true" maxlength="50" />
                        </div>
                        <div class="mb-3">
                            <label for="2" class="form-label">Contraseña</label>
                            <form:password path="password" cssClass="form-control w-50" required="true" maxlength="10" />
                        </div>
                        <div class="mb-3">
                            <label for="1" class="form-label">Nombres</label>
                            <form:input path="nombres" cssClass="form-control w-75" required="true" maxlength="50" />
                        </div>
                        <div class="mb-3">
                            <label for="2" class="form-label">Apellidos</label>
                            <form:input path="apellidos" cssClass="form-control w-75" required="true" maxlength="50" />
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                    </form:form>
                </div>
            </div>
            <jsp:include page="footer.jsp" />