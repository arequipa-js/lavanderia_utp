<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Actualizar visita</h2>
                <form:form action="visita_update" method="post" modelAttribute="visita">
                    <form:hidden path="id" />
                    <div class="mb-3">
                        <label for="1" class="form-label">Solicitud</label>
                        <form:select path="solicitudId" cssClass="form-select w-50">
                            <c:forEach var="s" items="${listSolicitudes}">
                                <form:option value="${s.id}" label="Solicitud 00-${s.id} ${s.cliente}" />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="1" class="form-label">Movilidad</label>
                        <form:select path="movilidadId" cssClass="form-select w-50">
                            <c:forEach var="m" items="${listMovilidades}">
                                <form:option value="${m.id}" label="${m.nombre} - ${m.chofer} " />
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="fechaRecojo" class="form-label">Fecha de recojo</label>
                        <form:input type="date" path="fechaRecojo" cssClass="form-control w-25" required="true" min="2022-01-01" />
                    </div>
                    <div class="mb-3">
                        <label for="horaRecojo" class="form-label">Hora de recojo</label>
                        <form:select path="horaRecojo" cssClass="form-select w-25">
                            <form:option value="09:00" label="09:00" />
                            <form:option value="10:00" label="10:00" />
                            <form:option value="11:00" label="11:00" />
                            <form:option value="12:00" label="12:00" />
                            <form:option value="13:00" label="13:00" />
                            <form:option value="14:00" label="14:00" />
                            <form:option value="15:00" label="15:00" />
                            <form:option value="16:00" label="16:00" />
                            <form:option value="17:00" label="17:00" />
                            <form:option value="18:00" label="18:00" />
                        </form:select>
                    </div>
                    <div class="mb-3">
                      <label for="1" class="form-label">Estado</label>
                      <form:select path="estado" cssClass="form-select w-25">
                        <form:option value="P" label="Pendiente" />
                        <form:option value="R" label="Realizada" />
                      </form:select>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />