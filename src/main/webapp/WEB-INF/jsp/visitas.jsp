<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-100 mt-5 mb-5">
        <h2 class="text-left mb-3">Visitas</h2>
        <a class="btn btn-primary mb-3" href="visitas_add.html" role="button">Programar Visita</a>
        <table class="table bg-white p-5">
            <thead>
                <tr>
                    <th scope="col">Solicitud</th>
                    <th scope="col">Cliente</th>
                    <th scope="col">Movilidad</th>
                    <th scope="col">Fecha de recojo</th>
                    <th scope="col">Hora de recojo</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${listVisitas}">
                    <tr>
                        <td>00-${v.solicitudId}</td>
                        <td>${v.cliente}</td>
                        <td>${v.movilidad}</td>
                        <td>${v.fechaRecojo}</td>
                        <td>${v.horaRecojo}</td>
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
            location.href = "visita_delete?id=" + id;
        } else {
            console.log('No');
        }
    }
</script>