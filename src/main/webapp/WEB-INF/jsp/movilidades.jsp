<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Movilidades</h2>
        <a class="btn btn-primary mb-3" href="movilidades_add.html" role="button">Agregar Movilidad</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Marca</th>
                    <th scope="col">Modelo</th>
                    <th scope="col">Chofer</th>
                    <th scope="col">Disponible</th>
                    <th scope="col">Fecha de creacion</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${listMovilidades}">
                    <tr>
                        <td>${m.id}</td>
                        <td>${m.nombre}</td>
                        <td>${m.marca}</td>
                        <td>${m.modelo}</td>
                        <td>${m.chofer}</td>
                        <td><c:out value="${m.disponible ? 'Si' : 'No'}" /></td>
                        <td>${m.fechaCreacion}</td>
                        <td><a href="movilidad_edit?id=${m.id}"><i class="bi bi-pencil-fill"></i></a></td>
                        <td><a href="javascript:confirmDelete(${m.id});"><i class="bi bi-trash-fill"></i></a></td>
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
            location.href = "movilidad_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>