
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
        <link rel="icon" href="img/sacsavd_logo.ico"><!-- data:;base64,= -->
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://kit.fontawesome.com/4aba0d4f1c.js" crossorigin="anonymous"></script>

        <title>Control de Clientes </title>
    </head>
    <body>
        <!--HEADER-->
        <jsp:include page="WEB-INF/paginas/comunes/cabecero.jsp"/>

        <!-- BOTONES DE NAVEGACION-->


        <main>
            <div id="mySidenav" class="sidenav">
                <aside>
                <center>
                    <img src="img/sacsavd_logo.png" class="img-fluid" alt="Logo SACSAVD"><!--class="img-fluid" alt="Responsive image" -->
                </center>
                <a class="closebtn" href="javascript:void(0)" onclick="closeNav()">&times;</a>
                <a id="asist" href="#"> USUARIOS</a>
                <a id="alta" href="#">CARTERAS</a>
                <a id="ver" href="#">STATUS</a>
                <a id="pagos" href="#">VER TODOS</a>
</aside>
                <aside>
                    <div id="sidebar" class="nav-collapse ">
                        <!-- sidebar menu start-->
                        <ul class="sidebar-menu" id="nav-accordion">

                            <h5 class="centered"><?php echo $_SESSION["name_user"] ?></h5>
                            <li class="mt">
                                <a class="active" href="frm_main.php">
                                    <i class="fa fa-dashboard"></i>
                                    <span>Panel Principal</span>
                                </a>
                            </li>
                            <li class="sub-menu">
                                <a href="javascript:;">
                                    <i class="fa fa-cogs"></i>
                                    <span>Administracion </span>
                                    <i class="fa fa-caret-down" style="float-right"></i>
                                </a>
                                <ul class="sub">
                                    <li><a href="frm_usuarios.php">Usuarios</a></li>
                                    <li><a href="frm_almacen.php">Almacen</a></li>
                                    <li><a href="frm_eligealmacen.php">Establecer Almacen</a></li>
                                    <li><a href="frm_costos.php">Costo Recargas</a></li>
                                    <li><a href="frm_tipocartera.php">Tipo Cartera</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="javascript:;">
                                    <i class="fa fa-desktop"></i>
                                    <span>Operaciones</span>
                                    <i class="fa fa-caret-down" style="float-right"></i>
                                </a>
                                <ul class="sub">
                                    <li><a href="frm_cartera.php">Cargar Cartera</a></li>
                                    <li><a href="frm_asignacion.php">Asignar Carga de Trabajo</a></li>
                                    <li><a href="imprimircartera.php">Imprimir Cartera</a></li>
                                    <li><a href="font_awesome.html">Reporte Mandatorio</a></li>
                                    <li><a href="frm_paqueteria.php">Guia de Paqueteria</a></li>
                                    <li><a href="frm_relacion.php">Supervisor > Gestor</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="javascript:;">
                                    <i class=" fa fa-bar-chart-o"></i>
                                    <span>Reportes</span>
                                    <i class="fa fa-caret-down" style="float-right"></i>
                                </a>
                                <ul class="sub">
                                    <li><a href="frm_verasignacion.php">Ver Asignacion</a></li>
                                    <li><a href="frm_consutacobertura.php">Consultar Cobertura</a></li>
                                    <li><a href="frm_consulta.php">Consultar Cuentas</a></li>
                                    <li><a href="flot_chart.html">Rendimiento Gestores</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="javascript:;">
                                    <i class="fa fa-th"></i>
                                    <span>Recuperacion</span>
                                    <i class="fa fa-caret-down" style="float-right"></i>
                                </a>
                                <ul class="sub">
                                    <li><a href="frm_capguia.php">Captura de recuperacion</a></li>
                                    <li><a href="frm_validarrecuperacion.php">Validar Recuperacion</a></li>
                                    <li><a href="frm_cierrarecuperacion.php">Cerrar Recuperaciones</a></li>
                                    <li><a href="frm_cierrareactivacion.php">Cerrar Reactivaciones</a></li>
                                    <li><a href="frm_cierranegativas.php">Cerrar Negativas</a></li>
                                    <li><a href="frm_controltickets.php">Control de Tickets</a></li>
                                    <li><a href="frm_pendientestickets.php">Pendientes en Tickets</a></li>
                                    <li><a href="frm_fotosrechazadas.php">Fotos Rechazadas</a></li>
                                </ul>
                            </li>
                            <li class="sub-menu">
                                <a href="javascript:;">
                                    <i class="fa fa-book"></i>
                                    <span>Ayuda</span>
                                    <i class="fa fa-caret-down" style="float-right"></i>
                                </a>
                                <ul class="sub">
                                    <li><a href="blank.html">Manual de Operacion</a></li>
                                    <li><a href="login.html">Acerca de...</a></li>
                                </ul>
                            </li>
                        </ul>
                        <!-- sidebar menu end-->
                    </div>
                </aside>
            </div>

            <section class="container col-md-12 prueba">
                <div id="main">
                    <div class="container">
                        <div class="logoG">
                            <h2>SISTEMA DE ADMINISTRACION ISP</h2>
                        </div>
                    </div>
                </div>
            </section>

        </main>

        <!-- LISTADO CLIENTES-->


        <!--PIE DE PAGINA-->
        <jsp:include page="WEB-INF/paginas/comunes/piePagina.jsp"/>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>        

        <script src="js/codigo.js"></script>
    </body>
</html>

