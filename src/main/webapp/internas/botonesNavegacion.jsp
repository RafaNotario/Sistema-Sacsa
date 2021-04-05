
<!--Botones de navegacion-->
<section id="actions" class="py-6 mb-6 bg-light">
    <div class="container">
        <br>
        <div class="row">
            <form action="ProcesoArchivo" name="formulario" id="formulario" method="POST" enctype="multipart/form-data" accept-charset="utf-8"> <!-- action="action" --> 
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
            <div class="form-group">
                <iframe  src="" name="null" class="embed-responsive-item"> </iframe>
            </div>
        </div>
        
        <h1> es: ${guardo}</h1>
    </div>
</section>
    




