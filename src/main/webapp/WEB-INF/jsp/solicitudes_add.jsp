<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <jsp:include page="header_tags.jsp"/>
    <body>
        <jsp:include page="sidebar.jsp"/>
        <div class="p-3 mb-5 bg-dark text-white">
            <div class="container w-75 mt-5 mb-5">
                <div class="row justify-content-around">
                    <div class="col-5">
                        <h2 class="text-left mb-4">Registrar Solicitud</h2>
                        <form:form action="solicitud_add" method="post" modelAttribute="solicitud">
                            <form:hidden path="id" />
                            <div class="mb-3">
                                <label for="1" class="form-label">Cliente</label>
                                <form:select path="personaId" cssClass="form-select w-100" autocomplete="off" required="true">
                                    <c:forEach var="c" items="${listClientes}">
                                        <form:option value="${c.id}" label="${c.nombres} ${c.apellidos}" />
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <label for="fechaSolicitud" class="form-label">Fecha de Solicitud</label>
                                <form:input type="date" path="fechaSolicitud" cssClass="form-control w-75" required="true" min="2022-01-01" />
                            </div>
                            <div class="mt-5 mb-3">
                                <label for="1" class="form-label">Servicio</label>
                                <form:select path="servicioId" cssClass="form-select w-100">
                                    <c:forEach var="se" items="${listServicios}">
                                        <form:option value="${se.id}" label="${se.nombre}" />
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <label for="1" class="form-label">Prenda</label>
                                <form:select path="prendaId" cssClass="form-select w-100">
                                    <c:forEach var="p" items="${listPrendas}">
                                        <form:option value="${p.id}" label="${p.id} - ${p.cliente} - ${p.tipo} ${p.color} ${p.material} ${p.marca}" />
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Observaciones</label>
                                <form:textarea path="observaciones" rows="2" cols="20" cssClass="form-control w-100" required="true" />
                            </div>
                            <button type="submit" id="agregar_solicitud" class="btn btn-primary mt-3">Agregar servicio</button>    
                        </form:form>
                    </div>
                    <div class="col-6">
                      <div class="container bg-white h-75" id="servicios">
                        <h3 class="text-dark mt-5 p-3">Servicios:</h3>
                        <table id="tabla_servicios" class="table bg-white p-5">
                          <thead>
                            <tr>
                                <th scope="col">Servicio</th>
                                <th scope="col">Observaciones</th>
                                <th scope="col"></th>
                            </tr>
                          </thead>
                          <tbody>
                              <c:forEach var="s" items="${listSolicitudDetalles}">
                                <tr>
                                  <td>${s.servicio}</td>
                                  <td>${s.observaciones}</td>
                                  <td><a href="javascript:confirmDelete(${s.id});"><i class="bi bi-trash-fill"></i></a></td>
                                </tr>
                              </c:forEach>
                          </tbody>
                        </table>
                        <a class="text-center" href="solicitudes.html">
                           <button type="button" class="btn btn-primary mt-3">Finalizar Solicitud</button>
                        </a>
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
        <script type="text/javascript">
            new TomSelect("#personaId", {
                sortField: {
                    field: "text",
                    direction: "asc"
                }
            });

            function confirmDelete(id) {
                if (confirm('¿Estás seguro que deseas quitar el servicio de la solicitud?')) {
                    let solicitudId = document.getElementById("id").value;
                    location.href = "solicitud_detalle_delete?id=" + id + "&solicitudId=" + solicitudId;
                } else {
                    console.log('No');
                }
            }

            $("#personaId").on("change", function() {
                var clienteId = $(this).val();
                $.ajax({
                    type: 'POST',
                    url: 'prendas_cliente',
                    data: {
                        clienteId: parseInt(clienteId)
                    },
                    success: function(data) {
                        var prendas = $('#prendaId'), option="";
                        prendas.empty();
                        for(var i=0; i < data.length; i++) {
                          let prenda = data[i].cliente + " - " + data[i].tipo + " " + data[i].color + " " + data[i].material + " " + data[i].marca;
                          option = option + "<option value='"+data[i].id + "'>"+ prenda + "</option>";
                        }
                        prendas.append(option);
                    },
                    error: function (error) {
                        console.log("Error : ", error);
                    }
                });
             });
        </script>