
$(document).ready(function () {
    console.log("Liston");
    const menuOpc1 = document.querySelector('.asist');
    menuOpc1.click();
    
    
});

function cargarArchivo(elemento) { //obtiene el nombre del file y se lo agrega al input type=" 
    const file = elemento.files[0];
    const objHidden = document.formulario.nombre;
    objHidden.value = file.name;
    //document.formulario.target = "null";
    //document.formulario.action = "ServletControlador";//"ProcesoArchivo"
    //document.formulario.submit();
    console.log("archivon" + objHidden.value);
}

/*FUNCIONES PARA MENU*/
$("#asist").click(function () {
    //$("#main").load("/WEB-INF/paginas/cliente/listadoClientes.jsp");
    $("div#main").load("internas/botonesNavegacion.jsp");
    closeNav();
});

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
//    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
//    document.body.style.backgroundColor = "white";
}

function fileValidation(elemento) {//hay que depurarla no funciona
    var fileInput = elemento;
    var filePath = fileInput.value;
    var allowedExtensions = /(.xlsx)$/i;
    if (!allowedExtensions.exec(filePath)) {
        alert('Please upload file having extensions .xlsx only.');
        fileInput.value = '';
        return false;
    } else {
        //Image preview
        if (fileInput.files && fileInput.files[0]) {
            const objHidden = document.formulario.nombre;
            objHidden.value = fileInput.name;
            //document.formulario.target = "null";
            //document.formulario.action = "ServletControlador";//"ProcesoArchivo"
            //document.formulario.submit();
            console.log("archivon" + objHidden.value);
        }
    }
}