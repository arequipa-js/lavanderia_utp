<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Actualizar Cliente</h2>
                <form:form action="cliente_update" method="post" modelAttribute="cliente">
                    <form:hidden path="id" />
                    <div class="mb-3">
                        <label for="nombres" class="form-label">Nombres</label>
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
                    <c:if test="${sessionScope.clienteId == 0}">
                        <div class="mb-1">
                          <label class="form-label">Activo</label>
                        </div>
                        <div class="mb-3">
                            Si <form:radiobutton path="activo" value="1" cssClass="form-check-input" />
                            No <form:radiobutton path="activo" value="0" cssClass="form-check-input" />
                        </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />