<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
  <a class="bg-dark text-white" href="inicio.html"><h5>Lavandería UTP</h5></a>
  <a href="cliente_edit?id=${sessionScope.clienteId}" class=${sessionScope.clienteId == 0 ? 'd-none' : ''}><i class="bi bi-person-fill m-2"></i>Perfil</a>
  <a href="usuarios.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-people m-2"></i>Usuarios</a>
  <a href="categorias.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-bookmark-fill m-2"></i>Categorias</a>
  <a href="servicios.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-telephone-fill m-2"></i>Servicios</a>
  <a href="clientes.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-person-lines-fill m-2"></i>Clientes</a>
  <a href="prendas.html"><i class="bi bi-p-square-fill m-2"></i>Prendas</a>
  <a href="movilidades.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-car-front-fill m-2"></i>Movilidades</a>
  <a href="choferes.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-person-bounding-box m-2"></i>Choferes</a>
  <a href="solicitudes.html"><i class="bi bi-alarm-fill m-2"></i>Solicitudes</a>
  <a href="visitas.html"><i class="bi bi-alarm-fill m-2"></i>Visitas</a>
  <a href="reportes.html" class=${sessionScope.clienteId != 0 ? 'd-none' : ''}><i class="bi bi-clipboard2-fill m-2"></i>Reportes</a>
  <a href="/lavanderiautp"><i class="bi bi-box-arrow-left mt-5 m-2"></i>Cerrar sesion</a>
</div>
