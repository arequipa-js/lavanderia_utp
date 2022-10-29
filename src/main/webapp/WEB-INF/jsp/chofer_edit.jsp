<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-50 mt-5 mb-5">
                <h2 class="text-left mb-4">Actualizar Chofer</h2>
                <form:form action="chofer_update" method="post" modelAttribute="chofer">
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
                        <label for="nroLicencia" class="form-label">Nro. de licencia</label>
                        <form:input path="nroLicencia" cssClass="form-control w-25" required="true" min="10000000" max="99999999" />
                    </div>
                    <div class="mb-1">
                      <label class="form-label">Disponible</label>
                     </div>
                    <div class="mb-3">
                        Si <form:radiobutton path="disponible" value="1" cssClass="form-check-input" />  
                        No <form:radiobutton path="disponible" value="0" cssClass="form-check-input" />
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Actualizar</button>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp" />