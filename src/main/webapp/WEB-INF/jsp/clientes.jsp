<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="content text-white">
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            <div class="p-3 mb-5 bg-dark text-white">
                <div class="container w-100 mt-5 mb-5">
                    <h2 class="text-left mb-3">Clientes</h2>

                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <a class="btn btn-primary mb-3" href="clientes_add.html" role="button">Agregar Cliente</a>
                            </div>
                            <div class="col-6">
                                <form:form action="cliente_search" method="post" modelAttribute="cliente">
                                    <form:input path="nombres" cssClass="form-control w-50" required="true" />
                                    <button type="submit" class="btn btn-primary mt-3">Buscar</button>
                                </form:form>
                            </div>

                            <div class="col">
                                <a class="btn btn-primary mb-3" href="clientes.html" role="button">Ver todos</a>
                            </div>
                        </div>
                    </div>    

                    <table class="table bg-white p-5">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombres</th>
                                <th scope="col">Apellidos</th>
                                <th scope="col">DNI</th>
                                <th scope="col">Distrito</th>
                                <th scope="col">Dirección</th>
                                <th scope="col">Email</th>
                                <th scope="col">Teléfono</th>
                                <th scope="col">Sexo</th>
                                <th scope="col">Activo</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${listClientes}">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.nombres}</td>
                                    <td>${c.apellidos}</td>
                                    <td>${c.dni}</td>
                                    <td>${c.distrito}</td>
                                    <td>${c.direccion}</td>
                                    <td>${c.email}</td>
                                    <td>${c.telefono}</td>
                                    <td>${c.sexo}</td>
                                    <td><c:out value="${c.activo ? 'Si' : 'No'}" /></td>
                                    <td><a href="cliente_edit?id=${c.id}"><i class="bi bi-pencil-fill"></i></a></td>
                                    <td><a href="javascript:confirmDelete(${c.id});"><i class="bi bi-trash-fill"></i></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
            <script type="text/javascript">
                function confirmDelete(id) {
                    if (confirm('¿Estás seguro que deseas eliminar el registro?')) {
                        location.href = "cliente_delete?id=" + id;
                    } else {
                        console.log('No');
                    }
                }
            </script>