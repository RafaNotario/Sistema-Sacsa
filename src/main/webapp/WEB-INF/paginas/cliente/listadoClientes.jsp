
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US"/>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="table-responsive card">
                    <div class="card-header">
                        <h4>Listado de Archivos cargados</h4>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Fecha</th>
                                <th>cartera</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos todos los e  lementos de la lista de clientes-->
                            <c:forEach var="archivo" items="${archivos}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>   
                                    <td>${archivo.nombre} </td>
                                    <td> ${archivo.fecha} </td>
                                    <td>  <c:choose>
                                            <c:when test="${archivo.tipoCart == 1}">
                                                HEAT
                                            </c:when>
                                            <c:when test="${archivo.tipoCart == 2}">
                                                OFSC
                                            </c:when>
                                            <c:when test="${archivo.tipoCart == 3}">
                                                Cob. Campo
                                            </c:when>
                                            <c:when test="${archivo.tipoCart == 4}">
                                                Modem Espec.
                                            </c:when>
                                            <c:when test="${archivo.tipoCart == 5}">
                                                URGENTE
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ProcesoArchivo?accion=editar&idArchivo=${archivo.id}"
                                           class="btn btn-secondary"><i class="fas fa-angle-double-right"></i>Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- ini Tarjetas de totales -->
            <div class="col-md-3">
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Archivos</h3>
                        <h4 class="display-4">
                            <i class="fas fa-paperclip"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>
            </div>
            <!-- fin Tarjetas de totales -->
        </div>
    </div>
</section>
