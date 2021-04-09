package web;

import datos.ArchivoDaoJDBC;
import datos.ClienteDaoJDBC;
import dominio.Archivo;
import dominio.Cliente;
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

@MultipartConfig
@WebServlet("/ProcesoArchivo")
public class ProcesoArchivo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {

                case "eliminar"://acion del boton >>Editar del form principal
                    this.eliminarArchivo(request, response);
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
        //sesion.setAttribute("saldoTotal", calcularSaldoTotal(archivos));
        /* Al reenviar el formulario no cambia la url y duplica el dato insertado
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
         */
        response.sendRedirect("clientes.jsp");
    }

    private void eliminarArchivo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //REcuperamos datos del formulario editarCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        //Crear el objeto cliente
        Cliente cliente = new Cliente(idCliente);
        //Eliminar en la base de datos
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.err.println("Registros modificados: " + registrosModificados);
        //Redirigir hacia la accion default
        this.accionDefault(request, response);
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
        PrintWriter out = response.getWriter();
        String nomb = request.getParameter("nombre");
        String val = request.getParameter("opcion");
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
        out.println("Archivo: " + nomb);
        out.println("cartera: " + val);
    }

}
