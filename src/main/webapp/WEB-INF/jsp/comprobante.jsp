<jsp:include page="header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="p-3 mb-5 bg-dark text-white">
    <div class="container w-50 mt-5 mb-5">
        <div class="container bg-white" id="comprobante">
            <div class="row">
                <label class="mt-2 text-dark text-right">Recibo: 00${solicitud}</label><br>
                <label class="mt-2 text-dark text-right">Fecha: ${fechaSolicitud}</label>
                <h2 class="mt-2 text-dark text-center">Lavanderia UTP</h2>
            </div>
            <div class="row">
                <div class="col">
                    <h4 class="mt-5 mb-3 text-dark">Cliente: ${cliente.nombres} ${cliente.apellidos}</h4>
                </div>
            </div>
            <input id="clienteId" type="hidden" value="${cliente.id}">
            <div class="row">
                <div class="col">
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
                                <td><label class="text-dark">SUBTOTAL</label></td>
                                <td>S/${subtotal}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><label class="text-dark">IGV (18%)</label></td>
                                <td>S/<fmt:formatNumber type="number" value="${igv}" maxFractionDigits="1" /></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td><h4 class="text-dark">TOTAL</h4></td>
                                <td><h4 class="text-dark">S/<fmt:formatNumber type="number" value="${subtotal + igv}" maxFractionDigits="1" /></h4></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <a href="export_pdf?solicitudId=${solicitud}&total=<fmt:formatNumber type="number" value="${subtotal + igv}" maxFractionDigits="0" />">
            <button type="button" class="btn btn-primary mt-3">Exportar a PDF</button>
        </a>
        <a href="javascript:sendEmail();">
            <button type="button" class="btn btn-primary ml-5 mt-3">Enviar por correo</button>
        </a>
    </div>
</div>
<jsp:include page="footer.jsp" />
<script type="text/javascript">
    function sendEmail() {
        let comprobante = document.getElementById('comprobante').innerHTML;
        let messageStr = comprobante.replace(/>\s+</g, '><').trim();
        let clienteId = document.getElementById("clienteId").value;

        $.ajax({
            type: 'POST',
            url: 'enviar_comprobante',
            data: {
                clienteId: parseInt(clienteId),
                messageStr: messageStr
            },
            success: function(text) {
                alert("Comprobante enviado exitosamente al correo del cliente.");
            },
            error: function (jqXHR) {
                console.log("Error al enviar correo");
            }
        });
    }
</script>