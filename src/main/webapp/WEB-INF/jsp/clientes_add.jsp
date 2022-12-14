<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Agregar Cliente</h2>
                <c:if test="${param.error == 'true'}">
                    <div class="alert alert-danger w-50" role="alert">
                       Por favor, ingrese otro email. El email ingresado ya existe.
                    </div>
                </c:if>
                <form:form action="cliente_add" method="post" modelAttribute="cliente">
                    <div class="mb-3">
                        <label for="nombres" class="form-label">Nombres ${param.error}</label>
                        <form:input path="nombres" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="apellidos" class="form-label">Apellidos</label>
                        <form:input path="apellidos" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="dni" class="form-label">DNI</label>
                        <form:input type="number" path="dni" cssClass="form-control w-25" required="true" min="10000000" max="99999999" />
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Distrito</label>
                        <form:select path="distritoId" cssClass="form-select w-50">
                            <c:forEach var="d" items="${listDistritos}">
                                <form:option value="${d.id}" label="${d.distrito}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="direccion" class="form-label">Direccion</label>
                        <form:input path="direccion" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <form:input type="email" path="email" cssClass="form-control w-50" required="true" maxlength="50" />
                    </div>
                    <div class="mb-3">
                        <label for="telefono" class="form-label">Telefono</label>
                        <form:input type="number" path="telefono" cssClass="form-control w-25" required="true" maxlength="15" />
                    </div>
                    <div class="mb-1">
                      <label class="form-label">Sexo</label>
                     </div>
                    <div class="mb-3">
                        Masculino <form:radiobutton path="sexo" value="M"  cssClass="form-check-input" checked="true" />  
                        Femenino <form:radiobutton path="sexo" value="F"  cssClass="form-check-input" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Agregar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />