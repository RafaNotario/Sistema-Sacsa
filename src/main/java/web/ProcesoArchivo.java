package web;

import datos.ArchivoDaoJDBC;
import datos.ClienteDaoJDBC;
import datos.cuentaDaoJDBC;
import dominio.Archivo;
import dominio.Cliente;
import dominio.cuenta;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import procesarExcel.Test2;
import controlBackJDBC.controller;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
@WebServlet("/ProcesoArchivo")
public class ProcesoArchivo extends HttpServlet {

    Test2 procesarExcel = new Test2();//clase procesa excel
    controller cont = new controller();//clase valida con DB
    String warningArch = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminar"://acion del boton >>Editar del form principal
                    this.eliminarArchivo(request, response);
                    break;
                case "detallon"://acion del boton >>Editar del form principal
                    this.verdetalleArchivon(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Archivo> archivos = new ArchivoDaoJDBC().listarArchivosAll();
        //System.out.println("clientes = "+clientes);
        HttpSession sesion = request.getSession();//se pondran los datos en el alcanze de sesion
        sesion.setAttribute("archivos", archivos);//tenian request.
        sesion.setAttribute("totalClientes", archivos.size());
        sesion.setAttribute("warningArch", warningArch);
        //sesion.setAttribute("saldoTotal", calcularSaldoTotal(archivos));
        /* Al reenviar el formulario no cambia la url y duplica el dato insertado
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
         */
        response.sendRedirect("clientes.jsp");
    }

    private void eliminarArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario editarCliente
        int idArc = Integer.parseInt(request.getParameter("idArchivo"));
        //Crear el objeto cliente
        Archivo archivo = new Archivo(idArc);
        //Eliminar en la base de datos
        int registrosModificados = new ArchivoDaoJDBC().eliminar(archivo);
        try {
            cont.elimaRow(idArc);
        } catch (SQLException ex) {
            Logger.getLogger(ProcesoArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.err.println("Registros modificados: " + registrosModificados);
        //Redirigir hacia la accion default
        this.accionDefault(request, response);
    }

    private void verdetalleArchivon(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperar el idCliente
        int idArch = Integer.parseInt(request.getParameter("idArchivo"));
        List<cuenta> cuentas = new cuentaDaoJDBC().encontrar(idArch);
        request.setAttribute("cuentas", cuentas);
        request.setAttribute("idArchivo", idArch);
        request.setAttribute("totalArchivos", cuentas.size());
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //   processRequest(request, response);
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "cargar":
                    cargaCartera(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
                    break;
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void cargaCartera(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario editarCliente
        PrintWriter out = response.getWriter();
        String nomb = request.getParameter("nombre");
        String opcCartera = request.getParameter("opcion");
        if (nomb.isEmpty() || nomb == null) {//error en la carga de archivo nombe vacio!
            warningArch = "Error de carga verifique que exista un archivo cargado <br> y que el nombre sea visible";
            this.accionDefault(request, response);
        } else {
            try {
                //si existe archivo.nombre ver si ya ha sido cargado en el dia
                if (cont.validaExistArchivo(nomb)) {
                    //si ya existe archivo cargado manda alerta y redirige a default
                    warningArch = "Archivo <br> <b>" + nomb + "</b> <br> ya ha sido cargado <br> verifique porfavor.";
                    this.accionDefault(request, response);
                } else {
                    cargaArchivo(request, response);//lo copia en el servior
                    insertarArchivo(request, response);//guarda el nombre en la tabla
                    /*           switch (opcCartera) {
                case "ofsc"://insertar en tipoCartera = 1
                insertarArchivo(request, response);
                break;
                default:
                this.accionDefault(request, response);
                break;
                }*/
                }//else si nombre no existe en tabla archvio
            } catch (SQLException ex) {
                Logger.getLogger(ProcesoArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//else nombre vacio
    }

    private void insertarArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        java.util.Date date = new Date();
        String idU = request.getParameter("idUsuar"),
                nombre = request.getParameter("nombre"),
                timeDate = new java.sql.Timestamp(date.getTime()).toString(),
                tipoCart = request.getParameter("opcion");//obtener DateTime
        int tipCart = 0;
        switch (tipoCart) {
            case "heat":
                System.out.println("web.ProcesoArchivo.insertarArchivo() heat heat ejay");
                tipCart = 1;
                break;
            case "ofsc":
                tipCart = 2;
                break;
            case "cobcampo":
                tipCart = 3;
                break;
            case "modems":
                tipCart = 4;
                break;
            case "urgent":
                tipCart = 5;
                break;
        }
        //Crear el objeto cliente
        Archivo archivo = new Archivo(Integer.parseInt(idU), nombre, timeDate, tipCart, 1, 100);
        //Insertar en la base de datos
        int registrosModificados = new ArchivoDaoJDBC().insertar(archivo);

        System.out.println("Registros modificados: " + registrosModificados);

        try {//procesa el excel enviado
            int lastArchRegistrado = cont.getLastdIdArchi();//obtiene ultimo insertado
            procesarExcel.lee("A:/Archivos/" + nombre, tipCart, lastArchRegistrado);

        } catch (Exception ex) {
            Logger.getLogger(ProcesoArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        warningArch = "Archivo <br>  <b>" + nombre + "</b> <br>gurdado correctamente.";
        this.accionDefault(request, response);
    }//@end_insertarArchivo

    private void cargaArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nomb = request.getParameter("nombre");
        Part arch = request.getPart("archivo");
        InputStream is = arch.getInputStream();
        File f = new File("A:/Archivos/" + nomb);
        FileOutputStream ous = new FileOutputStream(f);
        int dato = is.read();
        while (dato != -1) {
            ous.write(dato);
            dato = is.read();
        }
        ous.close();
        is.close();
    }//@end_cargaArchivo

}
