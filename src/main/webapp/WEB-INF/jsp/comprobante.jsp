<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-50 mt-5 mb-5">
        <div class="container bg-white">
            <div class="row">
                <label class="mt-2 text-dark text-right">Recibo: 00${solicitud}</label>
                <label class="mt-2 text-dark text-right">Fecha: ${fechaSolicitud}</label>
                <h2 class="mt-2 text-dark text-center">Lavanderia UTP</h2>
            </div>
            <div class="row">
                <div class="col">
                    <h5 class="mt-5 text-dark">Cliente: ${cliente.nombres} - ${cliente.apellidos}</h5>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    Servicios:
                    <table class="table bg-white p-5">
                        <thead>
                            <tr>
                                <th scope="col">Servicio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="subtotal" value="${0}" />
                            <c:forEach var="s" items="${listSolicitudDetalles}">
                                <c:set var="subtotal" value="${s.cantidad * s.tarifa + subtotal}" />
                                <tr>
                                    <td>${s.servicio}</td>
                                    <td>${s.cantidad}</td>
                                    <td>S/${s.tarifa}</td>
                                    <td>S/<fmt:parseNumber value="${s.cantidad * s.tarifa}" /></td>
                                </tr>
                            </c:forEach>
                            <c:set var="igv" value="${subtotal * 0.18}" />
                            <tr>
                                <td></td>
                                <td></td>
                                <td><h6 class="text-dark">SUBTOTAL</h6></td>
                                <td>S/${subtotal}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><h6 class="text-dark">IGV (18%)</h6></td>
                                <td>S/<fmt:formatNumber type="number" value="${igv}" maxFractionDigits="1" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><h6 class="text-dark">TOTAL</h6></td>
                                <td>S/<fmt:formatNumber type="number" value="${subtotal + igv}" maxFractionDigits="1" /></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp" />