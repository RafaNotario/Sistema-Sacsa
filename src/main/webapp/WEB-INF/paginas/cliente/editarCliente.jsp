

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/4aba0d4f1c.js" crossorigin="anonymous"></script>

        <title>Editar Cliente </title>
    </head>
    <body>
        <!--HEADER-->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>

        <!--Botones de navegacion -->
        <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp"/>
        
        <section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Cuentas nuevas en Archivo: ${idArchivo}</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>#Cuenta</th>
                                <th>SSolicitu</th>
                                <th>Nombre</th>
                                <th>Status</th>
                                <th>C.P.</th>
                                
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos todos los elementos de la lista de clientes-->
                            <c:forEach var="cuenta" items="${cuentas}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>   <%   // ${cliente.idCliente}    %> 
                                    <td>${cuenta.numCuenta} </td>
                                    <td>${cuenta.ssolicitud_OT} </td>
                                    <td>${cuenta.nombre} </td>
                                    <td> ${cuenta.status} </td>
                                    <td> ${cuenta.cp} </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ProcesoArchivo?accion=detalle&idcuenta=${cuenta.id}"
                                           class="btn btn-secondary"><i class="fas fa-angle-double-right"></i>Gestionar
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
                        <h3>Total registros</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalArchivos}
                        </h4>
                    </div>
                </div>
            </div>
            <!-- fin Tarjetas de totales -->
        </div>
    </div>
</section>
       

        <!--PIE DE PAGINA-->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>

