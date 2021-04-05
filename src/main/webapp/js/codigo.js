
$(document).ready(function () {
    console.log("Liston");
});

$("#formulario").on("submit", function (e) {
    e.preventDefault();
    let formData = new FormData(document.querySelector("#formulario"));
    console.log(formData);
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