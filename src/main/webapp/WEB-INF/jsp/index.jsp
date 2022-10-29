<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <div class="main-container text-white">
            <div class="d-flex justify-content-md-center align-items-center vh-100">
                <div>
                    <img src="resources/images/logo.png" alt="Lavandería UTP">
                    <form:form action="login" method="post" modelAttribute="usuario" cssClass="w-100 mt-5 mb-5">
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                               ${error}
                            </div>
                        </c:if>
                        <div class="mb-3">
                            <label for=email" class="form-label">Email</label>
                            <form:input type="email" path="email" cssClass="form-control" required="true" maxlength="50" />
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <form:password path="password" cssClass="form-control" required="true" maxlength="10" />
                        </div>
                        <button class="btn btn-primary" type="submit">Iniciar sesión</button>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>
