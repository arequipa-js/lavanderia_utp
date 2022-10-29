<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Categorías</h2>
        <a class="btn btn-primary mb-3" href="categorias_add.html" role="button">Agregar Categoría</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripción</th>
                    <th scope="col">Activo</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="c" items="${listCategorias}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.nombre}</td>
                        <td>${c.descripcion}</td>
                        <td><c:out value="${c.activo ? 'Si' : 'No'}" /></td>
                        <td><a href="categoria_edit?id=${c.id}"><i class="bi bi-pencil-fill"></i></a></td>
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
            location.href = "categoria_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>