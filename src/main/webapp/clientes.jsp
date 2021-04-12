
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
            <jsp:include page="WEB-INF/paginas/comunes/menuLateral.jsp"/>
            <section class="container col-md-12 prueba">
                <div id="main">
                    <div class="container">
                        <div class="logoG">
                            <h2>SISTEMA DE ADMINISTRACION SACSAVD</h2>
                            <h3>Bienvenido</h3>
                        </div>
                    </div>
                </div>
            </section>
        </main>

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

