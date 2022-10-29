<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Prendas</h2>
        <a class="btn btn-primary mb-3" href="prendas_add.html" role="button">Agregar Prenda</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Cliente</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Color</th>
                    <th scope="col">Material</th>
                    <th scope="col">Marca</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Peso</th>
                    <th scope="col">Observaciones</th>
                    <th scope="col">Cantidad</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${listPrendas}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.cliente}</td>
                        <td>${p.tipo}</td>
                        <td>${p.color}</td>
                        <td>${p.material}</td>
                        <td>${p.marca}</td>
                        <td>${p.estado}</td>
                        <td>${p.peso}</td>
                        <td>${p.observaciones}</td>
                        <td>${p.cantidad}</td>
                        <td><a href="prenda_edit?id=${p.id}"><i class="bi bi-pencil-fill"></i></a></td>
                        <td><a href="javascript:confirmDelete(${p.id});"><i class="bi bi-trash-fill"></i></a></td>
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
            location.href = "prenda_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>