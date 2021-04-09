
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US"/>


<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Archivos cargados</h4>
                    </div>
                    <table class="table table-striped">
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
                                    <td>${status.count}</td>   <%   // ${cliente.idCliente}    %> 
                                    <td>${archivo.nombre} </td>
                                    <td> ${archivo.fecha} </td>
                                    <td> ${archivo.tipoCart} </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ProcesoArchivo?accion=eliminar&idCliente=${archivo.idUsuario}"
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
                            <i class="fas fa-users"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>
            </div>
            <!-- fin Tarjetas de totales -->
        </div>
    </div>
</section>

