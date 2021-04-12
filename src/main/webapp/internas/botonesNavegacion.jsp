
<!--Botones de navegacion-->
<section id="actions" class="py-6 mb-6 bg-light">
    <div class="container">
        <br>
        <div class="row">
            <div class="col-md-8">
                <form action="${pageContext.request.contextPath}/ProcesoArchivo?accion=cargar&idUsuar=1" 
                      name="formulario" id="formulario" method="POST" enctype="multipart/form-data" accept-charset="utf-8"> <!-- action="action" --> 
                    <fieldset>
                        <legend> Procesamiento de cartera. </legend>
                        <div class="form-group">
                            <label for="basic">Elija cartera a procesar </label>    
                            <select id="basic" class="form-control" name="opcion">
                                <option value="ofsc">OFSC</option>
                                <option value="heat" selected>HEAT</option>
                                <option value="cobcampo">COBRANZA CAMPO</option>
                                <option value="modems">MODEMS ESPECIAL</option>
                                <option value="urgent">URGENTE</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="file" class="form-control-file" name="archivo" onchange="cargarArchivo(this);"/> <!-- onchange="" cargarArchivo(this); -->
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="nombre" value=""/> 
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success"> ENVIAR </button>
                        </div>  
                    </fieldset>
                </form>
            </div>
            <div class="col-md-4">    
                <center>
                    <span class="alertArchivo">   ${warningArch}  </span>        
                </center>
            </div>
        </div>

    </div>
</section>

<!-- LISTADO CLIENTES-->
<jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp"/>





